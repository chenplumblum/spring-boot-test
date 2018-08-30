package com.plumblum.service;

import com.plumblum.entity.SysOprlog;
import com.plumblum.repository.SysOprLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Auther: cpb
 * @Date: 2018/8/30 09:31
 * @Description:
            */
    @Service("sysOprLogService")
    public class SysOprlogService {

    @Resource
    private SysOprLogRepository sysOprLogRepository;
//    配置另起新的事务，这样保存事务才不会被影响到。
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(SysOprlog sysOprlog){
        sysOprLogRepository.save(sysOprlog);
    }

}
