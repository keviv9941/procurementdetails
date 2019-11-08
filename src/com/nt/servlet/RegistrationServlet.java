package com.nt.servlet;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RegistrationServlet extends HttpServlet {
	public static  Date date = null;
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
    		ps=cn.prepareStatement("insert into procurement_details  values(?,?,?,?,?,?)");
    		}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    
		
		
	}
	
	
   
        
   
	
	
		
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int p=Integer.parseInt(request.getParameter("p1"));
	       String q=request.getParameter("p2");
	       String r=request.getParameter("p3");
	       int s=Integer.parseInt(request.getParameter("p4"));
	       int t=Integer.parseInt(request.getParameter("p5"));
	              String u= request.getParameter("p6");
	       
			 date = null;
			try {
				date = new SimpleDateFormat("yyyy/mm/dd").parse(u);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	       PrintWriter out=response.getWriter();
	       try{
	    	   ps.setInt(1, p);
	         ps.setString(2, q);
	       ps.setString(3, r);
	       ps.setInt(4, s);
	         ps.setInt(5,t);
	       
		ps.setDate(6,  (java.sql.Date) date);
	      // ps.setDate(6, date);
	       int c=ps.executeUpdate();
	       if(c==1) {
	    	//  RequestDispatcher rd=request.getRequestDispatcher("home.html");
	    	  //rd.include(request,response);
	    	   PreparedStatement ps1=ps=cn.prepareStatement("select  vendor_name,email  from vendor_details  join procurement_details using(vendor_id)");
	    	 ResultSet rs=ps1.executeQuery();
	    	out.println("thank you for registration");
	    	 
	    	   System.out.println("THANK YOU THE PROCUREMENT DETAILS WE SENT TO"+rs.getString(2)+"AND EMIL IS SENT TO"+rs.getString(3));
	       }
	       }
     catch(Exception e) {
     	e.printStackTrace();
     }
		
		
		
	}

}
