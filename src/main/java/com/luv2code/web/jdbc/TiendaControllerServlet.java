package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/TiendaControllerServlet")
public class TiendaControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TiendaDbUtil tiendaDbUtil;

	@Resource(name = "jdbc/web_student_tracker")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our student db util ... and pass in the conn pool / datasource
		try {
			tiendaDbUtil = new TiendaDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}

			// route to the appropriate method
			switch (theCommand) {

			case "LIST":
				listProducts(request, response);
				break;

			case "ADD":
				addStudent(request, response);
				break;

			case "LOAD":
				loadProduct(request, response);
				break;

			case "UPDATE":
				updateStudent(request, response);
				break;

			case "DELETE":
				deleteProduct(request, response);
				break;

			default:
				listProducts(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student id from form data
		String theproductId = request.getParameter("productId");

		// delete student from database
		tiendaDbUtil.deleteProduct(theproductId);

		// send them back to "list students" page
		listProducts(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		int id = Integer.parseInt(request.getParameter("productId"));
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		int stock = Integer.parseInt(request.getParameter("stock"));

		// create a new student object
		Producto theProduct = new Producto(id, nombre, descripcion, stock);

		// perform update on database
		tiendaDbUtil.updateProduct(theProduct);

		// send them back to the "list students" page
		listProducts(request, response);

	}

	private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student id from form data
		String theProductId = request.getParameter("productId");

		// get student from database (db util)
		Producto theProduct = tiendaDbUtil.getProduct(theProductId);

		// place student in the request attribute
		request.setAttribute("THE_PRODUCT", theProduct);

		// send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-product-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read student info from form data
		String name = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		int stock = Integer.parseInt(request.getParameter("stock"));

		// create a new student object
		Producto theProduct = new Producto(name, descripcion, stock);

		// add the student to the database
		tiendaDbUtil.addProduct(theProduct);

		// send back to main page (the student list)
		listProducts(request, response);
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get students from db util
		List<Producto> products = tiendaDbUtil.getProducts();

		// add students to the request
		request.setAttribute("PRODUCT_LIST", products);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-products.jsp");
		dispatcher.forward(request, response);
	}

}
