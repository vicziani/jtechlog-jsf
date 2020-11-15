package employees;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class EmployeeDetailsController {

    private final EmployeeService employeeService;

    private final MessageContext messageContext;

    private long id;

    private ModifyEmployeeCommand command = new ModifyEmployeeCommand();

    public void findEmployeeById() {
            var employee = employeeService.findEmployeeById(id);
            command = new ModifyEmployeeCommand();
            command.setId(employee.getId());
            command.setName(employee.getName());
            command.setSalary(employee.getSalary());
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public ModifyEmployeeCommand getCommand() {
        return command;
    }

    public String modifyEmployee() {
        employeeService.modifyEmployee(command);
        messageContext.addFlashMessage("employee_has_been_modified", command.getName());

        return "index.xhtml?faces-redirect=true";
    }

    public void setCommand(ModifyEmployeeCommand command) {
        this.command = command;
    }
}
