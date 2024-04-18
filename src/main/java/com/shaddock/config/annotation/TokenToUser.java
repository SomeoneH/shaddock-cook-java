package com.shaddock.config.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToUser {
    // 当前用户在request中的名字
    String value() default "user";
}
