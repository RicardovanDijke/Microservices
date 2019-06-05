package beans;

import domain.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import util.CartRow;

@ManagedBean
@SessionScoped
@Getter
@Setter
public final class StorefrontBean implements Serializable {

    private static final String STOCK_SERVICE_API = "http://localhost:8080/StockService";

    //private Map<String, Integer> cart = new HashMap<>();
    private List<CartRow> cart = new ArrayList<>();

    private List<Product> allProducts = new ArrayList<>();

    private Product selectedProduct;

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

        boolean contains = false;
        for (CartRow cartRow : cart) {
            if (cartRow.name.equals(product.getName())) {
                cartRow.amount += 1;
                contains = true;
                break;
            }
        }
        if (!contains) {
            cart.add(new CartRow(product.getName(), 1));
        }
    }

    public void onCartRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Game removed from Cart", ((CartRow) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        CartRow cartRowSelected = (CartRow) event.getObject();

        for (CartRow cartRow : cart) {
            if (cartRow.name.equals(cartRowSelected.getName())) {
                cartRow.amount -= 1;

                if (cartRow.amount == 0) {
                    cart.remove(cartRow);
                }
                break;
            }
        }
    }
}
