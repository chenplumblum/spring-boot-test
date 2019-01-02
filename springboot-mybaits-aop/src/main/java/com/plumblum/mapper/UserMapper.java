package com.plumblum.mapper;

import com.plumblum.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from mb_user ")
    public List<UsersEntity> getAll();
}
