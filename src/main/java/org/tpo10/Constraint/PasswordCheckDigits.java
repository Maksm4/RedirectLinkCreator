package org.tpo10.Constraint;

import jakarta.validation.Constraint;
import org.tpo10.Constraint.Validator.PasswordDigitsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordDigitsValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordCheckDigits {
    String message() default "{org.tpo10.PasswordCheckDigits.message}";
    Class[] groups() default  {};
    Class[] payload() default  {};
}