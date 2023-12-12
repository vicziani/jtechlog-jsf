package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(statements = {"delete from employee"})
class EmployeeRepositoryIT {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void testSaveThenQuery() {
        Employee employee = new Employee();
        employee.setName("John Doe");

        employeeRepository.save(employee);

        Employee e2 = new Employee();
        e2.setName("Jane Doe");

        employeeRepository.save(e2);

        List<Employee> employees =
                employeeRepository.findAll(Sort.by("name"));

        assertEquals(2, employees.size());
        assertEquals("John Doe", employees.get(1).getName());
    }
}
