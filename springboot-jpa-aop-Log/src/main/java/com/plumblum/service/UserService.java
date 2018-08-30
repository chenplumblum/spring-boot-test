package com.plumblum.service;

import com.plumblum.aspect.SysServiceLog;
import com.plumblum.entity.UsersEntity;
import com.plumblum.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @Auther: cpb
 * @Date: 2018/8/29 19:06
 * @Description:
 */
@Service("userService")
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserRepository userRepository;

    @Transactional
    @SysServiceLog(oprlogType="1",oprlogRemark="新增/修改用户",oprlogModule = "系统管理")
    public void insert(UsersEntity user){

        try {
            userRepository.save(user);
//            ArrayList list = new ArrayList(2);
//            System.out.println(list.get(11));
        }catch (RuntimeException e){
            logger.info("用户保存失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//就是这一句了，加上之后，抛了异常,  会回滚
        }finally{

        }


    }
}
