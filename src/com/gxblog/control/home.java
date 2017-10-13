package com.gxblog.control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.rowset.CachedRowSetImpl;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import com.gxblog.dao.articleDAO;

public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
             String username = request.getParameter("username");
             int start = Integer.parseInt(request.getParameter("start"));
             int end = Integer.parseInt(request.getParameter("end"));
             HttpSession session = null;
             
             PrintWriter out = response.getWriter();
             
             CachedRowSetImpl RowSet = articleDAO.showArticle(username, start, end);
             

    	     session = request.getSession();
    	     JSONArray ArticleArr = (JSONArray) session.getAttribute("ArticleArr");
 
             
             try {
				RowSet.first();
	            for(int i=0; i<RowSet.size(); i++){
          	           JSONObject ArticleObj = new JSONObject();
          	           ArticleObj.put("id", RowSet.getInt(1));
          	           ArticleObj.put("title",RowSet.getString(2));
          	           ArticleObj.put("content",RowSet.getString(3));
          	           ArticleObj.put("time",RowSet.getDate(4));
          	           ArticleObj.put("author",RowSet.getString(5));
                       
          	           ArticleArr.add(ArticleObj);
          	           RowSet.next();
	             }
	            out.println(ArticleArr.toString());    
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
