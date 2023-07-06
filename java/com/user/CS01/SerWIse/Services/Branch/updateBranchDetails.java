package com.CS01.SerWise.Services.Branch;

import com.CS01.SerWise.Controllers.branchTable;
import com.CS01.SerWise.Controllers.branchManagerTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class updateBranchDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String branchID = req.getParameter("branchID");
        String location = req.getParameter("location");
        String noOfSlotes = req.getParameter("noOfSlots");
        String branchManagerID = req.getParameter("branchManagerID");

        try {
            String managerset = "Employee_Id='%s'";
            managerset = String.format(managerset, branchManagerID);
            String branchset = "Number_of_Slots='%s' , Location='%s'";
            branchset = String.format(branchset, noOfSlotes, location);
            String branch = "Branch_Id='%s'" ;
            branch = String.format(branch, branchID);
            branchTable.update(branchset,branch);
            branchManagerTable.update(managerset,branch);
            resp.sendRedirect("/SerWise_war/branchlist");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
