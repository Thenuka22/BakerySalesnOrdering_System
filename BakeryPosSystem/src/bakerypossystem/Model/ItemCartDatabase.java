/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bakerypossystem.Model;

/**
 *
 * @author thenu
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ItemCartDatabase {
    private final Connection connection;
    private final DatabaseConnector dbConnector;

    public ItemCartDatabase() {
        dbConnector = new DatabaseConnector();
        connection = dbConnector.getConnection();
    }

public void addItemToCart(int id,String itemName, int quantity, double price,int orderNumber) {
     try {
//          // Generate a 4-digit random order number
//            int orderNumber = (int) (Math.random() * 9000) + 1000;
            // Define the SQL query to insert an item into the cart table
            String sql = "INSERT INTO pendingorders (id, item_name, quantity, price, order_number) VALUES (?, ?, ?, ?, ?)";

           
            
            int rowsAffected = 0; // Initialize rowsAffected

            try (
                // Create a PreparedStatement
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                // Set parameter values and execute the query for each row
            
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, itemName);
                    preparedStatement.setInt(3, quantity);
                    preparedStatement.setDouble(4, price);
                    preparedStatement.setInt(5, orderNumber); // Set the order number

                    rowsAffected += preparedStatement.executeUpdate();
           
            
                // Close the PreparedStatement (Connection will be closed separately)
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
}
  
public void addToSales(int id, String itemName, int quantity, double price, int orderNumber) {
    try {
        // Define the SQL query to insert an item into the SalesTable
        String sql = "INSERT INTO salestable (OrderNumber, ID, ItemName, Quantity, Price) VALUES (?, ?, ?, ?, ?)";

        int rowsAffected = 0; // Initialize rowsAffected

        try (
            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            // Set parameter values and execute the query for each row
            preparedStatement.setInt(1, orderNumber); // Set the order number
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3, itemName);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setDouble(5, price);

            rowsAffected += preparedStatement.executeUpdate();
        }

        // Handle rowsAffected and any other logic as needed

    } catch (SQLException e) {
        e.printStackTrace(); // Handle the exception appropriately
    }
}

    public void closeConnection() {
        dbConnector.closeConnection();
    }
}
