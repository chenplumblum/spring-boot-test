package com.plumblum.service.serviceImpl;

import com.plumblum.dao.UserMapper;
import com.plumblum.model.UserEntity;
import com.plumblum.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 09:55
 * @Description:
 */
@Service("userSerivce")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<UserEntity> getAll() {
        return userMapper.getAll();
    }
}
