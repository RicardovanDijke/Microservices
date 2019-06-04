package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final String LOGIN_SERVICE_API = "http://localhost:8080/LoginService/api";

    private String password = "user1";
    private String msg;
    private String username = "admin";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    //validate login
    public void Login() {
        System.out.println("Logging in");
        
        
        Form form = new Form();
        form.param("username", username)
                .param("password", password);
        Client client = ClientBuilder.newClient();

        System.out.println(client.target(LOGIN_SERVICE_API)
                .path("/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form)));

    }
}
