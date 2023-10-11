/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bakerypossystem.Model;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thenu
 */
public class BakeryItemDatabase {
    public static class BakeryItem {
        private String name;
        private double price;

        public BakeryItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    public BakeryItem retrieveBakeryItem(int itemId) {
        String query = "SELECT Name, Price FROM bakeryitem WHERE ItemID = " + itemId;
        DatabaseConnector dbConnector = new DatabaseConnector();
        ResultSet resultSet = dbConnector.retrieveData(query);

        try {
            if (resultSet.next()) {
                String itemName = resultSet.getString("Name");
                double price = resultSet.getDouble("Price");
                return new BakeryItem(itemName, price);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
