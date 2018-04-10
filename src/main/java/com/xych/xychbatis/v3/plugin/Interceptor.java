package com.xych.xychbatis.v3.plugin;

public interface Interceptor
{
    Object intercept(Invocation invocation) throws Throwable;

    Object plugin(Object target);
}
