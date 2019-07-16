package employees;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class DivByValidator implements ConstraintValidator<DivBy, Integer> {

    private int number;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer % number == 0;
    }

    @Override
    public void initialize(DivBy constraintAnnotation) {
        this.number = constraintAnnotation.number();
    }
}
