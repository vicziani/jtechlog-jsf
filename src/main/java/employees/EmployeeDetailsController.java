package employees;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class EmployeeDetailsController {

    private final EmployeeService employeeService;

    private final MessageContext messageContext;

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private ModifyEmployeeCommand command = new ModifyEmployeeCommand();

    public void findEmployeeById() {
            EmployeeDto employee = employeeService.findEmployeeById(id);
            command = new ModifyEmployeeCommand();
            command.setId(employee.getId());
            command.setName(employee.getName());
            command.setSalary(employee.getSalary());
    }

    public String modifyEmployee() {
        employeeService.modifyEmployee(command);
        messageContext.addFlashMessage("employee_has_been_modified", command.getName());

        return "index.xhtml?faces-redirect=true";
    }
}
