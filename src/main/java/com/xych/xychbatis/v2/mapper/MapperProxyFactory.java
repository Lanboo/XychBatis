package com.xych.xychbatis.v2.mapper;

import java.lang.reflect.Proxy;

import com.xych.xychbatis.v2.session.SqlSession;

public class MapperProxyFactory<T>
{
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface)
    {
        super();
        this.mapperInterface = mapperInterface;
    }

    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession)
    {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
    }
}
