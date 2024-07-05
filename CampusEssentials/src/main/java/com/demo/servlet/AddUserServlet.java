package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.beans.User;
import com.demo.service.UserService;
import com.demo.service.UserServiceImpl;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String uname = request.getParameter("uname");
		String role = request.getParameter("role");
		String pass = request.getParameter("pass");
		
		User newUser = new User(uname, pass, role);
		
		UserService us = new UserServiceImpl();
		
		boolean status = us.addUser(newUser);
		
		if (status) {
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			
			try {
		           // Introduce a delay of 1 second (1000 milliseconds)
		           Thread.sleep(1000);
		    } catch (InterruptedException e) {
		           e.printStackTrace();
		    }
			rd.forward(request, response);
		} 
		else {
			RequestDispatcher rd = request.getRequestDispatcher("register.html");
			
			out.print("<script>alert(\"Please Try Again!!!\")</script>");
			rd.include(request, response);
		}
	}

}
