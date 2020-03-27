	package Machines;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	
	public class DBConnection {
		
		private static final String USERNAME ="dbuser";
		private static final String PASSWORD ="dbpassword";
		private static final String CONN ="jdbc:mysql://localhost:8889/machines?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	
		public static Connection getConnection() throws SQLException {
			
			
			return DriverManager.getConnection(CONN, USERNAME, PASSWORD);
			
		}
		
	}
