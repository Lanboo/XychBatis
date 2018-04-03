package com.xych.test.xychbatis.v1;

import com.xych.test.mybatis.mapper.UserMapper;
import com.xych.test.pojo.User;
import com.xych.xychbatis.v1.Configuration;
import com.xych.xychbatis.v1.SimpleExecutor;
import com.xych.xychbatis.v1.SqlSession;

public class XychBatisTest
{
    public static void main(String[] args)
    {
        SqlSession sqlSession = new SqlSession(new Configuration(), new SimpleExecutor());
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectOne("1");
        System.out.println(user);
    }
}
