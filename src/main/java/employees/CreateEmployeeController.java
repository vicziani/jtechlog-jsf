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

    private MessageContext messageContext;

    private List<Integer> salaryOptions;

    private CreateEmployeeCommand command =
            new CreateEmployeeCommand("", 100_000);

    public CreateEmployeeController(EmployeeService employeeService, MessageContext messageContext) {
        this.employeeService = employeeService;
        this.messageContext = messageContext;
    }

    @PostConstruct
    public void initSalaryOptions() {
        salaryOptions = employeeService.listSalaryOptions();
    }

    public String createEmployee() {
        var count = employeeService.countEmployeesWithName(command.getName());
        if (count > 0) {
//            FacesContext.getCurrentInstance()
//                    .addMessage(null,
//                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name already exists!", ""));


            messageContext.addMessage("name_already_exists");
            return null;
        }

        employeeService.createEmployee(command);

        // FacesContext.getCurrentInstance() is null in

//        FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getFlash()
//                .setKeepMessages(true);
//
//        FacesContext.getCurrentInstance()
//                .addMessage(null,
//                        new FacesMessage("Employee has created with name "
//                                + command.getName()));
        messageContext.addFlashMessage("employee_has_created", command.getName());

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
