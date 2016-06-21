package com.hundsun.internet.ihoms2.form.constraints;

import java.sql.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfDateValidator implements ConstraintValidator<SelfDate, String> {

    public void initialize(SelfDate arg0) {

    }

    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if (arg0 == null || "".equals(arg0)) {
            return true;
        }
        try {
            Date date = Date.valueOf(arg0);
            String str = date.toString();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
