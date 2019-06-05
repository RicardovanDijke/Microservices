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

    private static final String LOGIN_SERVICE_API = "http://localhost:8080/LoginService/api";
    
    private String username = "user1";
    private String password = "admin";

    //validate login
    public void Login() {
        System.out.println("Logging in");

        Form form = new Form();
        form.param("username", username)
                .param("password", password);
        Client client = ClientBuilder.newClient();

        Response response = client.target(LOGIN_SERVICE_API)
                .path("/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form));

        if (response.getStatus() == 200) {
            System.out.println("logged in");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "storefront.xhtml?faces-redirect=true\"");
        }
    }
}
