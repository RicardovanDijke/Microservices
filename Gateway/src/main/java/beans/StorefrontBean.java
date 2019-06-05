package beans;

import domain.Cart;
import domain.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ManagedBean
@SessionScoped
public class StorefrontBean implements Serializable {

    private static final String STOCK_SERVICE_API = "http://localhost:8080/StockService/api";

    private Cart cart;

    private List<Product> allProducts = new ArrayList<>();

    public void getProductsInStock(){
        
        Client client = ClientBuilder.newClient();

        Response response = client.target(STOCK_SERVICE_API)
                .path("/allProducts")
                .request(MediaType.APPLICATION_JSON)
                .get();
        
    }   
}
