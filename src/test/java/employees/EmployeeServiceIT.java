package employees;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = {"delete from employee"})
public class EmployeeServiceIT {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testCreateThenList() {
        CreateEmployeeCommand createEmployeeCommand =
                new CreateEmployeeCommand("John Doe", 100_000);

        employeeService.createEmployee(createEmployeeCommand);

        List<EmployeeDto> employees = employeeService.listEmployees();

        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());

    }
}
