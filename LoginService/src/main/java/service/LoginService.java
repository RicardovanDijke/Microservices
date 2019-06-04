package service;

import dao.IAccountDAO;
import domain.Account;
import javax.inject.Inject;

/**
 *
 * @author Ricardo van Dijke
 */
public class LoginService {

    @Inject
    private IAccountDAO accountDAO;

    public boolean validate(String username, String password) {
        Account a = accountDAO.findByUsername(username);

        if (a != null && a.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
