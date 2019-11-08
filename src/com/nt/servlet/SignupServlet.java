package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SignupServlet extends HttpServlet {
	Connection cn;
	PreparedStatement ps;
	public void init(){
		ServletContext ctx=getServletContext();
		String driver=ctx.getInitParameter("driver");
		String url=ctx.getInitParameter("url");
		String user=ctx.getInitParameter("user");
		String pwd=ctx.getInitParameter("pwd");
		try {
			Class.forName(driver);
    		cn=DriverManager.getConnection(url,user,pwd);
    		ps=cn.prepareStatement("insert into user_register values(?,?,?)");
    		}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    
		
		
	}
	
	
   
        
   
	
	
		
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String n=request.getParameter("t1");
	       String u=request.getParameter("t2");
	       String p=request.getParameter("t3");
	       PrintWriter out=response.getWriter();
	       try{
	    	   ps.setString(1, n);
	         ps.setString(2, u);
	       ps.setString(3, p);
	       int c=ps.executeUpdate();
	       if(c==1) {
	    	   RequestDispatcher rd=request.getRequestDispatcher("home.html");
	    	  rd.include(request,response);
	    	   out.println("Registration successsfull");
	       }
	       }
     catch(Exception e) {
     	e.printStackTrace();
     }
		
		
		
	}

}
