package com.CS01.SerWise.Services.Branch;

import com.CS01.SerWise.Controllers.branchTable;
import com.CS01.SerWise.Controllers.branchManagerTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class deleteBranch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bid = req.getParameter("bid");
        try {
            branchTable.delete(bid);
            branchManagerTable.delete(bid);
            resp.sendRedirect("/SerWise_war/branchlist");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
