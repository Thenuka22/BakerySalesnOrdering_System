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
import javax.swing.JOptionPane;

public class PendingCompleted {

    // Your DatabaseConnector class or database connection handling logic
    private DatabaseConnector dbConnector;

    public PendingCompleted() {
        // Initialize your DatabaseConnector here
        dbConnector = new DatabaseConnector();
    }

    public boolean deleteRecord(String selectedId) {
        Connection connection = dbConnector.getConnection();
        if (connection != null) {
            PreparedStatement statement = null;
            try {
                // Define your SQL DELETE statement
                String deleteQuery = "DELETE FROM pendingorders WHERE order_number = ?";
                
                // Create a PreparedStatement and set the ID parameter
                statement = connection.prepareStatement(deleteQuery);
                statement.setString(1, selectedId);
                
                // Execute the delete operation
                int rowsAffected = statement.executeUpdate();
                
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Order Completed");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dbConnector.closeConnection(); // Close the database connection
            }
        }
         JOptionPane.showMessageDialog(null, "Something Went Wrong! or Failed Complting Order");
        return false; // Record deletion failed
    }
}

