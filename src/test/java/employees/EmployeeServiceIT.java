package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(statements = {"delete from employee"})
class EmployeeServiceIT {

    @Autowired
    EmployeeService employeeService;

    @Test
    void testCreateThenList() {
        CreateEmployeeCommand createEmployeeCommand =
                new CreateEmployeeCommand("John Doe", 100_000);

        employeeService.createEmployee(createEmployeeCommand);

        List<EmployeeDto> employees = employeeService.listEmployees();

        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());

    }
}
