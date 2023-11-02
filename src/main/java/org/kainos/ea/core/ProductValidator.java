package org.kainos.ea.core;

import org.kainos.ea.cli.ProductRequest;

public class ProductValidator {

    /**
     * Validates the data contained in a ProductRequest object
     *
     * @param product The product to validate
     * @return A string containing validation error messages, or null if no validation errors were detected
     */
    public static String validateProduct(ProductRequest product) {

        boolean errorFound = false;
        String errorMessage = "Please correct the following errors:\n\n";

        // Validate the product name
        String productName = product.getName();
        if (productName == null || productName.length() < 1 || productName.length() > 255) {
            errorFound = true;
            errorMessage += "Product name must be between 1-255 characters\n";
        }

        // Validate the product description
        String description = product.getDescription();
        if (description == null || description.length() < 1 || description.length() > 255) {
            errorFound = true;
            errorMessage += "Description must be between 1-255 characters\n";
        }

        // Validate the price
        double price = product.getPrice();
        if (price <= 0 || price >= 10000) {
            errorFound = true;
            errorMessage += "Price must be greater than 0 and less than 10000\n";
        }

        // Validate the supplier ID
        int supplier_id = product.getSupplierID();
        if (supplier_id <= 0) {
            errorFound = true;
            errorMessage += "Supplier ID must be a positive integer";
        }

        if (errorFound) {
            return errorMessage;
        }

        return null;

    }

}
