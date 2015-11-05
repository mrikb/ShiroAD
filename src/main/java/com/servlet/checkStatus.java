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

public class checkStatus extends HttpServlet {
	 
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

	      PrintWriter out = response.getWriter();
		  String title = "Login status";
	      String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	      "transitional//en\">\n";
	      out.println(docType +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1 align=\"center\">" + title + "</h1>\n" +
	                "<ul>\n" +
	                "  <li><b>Session Active </b>: "
	                + currentUser.isAuthenticated() + "\n" +
	                "</ul>\n" +
	                "</body></html>");
	  }
	  // Method to handle POST method request.
	  public void doPost(HttpServletRequest request,
	                     HttpServletResponse response)
	      throws ServletException, IOException {
	     doGet(request, response);
	  }
	}