package employees;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
@RequiredArgsConstructor
public class IndexController {

    private final EmployeeService employeeService;

    private final MessageContext messageContext;

    public List<EmployeeDto> getEmployees() {
        return employeeService.listEmployees();
    }

    public String deleteEmployee(EmployeeDto employee) {
        var command = new DeleteEmployeeCommand();
        command.setId(employee.getId());
        employeeService.deleteEmployee(command);
        messageContext.addFlashMessage("employee_has_been_deleted", employee.getName());
        return "index.xhtml?faces-redirect=true";
    }
}
