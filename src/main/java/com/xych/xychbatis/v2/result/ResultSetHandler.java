package com.xych.xychbatis.v2.result;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xych.xychbatis.v2.config.Configuration;

public class ResultSetHandler
{
    private final Configuration configuration;

    public ResultSetHandler(Configuration configuration)
    {
        super();
        this.configuration = configuration;
    }

    public <T> T handle(ResultSet rs, Class<T> type) throws Exception
    {
        T pojo = type.newInstance();
        if(rs.next())
        {
            for(Field field : type.getDeclaredFields())
            {
                setValue(pojo, field, rs);
            }
        }
        return pojo;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs) throws Exception
    {
        Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field, rs, field.getName()));
    }

    private Object getResult(Field field, ResultSet rs, String colomn) throws SQLException
    {
        //TODO type handles
        //bean属性的名字必须要和数据库column的名字一样
        Class<?> type = field.getType();
        // 针对我自己的表T_S_User做一些处理,原因，数据库列名是c_id,pojo属性是id
        if(colomn == "id")
        {
            colomn = "c_id";
        }
        if(Integer.class == type)
        {
            return rs.getInt(colomn);
        }
        if(String.class == type)
        {
            return rs.getString(colomn);
        }
        return rs.getObject(colomn);
    }

    private String upperCapital(String name)
    {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}
