package com.xych.xychbatis.v3.executor;

import com.xych.xychbatis.v3.config.Configuration;
import com.xych.xychbatis.v3.mapper.MapperData;

public class CachingExecutor implements Executor
{
    protected Configuration configuration = null;

    
    public CachingExecutor(Configuration configuration)
    {
        super();
        this.configuration = configuration;
    }

    @Override
    public <T> T query(MapperData<T> mapperData, Object params)
    {
        return null;
    }
}
