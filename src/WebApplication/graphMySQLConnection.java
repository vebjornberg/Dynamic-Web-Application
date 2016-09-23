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

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.mysql.jdbc.Driver;


public class graphMySQLConnection {
	
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
	public ArrayList<GraphBean> entityStore() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql ="SELECT * FROM entity_store"; 
			ResultSet result = statement.executeQuery(sql);
			ArrayList<GraphBean> edgeEntities = new ArrayList<GraphBean>();
			while(result.next()) {
				GraphBean graphEntityBean = new GraphBean();
				graphEntityBean.setNode_from(result.getString("entity_id"));
				graphEntityBean.setEdge(result.getString("entity_attribute"));
				graphEntityBean.setNode_to(result.getString("attribute_value"));
				edgeEntities.add(graphEntityBean);
			}
			result.close();
			closeConnection();
			return edgeEntities;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	
	public ArrayList<GraphBean> graphStore() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql ="SELECT * FROM graph_store"; 
			ResultSet result = statement.executeQuery(sql);
			ArrayList<GraphBean> edgeEntities = new ArrayList<GraphBean>();
			while(result.next()) {
				GraphBean graphEntityBean = new GraphBean();
				graphEntityBean.setNode_from(result.getString("node_from"));
				graphEntityBean.setEdge(result.getString("edge"));
				graphEntityBean.setNode_to(result.getString("node_to"));
				edgeEntities.add(graphEntityBean);
			}
			result.close();
			closeConnection();
			return edgeEntities;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

}
