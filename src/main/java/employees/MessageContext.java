package employees;

import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.ResourceBundle;

@Component
public class MessageContext {

    public void addMessage(String key, String... arguments) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{msgs}", ResourceBundle.class);
        String pattern = bundle.getString(key);
        String message = MessageFormat.format(pattern, (Object[]) arguments);

        facesContext
                .addMessage(null,
                        new FacesMessage(message));
    }

    public void addFlashMessage(String key, String... arguments) {
        setFlashMessages();

        addMessage(key, arguments);
    }

    private void setFlashMessages() {
          FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .setKeepMessages(true);
    }
}
