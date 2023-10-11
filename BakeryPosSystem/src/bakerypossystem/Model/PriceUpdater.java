/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bakerypossystem.Model;

/**
 *
 * @author thenu
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

public class PriceUpdater {
    private final Connection connection;
     private JComboBox<String> IDnumDropdown;
    private JTextField OldPriceTextArea;
        private final DatabaseConnector databaseConnector;
        

    public PriceUpdater(Connection connection) {
        this.connection = connection;
        databaseConnector = new DatabaseConnector();
    }
    public void initializeComponents(JComboBox<String> comboBox, JTextField textArea) {
        IDnumDropdown = comboBox;
        OldPriceTextArea = textArea ;

        // dropdown 
        populatIDNumbers();

        // Add an ActionListener to the dropdown to display order details when an order is selected
       IDnumDropdown.addActionListener(e -> {
            String selectedOrderNumber = (String) IDnumDropdown.getSelectedItem();
            
            displayOrderDetails(selectedOrderNumber);
        });
    }
    public void populatIDNumbers() {
        IDnumDropdown.removeAllItems(); // Clear existing items
        
        String query = "SELECT itemID FROM bakeryitem";
        ResultSet resultSet = databaseConnector.retrieveData(query);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    String orderNumber = resultSet.getString("itemID");
                    IDnumDropdown.addItem(orderNumber);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     public void displayOrderDetails(String idNumber) {
     OldPriceTextArea.setText(""); // Clear existing text
        
        String query = "SELECT Name,Price FROM bakeryitem WHERE itemID = '" + idNumber + "'";
        ResultSet resultSet = databaseConnector.retrieveData(query);

        if (resultSet != null) {
            StringBuilder details = new StringBuilder();

            try {
                while (resultSet.next()) {
                    String itemName = resultSet.getString("Name");
                    int quantity = resultSet.getInt("Price");
                    details.append(itemName).append(": ").append(quantity).append("\n");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            OldPriceTextArea.setText(details.toString());
        }
    }

    public void incrementPrices(double incrementValue) {
        try {
            String updateQuery = "UPDATE bakeryitem SET Price = Price + ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, incrementValue);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Price Updated Succesfuly!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Price Update Failed!");
        }
    }

    public void decrementPrices(double decrementValue) {
        try {
            String updateQuery = "UPDATE bakeryitem SET Price = Price - ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, decrementValue);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Price Updated Succesfuly!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Price Update Failed!");
        }
    }
      public void UpdatePrices(BigDecimal decrementValue,String id) {
        try {
            String updateQuery = "UPDATE bakeryitem SET Price = ? Where ItemID = '" + id + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setBigDecimal(1, decrementValue);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Price Updated Succesfuly!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Price Update Failed!");
        }
    }
}

