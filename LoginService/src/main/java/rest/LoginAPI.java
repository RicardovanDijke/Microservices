package rest;

import domain.Account;
import javax.inject.Inject;
import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import service.AccountService;
import service.LoginService;

/**
 *
 * /**
 *
 * @author Ricardo van Dijke
 */
@Path("/")
public class LoginAPI {

    @Inject
    private AccountService accountService;

    @Inject
    private LoginService loginService;

    @POST
    @Produces(APPLICATION_JSON)
    @Path("login")
    public Account login(@FormParam("username") String username, @FormParam("password") String password) {

        System.out.println("username: " + username);
        System.out.println("password: " + password);

        if (loginService.validate(username, password)) {
            Account account = accountService.findByUsername(username);

            return account;
        }
        return null;
    }
}
