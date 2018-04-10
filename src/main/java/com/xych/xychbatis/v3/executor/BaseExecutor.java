package com.xych.xychbatis.v3.executor;

import com.xych.xychbatis.v3.config.Configuration;

public abstract class BaseExecutor implements Executor
{
    protected Configuration configuration = null;

    public BaseExecutor(Configuration configuration)
    {
        super();
        this.configuration = configuration;
    }
}
