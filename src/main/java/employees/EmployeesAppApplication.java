package employees;

import employees.ws.EmployeeEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class EmployeesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesAppApplication.class, args);
	}

	@Bean
	public Endpoint endpoint(EmployeeEndpoint employeeEndpoint, Bus bus) {
		var endpoint = new EndpointImpl(bus, employeeEndpoint);
		endpoint.publish("/employees");
		return endpoint;
	}

}
