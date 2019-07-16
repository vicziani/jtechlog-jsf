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
@Sql(statements = {"delete from address", "delete from employee"})
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveThenQuery() {
        Employee employee = new Employee();
        employee.setName("John Doe");

        Address a1 = new Address();
        a1.setCity("Budapest");
        a1.setEmployee(employee);
        employee.addAddress(a1);

        Address a2 = new Address();
        a2.setCity("Békéscsaba");
        employee.addAddress(a2);
        a2.setEmployee(employee);

        employeeRepository.save(employee);

        Employee e2 = new Employee();
        e2.setName("Jane Doe");

        Address a3 = new Address();
        a3.setCity("Debrecen");
        e2.addAddress(a3);

        Address a4 = new Address();
        a4.setCity("Miskolc");
        e2.addAddress(a4);

        employeeRepository.save(e2);

        List<Employee> employees =
                employeeRepository.listEmployeesWithAddresses();

        assertEquals(2, employees.size());
        assertEquals("John Doe", employees.get(1).getName());
    }
}
