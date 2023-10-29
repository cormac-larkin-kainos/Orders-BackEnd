package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.ProductService;
import org.kainos.ea.client.FailedToRetrieveProductsException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("DropWizardOrdersAPI: Product Endpoints")
@Path("/api")
public class ProductController {

    private final ProductService productService = new ProductService();

    /**
     * Gets all products from the database
     *
     * @return A '200 OK' response and a list of all products.
     * Returns a '500 Internal Server Error' response if a database error occurred.
     */
    @GET
    @Path("products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {

        try {
            return Response.ok(productService.getAllProducts()).build();
        } catch (FailedToRetrieveProductsException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }

    }

}
