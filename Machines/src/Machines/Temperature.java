package Machines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Machines.DBConnection;

public class Temperature {

        private int degre;
        private String machine_id;

        public Temperature() {
            degre = 0;
            machine_id = "";
        }
        
        

        public int Ajout(String machine_id , int degre) throws SQLException
        {
            int res = 0, res1 = 0;
            String req;
            ResultSet rs = null ;
			
            try
            {
            	Connection connection = DBConnection.getConnection();
                if (existMachine( machine_id, "Temperature")) {

                    req = "update  Temperature set Degre='" + degre + "'where machine_id='" + machine_id + "'";   
                 }
                else req = "insert into  Temperature (Degre,machine_id) values('" + degre + "','" + machine_id + "')";

                PreparedStatement stmt = connection.prepareStatement(req, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt.executeUpdate();
            }
            catch (Exception e)
            {
               System.out.print("exeption=" + e.getMessage());
            }

            finally {
				if (rs != null)
				{rs.close();}
				
			}

            return res;
        }


        public int AfficheTemp(String machine_id) throws SQLException {
            String req="";
            int res = -1;
            ResultSet rs = null;
            
            
            try
            {   
            	Connection connection = DBConnection.getConnection();
                req = "select degre from Temperature where  machine_id='" + machine_id + "'";
                     if(existMachine(machine_id, "machine"))
                      {
                    	 PreparedStatement stmt = connection.prepareStatement(req, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             			
                    	 rs = stmt.executeQuery();
                     
                       if (rs.first())
                        {
                         
                         res = (int)(rs.getInt("Degre"));
                         rs.next();
                         }
                      }

                    else res = 0;

            }
            catch (Exception e)
            {
                System.out.print("exeption="+e.getMessage());
            }
            
			finally {
				if (rs != null)
				{rs.close();}
				
			}
            
          // Console.WriteLine("val="+val);
            return (res);
        }


        public Boolean existMachine(String machine_id,String table) {
            Boolean exist=false;
            String req_machine;
        	ResultSet rs = null ;
            Connection connection;
			try {
				connection = DBConnection.getConnection();
			
			
			
	            if (table=="machine")  req_machine = "select ID_machine from "+table+ " where  ID_machine='" + machine_id + "'";
	            else req_machine = "select machine_id from " + table + " where  machine_id='" + machine_id + "'";
	            PreparedStatement stmt = connection.prepareStatement(req_machine, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	            rs = stmt.executeQuery();
	            if (rs.first())
	            	{ exist = true; }
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	            //cnx.Close();
	
	            return exist;
	        }
        
        
        
        

      
        
        public int getDegre()     
        {
            return degre;
        }

        public void setDegre(int value)
        {
            degre = value;
        }
        

    	public String getMachine_ID()
        {
            return machine_id;
        }

    	public void setMachine_id(String value)
        {
            machine_id = value;
        }
        
    
}
