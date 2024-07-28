package org.tpo10.Constraint;
import jakarta.validation.Constraint;
import org.tpo10.Constraint.Validator.PasswordSpecialValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordSpecialValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordCheckSpecial {
    String message() default "{org.tpo10.PasswordSpecial.message}";
    Class[] groups() default  {};
    Class[] payload() default  {};
}