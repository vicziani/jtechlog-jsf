package employees;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Component
@RequestScope
@RequiredArgsConstructor
public class CreateEmployeeController {

    private final EmployeeService employeeService;

    private final MessageContext messageContext;

    @Getter
    private List<Integer> salaryOptions;

    @Getter
    @Setter
    private CreateEmployeeCommand command =
            new CreateEmployeeCommand("", 100_000);

    @PostConstruct
    public void initSalaryOptions() {
        salaryOptions = employeeService.listSalaryOptions();
    }

    public String createEmployee() {
        int count = employeeService.countEmployeesWithName(command.getName());
        if (count > 0) {
            messageContext.addMessage("name_already_exists");
            return null;
        }
        employeeService.createEmployee(command);
        messageContext.addFlashMessage("employee_has_been_created", command.getName());
        return "index.xhtml?faces-redirect=true";
    }

}
