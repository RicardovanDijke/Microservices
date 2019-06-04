package dao;

import domain.Account;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Default;

/**
 *
 * @author Ricardo van Dijke
 */
@Default
public class AccountDAO_Collection implements IAccountDAO {

    private List<Account> memory = new ArrayList<>();

    public AccountDAO_Collection() {
        memory.add(new Account("user1", "admin"));
        memory.add(new Account("user2", "admin"));
        memory.add(new Account("user3", "admin"));
        memory.add(new Account("user4", "admin"));
    }

    @Override
    public Account findByUsername(String username) {
        for (Account account : memory) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }
}
