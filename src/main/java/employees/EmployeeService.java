package employees;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeDto> listEmployees() {
        return employeeRepository
                .findAll(Sort.by("name"))
                .stream()
                .map(EmployeeDto::new)
                .collect(Collectors.toList());
    }

    public void createEmployee(CreateEmployeeCommand command) {
        if (isEmployeeWithName(command.getName())) {
            throw new NameAlreadyExistsException("Employee exists with name: %s".formatted(command.getName()));
        }
        Employee employee = new Employee();
        employee.setName(command.getName());
        employee.setSalary(command.getSalary());

        employeeRepository.save(employee);
    }

    public EmployeeDto findEmployeeById(long id) {
        return new EmployeeDto(employeeRepository.findById(id).orElseThrow(illegalArgumentException(id)));
    }

    @Transactional
    public void modifyEmployee(ModifyEmployeeCommand command) {
        Employee employee = employeeRepository.findById(command.getId()).orElseThrow(illegalArgumentException(command.getId()));
        employee.setName(command.getName());
        employee.setSalary(command.getSalary());
    }

    public void deleteEmployee(DeleteEmployeeCommand command) {
        employeeRepository.deleteById(command.getId());
    }

    public List<Integer> listSalaryOptions() {
        return List.of(100_000, 200_000, 500_000);
    }

    private boolean isEmployeeWithName(String name) {
        int count =
                employeeRepository.countEmployeesWithName(name);
        return count > 0;
    }

    private static Supplier<IllegalArgumentException> illegalArgumentException(long id) {
        return () -> new IllegalArgumentException("Employee not found with id: %d".formatted(id));
    }
}
