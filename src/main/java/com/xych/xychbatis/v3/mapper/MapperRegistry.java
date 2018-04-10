package com.xych.xychbatis.v3.mapper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.xych.xychbatis.v3.annotations.Delete;
import com.xych.xychbatis.v3.annotations.Insert;
import com.xych.xychbatis.v3.annotations.Select;
import com.xych.xychbatis.v3.annotations.Update;
import com.xych.xychbatis.v3.session.SqlSession;

public class MapperRegistry
{
    public static final Map<String, MapperData<?>> methodSqlMapping = new HashMap<>();
    public static final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> MapperData<T> get(String methodKey)
    {
        return (MapperData<T>) methodSqlMapping.get(methodKey);
    }

    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession)
    {
        @SuppressWarnings("unchecked")
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(clazz);
        if(mapperProxyFactory == null)
        {
            throw new RuntimeException("Type " + clazz + " is not known to the MapperRegistry.");
        }
        return mapperProxyFactory.newInstance(sqlSession);
    }

    public <T> void addMapper(Class<T> clazz)
    {
        if(clazz.isInterface())
        {
            knownMappers.put(clazz, new MapperProxyFactory<T>(clazz));
            // loading MapperData
            Method[] methods = clazz.getMethods();
            for(Method method : methods)
            {
                String sql = "";
                String name = "";
                Select select = method.getAnnotation(Select.class);
                if(select != null)
                {
                    name = select.name().length() > 0 ? select.name() : method.getDeclaringClass().getName() + "." + method.getName();
                    sql = select.value();
                    methodSqlMapping.put(name, new MapperData<>(sql, method.getReturnType()));
                    continue;
                }
                Insert insert = method.getAnnotation(Insert.class);
                if(insert != null)
                {
                    name = insert.name().length() > 0 ? insert.name() : method.getDeclaringClass().getName() + "." + method.getName();
                    sql = insert.value();
                    methodSqlMapping.put(name, new MapperData<>(sql, method.getReturnType()));
                    continue;
                }
                Update update = method.getAnnotation(Update.class);
                if(update != null)
                {
                    name = update.name().length() > 0 ? update.name() : method.getDeclaringClass().getName() + "." + method.getName();
                    sql = update.value();
                    methodSqlMapping.put(name, new MapperData<>(sql, method.getReturnType()));
                    continue;
                }
                Delete delete = method.getAnnotation(Delete.class);
                if(delete != null)
                {
                    name = delete.name().length() > 0 ? delete.name() : method.getDeclaringClass().getName() + "." + method.getName();
                    sql = delete.value();
                    methodSqlMapping.put(name, new MapperData<>(sql, method.getReturnType()));
                    continue;
                }
            }
        }
    }
}
