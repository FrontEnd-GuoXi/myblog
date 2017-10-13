package com.gxblog.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;

public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event){
		   HttpSession session = event.getSession();
		   JSONArray ArticleArr = new JSONArray();
		   session.setAttribute("ArticleArr", ArticleArr);
	}
	
	public void sessionDestroyed(HttpSessionEvent event){
		
	}
	
}
