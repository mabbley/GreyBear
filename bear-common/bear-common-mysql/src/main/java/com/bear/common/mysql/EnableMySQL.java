package com.bear.common.mysql;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by mby on 2019/4/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MySQLConfig.class})
public @interface EnableMySQL {
}
