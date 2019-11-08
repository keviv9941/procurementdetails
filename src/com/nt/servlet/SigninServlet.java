package com.nt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SigninServlet extends HttpServlet {
	Connection cn;
	PreparedStatement ps;
	public void init() {
    	ServletContext ctx=getServletContext();
    	String driver=ctx.getInitParameter("driver");
    	String url=ctx.getInitParameter("url");
    	String user=ctx.getInitParameter("user");
    	String pwd=ctx.getInitParameter("pwd");
    	try {
    		Class.forName(driver);
    		cn=DriverManager.getConnection(url,user,pwd);
    		ps=cn.prepareStatement("select count(*) from user_register where uname=? and pwd=?");
    		}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	       String u=request.getParameter("t1");
	       String p=request.getParameter("t2");
	       PrintWriter out=response.getWriter();
	       try{
	    	   ps.setString(1, u);
	         ps.setString(2, p);
	         ResultSet rs=ps.executeQuery();
	         rs.next();
	         int c=rs.getInt(1);
	         if(c==1) {
	        	out.println("<html><body bgcolor=cyan><h2>");
	        	 out.println("Welcome to Registration ");
	        	 RequestDispatcher rd=request.getRequestDispatcher("registration.html");
	        	 rd.include(request,response);
	        	 out.println("</h2></body></html>");
	        			         }
	         else {
	        	 out.println("Invalid UserName and password");
	        	 RequestDispatcher rd=request.getRequestDispatcher("home.html");
	        	 rd.include(request,response);
	        	}
	       }
	       catch(Exception e) {
	    	   e.printStackTrace();
	    	   }
}

}
	
