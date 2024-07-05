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

import com.demo.beans.Product;
import com.demo.beans.User;
import com.demo.service.ProductService;
import com.demo.service.ProductServiceImpl;

/**
 * Servlet implementation class editProductServlet
 */
@WebServlet("/editProduct")
public class editProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(user!=null && user.getRole().equals("admin")) {
			int pid = Integer.parseInt(request.getParameter("pid"));
			
			ProductService  ps = new ProductServiceImpl();
			Product product = ps.getById(pid);
			
			out.println("<body style=\"background-image: linear-gradient(to top, #ff464d, #ffa2a5);\">");
			out.println("<h1>Welcome, " + user.getUname().toUpperCase() + "</h1>");
			out.println("<hr style='margin-bottom: 15px;'>");
			out.println("<form action=\"displayProduct\" method=\"post\"><div style=\"text-align: right; display: flex; justify-content: end;\">");
			out.println("<button type=\"submit\" style='width:70px; padding:10px; border-radius:15px; border:none;'>Back</button></div></form>");
			out.println("<hr>");
			out.println("<div style=\"display:flex; justify-content:center; height:80vh; align-items:center;\">");
			out.println("<form action='updateProduct' method='post' style='text-align: right; font-size: 20px'>");
			out.println("Product Id: <input type='text' name='pid' id='pid' style='margin:20px; border:none; padding:5px; height:40px; border-radius:10px;' value='" + product.getPid() + "' readonly><br>");
			out.println(" Product Name:<input type='text' name='pname' id='pname' style='margin:20px; border:none; padding:5px; height:40px; border-radius:10px;' value='" + product.getPname() + "'><br>");
			out.println("Quantity : <input type='number' name='qty' id='qty' min='0' style='margin:20px; border:none; padding:5px; height:40px; border-radius:10px;' value='" + product.getQty() + "'><br>");
			out.println(" Price: <input type='number' name='price' id='price' step=\"0.01\" min='0' style='margin:20px; border:none; padding:5px; height:40px; border-radius:10px;' value='" + product.getPrice() + "'><br>");
			out.println("<button type='submit' name='btn' id='btn' style='margin-right:85px; padding:10px; border-radius:10px; border:none;'>Update Product</button>");
			out.println("</form></div>");
			
		}
		else {
			System.out.println("reached without login");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			out.println("<script>alert(\"Please enter credentials!!\")</script>");
			rd.include(request, response);
		}
	}

}
