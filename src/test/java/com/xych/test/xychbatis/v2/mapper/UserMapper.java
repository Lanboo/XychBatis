package com.xych.test.xychbatis.v2.mapper;

import com.xych.test.pojo.User;
import com.xych.xychbatis.v2.annotations.Select;

public interface UserMapper
{
    @Select(value = "select * from T_S_USER where c_id=?")
    User selectOne(String id);
}