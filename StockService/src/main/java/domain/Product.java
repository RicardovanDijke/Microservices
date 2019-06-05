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

    private int id;
    private Double price;
    private String name;
    private int amountLeft;
    private String imageUrl;

    public Product(int id, String name, Double price, int amountLeft, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amountLeft = amountLeft;
        this.imageUrl = imageUrl;
    }
}
