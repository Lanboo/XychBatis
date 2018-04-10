package com.xych.test.xychbatis.v3.mapper;

import com.xych.test.pojo.User;
import com.xych.xychbatis.v3.annotations.Select;

public interface UserMapper
{
    @Select(value = "select * from T_S_USER where c_id=?")
    User selectOne(String id);
}