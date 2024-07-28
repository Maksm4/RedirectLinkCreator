package org.tpo10.Constraint.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.tpo10.Constraint.PasswordCheckSpecial;

public class PasswordSpecialValidator implements ConstraintValidator<PasswordCheckSpecial, String> {
    @Override
    public void initialize(PasswordCheckSpecial constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.isEmpty() || password.matches("^(?:.*[^\\w\\s]){4}.*$");
    }
}
