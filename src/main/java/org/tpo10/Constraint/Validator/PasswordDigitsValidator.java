package org.tpo10.Constraint.Validator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.tpo10.Constraint.PasswordCheckDigits;


public class PasswordDigitsValidator implements ConstraintValidator<PasswordCheckDigits, String> {


    @Override
    public void initialize(PasswordCheckDigits constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.isEmpty() || password.matches("^(?:\\D*\\d\\D*){3,}$");
    }
}
