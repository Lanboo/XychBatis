package com.xych.test.mybatis.mapper;

import com.xych.test.pojo.User;

public interface UserMapper
{
    User selectOne(String id);
}