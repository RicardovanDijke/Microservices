package domain;

import lombok.*;


/**
 *
 * @author Ricardo
 */
@Getter
@Setter
@NoArgsConstructor
public class Account {

    private String username;
    private String password;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
}
