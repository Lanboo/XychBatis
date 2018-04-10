package com.xych.xychbatis.v3.mapper;

import java.lang.reflect.Method;

import com.xych.xychbatis.v3.annotations.Delete;
import com.xych.xychbatis.v3.annotations.Insert;
import com.xych.xychbatis.v3.annotations.Select;
import com.xych.xychbatis.v3.annotations.Update;
import com.xych.xychbatis.v3.session.SqlSession;

public class MapperMethod
{
    private Class<?> classInterface;
    private Method method;

    public MapperMethod(Class<?> classInterface, Method method)
    {
        super();
        this.classInterface = classInterface;
        this.method = method;
    }

    public Object execute(SqlSession sqlSession, Object[] args)
    {
        String methodName = classInterface.getName() + "." + method.getName();
        Select select = method.getAnnotation(Select.class);
        if(select != null)
        {
            methodName = select.name().length() > 0 ? select.name() : methodName;
            return sqlSession.selectOne(methodName, args);
        }
        Insert insert = method.getAnnotation(Insert.class);
        if(insert != null)
        {
            methodName = insert.name().length() > 0 ? insert.name() : methodName;
            return sqlSession.insert(methodName, args);
        }
        Update update = method.getAnnotation(Update.class);
        if(update != null)
        {
            methodName = update.name().length() > 0 ? update.name() : methodName;
            return sqlSession.update(methodName, args);
        }
        Delete delete = method.getAnnotation(Delete.class);
        if(delete != null)
        {
            methodName = delete.name().length() > 0 ? delete.name() : methodName;
            return sqlSession.update(methodName, args);
        }
        return sqlSession.exec(methodName, args);
    }

    public static void main(String[] args) throws Exception
    {
        Method method = Integer.class.getMethod("intValue");
        System.out.println(method.getName());
    }
}
