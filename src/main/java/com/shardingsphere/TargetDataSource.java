package com.shardingsphere;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    DataSourceType value();

    boolean isDatabaseShardingOnly() default true;
}