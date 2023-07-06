package com.CS01.SerWise.Services.Inventory;

import com.CS01.SerWise.Controllers.inventoryItemTable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class updateInventory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        String afterWhere = "Inventory_Item_Id='%s'";
        afterWhere = String.format(afterWhere, itemId);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/GeneralManager/Inventory/UpdateInventory.jsp");
        try {
            ArrayList<String[]> results = inventoryItemTable.select("*",afterWhere);
            for(String[] i:results){
                req.setAttribute("ItemID",i[0]);
                req.setAttribute("ItemName",i[1]);
                req.setAttribute("Measure",i[2]);
                req.setAttribute("Price",i[3]);
            }
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
