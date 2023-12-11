package employees;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeCommand {

    @NotBlank
    private String name;

    private int salary;

}
