package employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void testListEmployees() {
        when(employeeRepository.findAll(any(Sort.class)))
                .thenReturn(List.of(new Employee(1L, "John Doe", 100_000)));

        List<EmployeeDto> employees = employeeService.listEmployees();

        assertEquals(1, employees.size());
        assertEquals("John Doe", employees.get(0).getName());
    }

    @Test
    void testCreateEmployee() {
        employeeService.createEmployee(new CreateEmployeeCommand("John Doe", 100_000));

        verify(employeeRepository).save(argThat(e -> e.getName().equals("John Doe")));
    }
}
