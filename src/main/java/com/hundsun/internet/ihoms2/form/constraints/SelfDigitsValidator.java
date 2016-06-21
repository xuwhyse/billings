package com.hundsun.internet.ihoms2.form.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfDigitsValidator implements
        ConstraintValidator<SelfDigits, String> {

    private final String pattern = "^(\\d+)?$";

    public void initialize(SelfDigits arg0) {

    }

    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if (arg0 == null) {
            return true;
        }
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(arg0);
        boolean b = m.matches();
        return b;
    }

}
