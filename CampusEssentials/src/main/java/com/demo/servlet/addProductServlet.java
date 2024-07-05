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
import com.demo.service.ProductService;
import com.demo.service.ProductServiceImpl;

/**
 * Servlet implementation class addProductServlet
 */
@WebServlet("/addProduct")
public class addProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProductServlet() {
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
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user"); 
		
		if(user != null && user.getRole().equals("admin")) {	
			String pname = request.getParameter("pname");
			int qty = Integer.parseInt(request.getParameter("qty"));
			float price = Float.parseFloat(request.getParameter("price"));
			
			ProductService ps = new ProductServiceImpl();
			
			boolean result = ps.addProduct(pname, qty, price);
			
			if (result) {
				out.println("<script>alert(\"Product Added Successfully!!!\")</script>");
				RequestDispatcher rd = request.getRequestDispatcher("displayProduct");
				rd.forward(request, response);
			}
			else {
				out.println("<script>alert(\"Error. Please Try Again!!!\")</script>");
				RequestDispatcher rd = request.getRequestDispatcher("addProduct.html");
				rd.include(request, response);
			}
		}
		else {
			out.println("reached without login");
			RequestDispatcher rd= request.getRequestDispatcher("Login.html");
			rd.forward(request, response);
		}
	}

}
