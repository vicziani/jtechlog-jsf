package employees;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexForwardController {

    @RequestMapping("/")
    public String index() {
        return "forward:index.xhtml";
    }
}
