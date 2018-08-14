package com.plumblum.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Auther: cpb
 * @Date: 2018/8/14 11:09
 * @Description:
 */
public class RetryLimitCredentialsMatcher extends  HashedCredentialsMatcher {

    private static final Logger logger = LoggerFactory.getLogger(RetryLimitCredentialsMatcher.class);

    //集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
    private Cache<String,AtomicInteger> passwordRetryCache;

    //最大校验次数。
    private int maxRetryCount = 3;


    public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > 3) {
            logger.warn("用户: " + username + " tried to login more than 3 times in period");
            throw new ExcessiveAttemptsException("用户名: " + username + " tried to login more than 3 times in period"
            );
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry data
            passwordRetryCache.remove(username);
        }
        return matches;
    }


}
