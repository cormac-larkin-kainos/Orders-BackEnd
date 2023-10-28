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


}
