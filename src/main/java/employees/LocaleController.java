package employees;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Locale;

@Component
@SessionScope
@Getter
public class LocaleController {

    private Locale locale = new Locale("hu");

    public void changeLocale(String lang) {
        locale = new Locale(lang);
    }
}
