import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBConnect {
	public Connection getConnection(){
		String username, password, dbname;
	
		username = "root";
		password = "root";
		dbname = "library";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		
		System.out.print("MySQL JDBC Driver Registered.");
		Connection connection = null;
		
		try{
			connection  = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname,username,password);
			
		} catch (SQLException e){
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		
		if (connection != null){
			System.out.println("Success!");
		} else {
			System.out.println("Failed");
		}
		
		return connection;	
	}
}
