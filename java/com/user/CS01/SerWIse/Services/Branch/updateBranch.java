package com.CS01.SerWise.Services.Branch;

import com.CS01.SerWise.Controllers.branchTable;
import com.CS01.SerWise.Controllers.branchManagerTable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class updateBranch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        try {
            String branch = "Branch_Id="+bid;
            ArrayList<String[]> results = branchTable.select("*",branch);
            ArrayList<String[]> results1 = branchManagerTable.select("Employee_Id",branch);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/GeneralManager/Branch/UpdateBranch.jsp");
            for(String[] i: results){
                req.setAttribute("branchID",i[0]);
                req.setAttribute("noOfSlots",i[1]);
                req.setAttribute("location",i[2]);
            }
            for(String[] i: results1){
                req.setAttribute("branchManagerID",i[0]);
            }
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
