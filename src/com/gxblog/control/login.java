package com.gxblog.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.gxblog.entity.user;
import com.gxblog.dao.*;
import org.json.simple.JSONObject;
import java.util.HashMap;
import com.sun.rowset.CachedRowSetImpl;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int i=0;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username;
        String password;
        boolean isUsername;
        boolean isPassword;
        boolean isExist;
        
        
        response.setContentType("charset=utf-8");
        PrintWriter out = response.getWriter();
        
        username = request.getParameter("username");
        password = request.getParameter("password");
        isUsername = username.matches("[a-zA-Z0-9]{6,8}");
        isPassword = password.matches("[a-zA-Z0-9]{6,8}");

        if(isUsername && isPassword){
        	isExist = userDAO.UserSearch(username,password);
        	if(isExist){
        		 user user = new user();
        		 user.setUsername(username);
        		 user.setPassword(password);
        		 
        		 HttpSession session = request.getSession();
        	     session.setAttribute("user", user);

        		 CachedRowSetImpl RowSet = userDAO.UserInfo(username);
         		 JSONObject LoginSuccess = new JSONObject();
        		 LoginSuccess.put("status", true);
        		 try {
        			RowSet.first();
					LoginSuccess.put("name", RowSet.getString(1));
				} catch (SQLException e) {
					e.printStackTrace();
				}
        		 out.println(LoginSuccess.toString());
        	}
        }else{
        	     JSONObject LoginDefeat = new JSONObject();
        	     LoginDefeat.put("status", false);
        	     LoginDefeat.put("HintInfo","’À∫≈∫Õ√‹¬Î≤ª∆•≈‰£°£°£°");
        	     out.println(LoginDefeat.toString());
        }
		
            
	}

}
