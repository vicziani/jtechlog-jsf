package employees;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequestScope
@RequiredArgsConstructor
public class CreateEmployeeController {

    private final EmployeeService employeeService;

    private final MessageContext messageContext;

    private List<Integer> salaryOptions;

    private CreateEmployeeCommand command =
            new CreateEmployeeCommand("", 100_000);

    @PostConstruct
    public void initSalaryOptions() {
        salaryOptions = employeeService.listSalaryOptions();
    }

    public String createEmployee() {
        var count = employeeService.countEmployeesWithName(command.getName());
        if (count > 0) {
            messageContext.addMessage("name_already_exists");
            return null;
        }
        employeeService.createEmployee(command);
        messageContext.addFlashMessage("employee_has_been_created", command.getName());
        return "index.xhtml?faces-redirect=true";
    }

    public CreateEmployeeCommand getCommand() {
        return command;
    }

    public void setCommand(CreateEmployeeCommand command) {
        this.command = command;
    }

    public List<Integer> getSalaryOptions() {
        return salaryOptions;
    }


}
