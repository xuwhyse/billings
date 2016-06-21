package com.hundsun.internet.ihoms2.form.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfAfterValidator implements
        ConstraintValidator<SelfAfter, String[]> {

    public void initialize(SelfAfter arg0) {

    }

    public boolean isValid(String[] arg0, ConstraintValidatorContext arg1) {
        if (arg0[0] == null || "".equals(arg0[0])) {
            return true;
        }
        if (arg0[1] == null || "".equals(arg0[1])) {
            return true;
        }
        try {
            Double value = Double.parseDouble(arg0[0]);
            Double valueTarget = Double.parseDouble(arg0[1]);
            if (value >= valueTarget) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
