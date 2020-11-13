package Util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Entity.Product;
/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
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
		
		try {
			Product newProduct = new Product(name, barcode, colour, description);
			

			SessionFactory factory = new Configuration().configure()
									.addAnnotatedClass(Product.class)
									.buildSessionFactory();
			
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.save(newProduct);
			RequestDispatcher dispatcher;
			
			try {
				// commit the transaction
				session.getTransaction().commit();
			}catch(javax.persistence.PersistenceException exc) {
				dispatcher = request.getRequestDispatcher("WEB-INF\\views\\productNOT.jsp");
				dispatcher.forward(request, response);
				factory.close();
				return;	
			}
			factory.close();
			dispatcher = request.getRequestDispatcher("WEB-INF\\views\\productOK.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e){
			System.out.println("An error occurred while connecting MySQL databse");
		}
	}

}
