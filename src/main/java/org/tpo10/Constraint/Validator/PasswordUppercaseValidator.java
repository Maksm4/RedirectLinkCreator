package org.tpo10.Constraint.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.tpo10.Constraint.PasswordCheckUppercase;

public class PasswordUppercaseValidator implements ConstraintValidator<PasswordCheckUppercase, String> {

    @Override
    public void initialize(PasswordCheckUppercase constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.isEmpty() || password.matches("^(.*[A-Z]){2,}.*$");
    }
}
