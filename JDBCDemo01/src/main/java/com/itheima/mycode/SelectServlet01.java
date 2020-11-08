package com.itheima.mycode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/selectServlet01")
public class SelectServlet01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求体，响应体编码
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        //获取输入关键字
        String p_name = req.getParameter("p_NAME");
        //打印测试
        System.out.println("p_name = " + p_name);
        //获取输出流
        PrintWriter rw = resp.getWriter();
        //打印表头
        rw.write("序号" + "\t" + "商品名称" + "<br/>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //获取connection对象
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT", "root", "0337");
            Statement stat = con.createStatement();
            //拼sql语句
            String sql = "Select * From product Where NAME Like '%" + p_name + "%'";
            //打印测试
            System.out.println("sql = " + sql);
            ResultSet rs = stat.executeQuery(sql);
            //遍历输出
            while (rs.next()) {
                rw.write(rs.getInt("id") + "\t" + rs.getString("NAME") + "<br/>");
            }
            //关闭资源
            con.close();
            stat.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
