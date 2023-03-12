package com.CS01.SerWise.Services.Inventory;

import com.CS01.SerWise.Controllers.inventoryItemTable;
import com.CS01.SerWise.Controllers.inventoryItemBranchTable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class addInventory extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemID = req.getParameter("itemID");
        String itemName = req.getParameter("itemName");
        String measurement = req.getParameter("measurement");
        String price = req.getParameter("price");
        int noOfBranch = Integer.parseInt(req.getParameter("noOfBranches"));

        String inventoryAttributes = "Inventory_Item_Id, Name, Measurement, Price";
        String inventoryItemBranchAttributes = "inventory_item_Inventory_Item_Id,branch_Branch_Id";

        String inventryValues = "%s, '%s', '%s', '%s'";
        inventryValues = String.format(inventryValues, itemID, itemName, measurement, price);
        try {
            inventoryItemTable.insert(inventoryAttributes,inventryValues);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int k=1;k<=noOfBranch;k++){
            String branchid = req.getParameter("branchid"+k);
            String inventoryItemBranchValues = "%s,%s";
            inventoryItemBranchValues = String.format(inventoryItemBranchValues, itemID, branchid);
            try {
                inventoryItemBranchTable.insert(inventoryItemBranchAttributes,inventoryItemBranchValues);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        resp.sendRedirect("/SerWise_war/GeneralManager/Inventory/InventoryHome.jsp");
//        String stmnt = "insert into serwise.inventory_item (Inventory_Item_Id, Name, Measurement, Price) values (%s, '%s', '%s', '%s');";
//        stmnt = String.format(stmnt,itemID, itemName, measurement, price);
//        Connection con = null;
//        try {
//            con = DatabaseConnection.initializeDatabase();
//            Statement statement = con.createStatement();
//            statement.executeUpdate(stmnt);
//            resp.sendRedirect("/SerWise_war/GeneralManager/Inventory/InventoryHome.jsp");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }
}
