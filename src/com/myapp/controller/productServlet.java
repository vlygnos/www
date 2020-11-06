package com.myapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myapp.dao.ProductDao;
import com.myapp.model.Product;

/**
 * Servlet implementation class productServlet
 */
@WebServlet( "/add")
public class productServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private ProductDao productDao= new ProductDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF\\views\\productEnter.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int barcode =Integer.parseInt(request.getParameter("barcode"));
		String colour = request.getParameter("colour");
		String description = request.getParameter("description");
		Product product = new Product();
		
		product.setName(name);
		product.setBarcode(barcode);
		product.setColour(colour);
		product.setDescription(description);
		
		try {
			productDao.addProduct(product);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		RequestDispatcher dispatcher;
		if(productDao.added) 
			 dispatcher = request.getRequestDispatcher("WEB-INF\\views\\productOK.jsp");
		else
			 dispatcher = request.getRequestDispatcher("WEB-INF\\views\\productNOT.jsp");
		
		dispatcher.forward(request, response);
		
		
	}

}
