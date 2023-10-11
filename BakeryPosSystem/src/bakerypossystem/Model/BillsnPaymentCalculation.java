/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bakerypossystem.Model;

import bakerypossystem.View.CustomerView;
import javax.swing.JOptionPane;

/**
 *
 * @author thenu
 */
public class BillsnPaymentCalculation {
    
    
    
    
    
    
public double balanceCal(double totall,double paidd){
            
        double tot = totall;
        double paid = paidd;
        double balance = 0;
        
        if (paidd<totall){
            JOptionPane.showMessageDialog(null, "Paid Amount is less than Total payment");
        }
        else{
           balance = paid - tot ;
             JOptionPane.showMessageDialog(null, "Payment Received Successful");
                 
             
        }
        return balance;
 
}   


        
       
    
}
