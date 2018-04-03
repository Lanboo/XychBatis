package com.xych.xychbatis.v2.mapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import com.xych.xychbatis.v2.session.SqlSession;

public class MapperProxy<T> implements InvocationHandler
{
    private SqlSession sqlSession;
    private Class<T> classInterface;
    private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<Method, MapperMethod>();;

    public MapperProxy(SqlSession sqlSession, Class<T> classInterface)
    {
        super();
        this.sqlSession = sqlSession;
        this.classInterface = classInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        if(Object.class.equals(method.getDeclaringClass()))
        {
            return method.invoke(this, args);
        }
        final MapperMethod mapperMethod = cachedMapperMethod(method);
        return mapperMethod.execute(sqlSession, args);
    }

    private MapperMethod cachedMapperMethod(Method method)
    {
        MapperMethod mapperMethod = methodCache.get(method);
        if(mapperMethod == null)
        {
            mapperMethod = new MapperMethod(classInterface, method);
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}
