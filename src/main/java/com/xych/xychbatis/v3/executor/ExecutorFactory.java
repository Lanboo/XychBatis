package com.xych.xychbatis.v3.executor;

import com.xych.xychbatis.v3.config.Configuration;

public class ExecutorFactory
{
    public static final String SIMPLE = "SIMPLE";
    public static final String CACHING = "CACHING";

    public static Executor DEFAULT(Configuration configuration)
    {
        return get(SIMPLE, configuration);
    }

    public static Executor get(String key, Configuration configuration)
    {
        if(SIMPLE.equalsIgnoreCase(key))
        {
            return new SimpleExecutor(configuration);
        }
        if(CACHING.equalsIgnoreCase(key))
        {
            return new CachingExecutor(configuration);
        }
        throw new RuntimeException("no executor found");
    }
}
