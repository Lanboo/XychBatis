package com.xych.xychbatis.v2.executor;

import com.xych.xychbatis.v2.mapper.MapperData;

public interface Executor
{
    <T> T query(MapperData<T> mapperData, Object params);

}
