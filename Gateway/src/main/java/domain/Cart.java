package domain;

import java.util.HashMap;
import java.util.Map;
import lombok.*;

/**
 *
 * @author Ricardo van Dijke
 */
@Getter
@Setter
@NoArgsConstructor
public class Cart {

    private Map<Product, Integer> products = new HashMap<>();
//
//    public boolean addProduct(Product product) {
//      //  if(product.)
//    }
//
//    public boolean removeProduct() {
//
//    }
}
