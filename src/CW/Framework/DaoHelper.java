package CW.Framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DaoHelper {

	static final String DATABASE_URL="jdbc:mysql://localhost:3306/casaswilma";
	public Connection connection = null;
	
	public void getConnect(){
				
		 try {
			connection = DriverManager.getConnection(DATABASE_URL, "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	
	public void ReleaseAll(Statement stat, ResultSet result){
		try {
			if(connection!=null){
			connection.close();
			}
			if(stat!=null){
			stat.close();
			}
			if(result!=null){
			result.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
