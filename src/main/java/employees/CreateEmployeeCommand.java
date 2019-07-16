package employees;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CreateEmployeeCommand {

    @NotBlank
    private String name;

    @DivBy(number = 50)
    private int salary;

    public CreateEmployeeCommand() {
    }

    public CreateEmployeeCommand(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
