package com.hundsun.internet.ihoms2.form.constraints;

import java.sql.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfAfterdateValidator implements
        ConstraintValidator<SelfAfterdate, String[]> {

    public void initialize(SelfAfterdate arg0) {

    }

    public boolean isValid(String[] arg0, ConstraintValidatorContext arg1) {
        if (arg0[0] == null || "".equals(arg0[0])) {
            return true;
        }
        if (arg0[1] == null || "".equals(arg0[1])) {
            return true;
        }
        try {
            Date date = Date.valueOf(arg0[0]);
            Date dateTarget = Date.valueOf(arg0[1]);
            long value = date.getTime() - dateTarget.getTime();
            if (value >= 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
