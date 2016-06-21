package com.hundsun.internet.ihoms2.form.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**在时间之后.
 * @author: linyy
 * @since: 2014年4月30日 下午3:32:04
 * @history:
 */
@Constraint(validatedBy = { SelfAfterdateValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfAfterdate {
    String message() default "应该不早于与之相比较的时间！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
