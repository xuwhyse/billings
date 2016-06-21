package com.hundsun.internet.ihoms2.form.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfUsernameValidator implements
        ConstraintValidator<SelfUsername, String> {

    private final String pattern = "^(([a-zA-Z0-9_@.]{6,30}))?$";

    public void initialize(SelfUsername arg0) {

    }

    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        if (arg0 == null) {
            return true;
        }
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(arg0);
        boolean b = m.matches();
        return b;
    }

}
