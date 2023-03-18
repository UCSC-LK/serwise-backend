package com.CS01.SerWise.Services.Services;

import com.CS01.SerWise.Controllers.serviceTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class deleteService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serviceID = req.getParameter("ServiceId");
        try {
            serviceTable.delete(serviceID);
            resp.sendRedirect("/SerWise_war/serviceList");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
