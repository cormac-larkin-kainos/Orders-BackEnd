package org.kainos.ea.db;

import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.cli.ProductResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    /**
     * Gets all products from the database
     *
     * @return A list of all Products in the database
     * @throws SQLException If a database error occurs
     */
    public List<ProductResponse> getAllProducts() throws SQLException {

        // Establish a database connection
        Connection connection = DatabaseConnector.getConnection();
        if (connection == null) {
            throw new SQLException("Database connection failed");
        }

        // Query the database for all products
        String selectQuery = "SELECT product_id, product.name AS name, description, price, supplier_id, " +
                "supplier.name AS supplier_name FROM product INNER JOIN supplier " +
                "USING(supplier_id)";
        ResultSet result = connection.prepareStatement(selectQuery).executeQuery();

        // Add all products to a list and return the list
        List<ProductResponse> allProducts = new ArrayList<>();
        while (result.next()) {
            ProductResponse product = new ProductResponse(
                    result.getInt("product_id"),
                    result.getString("name"),
                    result.getString("description"),
                    result.getDouble("price"),
                    result.getString("supplier_name"),
                    result.getInt("supplier_id")
            );

            allProducts.add(product);
        }

        return allProducts;
    }

    /**
     * Gets a product with a specified product ID from the database
     *
     * @param productID The ID of the product to retrieve
     * @return A ProductResponse object to represent the retrieved product
     * @throws SQLException If a database error occurred
     */
    public ProductResponse getProductByID(int productID) throws SQLException {

        // Establish a database connection
        Connection connection = DatabaseConnector.getConnection();
        if (connection == null) {
            throw new SQLException("Database connection failed");
        }

        // Query the database for the product with the specified ID
        String selectQuery = "SELECT product_id, product.name AS name, description, price, supplier_id, " +
                "supplier.name AS supplier_name FROM product INNER JOIN supplier " +
                "USING(supplier_id) WHERE product_id = ?";

        // Create PreparedStatement and set the product ID parameter
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setInt(1, productID);

        ResultSet result = statement.executeQuery();

        // If a result comes back, construct a ProductResponse object and return it
        if(result.next()) {
            return new ProductResponse(
                    result.getInt("product_id"),
                    result.getString("name"),
                    result.getString("description"),
                    result.getDouble("price"),
                    result.getString("supplier_name"),
                    result.getInt("supplier_id")
            );
        }

        return null;

    }

    /**
     * Creates a new product in the database and returns its product ID
     *
     * @param product The product to create
     * @return The Product ID of the newly created product
     * @throws SQLException If a database error occurred
     */
    public int createProduct(ProductRequest product) throws SQLException {

        // Establish a database connection
        Connection connection = DatabaseConnector.getConnection();
        if (connection == null) {
            throw new SQLException("Database connection failed");
        }

        // Create a PreparedStatement to insert the new product and return the product ID
        String insertQuery = "INSERT INTO product (name, description, price, supplier_id) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, product.getName());
        statement.setString(2, product.getDescription());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getSupplierID());

        statement.executeUpdate();

        ResultSet result = statement.getGeneratedKeys();

        // Return the ID of the newly created product
        if (result.next()) {
            return result.getInt(1);
        }

        // Return -1 if no product ID was returned (error occurred during insertion)
        return -1;



    }

}
