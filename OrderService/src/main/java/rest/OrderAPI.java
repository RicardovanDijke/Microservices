package rest;

import domain.CartRow;
import java.util.List;
import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 *
 * /**
 *
 * @author Ricardo van Dijke
 */
@Path("/")
public class OrderAPI {

    @POST
    @Produces(APPLICATION_JSON)
    @Path("newOrder")
    public Response newOrder(List<CartRow> cart) {

        System.out.println("ORDERSERVICE: Got order!");
        return Response.ok().build();
    }

}
