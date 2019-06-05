package beans;

import domain.Cart;
import domain.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
@Getter
@Setter
public final class StorefrontBean implements Serializable {

    private static final String STOCK_SERVICE_API = "http://localhost:8080/StockService";

    private Cart cart;

    private List<Product> allProducts = new ArrayList<>();

    private Product selectedProduct;
    
    public StorefrontBean() {
        getProductsInStock();
    }

    @PostConstruct
    public void getProductsInStock() {
        System.out.println("getting stock");

        Client client = ClientBuilder.newClient();

        List<Product> products = client.target(STOCK_SERVICE_API)
                .path("/api/stock")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Product>>() {
                });

        for (Product p : products) {
            System.out.println(p.getName());
            p.setImageUrl(STOCK_SERVICE_API + '/' + p.getImageUrl());
            System.out.println(p.getImageUrl());
        }

        allProducts = products;
    }
}
