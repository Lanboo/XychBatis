package com.xych.xychbatis.v2.config;
import java.util.Properties;

import com.xych.xychbatis.v2.mapper.MapperData;
import com.xych.xychbatis.v2.mapper.MapperRegistry;
import com.xych.xychbatis.v2.session.SqlSession;

public class Configuration
{
    private String calssDriver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/Lanboo?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "root";
    private String password = "123456";
    private MapperRegistry mapperRegistry = new MapperRegistry();

    public Configuration()
    {
        super();
    }

    public Configuration(Properties properties)
    {
        this.calssDriver = properties.getProperty("xychbatis.jdbc.driver");
        this.url = properties.getProperty("xychbatis.jdbc.url");
        this.username = properties.getProperty("xychbatis.jdbc.username");
        this.password = properties.getProperty("xychbatis.jdbc.password");
        String mapperPage = properties.getProperty("xychbatis.mapper");
        mapperRegistry.loadMapper(mapperPage);
    }


    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession)
    {
        return mapperRegistry.getMapper(clazz, sqlSession);
    }

    public <T> MapperData<T> get(String methodKey)
    {
        return mapperRegistry.get(methodKey);
    }

    public String getCalssDriver()
    {
        return calssDriver;
    }

    public String getUrl()
    {
        return url;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
