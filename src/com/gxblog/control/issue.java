package com.gxblog.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import java.io.PrintWriter;

import com.gxblog.dao.articleDAO;
import com.gxblog.entity.*;


public class issue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String title;
		   String author;
		   String content;
		   String username;
		   user user = null;
		   
		   title = request.getParameter("title");
		   author = request.getParameter("author");
		   content = request.getParameter("content");
		   user = (user)request.getSession().getAttribute("user");
		   username = user.getUsername();	   
		   
		   boolean valid = title==null && "".equals(title.trim()) && author==null && "".equals(author.trim())
				   && content==null && "".equals(content.trim());
		   JSONObject status = new JSONObject();
		   PrintWriter out = response.getWriter();
		   if(valid){	       
			       status.put("status", false);
			       out.print(status.toString());
			       return;
		   }else{
			       blog article = new blog(title,author,content,username);
			       if(!articleDAO.AddArticle(article)){
			    	   status.put("status", false);
			    	   out.print(status.toString());
			       }else{
			    	   status.put("status",true);
			    	   out.print(status.toString());
			       }
			       
		   }
		   
	}

}
