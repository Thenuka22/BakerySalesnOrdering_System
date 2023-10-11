package bakerypossystem.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginValidation {
 ResultSet rst;
        
  public boolean signIn(String name, String password)
  {   
     DatabaseConnector dbConnector = new DatabaseConnector();
     Connection connection = dbConnector.getConnection();
      try{
        Statement st=connection.createStatement();
        rst=st.executeQuery("SELECT * FROM bakerystaff WHERE Username = '"+name+"' AND Password = '"+password+"'");
        if(rst.next()) {
            return true;
        }
        else 
            return false;
      }
      catch(Exception e) {
        System.err.println(e.getMessage());
      }
      return true;
    }
}
