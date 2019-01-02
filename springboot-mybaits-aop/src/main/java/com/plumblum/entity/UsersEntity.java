package com.plumblum.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 11:06
 * @Description:
 */
@Getter
@Setter
public class UsersEntity {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date created;
    private Date updated;
}
