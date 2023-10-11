/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bakerypossystem.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thenu
 */
public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector() {
        // database connection 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL driver
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakeryshop", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public ResultSet retrieveData(String query) {
        ResultSet resultSet = null;
        try {
//            String query = "SELECT Name,Price FROM bakeryitem";
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
        }
        return resultSet;
    }
     public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
 }
}


}
