package bakerypossystem.Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

public class DailyTopItemsPieChartPanel extends JPanel {

    private final DatabaseConnector dbConnector; // Database connection

    public DailyTopItemsPieChartPanel() {
      
        dbConnector = new DatabaseConnector();
        DefaultPieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(Color.BLACK);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        add(chartPanel);
         setBackground(Color.BLACK);
        
    }

    private DefaultPieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        // Retrieve data from the database
        HashMap<String, Integer> top5Items = getTop5Items();

        // Add the top 5 items to the dataset
        for (String itemName : top5Items.keySet()) {
            dataset.setValue(itemName, top5Items.get(itemName));
        }

        return dataset;
    }

    private HashMap<String, Integer> getTop5Items() {
        HashMap<String, Integer> top5Items = new HashMap<>();

        
        Connection connection = dbConnector.getConnection();

        if (connection != null) {
            try {
                String query = "SELECT ItemName, SUM(Quantity) as TotalQuantity " +
                        "FROM salestable " +
                        "GROUP BY ItemName " +
                        "ORDER BY TotalQuantity DESC " +
                        "LIMIT 5";
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                
                while (resultSet.next()) {
                    String itemName = resultSet.getString("ItemName");
                    int totalQuantity = resultSet.getInt("TotalQuantity");
                    top5Items.put(itemName, totalQuantity);
                }
            } catch (SQLException e) {
            } finally {
                dbConnector.closeConnection();
            }
        }

        return top5Items;
    }

    private JFreeChart createChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Top Items Sold",  // chart title
                dataset,                   // dataset
                true,                      // include legend
                true,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

         plot.setLabelOutlinePaint(Color.WHITE);
         plot.setLabelShadowPaint(Color.BLACK);
        return chart;
    }

  
}
   

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
  // </editor-fold>                        


    // Variables declaration - do not modify                     
    // End of variables declaration                   

    
