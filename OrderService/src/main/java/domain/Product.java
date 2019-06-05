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

    int id;
    private String name;
    private Double price;
    private int amountPicked;
    private int amountLeft;
    private String imageUrl;

    public Product(int id, String name, Double price, int amountLeft, String imageUrl) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.amountLeft = amountLeft;
        this.imageUrl = imageUrl;
        amountPicked = 0;
    }
}
