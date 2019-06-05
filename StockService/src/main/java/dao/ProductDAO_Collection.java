package dao;

import domain.Product;
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

        this.stock.add(new Product(0, "Borderlands", 5.00, 10, "resources\\images\\Borderlands1.jpg"));
        this.stock.add(new Product(1, "Borderlands 2", 12.50, 10, "resources\\images\\Borderlands2.jpg"));

        this.stock.add(new Product(2, "Half-Life", 7.80, 10, "resources\\images\\Half-Life.jpg"));
        this.stock.add(new Product(3, "Half-Life 2", 20.00, 10, "resources\\images\\Half-Life2.jpg"));
    }

    @Override
    public List<Product> getStock() {
        return stock;
    }
}
