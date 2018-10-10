package com.plumblum.service;

import com.plumblum.entity.SysUser;
import com.plumblum.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Auther: cpb
 * @Date: 2018/10/10 09:30
 * @Description:
 */
@Service("userService")
public class UserService {
    @Autowired
    private SysUserRepository userRepository;

    public SysUser create(SysUser sysUser){
        //进行加密
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        sysUser.setPassword(encoder.encode(sysUser.getPassword().trim()));
        userRepository.save(sysUser);
        return sysUser;
    }
}
