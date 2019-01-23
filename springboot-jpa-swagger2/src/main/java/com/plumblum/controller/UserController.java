package com.plumblum.controller;

import com.plumblum.entity.UsersEntity;
import com.plumblum.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2019/1/10 14:55
 * @Description:
 */
@RestController
@Api(value="/test", tags="测试接口模块")
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserService userService;
    @ApiOperation(value="展示所有用户信息", notes = "展示所有用户信息")
    @RequestMapping("findAll")
    public List<UsersEntity> findAll(){
        return userService.findAll();
    }

    @ApiOperation(value="查询单个用户", notes = "查询单个用户")
    @ApiImplicitParam(name="id", value="1", required = true, dataType = "long")
    @RequestMapping("findOne")
    public UsersEntity findOne(long id){
        return userService.findOne(id);
    }

}
