package employees;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> listEmployees() {
        return employeeRepository
                .findAll(Sort.by("name"))
                .stream()
                .map(EmployeeDto::new)
                .collect(Collectors.toList());
    }

    public void createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee();
        employee.setName(command.getName());
        employee.setSalary(command.getSalary());

        employeeRepository.save(employee);
    }

    public EmployeeDto findEmployeeById(long id) {
        return new EmployeeDto(employeeRepository.getOne(id));
    }

    @Transactional
    public void modifyEmployee(ModifyEmployeeCommand command) {
        Employee employee = employeeRepository.getOne(command.getId());
        employee.setName(command.getName());
        employee.setSalary(command.getSalary());
    }

    public void deleteEmployee(DeleteEmployeeCommand command) {
        employeeRepository.deleteById(command.getId());
    }

    public List<Integer> listSalaryOptions() {
        return List.of(100_000, 200_000, 500_000);
    }

    public int countEmployeesWithName(String name) {
        return
                employeeRepository.countEmployeesWithName(name);
    }
}
