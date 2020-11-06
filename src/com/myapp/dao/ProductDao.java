package com.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myapp.model.Product;

public class ProductDao {
	public boolean added;
	
	public void addProduct(Product product) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO product" +
            "  (barcode, name, colour, description) VALUES " +
            " (?, ?, ?, ?);";

        //int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/products?serverTimezone=UTC&useSSL=false", "root", "1234");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, product.getBarcode());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getColour());
            preparedStatement.setString(4, product.getDescription());
            
           
            if(searchProduct(connection,product.getBarcode())) {

	            //System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	           preparedStatement.executeUpdate();
	           added=true;
            }
            else 
            	added=false;

            
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }
	public boolean searchProduct(Connection con,int barcode) throws SQLException {
	    String query = "select barcode from product";
	    java.sql.Statement stmt =con.createStatement(); 
	    ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	      while (rs.next()) {
	        int barcodeCheck = rs.getInt("barcode");
	        
	        if(barcode==barcodeCheck) {
	        	return false;
	        }
	        //System.out.println(barcodeCheck);
	      }
	      return true;
	    
	  }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
