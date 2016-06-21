package com.hundsun.internet.ihoms2.form.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfEqualToValidator implements
        ConstraintValidator<SelfEqualTo, String[]> {

    public void initialize(SelfEqualTo arg0) {

    }

    public boolean isValid(String[] arg0, ConstraintValidatorContext arg1) {
        if (arg0[0] == null && arg0[1] == null) {
            return true;
        }
        if (arg0[0] == null && arg0[1] != null) {
            return false;
        }
        if (arg0[0] != null && arg0[1] == null) {
            return false;
        }
        String value = arg0[0];
        String valueTarget = arg0[1];
        if (value.equals(valueTarget)) {
            return true;
        }
        return false;
    }
}
