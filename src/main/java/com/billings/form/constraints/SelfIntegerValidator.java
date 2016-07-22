package com.billings.form.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfIntegerValidator implements
        ConstraintValidator<SelfInteger, String> {

    private final String pattern = "^(-?\\d+)?$";

    public void initialize(SelfInteger arg0) {

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
