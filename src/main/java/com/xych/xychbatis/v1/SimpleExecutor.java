package com.xych.xychbatis.v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xych.test.pojo.User;

public class SimpleExecutor implements Executor
{
    private final String calssDriver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/Lanboo?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String username = "root";
    private final String password = "123456";

    @SuppressWarnings("unchecked")
    @Override
    public <T> T selectOne(String sql, Object params)
    {
        System.out.println("开始查询数据库");
        //此处hardcode
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try
        {
            conn = getConn();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1, ((Object[]) params)[0]);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                user = new User();
                user.setId(rs.getString("c_iden"));
                user.setC_user_code(rs.getString("c_user_code"));
                user.setC_user_name(rs.getString("c_user_name"));
                //省略其他属性的setter
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.closeConn(conn);
        }
        return (T) user;
    }

    private Connection getConn() throws Exception
    {
        Class.forName(calssDriver);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    private void closeConn(Connection conn)
    {
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
