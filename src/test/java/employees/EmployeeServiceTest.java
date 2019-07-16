package employees;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testListEmployees() {
        when(employeeRepository.listEmployeesWithAddresses())
                .thenReturn(List.of(new Employee(1L, "John Doe", 100_000,
                        List.of(new Address(1L, "1111", "Budapest", "Fo ut 30"),
                                new Address(2L, "2222", "Debrecen", "Nagyerdo 3.")))));

        List<EmployeeDto> employees = employeeService.listEmployees();

        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals("Budapest, Debrecen", employees.get(0).getCities());
    }

    @Test
    public void testCreateEmployee() {
        employeeService.createEmployee(new CreateEmployeeCommand("John Doe", 100_000));

        ArgumentCaptor<Employee> argument = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(argument.capture());
        assertEquals("John Doe", argument.getValue().getName());
        assertEquals(100_000, argument.getValue().getSalary());
    }
}
