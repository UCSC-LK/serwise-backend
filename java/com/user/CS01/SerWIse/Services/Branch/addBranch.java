package com.CS01.SerWise.Services.Branch;

import com.CS01.SerWise.Controllers.branchTable;
import com.CS01.SerWise.Controllers.branchManagerTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class addBranch extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String branchID = req.getParameter("branchID");
        String location = req.getParameter("location");
        String address = req.getParameter("address");
        String noOfSlotes = req.getParameter("noOfSlots");
        String branchManagerID = req.getParameter("branchManagerID");
        if (branchManagerID.isEmpty()){
            branchManagerID="null";
        }

        try {
            String branchTableVals = "%s, %s,'%s'";
            branchTableVals = String.format(branchTableVals, branchID, noOfSlotes, location);
            branchTable.insert("Branch_Id, Number_of_Slots, Location",branchTableVals);

            String branchManagerTableVals = "%s, %s";
            branchManagerTableVals = String.format(branchManagerTableVals, branchID, branchManagerID);
            branchManagerTable.insert("Branch_Id, Employee_Id",branchManagerTableVals);

            resp.sendRedirect("/SerWise_war/GeneralManager/Branch/BranchHome.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
