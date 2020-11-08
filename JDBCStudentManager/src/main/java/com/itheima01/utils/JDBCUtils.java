package com.itheima01.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    //私有构造方法
    private JDBCUtils() {
    }

    //声明所需要的配置变量
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;
    private static Connection con;

    //提供静态代码块，读取配置文件的信息为变量赋值，注册驱动
    static {
        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();
            prop.load(is);
            driverClass = prop.getProperty("driverClass");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");

            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //提供获取数据库连接方式
    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;
    }

    //提供释放资源的方法
    public static void close(Connection con, Statement stat, ResultSet rs) {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void close(Connection con, Statement stat) {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

}
