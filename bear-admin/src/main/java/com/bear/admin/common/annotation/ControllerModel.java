package com.bear.admin.common.annotation;

import java.lang.annotation.*;

/**
 * Created by mby on 2019/4/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ControllerModel {

    String viewPath() default "noPage";
    String modelName() default "";
    String[] excludeLogs() default {};
}

