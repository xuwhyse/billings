package com.hundsun.internet.ihoms2.form.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**非负整数 .
 * @author: linyy
 * @since: 2014年4月29日 下午3:32:04
 * @history:
 */
@Constraint(validatedBy = { SelfDigitsValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfDigits {
    String message() default "应该为非负整数！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
