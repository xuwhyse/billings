package com.billings.form.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**大于等于某数.
 * @author: linyy
 * @since: 2014年4月30日 下午3:32:04
 * @history:
 */
@Constraint(validatedBy = { SelfAfterValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfAfter {
    String message() default "应该大于等于与之相比较的数！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
