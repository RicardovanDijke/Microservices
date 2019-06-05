package domain;

import lombok.*;

/**
 *
 * @author Ricardo van Dijke
 */
@Getter
@Setter
@NoArgsConstructor
public class Product {

    private Double price;
    private String name;
    private int amountPicked;
    private int amountLeft;

    public Product(Double price, String name, int amountLeft) {
        this.price = price;
        this.name = name;
        this.amountLeft = amountLeft;
        amountPicked = 0;
    }
}
