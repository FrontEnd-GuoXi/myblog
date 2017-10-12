package com.gxblog.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NotLogin implements Filter {


	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String currentURL = req.getRequestURI();
		String targetURL = currentURL.substring(currentURL.indexOf("/",1),currentURL.length());
		
		if( !"/login".equals(targetURL) ){
			HttpSession session = req.getSession(false);
			if(session == null || session.getAttribute("user") == null){
				System.out.println(req.getContextPath()+"/login.html-------------------------------");
				  res.sendRedirect(req.getContextPath()+"/login.html");
				  return;
			}
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
