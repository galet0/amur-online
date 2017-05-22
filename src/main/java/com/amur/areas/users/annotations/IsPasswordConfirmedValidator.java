package com.amur.areas.users.annotations;

import com.amur.areas.users.models.binding.users.RegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class IsPasswordConfirmedValidator implements ConstraintValidator<IsPasswordConfirmed, Object> {

    @Override
    public void initialize(IsPasswordConfirmed isPasswordConfirmed) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if(userClass instanceof RegistrationModel){
            String password = ((RegistrationModel) userClass).getPassword();
            String confirmPassword = ((RegistrationModel) userClass).getConfirmPassword();
            return confirmPassword.equals(password);
        }
        return false;
    }
}
