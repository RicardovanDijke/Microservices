package beans;

import domain.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
@Getter
@Setter
public final class StorefrontBean implements Serializable {

    private static final String STOCK_SERVICE_API = "http://localhost:8080/StockService";

    private Map<String, Integer> cart = new HashMap<>();
    //private List<Tuple<String,Integer>> cart = new ArrayList<>();
    
    private List<Product> allProducts = new ArrayList<>();

    private Product selectedProduct;

    public StorefrontBean() {
        cart = new HashMap<>();
    }

    @PostConstruct
    public void getProductsInStock() {
        //System.out.println("getting stock");

        Client client = ClientBuilder.newClient();

        List<Product> products = client.target(STOCK_SERVICE_API)
                .path("/api/stock")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Product>>() {
                });

        for (Product p : products) {
            //  System.out.println(p.getName());
            p.setImageUrl(STOCK_SERVICE_API + '/' + p.getImageUrl());
            // System.out.println(p.getImageUrl());
        }

        allProducts = products;
    }

    public void onProductRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Game added to Cart", ((Product) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        Product product = (Product) event.getObject();

        int count = cart.containsKey(product.getName()) ? cart.get(product.getName()) : 0;
        cart.put(product.getName(), count + 1);

        System.out.println(cart.toString());
    }

    public void onCartRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Game removed from Cart", ((Map.Entry<String, Integer>) event.getObject()).getKey());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        Map.Entry<String, Integer> cartRow = (Map.Entry<String, Integer>) event.getObject();

        int count = cart.containsKey(cartRow.getKey()) ? cart.get(cartRow.getKey()) : 0;
        cart.put(cartRow.getKey(), count - 1);

        if (cartRow.getValue() == 0) {
            cart.remove(cartRow.getKey());
        }
        
        if(cart.entrySet().isEmpty()){
            cart = new HashMap<>();
        }
        System.out.println(cart.toString());
    }
}
