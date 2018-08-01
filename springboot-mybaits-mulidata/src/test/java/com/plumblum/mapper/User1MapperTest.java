package com.plumblum.mapper;


import com.plumblum.entity.UserEntity;
import com.plumblum.mapper.test1.User1Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class User1MapperTest {

	@Autowired
	private User1Mapper userMapper;

//	@Test
//	public void testInsert() throws Exception {
//		userMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
//		userMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
//		userMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));
//
//		Assert.assertEquals(3, userMapper.getAll().size());
//	}
//
//	@Test
//	public void testQuery() throws Exception {
//		List<UserEntity> users = userMapper.getAll();
//		if(users==null || users.size()==0){
//			System.out.println("is null");
//		}else{
//			System.out.println(users.size());
//		}
//	}
//
//
//	@Test
//	public void testUpdate() throws Exception {
//		UserEntity user = userMapper.getOne(6l);
//		System.out.println(user.toString());
//		user.setNickName("neo");
//		userMapper.update(user);
//		Assert.assertTrue(("neo".equals(userMapper.getOne(6l).getNickName())));
//	}

	@Test
	public void test1(){
		List<UserEntity> list = userMapper.getAll();
		System.out.println(list.size());
	}
}