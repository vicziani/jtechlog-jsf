package employees;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Component
@RequestScope
public class WelcomeController {

    private String name;

    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Hello " + name + "!"));
        message = "retwretwer " + name;
    }

    public String getMessage() {
        return message;
    }
}
