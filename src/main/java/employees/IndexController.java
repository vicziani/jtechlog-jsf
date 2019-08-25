package employees;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Component
@RequestScope
public class IndexController {

    private EmployeeService employeeService;

    public IndexController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<EmployeeDto> getEmployees() {
        return employeeService.listEmployees();
    }

    public String deleteEmployee(EmployeeDto employee) {
        var command = new DeleteEmployeeCommand();
        command.setId(employee.getId());
        employeeService.deleteEmployee(command);

        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Employee has deleted with name "
                        + employee.getName())
        );

        return "index.xhtml?faces-redirect=true";
    }
}
