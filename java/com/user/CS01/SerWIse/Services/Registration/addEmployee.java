package com.CS01.SerWise.Services.Registration;

import com.CS01.SerWise.Controllers.employeeTable;
import com.CS01.SerWise.Controllers.userTable;
import com.CS01.SerWise.passwordHash;
import com.CS01.SerWise.passwordHashGenerate;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

public class addEmployee extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String contact = req.getParameter("contact");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String BranchID = req.getParameter("BranchID");
        String date = req.getParameter("date");
        int role =Integer.parseInt(req.getParameter("role"));
        String employeeID = req.getParameter("employeeID");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String employeeAttributes = "Employee_Id, First_Name, Last_Name, Address, Joined_Date, Phone_Number, Branch_Id";
        String employeeValues = "%s,'%s','%s','%s','%s','%s',%s";
        employeeValues = String.format(employeeValues, employeeID, firstName, lastName, address, date, contact, BranchID);


        try {
            passwordHashGenerate generatedHash = passwordHash.generateHashedPassword(password);
            String salt = Base64.getEncoder().encodeToString(generatedHash.salt);

            String userAttributes = "id,password,role,salt";
            String userValues = "'%s','%s',%s,'%s'";

            userValues = String.format(userValues, email, generatedHash.hashedPassword, role, salt);

            employeeTable.insert(employeeAttributes,employeeValues);
            userTable.insert(userAttributes,userValues);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/SerWise_war/GeneralManager/Employee/EmployeeHome.jsp");
//        PrintWriter out = resp.getWriter();

//        String stmnt1 = "insert into serwise.employee (Employee_Id, First_Name, Last_Name, Address, Joined_Date, Phone_Number, Branch_Id) values (%s,'%s','%s','%s %s','%s','%s',%s);";
//        stmnt1= String.format(stmnt1, employeeID, firstName, lastName, address, city, date, contact, BranchID);
//        out.println(stmnt1);

//        passwordHashGenerate generatedHash = null;
//        try {
//            generatedHash = passwordHash.generateHashedPassword(password);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        String salt = Base64.getEncoder().encodeToString(generatedHash.salt);

//        try {
//            Connection con = DatabaseConnection.initializeDatabase();
//            PreparedStatement ps2=con.prepareStatement("insert into serwise.user(id,password,role,salt) values (?,?,?,?)");
//            ps2.setString(1,email);
//            ps2.setString(2,generatedHash.hashedPassword);
//            ps2.setInt(3,role);
//            ps2.setString(4,salt);
//
//            Statement statement = con.createStatement();
////            statement.executeUpdate(stmnt1);
//            ps2.execute();
//            resp.sendRedirect("/SerWise_war/GeneralManager/Employee/EmployeeHome.jsp");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

    }
}
