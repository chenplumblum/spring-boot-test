package com.plumblum.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plumblum.model.UserEntity;
import com.plumblum.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 10:00
 * @Description:
 */
@RestController
public class UserController {
    @Resource
    public UserService userService;

    @RequestMapping("/user")
    public PageInfo getAll(){
        PageHelper.startPage(1,1);
        List<UserEntity> list = userService.getAll();
        PageInfo<UserEntity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
