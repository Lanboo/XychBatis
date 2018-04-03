package com.xych.xychbatis.v2.executor;

import com.xych.xychbatis.v2.config.Configuration;
import com.xych.xychbatis.v2.mapper.MapperData;

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
