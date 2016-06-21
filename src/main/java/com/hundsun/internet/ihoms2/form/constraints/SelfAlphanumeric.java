package com.hundsun.internet.ihoms2.form.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**[A-Za-z0-9_] 字母数字下划线.
 * @author: linyy
 * @since: 2014年4月30日 下午3:32:04
 * @history:
 */
@Constraint(validatedBy = { SelfAlphanumericValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfAlphanumeric {
    String message() default "不是字母、数字、下划线！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
