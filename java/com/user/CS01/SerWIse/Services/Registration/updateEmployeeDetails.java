package com.CS01.SerWise.Services.Registration;

import com.CS01.SerWise.Controllers.employeeTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class updateEmployeeDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String contact = req.getParameter("contact");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String BranchID = req.getParameter("BranchID");
        String date = req.getParameter("date");
        String role = req.getParameter("role");
        String employeeID = req.getParameter("employeeID");

        String afterSet = "First_Name='%s', Last_Name='%s', Address='%s', Joined_Date='%s', Phone_Number='%s', Branch_Id='%s'";
        String afterWhere = "Employee_Id='%s'";

        afterSet = String.format(afterSet, firstName, lastName, address, date, contact, BranchID);
        afterWhere = String.format(afterWhere, employeeID);

        try {
            employeeTable.update(afterSet,afterWhere);
            resp.sendRedirect("/SerWise_war/employeelist");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
