package com.CS01.SerWise.Services.Registration;

import com.CS01.SerWise.Controllers.employeeTable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class updateEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eid = req.getParameter("eid");
        String employeeValues = "Employee_Id="+eid;
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/GeneralManager/Employee/UpdateEmployee.jsp");
        try {
            ArrayList<String[]> results = employeeTable.select("*",employeeValues);
            for(String[] i:results){
                req.setAttribute("EmployeeID",i[0]);
                req.setAttribute("FirstName",i[1]);
                req.setAttribute("LastName",i[2]);
                req.setAttribute("Address",i[3]);
                req.setAttribute("JoinedDate",i[4]);
                req.setAttribute("Contact",i[5]);
                req.setAttribute("BranchID",i[6]);
            }
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
