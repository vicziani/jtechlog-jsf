package employees;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class EmployeeDto {

    private long id;

    private String name;

    private int salary;

    private LocalDateTime savedAt;

    private String cities;

    public EmployeeDto(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        salary = employee.getSalary();
        savedAt = employee.getSavedAt();
        cities = employee.getAddresses().stream()
                .map(Address::getCity)
                .collect(Collectors.joining(", "));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public LocalDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(LocalDateTime savedAt) {
        this.savedAt = savedAt;
    }
}
