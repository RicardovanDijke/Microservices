package domain;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

/**
 *
 * @author Ricardo van Dijke
 */
@Getter
@Setter
@NoArgsConstructor
public class Cart {

    private List<Product> products = new ArrayList<>();
}
