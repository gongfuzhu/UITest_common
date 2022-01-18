package com.gongfuzhu.common.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Report {

    String fileName() default "Report";

    long delayTime() default 0;

    boolean screen() default false;



}
