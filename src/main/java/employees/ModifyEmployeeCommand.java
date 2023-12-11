package employees;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ModifyEmployeeCommand {

    private long id;

    @NotBlank
    private String name;

    private int salary;

}
