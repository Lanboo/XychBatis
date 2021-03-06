[toc]
# 模拟MyBatis
目的：更好的理解MyBatis的原理

### MyBatis 编程式写法
##### MyBatis结构简图

![MyBatis结构简图](https://github.com/Lanboo/resource/blob/master/images/XychBatis/MyBatis%E7%BB%93%E6%9E%84%E7%AE%80%E5%9B%BE.png?raw=true)

```java
public class MyBatisTest
{
    public static SqlSession getSqlSession() throws FileNotFoundException
    {
        //配置文件
        InputStream configFile = new FileInputStream("D:\\File\\Code\\Java\\git\\other\\XychBatis\\src\\main\\resource\\config\\mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configFile);
        //加载配置文件得到SqlSessionFactory
        return sqlSessionFactory.openSession();
    }

    public static void main(String[] args) throws Exception
    {
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);
        User user = userMapper.selectOne("1");
        System.out.println(user);
    }
}
```

## XychBatis-version1.0

实现`MyBatisTest.main`方法中的三行代码。

``` java
public class XychBatisTest
{
    public static void main(String[] args)
    {
        SqlSession sqlSession = new SqlSession(new Configuration(), new SimpleExecutor());
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectOne("1");
        System.out.println(user);
    }
}
```

#### v1.0 类图

![v1类图](https://github.com/Lanboo/resource/blob/master/images/XychBatis/v1%E7%B1%BB%E5%9B%BE.png?raw=true)

#### v1.0时序图

![v1类图](https://github.com/Lanboo/resource/blob/master/images/XychBatis/v1%E6%97%B6%E5%BA%8F%E5%9B%BE.png?raw=true)

``` plantuml
////(多说一句，VSCode+Markdown Preview Enhanced插件+Graphviz应用程序  可以实现用PlanUML语法画UML图)
////代码如下，效果图如上
@startuml
SqlSession -> Configuration : getMapper
Configuration -> MapperProxy : 代理出Mapper的代理类
SqlSession <-- Configuration : 返回Mapper的代理类
[->SqlSession : Mapper接口调用自身的接口方法
SqlSession -> MapperProxy : 进入invock方法
MapperProxy -> SqlSession : selectOne
SqlSession -> Executor : selectOne
Executor -> 数据库 : 创建连接，执行SQL\n获取数据，封装数据并返回
SqlSession <-- 数据库 : 返回Pojo
[<--SqlSession : 返回Pojo
@enduml
```



## XychBatis-version2.0
``` java
public class XychBatisTest
{
    public static SqlSession getSqlSession() throws Exception
    {
        InputStream configFile = new FileInputStream("D:\\File\\Code\\Java\\git\\other\\XychBatis\\src\\main\\resource\\config\\v2\\xychbatis-config.properties");
        Properties properties = new Properties();
        properties.load(configFile);
        Configuration configuration = new Configuration(properties);
        SqlSession sqlSession = new SqlSession(configuration, ExecutorFactory.DEFAULT(configuration));
        return sqlSession;
    }

    public static void main(String[] args) throws Exception
    {
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectOne("1");
        System.out.println(user);
    }
}
```

#### v2.0 类图
![](https://github.com/Lanboo/resource/blob/master/images/XychBatis/v2%E7%B1%BB%E5%9B%BE.png?raw=true)

