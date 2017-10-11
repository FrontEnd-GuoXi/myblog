package com.gxblog.dao;

import com.gxblog.db.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.sun.rowset.CachedRowSetImpl;

public class userDAO {
    
	 public static boolean UserSearch(String username, String password){
		 PreparedStatement pstmt = null;
		 boolean isExist = false;
		 
		 Connection conn = DBconn.getConn();
		 String UserSql = "select * from user where username =? and password=? ";
		 try {
			pstmt = conn.prepareStatement(UserSql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			isExist = pstmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}finally{
			DBclose.Close(pstmt,conn);
		}
		
		 
		 return isExist;
	 }
	
	public static CachedRowSetImpl UserInfo(String username){
		CachedRowSetImpl RowSet = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Connection conn = DBconn.getConn();
		String sql = "select name from user where username = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs =  pstmt.executeQuery();
			RowSet = new CachedRowSetImpl();
			RowSet.populate(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return RowSet;
	}
	
}
