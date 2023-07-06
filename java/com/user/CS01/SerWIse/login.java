package com.user.CS01.SerWIse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
//        out.println(email);
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            String query = "select * from serwise.login where email='%s';".formatted(email);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()){
                if (pwd.equals(resultSet.getString("pwd"))){
                    resp.sendRedirect("GeneralManager/Branch/AddBranch.html");
                }  else{
                    out.println("Incorrect Password");
                    resp.sendRedirect("Login/login.html");
                }
            } else {
                out.println("Incorrect Email!!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
