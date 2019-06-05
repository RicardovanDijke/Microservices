package util;

import lombok.*;

/**
 *
 * @author Ricardo van Dijke
 */
@Getter
@Setter
@NoArgsConstructor
public class CartRow {

    public String name;
    public Integer amount;

    public CartRow(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }
}
