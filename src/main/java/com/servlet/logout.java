package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

public class logout extends HttpServlet {
	 
	  // Method to handle GET method request.
	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
	      // Set response content type
		  
	      Subject currentUser = SecurityUtils.getSubject();
	  if (currentUser.hasRole("tomcat")) {
	          System.out.println("We're authorized! :)");
	      } else {
	          System.out.println("We are not authorized :(");
	      }
		  
		  
	      System.out.println("Is Authenticated" + currentUser.isAuthenticated());
	      response.setContentType("text/html");

	      currentUser.logout();
	      response.sendRedirect("home.jsp");
	  }
	  // Method to handle POST method request.
	  public void doPost(HttpServletRequest request,
	                     HttpServletResponse response)
	      throws ServletException, IOException {
	     doGet(request, response);
	  }
	}