package domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.*;
/**
 *
 * @author Ricardo van Dijke
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User implements Serializable {
    
    @Id
    private Long id;

    private String username;
    
    private String password;
}
