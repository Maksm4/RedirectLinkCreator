package org.tpo10.Constraint.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.tpo10.Constraint.PasswordCheckDigits;
import org.tpo10.Constraint.PasswordCheckLowercase;

public class PasswordLowercaseValidator implements ConstraintValidator<PasswordCheckLowercase, String> {

    @Override
    public void initialize(PasswordCheckLowercase constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.isEmpty() || password.matches("^(?=.*[a-z]).*$");
    }
}
