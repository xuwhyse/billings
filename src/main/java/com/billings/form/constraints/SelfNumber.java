package com.billings.form.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**数字 .
 * @author: linyy
 * @since: 2014年4月29日 下午3:32:53
 * @history:
 */
@Constraint(validatedBy = { SelfNumberValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfNumber {
    String message() default "数字格式不正确！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
