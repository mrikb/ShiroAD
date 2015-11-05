package com.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

// Extend HttpServlet class
public class HelloForm extends HttpServlet {
 
  // Method to handle GET method request.
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
	  
	  UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("first_name"), request.getParameter("last_name"));
      Subject currentUser = SecurityUtils.getSubject();
	  System.out.println("Here " + currentUser);
	  try {
          currentUser.login(token);
          System.out.println("We've authenticated! :)");
      } catch (UnknownAccountException ex) {
      	System.out.println("Unknown user");
      } catch (IncorrectCredentialsException ex) {
      	System.out.println("Incorrect credentials");
      } catch (LockedAccountException ex) {
      	System.out.println("Account is Locked");
      } catch (AuthenticationException ex) {
      	System.out.println("Authentication Exception");
      	ex.printStackTrace();
      }

	  if (currentUser.hasRole("tomcat")) {
          System.out.println("We're authorized! :)");
      } else {
          System.out.println("We are not authorized :(");
      }
	  Session session = currentUser.getSession();
//	  session.setTimeout(10000);
      System.out.println("roles" + session.getTimeout());
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
	  String title = "Shiro Status";
      String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
      out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>First Name</b>: "
                + request.getParameter("first_name") + "\n" +
                "  <li><b>Authenticated</b>: "
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