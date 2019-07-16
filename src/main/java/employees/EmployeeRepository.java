package employees;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository
    extends JpaRepository<Employee, Long> {

    @Query("select distinct e from Employee e left join fetch e.addresses order by e.name")
    List<Employee> listEmployeesWithAddresses();

//    @Query("select distinct e from Employee e left join fetch e.addresses where e.name = :name")
//    Employee findEmployeeByNameWithAddresses(String name);

    @Query(value = "call find_employees(:name)",
        nativeQuery = true)
    int findEmployeeCountWithName(@Param("name") String name);
}
