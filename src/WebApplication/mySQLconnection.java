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
	public ArrayList<String> getEmails() {
		try {
			ArrayList<String> emails = new ArrayList<String>();
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT email FROM user_table";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				emails.add(result.getString("email"));
			}
			return emails;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public boolean getAdmin() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT admin FROM user_table WHERE username='" + username + "'";
			ResultSet result = statement.executeQuery(sql);
			int admin = 0;
			while(result.next()) {
				admin = result.getInt("activated");
			}
			result.close();
			closeConnection();
			if (admin==1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
			// TODO: handle exception
		}
	}
	public void setUserBean(UserBean userbean) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO user_table VALUES " +
			"('" + userbean.getEmail() + "', '" + userbean.getUsername() + "', '" + userbean.getFirstname() + "', '" 
					+ userbean.getLastname() + "', '" + userbean.getAddress() + "', '" + userbean.getCreditCard() 
					+ "', '" + userbean.getDateOfBirth() + "', 0, '" + userbean.getPassword() + "', 0, " + userbean.getConfirmationHash() + ")";
			statement.executeUpdate(sql);
			System.out.println(sql);
			closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ArrayList<PublicationBean> getPublications(String search) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT publication_table.*, author_table.* "
					+ "FROM publication_table INNER JOIN authoredby_table "
					+ "ON authoredby_table.publicationid = publication_table.publicationid "
					+ "INNER JOIN author_table ON author_table.authorid = authoredby_table.authorid "
					+ "WHERE publication_table.type LIKE '%" + search + "%' OR publication_table.title LIKE '%" + search +"%' OR "
					+ "publication_table.year LIKE '%"+ search + "%' OR publication_table.price LIKE '%" + search + "%' OR "
					+ "author_table.firstname LIKE '%" + search + "%' OR author_table.lastname LIKE '%" + search + "%'";
			ResultSet result = statement.executeQuery(sql);
			ArrayList<PublicationBean> publications = new ArrayList<PublicationBean>();
			while(result.next()) {
				PublicationBean publicationbean = new PublicationBean();
				publicationbean.setAuthorid(result.getInt("authorid"));
				publicationbean.setPublicationid(result.getInt("publicationid"));
				publicationbean.setType(result.getString("type"));
				publicationbean.setYear(result.getString("year"));
				publicationbean.setPrice(result.getString("price"));
				publicationbean.setFirstname(result.getString("firstname"));
				publicationbean.setLastname(result.getString("lastname"));
				publicationbean.setTitle(result.getString("title"));
				publications.add(publicationbean);
			}
			result.close();
			closeConnection();
			return publications;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			// TODO: handle exception
		}
	}
	public boolean getActivated(String username) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT activated FROM user_table WHERE username='" + username + "'";
			ResultSet result = statement.executeQuery(sql);
			int activated = 0;
			while(result.next()) {
				activated = result.getInt("activated");
			}
			result.close();
			closeConnection();
			if (activated==1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
			// TODO: handle exception
		}
	}
	public UserBean getUserInfo(String username) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM user_table WHERE username='" + username + "'";
			ResultSet result = statement.executeQuery(sql);
			UserBean userbean = new UserBean();
			while(result.next()) {
				userbean.setEmail(result.getString("email"));
				userbean.setUsername(result.getString("username"));
				userbean.setPassword(result.getString("password"));
				userbean.setFirstname(result.getString("firstname"));
				userbean.setLastname(result.getString("lastname"));
				userbean.setAddress(result.getString("address"));
				userbean.setCreditCard(result.getString("creditCard"));
				userbean.setDateOfBirth(result.getString("dateOfBirth"));
				userbean.setAdmin(result.getInt("admin"));
				userbean.setActivated(result.getInt("activated"));
				userbean.setConfirmationHash(result.getString("confirmationHash"));
			}
			result.close();
			closeConnection();
			return userbean;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			// TODO: handle exception
		}
	}
	public void updateUser(UserBean userbean) {
		
	}
	public void addPublication(PublicationBean publicationbean) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sqlPublication = "INSERT INTO publication_table VALUES " +
			"('" + publicationbean.getPublicationid() + "', '" + publicationbean.getType() + "', '" + publicationbean.getTitle() 
			+ "', '" + publicationbean.getYear() + "', '" + publicationbean.getPrice() + "')";
			String sqlAuthoredBy = "INSERT INTO authoredby_table VALUES " +
					"('" + publicationbean.getPublicationid() + "', '" + publicationbean.getAuthorid() + "')";
			statement.executeUpdate(sqlPublication);
			statement.executeUpdate(sqlAuthoredBy);
			closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

	
