package com.CS01.SerWise.Services.Services;

import com.CS01.SerWise.Controllers.serviceTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class updateServiceDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serviceID = req.getParameter("serviceID");
        String serviceName = req.getParameter("serviceName");
        String price = req.getParameter("price");
        String time = req.getParameter("timeSpend");

        String setService = "Name='%s',Price=%s,Time_Spent=%s";
        String whereService = "Service_Id=%s";
        setService = String.format(setService, serviceName, price, time);
        whereService = String.format(whereService, serviceID);

        try {
            serviceTable.update(setService,whereService);
            resp.sendRedirect("/SerWise_war/serviceList");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
