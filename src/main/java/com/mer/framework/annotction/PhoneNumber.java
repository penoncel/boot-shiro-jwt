package com.mer.framework.annotction;



import com.mer.framework.annotction.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * 自定义手机号注解 用来验证手机号格式
 * @author zhaoqi
 * @date 2020/5/20 17:20
 */
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "手机号无效";
    Class[] groups() default {};
    Class[] payload() default {};
}
