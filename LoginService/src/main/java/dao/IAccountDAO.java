package dao;

import domain.Account;

/**
 *
 * @author Ricardo van Dijke
 */
public interface IAccountDAO {

    Account findByUsername(String username);
}
