package com.xych.xychbatis.v1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.xych.pojo.User;

public class Configuration
{
    private static Map<String, MapperData<?>> methodSqlMapping = new HashMap<>();
    static
    {
        init();
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession)
    {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] { clazz }, new MapperProxy<T>(sqlSession));
    }

    /**
     * 代替读取xml配置文件的过程
     * 
     * @author 晓月残魂
     * @date 2018年4月2日上午11:57:45
     */
    private static void init()
    {
        String namespace = "com.xych.dao.UserMapper";
        Class<User> clazz = User.class;
        MapperData<User> mx = new MapperData<User>(namespace, clazz);
        mx.setMethodSql("selectOne", "select * from T_S_USER where ?");
        methodSqlMapping.put(namespace, mx);
    }

    public static MapperData<?> getMapperData(String namespace)
    {
        return methodSqlMapping.get(namespace);
    }

    /**
     * 存放SQL，代替的是Mapper.xml
     * @Description
     * @author 晓月残魂
     * @CreateDate 2018年4月2日下午12:00:07
     */
    public static class MapperData<T>
    {
        private Class<T> clazz;
        private String namespace;
        private Map<String, String> methodSqlMap = new HashMap<>();

        public MapperData(String namespace, Class<T> clazz)
        {
            super();
            this.namespace = namespace;
            this.clazz = clazz;
        }

        public Class<T> getClazz()
        {
            return clazz;
        }

        public String getNamespace()
        {
            return namespace;
        }

        public void setNamespace(String namespace)
        {
            this.namespace = namespace;
        }

        public void setMethodSql(String method, String sql)
        {
            this.methodSqlMap.put(method, sql);
        }

        public String getSql(String method)
        {
            return this.methodSqlMap.get(method);
        }
    }
}
