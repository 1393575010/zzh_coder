package com.itheima.mycode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo01 {
    Connection con;
    Statement stat;
    ResultSet rs;

    @Before
    public void init() throws Exception {
        //导入jar包
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取连接
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "0337");
        //获取执行者对象
        stat = con.createStatement();
    }

    @After
    public void closes() throws Exception {
        //关闭资源
        con.close();
        stat.close();
        rs.close();
    }

    @Test
    public void testDemo() throws Exception {
        //执行sql语句并返回结果
        String sql = "Select * FROM user";
        rs = stat.executeQuery(sql);
        //处理结果
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("NAME");
            System.out.println(id + "\t" + name);
        }
    }

}
