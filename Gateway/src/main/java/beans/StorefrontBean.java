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
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    private static final String ORDER_SERVICE_API = "http://localhost:8080/OrderService";

    private List<CartRow> cart = new ArrayList<>();
    private double totalCartPrice = 0;
    private List<Product> allProducts = new ArrayList<>();

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
            if (cartRow.product.getName().equals(product.getName())) {
                cartRow.amount += 1;
                contains = true;
                break;
            }
        }
        if (!contains) {
            cart.add(new CartRow(product, 1));
        }
    }

    public void onCartRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Game removed from Cart", ((CartRow) event.getObject()).getProduct().getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        CartRow cartRowSelected = (CartRow) event.getObject();

        for (CartRow cartRow : cart) {
            if (cartRow.getProduct().getName().equals(cartRowSelected.getProduct().getName())) {
                cartRow.amount -= 1;

                if (cartRow.amount == 0) {
                    cart.remove(cartRow);
                }
                break;
            }
        }
    }
    
    private void updateCartPrice(){
        totalCartPrice = 0;
        
        for(CartRow cr : cart){
          //  totalCartPrice += (cr.)
        }
    }

    public void order() {
        System.out.println("Sending Order");

        Client client = ClientBuilder.newClient();

        Response response = client.target(ORDER_SERVICE_API)
                .path("/api/newOrder")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(cart));

        if (response.getStatus() == 200) {
            System.out.println("order sent!");
            //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "storefront.xhtml?faces-redirect=true\"");
        }
    }
}
