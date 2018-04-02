package com.xych.test.xychbatis.v1;

import com.xych.dao.UserMapper;
import com.xych.pojo.User;
import com.xych.xychbatis.v1.Configuration;
import com.xych.xychbatis.v1.SimpleExcutor;
import com.xych.xychbatis.v1.SqlSession;

public class XychBatisTest
{
    public static void main(String[] args)
    {
        SqlSession sqlSession = new SqlSession(new Configuration(), new SimpleExcutor());
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectOne("1");
        System.out.println(user);
    }
}
