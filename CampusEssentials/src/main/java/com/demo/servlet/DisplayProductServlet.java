package com.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.beans.Product;
import com.demo.beans.User;
import com.demo.service.ProductService;
import com.demo.service.ProductServiceImpl;

/**
 * Servlet implementation class DisplayProductServlet
 */
@WebServlet("/displayProduct")
public class DisplayProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayProductServlet() {
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
			ProductService ps = new ProductServiceImpl();
			
			// retrieve all products from the database, and display all in the form of table
			// also display a link for add new product below the table
			List<Product> plist = ps.getAllProducts();
			out.println("<body style=\"background-image: linear-gradient(to top, #ff464d, #ffa2a5);\">");
			out.println("<script>function confirmDelete(pid) {");
			out.println("if (confirm(\"Are you sure you want to delete this product?\"))");
			out.println("{window.location.href = 'deleteProduct?pid=' + pid;}}\n");
			out.println("function editProduct(pid){window.location.href = 'editProduct?pid=' + pid;}</script>");
			out.println("<h1>Welcome,  " + user.getUname().toUpperCase() + "</h1>");
			out.println("<hr>");
			out.println("<div style=\"text-align:right\"><button onclick=\"location.href = 'addProduct.html'\" style=\"padding:10px; margin:10px; border:none; border-radius:10px; background: white; color: black cursor: pointer;\")>Add Product</button>&nbsp;&nbsp;");
			out.println("<button onclick=\"location.href = 'logout'\" style=\"padding:10px; margin:10px; border:none; border-radius:10px; background:#353535; color: wheat; cursor: pointer;\")>Logout</button></div>");
			out.println("<hr style=\"margin-bottom: 30px;\">");
			
			if (!plist.isEmpty()) {
				out.println("<table border=\"2\" style=\"width:80%; margin: 20px auto; font-size: 25px; padding:10px\"><tr><th style=\"width:10%;\">ProductID</th><th>Name</th><th>Qty</th><th>Price</th><th style=\"width:200px;\">Action</th></tr>");
				for(Product p : plist) {
					out.println("<tr style=\" text-align:center; font-size: 20px;\"><td style=\"width:10%; height:50px;\">" + p.getPid() + "</td><td>" + p.getPname() + "</td><td>" + p.getQty() + "</td><td>"+ p.getPrice() +" $</td>");
					out.println("<td style=\"width:20%;  height:50px; text-align:center;\"><button onclick=\"confirmDelete(" + p.getPid() + ");\" style=\"padding:10px; margin:10px; width:70px; border:none; border-radius: 20px; background: crimson; color: white; cursor: pointer;\")>Delete</button>");
					out.println("<button onclick=\"location.href = 'editProduct?pid=' + " + p.getPid()+ ";\" style=\"padding:10px; margin:10px; width:70px; border:none; border-radius: 20px; background: white; color: black; cursor: pointer;\")>Edit</button></td></tr>");
				}
			}
			else {
				out.println("<h4>No Products</h4>");
			}	
			out.println("</body>");
		}
		else {
			out.println("reached without login");
			RequestDispatcher rd= request.getRequestDispatcher("Login.html");
			rd.forward(request, response);
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
