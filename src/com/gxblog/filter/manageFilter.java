package com.gxblog.filter;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class manageFilter implements Filter{
      
	   protected String encoding=null;
	   protected FilterConfig config = null;
	  
       public void init(FilterConfig filterconfig){
    	     this.config = filterconfig;
    	   	 this.encoding = filterconfig.getInitParameter("encoding");
       }
       
       public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain){
    	   
	    	   HttpServletResponse response = (HttpServletResponse)resp; 
               if(encoding != null){
            	    response.setCharacterEncoding(encoding);
            	    response.setContentType("application/json; charset="+encoding);
               }else{
            	    response.setCharacterEncoding("UTF-8");
               }
               try {
				chain.doFilter(req, resp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

       }
       
       public void destroy(){
    	   this.config = null;
       }
       
}
