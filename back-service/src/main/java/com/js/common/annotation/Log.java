package com.js.common.annotation;

import java.lang.annotation.*;

/**
 * @Description 系统日志切面注解
 * @Author jiangshuang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String value() default "";
}
