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

@WebServlet("/selectServlet")
public class SelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf8");
        PrintWriter rw = resp.getWriter();
        rw.write("序号" + "\t" + "商品名称" + "<br/>");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "0337");
            Statement stat = con.createStatement();
            String sql = "Select * From product";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                rw.write(rs.getInt("id") + "\t" + rs.getString("NAME") + "<br/>");
            }
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
