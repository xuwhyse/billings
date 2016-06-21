package com.hundsun.internet.ihoms2.form.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfMobileValidator implements
        ConstraintValidator<SelfMobile, String> {

    private final String pattern = "^(1[358]\\d{9})?$";

    public void initialize(SelfMobile arg0) {

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
