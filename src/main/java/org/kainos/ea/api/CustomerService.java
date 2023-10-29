package org.kainos.ea.api;

import org.kainos.ea.cli.CustomerRequest;
import org.kainos.ea.cli.CustomerResponse;
import org.kainos.ea.client.*;
import org.kainos.ea.client.InvalidCustomerException;
import org.kainos.ea.core.CustomerValidator;
import org.kainos.ea.db.CustomerDAO;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private final CustomerDAO customerDAO = new CustomerDAO();

    /**
     * Gets all customers from the database
     *
     * @return A list of CustomerResponse objects, representing all the customers in the database
     * @throws FailedToRetrieveCustomersException If customers could not be retrieved from the database.
     */
    public List<CustomerResponse> getALlCustomers() throws FailedToRetrieveCustomersException {

        try {
            return customerDAO.getAllCustomers();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToRetrieveCustomersException();
        }

    }

    /**
     *  Retrieves a customer with the specified customer ID from the database.
     *
     * @param customerID The ID of the customer to retrieve.
     * @return A CustomerResponse object which represents the customer with the specified ID
     * @throws CustomerDoesNotExistException If no customer exists with the specified ID
     * @throws FailedToRetrieveCustomerException If an error occurred when retrieving the customer data
     */
    public CustomerResponse getCustomerByID(int customerID) throws CustomerDoesNotExistException, FailedToRetrieveCustomerException {

        try {

            CustomerResponse customer = customerDAO.getCustomerByID(customerID);

            if (customer == null) {
                throw new CustomerDoesNotExistException();
            }

            return customerDAO.getCustomerByID(customerID);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToRetrieveCustomerException();
        }

    }

    /**
     * Creates a new customer in the database
     *
     * @param customer The customer to create
     * @return The customer ID of the newly created customer
     * @throws InvalidCustomerException If the customers details failed validation
     * @throws FailedToCreateCustomerException If a database error occurred when creating the customer
     * @see CustomerValidator
     */
    public int createCustomer(CustomerRequest customer) throws InvalidCustomerException, FailedToCreateCustomerException {

        // Validate the CustomerRequest before attempting to add the new customer
        String validationErrorMessage = CustomerValidator.validateCustomer(customer);
        if (validationErrorMessage != null) {
            throw new InvalidCustomerException(validationErrorMessage);
        }

        try {
            return customerDAO.createCustomer(customer);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateCustomerException();
        }
    }

    /**
     * Updates the details of a customer with a specified customer ID
     *
     * @param customerID The ID of the customer to update
     * @param customer A CustomerRequest object containing the customer's new details
     * @throws InvalidCustomerException If the supplied customer details failed validation
     * @throws CustomerDoesNotExistException If no customer with the specified customer ID exists
     * @throws FailedToUpdateCustomerException If a database error occurred
     * @see CustomerValidator
     */
    public void updateCustomer(int customerID, CustomerRequest customer) throws InvalidCustomerException, CustomerDoesNotExistException, FailedToUpdateCustomerException {

        try {
            // First, check if the specified customer exists
            if (customerDAO.getCustomerByID(customerID) == null) {
                throw new CustomerDoesNotExistException();
            }

            // If they exist, validate the new customer details
            String validationError = CustomerValidator.validateCustomer(customer);
            if (validationError != null) {
                throw new InvalidCustomerException(validationError);
            }

            customerDAO.updateCustomer(customerID, customer);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToUpdateCustomerException();
        }
    }

}
