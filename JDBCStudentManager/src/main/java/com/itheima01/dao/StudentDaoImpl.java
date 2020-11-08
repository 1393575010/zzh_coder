package com.itheima01.dao;

import com.itheima01.domain.Student;
import com.itheima01.utils.JDBCUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class StudentDaoImpl implements StudentDao {


    @Override
    public ArrayList<Student> findAll() {
        ResultSet resultSet = null;
        Statement state = null;
        Connection con = null;
        ArrayList<Student> list = new ArrayList<>();
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2 获取数据库连接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db4", "root", "0337");
            //3 获取执行者对象
            state = con.createStatement();
            //4 执行sql语句，并且接收返回的结果集
            String sql = "Select * from student";
            resultSet = state.executeQuery(sql);
            //5 处理结果集
            while (resultSet.next()) {
                Integer id = resultSet.getInt("sid");
                String name = resultSet.getString("NAME");
                Integer age = resultSet.getInt("age");
                Date birthday = resultSet.getDate("birthday");
                list.add(new Student(id, name, age, birthday));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6 释放资源
            JDBCUtils.close(con, state, resultSet);
        }


        return list;
    }

    @Override
    public Student findbyId(Integer id) {
        ResultSet resultSet = null;
        Statement state = null;
        Connection con = null;
        Student stu = new Student();
        try {
            con = JDBCUtils.getConnection();
            //3 获取执行者对象
            state = con.createStatement();
            //4 执行sql语句，并且接收返回的结果集
            String sql = "Select * from student Where sid='" + id + "'";
            resultSet = state.executeQuery(sql);
            //5 处理结果集
            while (resultSet.next()) {
                stu.setSid(resultSet.getInt("sid"));
                stu.setName(resultSet.getString("NAME"));
                stu.setAge(resultSet.getInt("age"));
                stu.setBirthday(resultSet.getDate("birthday"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6 释放资源
            JDBCUtils.close(con, state, resultSet);
        }


        return stu;
    }

    @Override
    public int insert(Student stu) {
        Connection con = null;
        Statement state = null;
        int result = 0;
        try {
            con = JDBCUtils.getConnection();
            //3 获取执行者对象
            state = con.createStatement();
            //4 执行sql语句，并且接收返回的结果集
            Integer sid = null;
            String name = stu.getName();
            Integer age = stu.getAge();

            java.util.Date d = stu.getBirthday();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String birthday = sdf.format(d);
            String sql = "Insert Into student Values (" + sid + ",'" + name + "'," + age + ",'" + birthday + "')";
            System.out.println("sql = " + sql);
            result = state.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6 释放资源
            JDBCUtils.close(con, state);
        }
        return result;
    }

    @Override
    public int update(Student stu) {
        //"jdbc:mysql://localhost:3306/db1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT"
        Connection con = null;
        Statement state = null;
        int result = 0;
        try {
            con = JDBCUtils.getConnection();
            //3 获取执行者对象
            //3 获取执行者对象
            state = con.createStatement();
            //4 执行sql语句，并且接收返回的结果集
            Integer sid = stu.getSid();
            String name = stu.getName();
            Integer age = stu.getAge();

            java.util.Date d = stu.getBirthday();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String birthday = sdf.format(d);
            String sql = "UPDATE student Set sid=" + sid + ",name='" + name + "',age=" + age + ",birthday='" + birthday + "'Where sid=" + stu.getSid();
            System.out.println("sql = " + sql);
            result = state.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6 释放资源
            JDBCUtils.close(con, state);
        }
        return result;
    }

    @Override
    public int delete(Integer id) {
        //"jdbc:mysql://localhost:3306/db1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT"
        Connection con = null;
        Statement state = null;
        int result = 0;
        try {
            con = JDBCUtils.getConnection();
            //3 获取执行者对象
            state = con.createStatement();

            String sql = "Delete From student Where sid=" + id;
            System.out.println("sql = " + sql);
            result = state.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6 释放资源
            JDBCUtils.close(con, state);

        }
        return result;
    }
}
