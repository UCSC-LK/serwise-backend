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

public class getServiceList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<String[]> results = serviceTable.select();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/GeneralManager/Services/manageServicesHome.jsp");

            int noOfRows =0;

            for (String[] i:results){
                req.setAttribute("serviceID"+noOfRows,i[0]);
                req.setAttribute("name"+noOfRows,i[1]);
                req.setAttribute("price"+noOfRows,i[2]);
                req.setAttribute("time"+noOfRows,i[3]);
                noOfRows+=1;
            }

            req.setAttribute("noOfRows",noOfRows);
            requestDispatcher.forward(req,resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
