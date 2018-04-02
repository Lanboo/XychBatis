package com.xych.test.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xych.dao.UserMapper;
import com.xych.pojo.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MyBatisTest
{
    public static SqlSession getSqlSession() throws FileNotFoundException
    {
        //配置文件
        InputStream configFile = new FileInputStream("D:\\File\\Code\\Java\\git\\other\\XychBatis\\src\\main\\resource\\config\\mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        //加载配置文件得到SqlSessionFactory
        return sqlSessionFactory.openSession();
    }

    public static void main(String[] args) throws Exception
    {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        User user = userMapper.selectOne("1");
        System.out.println(user);
    }
}
