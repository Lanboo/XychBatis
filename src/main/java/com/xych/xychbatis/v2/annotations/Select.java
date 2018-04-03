package com.xych.xychbatis.v2.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Description
 * @author 晓月残魂
 * @CreateDate 2018年4月2日下午10:05:27
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select
{
    String name() default "";

    String value();
}
