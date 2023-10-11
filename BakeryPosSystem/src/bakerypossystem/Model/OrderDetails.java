/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bakerypossystem.Model;

/**
 *
 * @author thenu
 */
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetails {
    private final DatabaseConnector databaseConnector;
    private JComboBox<String> orderNumberDropdown;
    private JTextArea orderDetailsTextArea;

    public OrderDetails() {
        databaseConnector = new DatabaseConnector();
    }

    public void initializeComponents(JComboBox<String> comboBox, JTextArea textArea) {
        orderNumberDropdown = comboBox;
        orderDetailsTextArea = textArea;

        // Initialize the dropdown by populating order numbers
        populateOrderNumbers();

        // Add an ActionListener to the dropdown to display order details when an order is selected
        orderNumberDropdown.addActionListener(e -> {
            String selectedOrderNumber = (String) orderNumberDropdown.getSelectedItem();
            displayOrderDetails(selectedOrderNumber);
        });
    }

    public void populateOrderNumbers() {
        orderNumberDropdown.removeAllItems(); // Clear existing items
        
        String query = "SELECT DISTINCT order_number FROM pendingorders";
        ResultSet resultSet = databaseConnector.retrieveData(query);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String orderNumber = resultSet.getString("order_number");
                    orderNumberDropdown.addItem(orderNumber);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Getting Data from Databse Failed!");
            }
        }
    }

    public void displayOrderDetails(String orderNumber) {
        orderDetailsTextArea.setText(""); // Clear existing text
        
        String query = "SELECT item_name, quantity FROM pendingorders WHERE order_number = '" + orderNumber + "'";
        ResultSet resultSet = databaseConnector.retrieveData(query);

        if (resultSet != null) {
            StringBuilder details = new StringBuilder();

            try {
                while (resultSet.next()) {
                    String itemName = resultSet.getString("item_name");
                    int quantity = resultSet.getInt("quantity");
                    details.append(itemName).append(": ").append(quantity).append("\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Getting Data from Databse Failed!");
            }

            orderDetailsTextArea.setText(details.toString());
        }
    }
}