package WebApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class mySQLconnection {
	
	DataSource datasource = null;
	
	//Class.forName("com.mysql.jdbc.Driver");
	//Connection connection = DriverManager.getConnection("jdbc.mysql://localhost:3306/webApplicationDatabase", "root", "manchester123");
	
	
	public Connection getConnection() {
		try {
			//Context context = (Context) new InitialContext().lookup("java:comp/env");
			//datasource = (DataSource) context.lookup("jdbc/webApplicationDatabase");
			//Connection connection = datasource.getConnection();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("kommer vi hit?");
			Connection connection = DriverManager.getConnection("jdbc.mysql://localhost:3306/webApplicationDatabase", "root", "manchester123");
			System.out.println("Hva skjer her?");
			return connection;
		} catch (Exception e) {
			System.out.println("Hva skjer her da mon tro");
			return null;
		}
	}
}
	
