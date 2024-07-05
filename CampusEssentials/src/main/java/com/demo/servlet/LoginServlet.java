package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.beans.User;
import com.demo.service.LoginService;
import com.demo.service.LoginServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/validate")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		
		LoginService ls = new LoginServiceImpl();
		
		User user = ls.validateUser(uname, pass);
		
		System.out.println(user);
		
		if (user != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("user", user);
			
			if(user.getRole().equals("admin")) {
				RequestDispatcher rd = request.getRequestDispatcher("displayProduct");
				rd.forward(request, response);
			}
			else {
				out.println("Error: Login as ADMIN");
			}
		}
		else {
			out.println("<script>alert(\"Wrong Credentials!!!\")</script>");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
		}
		
	}

}
