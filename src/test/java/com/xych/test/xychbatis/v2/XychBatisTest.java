package com.xych.test.xychbatis.v2;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.xych.test.pojo.User;
import com.xych.test.xychbatis.v2.mapper.UserMapper;
import com.xych.xychbatis.v2.config.Configuration;
import com.xych.xychbatis.v2.executor.ExecutorFactory;
import com.xych.xychbatis.v2.session.SqlSession;

public class XychBatisTest
{
    public static SqlSession getSqlSession() throws Exception
    {
        InputStream configFile = new FileInputStream("D:\\File\\Code\\Java\\git\\other\\XychBatis\\src\\main\\resource\\config\\v2\\xychbatis-config.properties");
        Properties properties = new Properties();
        properties.load(configFile);
        Configuration configuration = new Configuration(properties);
        SqlSession sqlSession = new SqlSession(configuration, ExecutorFactory.DEFAULT(configuration));
        return sqlSession;
    }

    public static void main(String[] args) throws Exception
    {
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectOne("1");
        System.out.println(user);
    }
}
