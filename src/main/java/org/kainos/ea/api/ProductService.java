package org.kainos.ea.api;

import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.cli.ProductResponse;
import org.kainos.ea.client.*;
import org.kainos.ea.core.CustomerValidator;
import org.kainos.ea.core.ProductValidator;
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

    /**
     * Gets a product with a specified product ID from the database
     *
     * @param productID The ID of the product to retrieve
     * @return A ProductResponse object which represents the specified product
     * @throws ProductDoesNotExistException If no product with the specified product ID exists
     * @throws FailedToRetrieveProductException If a database error occurred
     */
    public ProductResponse getProductByID(int productID) throws ProductDoesNotExistException, FailedToRetrieveProductException {

        try {
            ProductResponse product = productDAO.getProductByID(productID);

            if (product == null) {
                throw new ProductDoesNotExistException();
            }

            return product;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToRetrieveProductException();
        }

    }

    /**
     * Creates a new product and returns its product ID
     *
     * @param product The new product to create
     * @return The Product ID of the newly created product
     * @throws InvalidProductException If the supplied product details failed validation
     * @throws FailedToCreateProductException If a database error occurred
     */
    public int createProduct(ProductRequest product) throws InvalidProductException, FailedToCreateProductException {

        // Validate the ProductRequest before attempting to add the new product
        String validationErrorMessage = ProductValidator.validateProduct(product);

        if (validationErrorMessage != null) {
            throw new InvalidProductException(validationErrorMessage);
        }

        try {
            return productDAO.createProduct(product);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateProductException();
        }

    }

}
