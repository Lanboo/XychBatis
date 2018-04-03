package com.xych.xychbatis.v2.session;

import com.xych.xychbatis.v2.config.Configuration;
import com.xych.xychbatis.v2.executor.Executor;
import com.xych.xychbatis.v2.mapper.MapperData;

public class SqlSession
{
    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor)
    {
        super();
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz)
    {
        return configuration.getMapper(clazz, this);
    }

    public <T> T selectOne(String methodKey, Object params)
    {
        MapperData<T> mapperData = configuration.get(methodKey);
        return this.executor.query(mapperData, params);
    }

    public <T> T insert(String methodKey, Object params)
    {
        return null;
    }

    public <T> T update(String methodKey, Object params)
    {
        return null;
    }

    public <T> T delete(String methodKey, Object params)
    {
        return null;
    }

    public <T> T exec(String methodKey, Object params)
    {
        return null;
    }

    public Configuration getConfiguration()
    {
        return configuration;
    }
}
