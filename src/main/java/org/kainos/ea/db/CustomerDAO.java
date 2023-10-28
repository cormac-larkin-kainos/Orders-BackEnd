package org.kainos.ea.db;

import org.checkerframework.checker.units.qual.C;
import org.kainos.ea.cli.CustomerRequest;
import org.kainos.ea.cli.CustomerResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    /**
     * Retrieves all customers from the database
     *
     * @return A list of CustomerResponse objects, representing all the customers in the database
     * @throws SQLException If an error occurs when establishing a database connection
     */
    public List<CustomerResponse> getAllCustomers() throws SQLException {

        // Establish a database connection
        Connection connection = DatabaseConnector.getConnection();
        if (connection == null) {
            throw new SQLException("Database connection failed");
        }

        // Query the database for the details of all customers
        String selectQuery = "SELECT first_name, last_name, address, phone, email FROM customer";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();

        // Create a list of CustomerResponse objects from the result set
        List<CustomerResponse> allCustomers = new ArrayList<>();
        while (resultSet.next()) {
            CustomerResponse customer = new CustomerResponse(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("address"),
                    resultSet.getString("phone"),
                    resultSet.getString("email")
            );

            allCustomers.add(customer);
        }

        return allCustomers;

    }

    public CustomerResponse getCustomerByID(int customerID) throws SQLException {

        // Establish a database connection
        Connection connection = DatabaseConnector.getConnection();
        if (connection == null) {
            throw new SQLException("Database connection failed");
        }

        // Query the database for the specified customer
        String selectQuery = "SELECT first_name, last_name, address, phone, email FROM customer WHERE customer_id = ?";
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        statement.setInt(1, customerID);

        // Return a CustomerResponse containing the customer's details
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new CustomerResponse(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("address"),
                    resultSet.getString("phone"),
                    resultSet.getString("email")
            );
        }

        // Return null if the customer was not found
        return null;

    }

    /**
     * Adds a new customer to the database and returns their customer ID
     *
     * @param customer The customer to add to the database
     * @return The customer ID of the newly created customer, or -1 if the insertion failed
     */
    public int createCustomer(CustomerRequest customer) throws SQLException {

        // Establish a database connection
        Connection connection = DatabaseConnector.getConnection();
        if (connection == null) {
            throw new SQLException("Database connection failed");
        }

        // Insert the new customer into the database
        String insertStatement = "INSERT INTO customer (first_name, last_name, address, phone, email)" +
                " VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, customer.getFirstName());
        statement.setString(2, customer.getLastName());
        statement.setString(3, customer.getAddress());
        statement.setString(4, customer.getPhone());
        statement.setString(5, customer.getEmail());

        statement.executeUpdate();

        // Return the Customer ID of the newly inserted customer
        ResultSet result = statement.getGeneratedKeys();
        if (result.next()) {
            return result.getInt(1);
        }

        // Return -1 if no customer ID was returned (error occurred during insertion)
        return -1;
    }
}
