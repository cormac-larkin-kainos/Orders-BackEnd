package org.kainos.ea.api;

import org.kainos.ea.cli.ProductResponse;
import org.kainos.ea.client.FailedToRetrieveProductsException;
import org.kainos.ea.db.ProductDAO;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductDAO productDAO = new ProductDAO();

    /**
     * Retrieves all products from the database
     *
     * @return A list of all Products in the database
     * @throws FailedToRetrieveProductsException If a database error occurred
     */
    public List<ProductResponse> getAllProducts() throws FailedToRetrieveProductsException {

        try {
            return productDAO.getAllProducts();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToRetrieveProductsException();
        }

    }

}
