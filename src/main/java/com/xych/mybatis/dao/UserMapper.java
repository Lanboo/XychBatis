package com.xych.mybatis.dao;

import com.xych.mybatis.pojo.User;

public interface UserMapper
{
    User selectOne(String id);
}