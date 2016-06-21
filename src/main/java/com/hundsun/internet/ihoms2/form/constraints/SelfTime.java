package com.hundsun.internet.ihoms2.form.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**时间.
 * @author: linyy
 * @since: 2014年4月30日 下午3:32:04
 * @history:
 */
@Constraint(validatedBy = { SelfTimeValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfTime {
    String message() default "时间格式 不正确！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
