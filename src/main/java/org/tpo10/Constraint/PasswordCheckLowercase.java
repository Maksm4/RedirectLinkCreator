package org.tpo10.Constraint;
import jakarta.validation.Constraint;
import org.tpo10.Constraint.Validator.PasswordLowercaseValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordLowercaseValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordCheckLowercase {
    String message() default "{org.tpo10.PasswordLowercase.message}";
    Class[] groups() default  {};
    Class[] payload() default  {};
}