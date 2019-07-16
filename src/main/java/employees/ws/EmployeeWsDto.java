package employees.ws;

import employees.EmployeeDto;

public class EmployeeWsDto {

    private String name;

    private int salary;

    public EmployeeWsDto() {
    }

    public EmployeeWsDto(EmployeeDto employee) {
        name = employee.getName();
        salary = employee.getSalary();
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
