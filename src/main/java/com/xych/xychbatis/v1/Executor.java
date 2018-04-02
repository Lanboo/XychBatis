package com.xych.xychbatis.v1;

public interface Executor
{
    <T> T selectOne(String sql, Object params);
}
