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

public class getBranchList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<String[]> results = branchTable.select();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/GeneralManager/Branch/ManageBranchHome.jsp");
            int noofrows = 0;
            for (String[] i : results){
                req.setAttribute("branchID"+noofrows,i[0]);
                req.setAttribute("noOfSlots"+noofrows,i[1]);
                req.setAttribute("location"+noofrows,i[2]);
                ArrayList<String[]> branchmanager = branchManagerTable.select("Employee_Id","Branch_Id="+i[0]);
                String[] temp = branchmanager.get(0);
                req.setAttribute("managerID"+noofrows,temp[0]);
                noofrows+=1;
            }
            req.setAttribute("noOfRows",noofrows);
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
