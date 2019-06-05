package dao;

import domain.Product;
import java.util.List;

/**
 *
 * @author Ricardo van Dijke
 */
public interface ProductDAO {
    List<Product> getStock();
}
