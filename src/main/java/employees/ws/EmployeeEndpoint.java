package employees.ws;

import employees.EmployeeService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.stream.Collectors;

@Service
@WebService(targetNamespace = "http://ksh.hu/employees")
public class EmployeeEndpoint {

    private EmployeeService employeeService;

    public EmployeeEndpoint(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @XmlElement(name = "employee")
    @XmlElementWrapper(name = "employees")
    public List<EmployeeWsDto> listEmployees() {
        return employeeService.listEmployees()
                .stream().map(EmployeeWsDto::new)
                .collect(Collectors.toList());
    }
}
