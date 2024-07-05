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

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate();
		response.setContentType("text/html");	
		PrintWriter out = response.getWriter();
		
		out.println("<html><body style=\"background: #ffcfcf;\">");
        out.println("<hr style=\"margin-top: 35px\"><h2 style=\"text-align:center; margin-top: 35px\">Thanks for visiting!!!<br>Redirecting to LOGIN!!!</h2>");
        out.println("</body></html>");

        // Set refresh header to redirect to login page after 3 seconds
        response.setHeader("Refresh", "3; URL=Login.html");
	}

}
