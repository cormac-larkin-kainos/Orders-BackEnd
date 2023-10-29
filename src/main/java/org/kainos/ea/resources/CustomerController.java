package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.CustomerService;
import org.kainos.ea.cli.CustomerRequest;
import org.kainos.ea.cli.Customer;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("DropWizardOrdersAPI: Customer Endpoints")
@Path("/api")
public class CustomerController {

    private final CustomerService customerService = new CustomerService();

    /**
     * Returns a JSON response with the details of all customers
     *
     * @return A '200 OK' response and a list of all customers, or a '500 Internal Server' response
     * if an error occurred when retrieving the customers
     */
    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {

        try {
            return Response.ok(customerService.getALlCustomers()).build();
        } catch (FailedToRetrieveCustomersException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

    }

    /**
     * Retrieves a specific customer
     *
     * @param customerID The ID of the customer to retrieve
     * @return A '200 OK' response and the customer details, or a
     * '500 Internal Server Error' response if a database error occurred.
     * Returns '404 Not Found' if no customer with the specified ID exists
     */
    @GET
    @Path("customers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerByID(@PathParam("id") int customerID) {

        try {
            Customer customer = customerService.getCustomerByID(customerID);
            return Response.ok(customer).build();
        } catch (CustomerDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (FailedToRetrieveCustomerException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

    }

    /**
     * Creates a new Customer and returns their customer ID
     *
     * @param customer The customer to create
     * @return A '200 OK' response containing the customer ID of the newly created customer.
     * If invalid customer details were supplied, returns a '400 Bad Request' response.
     * If a database error occurred and the customer could not be created, returns a '500 Internal Server Error' response.
     */
    @POST
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerRequest customer) {

        try {
            return Response.ok(customerService.createCustomer(customer)).build();
        } catch (InvalidCustomerException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (FailedToCreateCustomerException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

    }

    /**
     * Updates the details of a customer with a specified customer ID
     *
     * @param customerID The ID of the customer to update
     * @param customer A CustomerRequest object containing the customer's new details
     * @return A '204 No Content' response if the customer was successfully updated.
     * Returns a '400 Bad Request' response if the supplied customer details failed validation.
     * Returns a '404 Not Found' response if no customer with the specified ID exists.
     * Returns a '500 Internal Server Error' response if a database error occurred.
     */
    @PUT
    @Path("/customers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") int customerID, CustomerRequest customer) {

        try {
            customerService.updateCustomer(customerID, customer);
            return Response.noContent().build();
        } catch (InvalidCustomerException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (CustomerDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (FailedToUpdateCustomerException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

    }

    /**
     * Deletes a customer with a specified customer ID
     *
     * @param customerID The ID of the customer to delete
     * @return A '204 No Content' response if the customer was successfully deleted.
     * Returns a '404 Not Found response if the specified customer ID does not exist
     * Returns a '500 Internal Server Error' response if a database error occurred.
     */
    @DELETE
    @Path("/customers/{id}")
    public Response deleteCustomer(@PathParam("id") int customerID) {

        try {
            customerService.deleteCustomer(customerID);
            return Response.noContent().build();
        } catch (CustomerDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (FailedToDeleteCustomerException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

    }
}
