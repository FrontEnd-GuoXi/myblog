package com.gxblog.dao;

import com.sun.rowset.CachedRowSetImpl;
import com.gxblog.entity.*;
import com.gxblog.db.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class articleDAO {
	   	    
	  public static boolean AddArticle(blog article){

		   Connection conn = null;
		   PreparedStatement pstmt = null;
		   String sql = null;
		   boolean whether = false;
           
		   conn = DBconn.getConn();
		   sql = "insert into blog (title,content,author,username) values(?,?,?,?)";
		   try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setString(2, article.getContent());
			pstmt.setString(3, article.getAuthor());
			pstmt.setString(4, article.getUsername());
			whether = pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBclose.Close(pstmt, conn);		   
		}
		    return whether;
	   }

	 public static CachedRowSetImpl showArticle(String username,int start , int end){
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    String sql = null;
		    CachedRowSetImpl RowSet = null;
		    ResultSet rs = null;
		    
		    conn = DBconn.getConn();
		    sql = "select * from blog where username = ? limit ? , ?";
		    try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,username);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
				RowSet = new CachedRowSetImpl();
				RowSet.populate(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		 return RowSet;
	 }
	
}
