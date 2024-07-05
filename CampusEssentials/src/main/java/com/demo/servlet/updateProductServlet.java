package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.beans.Product;
import com.demo.service.ProductService;
import com.demo.service.ProductServiceImpl;

/**
 * Servlet implementation class updateProductServlet
 */
@WebServlet("/updateProduct")
public class updateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProductServlet() {
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
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		String pname = request.getParameter("pname");
		int qty = Integer.parseInt(request.getParameter("qty"));
		double price = Double.parseDouble(request.getParameter("price"));
		
		Product up = new Product(pid, pname, qty, price);
		
		ProductService ps = new ProductServiceImpl();
		
		boolean status = ps.updateProduct(up);
		
		if(status) {
			RequestDispatcher rd = request.getRequestDispatcher("displayProduct");
			out.println("<script>alert(\"Product Updated Successfully\")</script>");
			rd.include(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("displayProduct");
			out.println("<script>alert(\"Error Please Try Again!!!\")</script>");
			rd.include(request, response);
		}
	}

}
