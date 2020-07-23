package com.mer.framework.annotction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义加密注解 @DES
 * @description: 自定义注解，用来标识请求类 或者方法是否使用AOP加密解密
 * @author zq
 */
@Target({ElementType.TYPE,ElementType.METHOD})              // 可以作用在类上和方法上
@Retention(RetentionPolicy.RUNTIME)                               // 运行时起作用
public @interface DES {
    // 参数类中传递加密数据的属性名，默认 sign
    String signName() default "sign";
}
