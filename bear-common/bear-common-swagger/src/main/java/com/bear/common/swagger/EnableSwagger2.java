package com.bear.common.swagger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by mby on 2019/4/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@springfox.documentation.swagger2.annotations.EnableSwagger2
@Import({Swagger2Config.class})
public @interface EnableSwagger2 {
}
