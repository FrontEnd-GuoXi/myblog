package com.gxblog.dao;

import com.sun.rowset.CachedRowSetImpl;
import com.gxblog.entity.*;
import com.gxblog.db.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class manageDAO {
	
	       
          public static CachedRowSetImpl searchBlog(String username){
        	  
        	  CachedRowSetImpl RowSet = null;
        	  PreparedStatement pstmt = null;
        	  ResultSet rs = null;
        	  String sql = "select id, title from blog where username = ?";
        	  Connection conn = DBconn.getConn();
        	  
        	  try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				RowSet = new CachedRowSetImpl();
				RowSet.populate(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBclose.Close(pstmt, rs ,conn);
			}
        	  return RowSet;
          } 
}
