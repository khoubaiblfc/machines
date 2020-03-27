package Machines;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Machines.DBConnection;

public class UnitProd {


        private int iD_Unit;
        private int nombre;
        private String  machine_id;

        public UnitProd() {
            iD_Unit = 0;
            nombre = 0;
            machine_id = "";
        }

        public int Ajout(String machine_id,int nombre) throws SQLException
        {
            int res = -1;
            ResultSet rs = null ;
            try
            {
                if (existMachine(machine_id))
                {
                	Connection connection = DBConnection.getConnection();
        			

                    String req = "insert into UniteProd (nombre,machine_id) values('" + nombre + "','" + machine_id + "')";
                    PreparedStatement stmt = connection.prepareStatement(req, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    res = stmt.executeUpdate();

                }
               
            }
            catch (SQLException e)
            {
                System.err.println("Création impossible "+e);
            }

            finally {
    			if (rs != null)
    			{rs.close();}
            }

            return res;
        }

        public int totalUnite(String machine_id) throws SQLException {

            int res = 0;
            String req;
            ResultSet rs = null ;

            try
            {
            	Connection connection = DBConnection.getConnection();
    			
                req = "select sum(nombre) as somme from UniteProd where  machine_id='" + machine_id + "'";
               if (existMachine(machine_id))
                {
            	   	PreparedStatement stmt = connection.prepareStatement(req, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            	   	rs = stmt.executeQuery();

                    if (rs.first())
                    {
                        
                        res = (int)(rs.getInt("somme"));
                        rs.next();

                    }
                }

                else res = -1;
            }
            catch (Exception e)
            {
                System.out.println("exeption=" + e.getMessage());
            }


            finally {
    			if (rs != null)
    			{rs.close();}
            }

            return res;
        }



        public double  Average(String machine_id) throws SQLException {

            double res = 0;
            String req;
            ResultSet rs = null ;

            try
            {
            	Connection connection = DBConnection.getConnection();
    			
                req = "select count(machine_id) as moyenne from UniteProd where machine_id='"+ machine_id +"'";
              
                if (existMachine(machine_id))
                {
                	PreparedStatement stmt = connection.prepareStatement(req, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                	rs = stmt.executeQuery();

                    if (rs.first())
                    {
                        res = totalUnite(machine_id) /(double)(rs.getInt("moyenne"));
                        rs.next();                      

                    }
                }

                else res = -1;
            }
            catch (Exception e)
            {
                System.out.println("exeption=" + e.getMessage());
            }


            finally {
    			if (rs != null)
    			{rs.close();}
    		}


            return res;


        }




        public Boolean existMachine(String machine_id) throws SQLException
        {
            Boolean exist = false;
            String req_machine;
            ResultSet rs = null ;
            
            try {   
	            Connection connection = DBConnection.getConnection();
				
	            req_machine = "select ID_machine from machine where  ID_machine='" + machine_id + "'";
	            PreparedStatement stmt = connection.prepareStatement(req_machine, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	            rs = stmt.executeQuery();
				if (rs.first())
	            { exist = true; }

            } catch (SQLException e) {
    			System.err.println(e);
    		}
    		
    		finally {
    			if (rs != null)
    			{rs.close();}
    		}

            return exist;
        }



        
    	public int getiD_Unit() 
        {
            return iD_Unit;
        }

        public void setiD_Unit(int value)   
        {
            iD_Unit = value;
        }
        

        
        public int getnombre()  
        {
            return nombre;
        }

        public void setnombre(int value) 
        {         
            nombre = value;
        }
    

        public String getMachine_id()     
        {
            return machine_id;
        }

        public void setMachine_id(String value)             
        {
            machine_id = value;
        }
        
       
    
}
