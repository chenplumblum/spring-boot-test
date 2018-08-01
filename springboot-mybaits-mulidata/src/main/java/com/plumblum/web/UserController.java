package com.plumblum.web;

import java.util.List;

import com.plumblum.entity.UserEntity;
import com.plumblum.mapper.test1.User1Mapper;
import com.plumblum.mapper.test2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

    @Autowired
    private User1Mapper user1Mapper;

	@Autowired
	private User2Mapper user2Mapper;

	@RequestMapping("/getUsers")
	public List<UserEntity> getUsers() {
		List<UserEntity> users=user1Mapper.getAll();
		return users;
	}

}