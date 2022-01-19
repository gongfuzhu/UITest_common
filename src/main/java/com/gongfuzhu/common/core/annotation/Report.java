package com.gongfuzhu.common.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Report {

    /**
     * 存放报告的文件名
     * @return
     */
    String fileName() default "Report";

    /**
     * 脚本场景
     * @return
     */
    String scene();

    /**
     * 延迟时间
     * @return
     */
    long delayTime() default 0;

    /**
     * 是否截屏 默认否
     * @return
     */
    boolean screnShot() default false;



}
