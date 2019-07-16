package employees;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DivByValidator.class)
public @interface DivBy {

    String message() default "Can not divide with {number}!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int number() default 10;
}
