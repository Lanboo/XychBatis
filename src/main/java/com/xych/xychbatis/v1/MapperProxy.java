package com.xych.xychbatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.ibatis.reflection.ExceptionUtil;

import com.xych.xychbatis.v1.Configuration.MapperData;

public class MapperProxy<T> implements InvocationHandler
{
    private SqlSession sqlSession = null;

    public MapperProxy(SqlSession sqlSession)
    {
        super();
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        try
        {
            if(Object.class.equals(method.getDeclaringClass()))
            {
                return method.invoke(this, args);
            }
            MapperData<?> mapperData = null;
            mapperData = Configuration.getMapperData(method.getDeclaringClass().getName());
            if(mapperData != null)
            {
                return this.sqlSession.selectOne(mapperData.getSql(method.getName()), args);
            }
        }
        catch(Throwable t)
        {
            throw ExceptionUtil.unwrapThrowable(t);
        }
        return method.invoke(this, args);
    }
}
