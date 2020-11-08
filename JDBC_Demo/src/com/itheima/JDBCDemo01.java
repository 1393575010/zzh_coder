package com.itheima;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo01 {
    public static void main(String[] args) throws Exception {
        //1. 导入jar包
        //2. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //3. 获取数据库连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "0337");
        //4. 获取执行者对象
        Statement statement = con.createStatement();
        //5. 执行sql语句并返回结果
        String sql = "SELECT *  FROM user";
        ResultSet rs = statement.executeQuery(sql);
        //6. 处理结果
        while (rs.next()) {
            String s = rs.getInt("id") + "\t" + rs.getString("NAME");
            System.out.println(s);
        }
        //7. 释放资源
        con.close();
        statement.close();
        rs.close();
    }
}
