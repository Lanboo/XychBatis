package com.xych.xychbatis.v3.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Plugin implements InvocationHandler
{
    private final Object target;
    private final Interceptor interceptor;
    private Map<Class<?>, Set<Method>> signatureMap;

    public Plugin(Object target, Interceptor interceptor, Map<Class<?>, Set<Method>> signatureMap)
    {
        super();
        this.target = target;
        this.interceptor = interceptor;
        this.signatureMap = signatureMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        //获取method所在类是否是拦截器的拦截对象
        Set<Method> methods = signatureMap.get(method.getDeclaringClass());
        if(methods != null && methods.contains(method))
        {//method所在类是拦截器的拦截对象，并且method是在拦截器的拦截范围内，那么走拦截器方法
            return interceptor.intercept(new Invocation(target, method, args));
        }
        return method.invoke(target, args);
    }

    public static Object wrap(Object target, Interceptor interceptor)
    {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);//获取拦截器配置的方法（即当前拦截器的作用域）
        Class<?> type = target.getClass();
        Class<?>[] interfaces = getAllInterfaces(type, signatureMap);//获取需要代理的接口
        if(interfaces != null && interfaces.length > 0)
        {
            return Proxy.newProxyInstance(type.getClassLoader(), interfaces, new Plugin(target, interceptor, signatureMap));
        }
        return target;
    }

    /**
     * 只针对target的所有接口在拦截器中配置了，才会被代理
     * @param type
     * @param signatureMap2
     * @return
     * @author 晓月残魂
     * @date 2018年4月10日上午1:02:58
     */
    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap)
    {
        Set<Class<?>> interfaces = new HashSet<Class<?>>();
        while(type != null)
        {
            for(Class<?> c : type.getInterfaces())
            {
                if(signatureMap.containsKey(c))
                {
                    interfaces.add(c);
                }
            }
            type = type.getSuperclass();
        }
        return interfaces.toArray(new Class<?>[interfaces.size()]);
    }

    /**
     * 用来获取拦截器上声明方法，即此拦截器的作用范围
     * @param interceptor
     * @return
     * @author 晓月残魂
     * @date 2018年4月10日上午12:53:21
     */
    private static Map<Class<?>, Set<Method>> getSignatureMap(Interceptor interceptor)
    {
        Intercepts interceptsAnnotation = interceptor.getClass().getAnnotation(Intercepts.class);
        if(interceptsAnnotation == null)
        {//当前拦截器没有添加@Intercepts注解时
            throw new RuntimeException("No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
        }
        Signature[] sigs = interceptsAnnotation.value();
        Map<Class<?>, Set<Method>> signatureMap = new HashMap<Class<?>, Set<Method>>();
        for(Signature sig : sigs)
        {
            Set<Method> methods = signatureMap.get(sig.type());
            if(methods == null)
            {
                methods = new HashSet<Method>();
                signatureMap.put(sig.type(), methods);
            }
            try
            {
                Method method = sig.type().getMethod(sig.method(), sig.args());
                methods.add(method);
            }
            catch(NoSuchMethodException e)
            {
                throw new RuntimeException("Could not find method on " + sig.type() + " named " + sig.method() + ". Cause: " + e, e);
            }
        }
        return signatureMap;
    }
}
