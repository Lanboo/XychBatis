package com.xych.test.xychbatis.v3.plugin;

import java.lang.reflect.Method;

import com.xych.xychbatis.v3.executor.Executor;
import com.xych.xychbatis.v3.mapper.MapperData;
import com.xych.xychbatis.v3.plugin.Interceptor;
import com.xych.xychbatis.v3.plugin.Intercepts;
import com.xych.xychbatis.v3.plugin.Invocation;
import com.xych.xychbatis.v3.plugin.Plugin;
import com.xych.xychbatis.v3.plugin.Signature;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MapperData.class, Object.class }) })
public class TestInterceptor implements Interceptor
{
    @Override
    public Object intercept(Invocation invocation) throws Throwable
    {
        Object obj = invocation.getTarget();
        Method method = invocation.getMethod();
        System.out.println("被拦接的对象：" + obj);
        System.out.println("被拦截的方法：" + method);
        return method.invoke(obj, invocation.getArgs());
    }

    @Override
    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }
}
