package service;

import dao.IAccountDAO;
import domain.Account;
import javax.inject.Inject;

/**
 *
 * @author Ricardo van Dijke
 */
public class AccountService {

    @Inject
    private IAccountDAO accountDAO;

    public Account findByUsername(String username) {
        return accountDAO.findByUsername(username);
    }
}
