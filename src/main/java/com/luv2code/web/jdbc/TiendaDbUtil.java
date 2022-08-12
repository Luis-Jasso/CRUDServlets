package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

public class TiendaDbUtil {

	private DataSource dataSource;

	public TiendaDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Producto> getProducts() throws Exception {

		List<Producto> products = new ArrayList<>();
		String sql = "select * from productos";
		ResultSet myRs = null;

		try (Connection myConn = dataSource.getConnection(); Statement myStmt = myConn.createStatement();) {
			// get a connection
			// create sql statement
			// execute query
			// process result set

			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("nombre");
				String descripcion = myRs.getString("descripcion");
				int stock = myRs.getInt("stock");

				// create new student object
				Producto tempProduct = new Producto(id, name, descripcion, stock);

				// add it to the list of students
				products.add(tempProduct);
			}

			return products;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close(); // doesn't really close it ... just puts back in connection pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addProduct(Producto theProduct) throws Exception {

		// create sql for insert
		String sql = "insert into productos " + "(nombre, descripcion, stock) " + "values (?, ?, ?)";
		PreparedStatement myStmt = null;

		// get db connection
		try (Connection myConn = dataSource.getConnection();) {

			// set the param values for the student

			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, theProduct.getName());
			myStmt.setString(2, theProduct.getDescripcion());
			myStmt.setInt(3, theProduct.getStock());

			// execute sql insert
			myStmt.execute();

		} finally {
			// clean up JDBC objects
			close(null, myStmt, null);
		}
	}

	public Producto getProduct(String theProductId) throws Exception {

		Producto theProduct = null;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		int productId;

		try (Connection myConn = dataSource.getConnection();) {

			if (!theProductId.isEmpty()) {
				productId = Integer.parseInt(theProductId);
			} else {
				productId = 1;
			}
			// convert student id to int

			// create sql to get selected student

			String sql = "select * from productos where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, productId);
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				String nombre = myRs.getString("nombre");
				String descripcion = myRs.getString("descripcion");
				int stock = myRs.getInt("stock");

				// use the studentId during construction
				theProduct = new Producto(productId, nombre, descripcion, stock);
			} else {
				throw new Exception("Could not find product id: " + productId);
			}
			

			return theProduct;
		} finally {
			// clean up JDBC objects
			close(null, myStmt, null);
		}
	}

	public void updateProduct(Producto theProduct) throws Exception {

		// create SQL update statement
		String sql = "update productos " + "set nombre=?, descripcion=?, stock=? " + "where id=?";

		PreparedStatement myStmt = null;

		try (Connection myConn = dataSource.getConnection();) {
			// get db connection

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theProduct.getName());
			myStmt.setString(2, theProduct.getDescripcion());
			myStmt.setInt(3, theProduct.getStock());
			myStmt.setInt(4, theProduct.getId());

			// execute SQL statement
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(null, myStmt, null);
		}
	}

	public void deleteProduct(String theProductId) throws Exception {

		// create sql to delete product
		String sql = "delete from productos where id=?";

		PreparedStatement myStmt = null;

		try (Connection myConn = dataSource.getConnection();) {
			// convert product id to int
			int productId = Integer.parseInt(theProductId);

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, productId);

			// execute sql statement
			myStmt.execute();
		} finally {
			// clean up JDBC code
			close(null, myStmt, null);
		}
	}
}
