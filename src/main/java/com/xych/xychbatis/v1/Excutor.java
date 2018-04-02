package com.xych.xychbatis.v1;

public interface Excutor
{
    <T> T selectOne(String sql, Object params);
}
