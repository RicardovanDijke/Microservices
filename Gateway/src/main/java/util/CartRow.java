package util;

import domain.Product;
import lombok.*;

/**
 *
 * @author Ricardo van Dijke
 */
@Getter
@Setter
@NoArgsConstructor
public class CartRow {

    public Product product;
    public Integer amount;

    public CartRow(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }
}
