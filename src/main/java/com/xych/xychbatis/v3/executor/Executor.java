package com.xych.xychbatis.v3.executor;

import com.xych.xychbatis.v3.mapper.MapperData;

public interface Executor
{
    <T> T query(MapperData<T> mapperData, Object params);
}
