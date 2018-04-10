package com.xych.xychbatis.v3.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xych.xychbatis.v3.config.Configuration;
import com.xych.xychbatis.v3.mapper.MapperData;
import com.xych.xychbatis.v3.result.ResultSetHandler;

public class StatementHandler
{
    private final Configuration configuration;
    private final ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration)
    {
        super();
        this.configuration = configuration;
        resultSetHandler = new ResultSetHandler(configuration);
    }

    public <T> T query(MapperData<T> mapperData, Object params)
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            conn = getConn();
            pstmt = conn.prepareStatement(mapperData.getSql());
            if(params != null)
            {
                Object[] objs = (Object[]) params;
                int k = 1;
                for(Object obj : objs)
                {
                    pstmt.setObject(k++, obj);
                }
            }
            pstmt.execute();
            rs = pstmt.getResultSet();
            return (T) resultSetHandler.handle(rs, mapperData.getType());
        }
        catch(Exception e)
        {
            throw new RuntimeException("查询失败", e);
        }
        finally
        {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConn()
    {
        try
        {
            Class.forName(configuration.getCalssDriver());
            Connection conn = DriverManager.getConnection(configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
            return conn;
        }
        catch(Exception e)
        {
            throw new RuntimeException("创建连接失败", e);
        }
    }

    private void close(Connection conn, Statement stmt, ResultSet rs)
    {
        if(rs != null)
        {
            try
            {
                rs.close();
            }
            catch(SQLException e)
            {
                throw new RuntimeException("ResultSet关闭失败");
            }
        }
        if(stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch(SQLException e)
            {
                throw new RuntimeException("Statement关闭失败");
            }
        }
        if(conn != null)
        {
            try
            {
                conn.close();
            }
            catch(SQLException e)
            {
                throw new RuntimeException("连接关闭失败");
            }
        }
    }
}
