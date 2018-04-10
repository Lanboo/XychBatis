package com.xych.xychbatis.v3.mapper;

public class MapperData<E>
{
    /**
     * 该方法对应的sql
     */
    private String sql;
    /**
     * 该sql如果是查询，那查询结果对应的类型
     */
    private Class<E> type;

    public MapperData(String sql, Class<E> type)
    {
        super();
        this.sql = sql;
        this.type = type;
    }

    public String getSql()
    {
        return sql;
    }

    public Class<E> getType()
    {
        return type;
    }
}
