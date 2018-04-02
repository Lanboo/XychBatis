package com.xych.xychbatis.v1;

public class SqlSession
{
    private Configuration configuration;
    private Excutor excutor;

    public SqlSession(Configuration configuration, Excutor excutor)
    {
        super();
        this.configuration = configuration;
        this.excutor = excutor;
    }

    public <T> T getMapper(Class<T> clazz)
    {
        return configuration.getMapper(clazz, this);
    }

    public <T> T selectOne(String sql, Object params)
    {
        return this.excutor.selectOne(sql, params);
    }
}
