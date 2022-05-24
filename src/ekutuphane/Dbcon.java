package ekutuphane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbcon {

	static String username="root";
	static String password="mysql1234";
	static String Url="jdbc:mysql://localhost:3306/ekutuphane";
	
	public Connection getConnection() throws SQLException
	{
		return (Connection) DriverManager.getConnection(Url,username,password);
	}
	public void SqlException(Exception e)
	{
		System.out.println(e.getMessage());
	}
}
