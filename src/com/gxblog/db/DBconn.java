package com.gxblog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconn {

	public static Connection getConn(){
	 	Connection conn = null;
	 	String user ="root";
		String password = "1206189299";
		String url = "jdbc:mysql://localhost:3306/myblog";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return conn;
		   
	}
	
}
