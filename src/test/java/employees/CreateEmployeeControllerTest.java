package employees;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateEmployeeControllerTest {

    @Mock
    private MessageContext messageContext;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private CreateEmployeeController createEmployeeController;

    @Test
    public void testCreateEmployee() {
        createEmployeeController.setCommand(new CreateEmployeeCommand("John Doe", 100_000));
        createEmployeeController.createEmployee();

        verify(messageContext).addFlashMessage(eq("employee_has_created"), eq("John Doe"));
        verify(employeeService).createEmployee(argThat(command -> command.getName().equals("John Doe")));
    }

    @Test
    public void testCreateEmployeeWhenEmployeeExists() {
        when(employeeService.countEmployeesWithName(anyString())).thenReturn(1);
        createEmployeeController.setCommand(new CreateEmployeeCommand("John Doe", 100_000));
        createEmployeeController.createEmployee();

        verify(messageContext).addMessage("name_already_exists");
        verify(employeeService, never()).createEmployee(any(CreateEmployeeCommand.class));
    }
}
