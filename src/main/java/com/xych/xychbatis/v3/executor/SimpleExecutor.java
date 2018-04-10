package com.xych.xychbatis.v3.executor;

import com.xych.xychbatis.v3.config.Configuration;
import com.xych.xychbatis.v3.mapper.MapperData;
import com.xych.xychbatis.v3.statement.StatementHandler;

public class SimpleExecutor extends BaseExecutor
{
    public SimpleExecutor(Configuration configuration)
    {
        super(configuration);
    }

    @Override
    public <T> T query(MapperData<T> mapperData, Object params)
    {
        StatementHandler handler = new StatementHandler(configuration);
        return (T) handler.query(mapperData, params);
    }
}
