package org.tpo10.Constraint;
import jakarta.validation.Constraint;
import org.tpo10.Constraint.Validator.PasswordUppercaseValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordUppercaseValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordCheckUppercase {
    String message() default "{org.tpo10.PasswordUppercase.message}";
    Class[] groups() default  {};
    Class[] payload() default  {};

}