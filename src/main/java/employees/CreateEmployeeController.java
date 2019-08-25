package employees;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Component
@RequestScope
public class CreateEmployeeController {

    private EmployeeService employeeService;

    private List<Integer> salaryOptions;

    private CreateEmployeeCommand command =
            new CreateEmployeeCommand("", 100_000);

    public CreateEmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostConstruct
    public void initSalaryOptions() {
        salaryOptions = employeeService.listSalaryOptions();
    }

    public String createEmployee() {

        var count = employeeService.findEmployeeCountWithName(command.getName());
        if (count > 0) {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name already exists!", ""));
            return null;
        }

        employeeService.createEmployee(command);

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .setKeepMessages(true);

        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage("Employess has created with name "
                                + command.getName()));

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
