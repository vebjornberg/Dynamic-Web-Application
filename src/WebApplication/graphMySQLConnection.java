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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mysql.jdbc.Driver;


public class graphMySQLConnection {
	
	static final String jdbcDriver = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql://mysqlapplicationdb.cvyzbw5chrfk.ap-southeast-2.rds.amazonaws.com:3306/webApplicationdb";
	static final String username = "vebjorbe";
	static final String password = "manchester";
	
	static DataSource datasource = null;
	static Connection connection = null;
	
	ArrayList<JSONObject> graph = new ArrayList<JSONObject>();
	

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
	public ArrayList<JSONObject> graphStore() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			ArrayList<JSONObject> graph = new ArrayList<JSONObject>();
			String sql ="SELECT * FROM graph_store"; 
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				JSONObject oJsonInner = new JSONObject();
				JSONArray arr = new JSONArray();
				oJsonInner.put("source",result.getString("node_from"));
				oJsonInner.put("target",result.getString("node_to"));
				graph.add(oJsonInner);
			}
			result.close();
			closeConnection();
			System.out.println(graph);
			return graph;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		graphMySQLConnection con = new graphMySQLConnection();
		con.graphStore();
	}

}
