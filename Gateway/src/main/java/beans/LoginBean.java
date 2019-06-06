package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.*;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class LoginBean implements Serializable {

    private static final String LOGIN_SERVICE_API = "http://localhost:8080/LoginService";

    private String username = "user1";
    private String password = "admin";

    private String message;

    //validate login
    public void Login() {
        System.out.println("Logging in");

        Form form = new Form();
        form.param("username", username)
                .param("password", password);
        Client client = ClientBuilder.newClient();

        Response response = client.target(LOGIN_SERVICE_API)
                .path("/api/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form));

        switch (response.getStatus()) {
            case 200:
                System.out.println("logged in");
                FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "storefront.xhtml?faces-redirect=true\"");
                break;
            case 204:
                System.out.println("Wrong credentials");
                message = "Invalid user and or password combination.";
                break;
            default:
                message = "Could not connect to loginService.";
                break;
        }
    }
    public void refreshMsg(){
        message = "";
    }
}
