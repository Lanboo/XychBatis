package com.xych.dao;

import com.xych.pojo.User;

public interface UserMapper
{
    User selectOne(String id);
}