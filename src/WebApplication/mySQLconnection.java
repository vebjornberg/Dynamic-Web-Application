package WebApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.Driver;

public class mySQLconnection {
	
	static final String jdbcDriver = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql://mysqlapplicationdb.cvyzbw5chrfk.ap-southeast-2.rds.amazonaws.com:3306/webApplicationdb";
	static final String username = "vebjorbe";
	static final String password = "manchester";
	
	static DataSource datasource = null;
	static Connection connection = null;
	

	public void establishConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL, username, password);			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getPassword(String username) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT password FROM user_table WHERE username='" + username + "'";
			ResultSet result = statement.executeQuery(sql);
			String userPassword = "";
			while(result.next()) {
				userPassword = result.getString("password");
			}
			result.close();
			closeConnection();
			return userPassword;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			// TODO: handle exception
		}
	}
	public ArrayList<String> getUsernames() {
		try {
			ArrayList<String> usernames = new ArrayList<String>();
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT username FROM user_table";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				usernames.add(result.getString("username"));
			}
			return usernames;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public boolean getAdmin() {
		return false;
	}
	public void setUser(String email, String username, String firstname, String lastname, String address, String creditCard, String dateOfBirth, String password) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO user_table VALUES " +
			"('" + email + "', '" + username + "', '" + firstname + "', '" + lastname + "', '" + address + "', '" + creditCard + "', '" + dateOfBirth + "', 0, '" + password + "')";
			
			statement.executeUpdate(sql);
			System.out.println(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

	
