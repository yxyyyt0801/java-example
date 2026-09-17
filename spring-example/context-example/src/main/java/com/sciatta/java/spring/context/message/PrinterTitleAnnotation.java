package com.sciatta.java.spring.context.message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yangxiaoyu on 2026/9/17<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * PrinterTitleAnnotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PrinterTitleAnnotation {
    String value() default "";
    boolean isNeed() default false;
}
