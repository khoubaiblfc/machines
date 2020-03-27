package Machines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import Machines.DBConnection;


public class Machine {
	
	 private String iD_machine;
     private String name_machine;

     public Machine() {
         this.iD_machine = "";
         this.name_machine = "";
         

     }

     public int Ajout(String iD_machine, String name_machine) throws SQLException {
         int res = 0;
         ResultSet rs = null ;
         
			
         try {
        	 Connection connection = DBConnection.getConnection();
             String req = "insert into machine (ID_machine,Name_machine) values('" + iD_machine + "','" + name_machine + "')";
             PreparedStatement stmt = connection.prepareStatement(req, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
 			
             res = stmt.executeUpdate();
         } catch (Exception e) {
             System.out.println("Création impossible");  
         }

         finally {
 			if (rs != null)
 			{rs.close();}
 		}

         return res;
         }


     /* public int delete(String iD_machine)
      {
          SqlConnection cnx = new SqlConnection(@"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=C:\Testetechnique\Testetechnique\machines_system.mdf;Integrated Security=True");
          cnx.Open();
          String req = "delete from machine where ID_machine='"+ iD_machine+ "'";
          SqlCommand cmd = new SqlCommand(req, cnx);
          int res = cmd.ExecuteNonQuery();
          cnx.Close();
          return res;
      }
      */

     public Machine(String iD_machine)
     {
         this.iD_machine = iD_machine;
     }

     public String getID_machine()
     {
         return iD_machine;
     }

     public void setiD_machine(String value)
     {
    	 iD_machine = value;
     }
        
    
 
}
