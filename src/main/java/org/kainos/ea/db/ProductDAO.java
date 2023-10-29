package org.kainos.ea.db;

import org.kainos.ea.cli.ProductResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
