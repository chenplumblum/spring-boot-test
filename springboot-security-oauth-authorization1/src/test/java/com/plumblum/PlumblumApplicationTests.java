package com.plumblum;


import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlumblumApplicationTests {

//
//
//	@Autowired
//	private SysUserRepository sysUserRepository;
//		@Test
//		public void contextLoads() {
//			String pwd = "root";
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
//			// 加密
//			String encodedPassword = passwordEncoder.encode(pwd);
//			System.out.println("【加密后的密码为：】" + encodedPassword);
//
//			SysUser user = new SysUser();
//			user.setId(2l);
//			user.setPassword(encodedPassword);
//			user.setUsername("admin");
//			sysUserRepository.save(user);
//		}


}
