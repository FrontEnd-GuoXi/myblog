package com.gxblog.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.gxblog.entity.*;
import com.gxblog.dao.*;
import com.sun.rowset.CachedRowSetImpl;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.io.*; 
import java.util.HashMap;


public class manager extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String title;
           int id;
           boolean valid = true;
           
           PrintWriter out = response.getWriter();
           JSONArray  allInfo = new JSONArray();
           
		   HttpSession session = request.getSession(false);
		   user user = (user) session.getAttribute("user");
		   System.out.println("user_____"+user);
           CachedRowSetImpl RowSet = manageDAO.searchBlog(user.getUsername());
           try {
			RowSet.first();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
           
           for(int i=0; i<RowSet.size()&& valid; i++){
        	      JSONObject singleInfo = new JSONObject();
        	      try {
					 id = RowSet.getInt(1);
					 title = RowSet.getString(2);
					 singleInfo.put("id",id);
					 singleInfo.put("title",title);
					 allInfo.add(singleInfo);
					 valid = RowSet.next();
				} catch (SQLException e) {
					e.printStackTrace();
				}
           }
           response.setCharacterEncoding("UTF-8");
           out.print(allInfo.toString());
              
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
