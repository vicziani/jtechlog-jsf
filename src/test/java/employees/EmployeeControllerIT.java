package employees;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = {"delete from employee"})
class EmployeeControllerIT {

    @LocalServerPort
    int port;

    @Test
    void create() throws Exception {
        try (var webClient = new WebClient()) {
            webClient.getOptions().setJavaScriptEnabled(false); // WARNING: Obsolete content type encountered: 'text/javascript' üzenetek ellen
            var prefix = "http://localhost:%d".formatted(port);
            HtmlPage page = webClient.getPage(prefix + "/create-employee.xhtml"); // var deklarációval nem működik
            assertEquals("Employees", page.getTitleText());

            var form = page.getFormByName("create-employee-form");
            form.getInputByName("create-employee-form:name").type("John Doe");
            HtmlPage indexPage = form.getInputByName("create-employee-form:create-button").click();
            assertEquals("Employees", page.getTitleText());
            assertTrue(indexPage.asXml().contains("John Doe"));

        }
    }
}
