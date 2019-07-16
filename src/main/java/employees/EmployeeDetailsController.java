package employees;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.faces.context.FacesContext;

@Component
@RequestScope
public class EmployeeDetailsController {

    private EmployeeService employeeService;

    private long id;

    private ModifyEmployeeCommand command = new ModifyEmployeeCommand();

    public EmployeeDetailsController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .put("successMessage", "Employess has modified with name " + command.getName());

        return "index.xhtml?faces-redirect=true";
    }

    public void setCommand(ModifyEmployeeCommand command) {
        this.command = command;
    }
}
