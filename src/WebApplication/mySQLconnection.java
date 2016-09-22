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
					+ "', '" + userbean.getDateOfBirth() + "', 0, '" + userbean.getPassword() + "', 0, '" + userbean.getConfirmationHash() + "', 0)";
			statement.executeUpdate(sql);
			System.out.println(sql);
			closeConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
				publicationbean.setSale(result.getInt("sale"));
				publicationbean.setNumsold(result.getInt("numsold"));
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
				userbean.setBanned(result.getInt("banned"));
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
	public UserBean getUserInfoFromEmail(String email) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM user_table WHERE email='" + email + "'";
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
				userbean.setBanned(result.getInt("banned"));
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
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "UPDATE user_table SET user_table.email='" + userbean.getEmail() + "', user_table.username='" + userbean.getUsername() +"', "
					+ "user_table.firstname='" + userbean.getFirstname() + "', user_table.lastname='" + userbean.getLastname() + "', "
					+ "user_table.address='" + userbean.getAddress() + "', user_table.creditCard='" + userbean.getCreditCard() + "', "
					+ "user_table.dateOfBirth='" + userbean.getDateOfBirth() + "', user_table.admin=" + userbean.getAdmin()
					+ ", user_table.password='" + userbean.getPassword() + "', user_table.activated=" + userbean.getActivated()
					+ ", user_table.confirmationHash='" + userbean.getConfirmationHash() + "', " + "user_table.banned=" + userbean.getBanned() + " "
					+ "WHERE user_table.username='" + userbean.getUsername() + "'";
			statement.executeUpdate(sql);
			System.out.println(sql);
			closeConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void addPublication(PublicationBean publicationbean) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sqlPublication = "INSERT INTO publication_table (publication_table.type, publication_table.title, publication_table.year, publication_table.price, publication_table.sale, publication_table.numsold) VALUES " +
			"('" + publicationbean.getType() + "', '" + publicationbean.getTitle() 
			+ "', '" + publicationbean.getYear() + "', '" + publicationbean.getPrice() + "', 1, 0)";
			String sqlAuthoredBy = "INSERT INTO authoredby_table (authoredby_table.authorid) VALUES " +
					"(" + publicationbean.getAuthorid() + ")";
			statement.executeUpdate(sqlPublication);
			statement.executeUpdate(sqlAuthoredBy);
			closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ArrayList<AuthorBean> getAuthors() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT author_table.* "
					+ "FROM author_table";
			ResultSet result = statement.executeQuery(sql);
			ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();
			while(result.next()) {
				AuthorBean authorbean = new AuthorBean();
				authorbean.setAuthorid(result.getInt("authorid"));
				authorbean.setFirstname(result.getString("firstname"));
				authorbean.setLastname(result.getString("lastname"));
				authors.add(authorbean);
			}
			result.close();
			closeConnection();
			return authors;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			// TODO: handle exception
		}
	}
	public ArrayList<AuthorBean> getAuthorsBySearch(String search) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT author_table.* "
					+ "FROM author_table "
					+ "WHERE author_table.firstname LIKE '%" + search + "%' OR author_table.lastname LIKE '%" + search +"%'";
			ResultSet result = statement.executeQuery(sql);
			ArrayList<AuthorBean> authors = new ArrayList<AuthorBean>();
			while(result.next()) {
				AuthorBean authorbean = new AuthorBean();
				authorbean.setAuthorid(result.getInt("authorid"));
				authorbean.setFirstname(result.getString("firstname"));
				authorbean.setLastname(result.getString("lastname"));
				authors.add(authorbean);
			}
			result.close();
			closeConnection();
			return authors;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			// TODO: handle exception
		}
	}
	public ArrayList<PublicationBean> getPublicationsAdvanced(String type, String title, String firstname, String lastname, String year) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT publication_table.*, author_table.* "
					+ "FROM publication_table INNER JOIN authoredby_table "
					+ "ON authoredby_table.publicationid = publication_table.publicationid "
					+ "INNER JOIN author_table ON author_table.authorid = authoredby_table.authorid "
					+ "WHERE publication_table.type LIKE '%" + type + "%' AND publication_table.title LIKE '%" + title +"%' AND "
					+ "publication_table.year LIKE '%"+ year + "%' AND "
					+ "author_table.firstname LIKE '%" + firstname + "%' AND author_table.lastname LIKE '%" + lastname + "%'";
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
				publicationbean.setSale(result.getInt("sale"));
				publicationbean.setNumsold(result.getInt("numsold"));
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
	public void updatePublicationBean(PublicationBean publicationbean) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "UPDATE publication_table SET publication_table.publicationid=" + publicationbean.getPublicationid() + ", publication_table.type='" + publicationbean.getType() +"', "
					+ "publication_table.title='" + publicationbean.getTitle() + "', publication_table.year='" + publicationbean.getYear() + "', "
					+ "publication_table.price='" + publicationbean.getPrice() + "', publication_table.sale=" + publicationbean.getSale() + ", publication_table.numsold=" + publicationbean.getNumsold() + " "
					+ "WHERE publication_table.publicationid=" + publicationbean.getPublicationid();
			statement.executeUpdate(sql);
			System.out.println(sql);
			closeConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void addAuthor(String firstname, String lastname) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sqlAuthor = "INSERT INTO author_table (author_table.firstname, author_table.lastname) VALUES " +
			"('" + firstname + "', '" + lastname + "')";
			statement.executeUpdate(sqlAuthor);
			closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public int getAuthorIdByName(String firstName, String lastName) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT author_table.authorid FROM author_table WHERE author_table.firstname='" + firstName + "' AND author_table.lastname='" + lastName + "'";
			ResultSet result = statement.executeQuery(sql);
			int id = 0;
			while(result.next()) {
				id = result.getInt("authorid");
			}
			result.close();
			closeConnection();
			return id;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
			// TODO: handle exception
		}
	}
	public ArrayList<PublicationBean> getCart(String username) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT cart_table.* "
					+ "FROM cart_table "
					+ "WHERE cart_table.username='" + username + "'";
			ResultSet result = statement.executeQuery(sql);
			ArrayList<PublicationBean> cart = new ArrayList<PublicationBean>();
			while(result.next()) {
				int publicationid = result.getInt("publicationid");
				PublicationBean publicationbean = new PublicationBean();
				publicationbean = getPublicationById(publicationid);
				cart.add(publicationbean);
			}
			result.close();
			closeConnection();
			return cart;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			// TODO: handle exception
		}
	}
	public PublicationBean getPublicationById(int publicationid) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT publication_table.*, author_table.* "
					+ "FROM publication_table INNER JOIN authoredby_table "
					+ "ON authoredby_table.publicationid = publication_table.publicationid "
					+ "INNER JOIN author_table ON author_table.authorid = authoredby_table.authorid "
					+ "WHERE publication_table.publicationid=" + publicationid + "";
			ResultSet result = statement.executeQuery(sql);
			PublicationBean publicationbean = new PublicationBean();
			while(result.next()) {
				publicationbean.setAuthorid(result.getInt("authorid"));
				publicationbean.setPublicationid(result.getInt("publicationid"));
				publicationbean.setType(result.getString("type"));
				publicationbean.setYear(result.getString("year"));
				publicationbean.setPrice(result.getString("price"));
				publicationbean.setFirstname(result.getString("firstname"));
				publicationbean.setLastname(result.getString("lastname"));
				publicationbean.setTitle(result.getString("title"));
				publicationbean.setSale(result.getInt("sale"));
				publicationbean.setNumsold(result.getInt("numsold"));
			}
			result.close();
			closeConnection();
			return publicationbean;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
			// TODO: handle exception
		}
	}
	public void addCart(String username, ArrayList<PublicationBean> cart) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			for(int i=0; i<cart.size(); i++) {
				String sqlCart = "INSERT INTO cart_table (cart_table.username, cart_table.publicationid) VALUES "
						+ "('" + username + "', " + cart.get(i).getPublicationid() + ")";
				statement.executeUpdate(sqlCart);
			}
			closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

	
