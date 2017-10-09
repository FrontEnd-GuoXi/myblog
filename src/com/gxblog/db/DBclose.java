package com.gxblog.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBclose {
        public static void Close(PreparedStatement pstmt, ResultSet rs, Connection conn ){
        	   try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        
        public static void Close(PreparedStatement pstmt, Connection conn){
        	try{
        		pstmt.close();
        		conn.close();
        	}catch(SQLException e){
        		e.printStackTrace();
        	}
        }
}
