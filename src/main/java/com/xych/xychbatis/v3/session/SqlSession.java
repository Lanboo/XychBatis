package com.xych.xychbatis.v3.session;

import com.xych.xychbatis.v3.config.Configuration;
import com.xych.xychbatis.v3.executor.Executor;
import com.xych.xychbatis.v3.mapper.MapperData;

public class SqlSession
{
    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration)
    {
        super();
        this.configuration = configuration;
        this.executor = this.configuration.newExecutor();
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
