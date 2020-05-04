package com.fzutopic.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AdminLoginToken {
    boolean required() default true;
}
