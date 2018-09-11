package com.plumblum.shiro;

import com.plumblum.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Auther: cpb
 * @Date: 2018/8/10 10:57
 * @Description:
 */
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

//    对密码进行加密（用在新增用户，和修改密码）
    public void encryptPassword(User user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,//加密算法
                user.getPassword(),//密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//盐值 username+salt（随机数）注意（插入数据库的只有salt（随机数））
                hashIterations//hash次数
        ).toHex();

        user.setPassword(newPassword);
    }
}
