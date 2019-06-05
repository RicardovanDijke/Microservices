package dao;

import domain.Product;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo van Dijke
 */
public class ProductDAO_Collection implements ProductDAO {

    private List<Product> stock = new ArrayList<>();

    public ProductDAO_Collection() throws IOException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        //this.stock.add(new Product("Borderlands 1", 5.00, 10));
        //this.stock.add(new Product("Borderlands 2", 12.50, 10, toolkit.getImage("images/Borderlands2.jpg")));
        this.stock.add(new Product(0, "Borderlands 2", 12.50, 10, "resources\\images\\Borderlands2.jpg"));

        //this.stock.add(new Product("Borderlands 1", 5.00, 10));
        //this.stock.add(new Product("Borderlands 1", 5.00, 10));
    }

    @Override
    public List<Product> getStock() {
        return stock;
    }
}
