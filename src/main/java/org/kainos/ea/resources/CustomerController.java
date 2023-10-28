package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.CustomerService;
import org.kainos.ea.cli.CustomerRequest;
import org.kainos.ea.cli.CustomerResponse;
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
     * @return A 200 OK response and a list of all customers, or a 500 Internal Server response
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

    @GET
    @Path("customers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerByID(@PathParam("id") int customerID) {

        try {
            CustomerResponse customer = customerService.getCustomerByID(customerID);
            return Response.ok(customer).build();
        } catch (CustomerDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (FailedToRetrieveCustomerException e) {
            return Response.serverError().build();
        }

    }

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
}
