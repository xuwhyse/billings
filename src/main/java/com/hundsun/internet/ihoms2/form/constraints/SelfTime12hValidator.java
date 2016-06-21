package com.hundsun.internet.ihoms2.form.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfTime12hValidator implements
        ConstraintValidator<SelfTime12h, String> {

    private final String pattern = "^((0?[1-9]|1[012])(:[0-5]\\d){1,2}(\\ ?[AP]M))?$";

    public void initialize(SelfTime12h arg0) {

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
