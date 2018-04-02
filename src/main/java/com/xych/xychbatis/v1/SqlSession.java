package com.xych.xychbatis.v1;

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

    public <T> T selectOne(String sql, Object params)
    {
        return this.executor.selectOne(sql, params);
    }
}
