package com.user.CS01.SerWIse;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "userReg" , value = "/userReg")
public class userReg extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fName = req.getParameter("fName");
        String lName = req.getParameter("lName");
        String contactNum = req.getParameter("contactNum");
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");

        resp.setContentType("text/html");
        PrintWriter pw= resp.getWriter();
        //initializing connections
        Connection con=null;
        Statement stmt=null;
        ResultSet rs1=null;
        ResultSet rs2=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3305/serwise","root","");
            stmt = con.createStatement();
            stmt.executeUpdate("insert into serwise.registered_client (First_Name , Last_Name , Contact ) values (fName,lName,contactNum);");
//            rs2 = stmt.executeQuery("insert into serwise.login (emial , pwd ) values (" + email + pwd +");");
            if (rs1.next()) {
                pw.println(rs1);
            }else {
                pw.println("false");
            }
        }// End of try block
        catch(Exception e) {e.printStackTrace();}

//        PrintWriter out = resp.getWriter();
//        out.println("Your name is" + name);
    }
}
