package com.xych.xychbatis.v2.executor;

import com.xych.xychbatis.v2.config.Configuration;

public abstract class BaseExecutor implements Executor
{
    protected Configuration configuration = null;

    public BaseExecutor(Configuration configuration)
    {
        super();
        this.configuration = configuration;
    }
}
