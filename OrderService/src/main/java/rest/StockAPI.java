package rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import service.StockService;

/**
 *
 * /**
 *
 * @author Ricardo van Dijke
 */
@Path("/")
public class StockAPI {

    @Inject
    private StockService stockService;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("newOrder")
    public List<CartRow> newOrder(){
        
        return stockService.getStock();
    }
   
}
