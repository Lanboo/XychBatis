package com.xych.xychbatis.v3.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.xych.xychbatis.v3.executor.Executor;
import com.xych.xychbatis.v3.executor.ExecutorFactory;
import com.xych.xychbatis.v3.mapper.MapperData;
import com.xych.xychbatis.v3.mapper.MapperRegistry;
import com.xych.xychbatis.v3.plugin.Interceptor;
import com.xych.xychbatis.v3.plugin.InterceptorChain;
import com.xych.xychbatis.v3.session.SqlSession;

public class Configuration
{
    private String calssDriver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/Lanboo?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String username = "root";
    private String password = "123456";
    protected final MapperRegistry mapperRegistry = new MapperRegistry();
    protected final InterceptorChain interceptorChain = new InterceptorChain();

    public Configuration()
    {
        super();
    }

    public Configuration(Properties properties)
    {
        this.calssDriver = properties.getProperty("xychbatis.jdbc.driver");
        this.url = properties.getProperty("xychbatis.jdbc.url");
        this.username = properties.getProperty("xychbatis.jdbc.username");
        this.password = properties.getProperty("xychbatis.jdbc.password");
        this.loadMapper(properties.getProperty("xychbatis.mapper"));
        this.loadInterceptor(properties.getProperty("xychbatis.plugin"));
    }

    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession)
    {
        return mapperRegistry.getMapper(clazz, sqlSession);
    }

    public <T> MapperData<T> get(String methodKey)
    {
        return mapperRegistry.get(methodKey);
    }

    public void addInterceptor(Interceptor interceptor)
    {
        interceptorChain.addInterceptor(interceptor);
    }

    public Executor newExecutor()
    {
        return this.newExecutor(ExecutorFactory.SIMPLE);
    }

    public Executor newExecutor(String executorKey)
    {
        Executor executor = null;
        if(ExecutorFactory.SIMPLE.equalsIgnoreCase(executorKey))
        {
            executor = ExecutorFactory.DEFAULT(this);
        }
        else if(ExecutorFactory.CACHING.equalsIgnoreCase(executorKey))
        {
            executor = ExecutorFactory.get(executorKey, this);
        }
        else
        {
            throw new RuntimeException("no Executor Impl:type=" + executorKey);
        }
        executor = (Executor) interceptorChain.pluginAll(executor);
        return executor;
    }

    private void loadInterceptor(String pluginStr)
    {
        if(pluginStr == null || pluginStr.length() == 0)
        {
            return;
        }
        String[] plugins = pluginStr.split(",");
        try
        {
            for(String pluginName : plugins)
            {
                Class<?> clazz = Class.forName(pluginName);
                if(Interceptor.class.isAssignableFrom(clazz))
                {
                    Interceptor interceptor;
                    interceptor = (Interceptor) clazz.newInstance();
                    this.interceptorChain.addInterceptor(interceptor);
                }
                else
                {
                    throw new RuntimeException(pluginName + " is not Interceptor");
                }
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException("load Interceptor Fail");
        }
    }

    private void loadMapper(String mapperPage)
    {
        String classpath = this.getClass().getResource("/").getPath();
        String basePaht = classpath + mapperPage.replace(".", File.separator);
        List<String> mapperPaths = new ArrayList<>();
        loadMapper(new File(basePaht), mapperPaths);
        try
        {
            for(String str : mapperPaths)
            {
                String className = str.replace(classpath.replace("/", "\\").replaceFirst("\\\\", ""), "").replace("\\", ".").replace(".class", "");
                Class<?> clazz = Class.forName(className);
                mapperRegistry.addMapper(clazz);
            }
        }
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException("初始化Mapper信息出错", e);
        }
    }

    private void loadMapper(File file, List<String> mapperPaths)
    {
        if(file.isDirectory())
        {
            File[] files = file.listFiles();
            for(File f1 : files)
            {
                loadMapper(f1, mapperPaths);
            }
        }
        else
        {
            if(file.getName().endsWith(".class"))
            {
                mapperPaths.add(file.getPath());
            }
        }
    }

    public String getCalssDriver()
    {
        return calssDriver;
    }

    public String getUrl()
    {
        return url;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }
}
