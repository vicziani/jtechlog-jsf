package employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository
    extends JpaRepository<Employee, Long> {

    @Query(value = "select count(employee.id) from Employee employee where employee.name = :name")
    int countEmployeesWithName(@Param("name") String name);
}
