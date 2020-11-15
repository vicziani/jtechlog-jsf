package employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateEmployeeControllerTest {

    @Mock
    MessageContext messageContext;

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    CreateEmployeeController createEmployeeController;

    @Test
    void testCreateEmployee() {
        createEmployeeController.setCommand(new CreateEmployeeCommand("John Doe", 100_000));
        createEmployeeController.createEmployee();

        verify(messageContext).addFlashMessage(eq("employee_has_been_created"), eq("John Doe"));
        verify(employeeService).createEmployee(argThat(command -> command.getName().equals("John Doe")));
    }

    @Test
    void testCreateEmployeeWhenEmployeeExists() {
        when(employeeService.countEmployeesWithName(anyString())).thenReturn(1);
        createEmployeeController.setCommand(new CreateEmployeeCommand("John Doe", 100_000));
        createEmployeeController.createEmployee();

        verify(messageContext).addMessage("name_already_exists");
        verify(employeeService, never()).createEmployee(any(CreateEmployeeCommand.class));
    }
}
