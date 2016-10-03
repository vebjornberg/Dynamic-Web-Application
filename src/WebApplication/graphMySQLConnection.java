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
	public JSONArray graphStore() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			JSONArray array = new JSONArray();
			String sql ="SELECT * FROM graph_store"; 
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				JSONObject oJsonInner = new JSONObject();
				oJsonInner.put("source",result.getString("node_from"));
				oJsonInner.put("target",result.getString("node_to"));
				array.add(oJsonInner);
			}
			result.close();
			closeConnection();
			return array;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	public ArrayList<String> getSearchNodes(String search) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			String sql ="SELECT * FROM graph_store WHERE graph_store.node_from='" + search + "' OR "
					+ "graph_store.node_to='" + search + "'"; 
			ResultSet result = statement.executeQuery(sql);
			ArrayList<String> searchNodes = new ArrayList<String>();
			searchNodes.add(search);
			while(result.next()) {
				if (result.getString("node_from")!=search) {
					searchNodes.add(result.getString("node_from"));
				} else {
					searchNodes.add(result.getString("node_to"));
				}
			}
			result.close();
			closeConnection();
			return searchNodes;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	public JSONArray getNeighborNodes(String node) {
		try {
			establishConnection();
			Statement statement = connection.createStatement();
			JSONArray array = new JSONArray();
			String sql ="SELECT * FROM graph_store WHERE graph_store.node_from LIKE '" + node + "' OR "
					+ "graph_store.node_to LIKE '" + node + "'"; 
			ResultSet result = statement.executeQuery(sql);
			while(result.next()) {
				JSONObject oJsonInner = new JSONObject();
				oJsonInner.put("source",result.getString("node_from"));
				oJsonInner.put("target",result.getString("node_to"));
				array.add(oJsonInner);
			}
			result.close();
			closeConnection();
			return array;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}
	public JSONArray getSearchResults(String search) {
		try {
			ArrayList<String> searchNodes = new ArrayList<String>();
			searchNodes = getSearchNodes(search);
			JSONArray result = new JSONArray();
			if (search.equals("")) {
				result = graphStore();
			} else {
				for(int i=0; i<searchNodes.size(); i++) {
					JSONArray temp = new JSONArray();
					temp = getNeighborNodes(searchNodes.get(i));
					result.addAll(temp);
				}
			}
			return result;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}


}
