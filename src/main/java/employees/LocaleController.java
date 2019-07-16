package employees;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Locale;

@Component
@SessionScope
public class LocaleController {

    private Locale locale = new Locale("hu");

    public Locale getLocale() {
        return locale;
    }

    public void changeLocale(String lang) {
        locale = new Locale(lang);
    }
}
