package com.CS01.SerWise.Services.Services;

import com.CS01.SerWise.Controllers.serviceTable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class updateService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String serviceID = req.getParameter("ServiceId");
        String setWhere = "Service_Id='%s'";
        setWhere = String.format(setWhere, serviceID);
        try {
            ArrayList<String[]> results = serviceTable.select("*",setWhere);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/GeneralManager/Services/updateServices.jsp");

            for (String[] i:results){
                req.setAttribute("serviceID",i[0]);
                req.setAttribute("serviceName",i[1]);
                req.setAttribute("price",i[2]);
                req.setAttribute("time",i[3]);
            }

            requestDispatcher.forward(req,resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
