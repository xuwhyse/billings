package com.hundsun.internet.ihoms2.form.constraints;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfIdcardValidator implements
        ConstraintValidator<SelfIdcard, String> {

    private final String pattern = "^(\\d{14}(\\d{3})?[\\w\\d])?$";

    public void initialize(SelfIdcard arg0) {

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
