package com.dcd.finance.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：ZWQ
 * @version ：1.0
 * @date ：2019/10/16 - 18:46
 * @description ：需要登录才能进行操作的注解UserLoginToken
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}
