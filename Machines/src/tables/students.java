package tables;

import java.sql.ResultSet;
import java.sql.SQLException;


public class students {
	
	public static void getStudents(ResultSet rs) throws SQLException {
		while (rs.next()) {
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("machine ID: "+ rs.getString("machine_id")+ "'s temperaure: ");
			buffer.append(rs.getInt("Degre")+ " ");
			
			//buffer.append(rs.getDate("dob"));
			
			System.out.println(buffer.toString());
		}
		
	}

}
