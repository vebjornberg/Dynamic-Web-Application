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
	PreparedStatement preparedStatement = null;
	private static final String sql = "SELECT * FROM webApplicationDatabase.Publication_Table";
	
	/**public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc.mysql://localhost:3306/webApplicationDatabase", "root", "manchester123");
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}**/
	public Connection getConnection() {
		try {
			Context context = (Context) new InitialContext().lookup("java:comp/env");
			datasource = (DataSource) context.lookup("jdbc/webApplicationDatabase");
			Connection connection = datasource.getConnection();
			return connection;
			//preparedStatement = connection.prepareStatement(sql);
			//ResultSet resultset = preparedStatement.executeQuery();
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
}
	
