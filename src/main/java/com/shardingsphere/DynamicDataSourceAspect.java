package com.shardingsphere;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Order(-10)
@Component

public class DynamicDataSourceAspect {


    private static final Logger LOGGER = LogManager.getLogger(DynamicDataSourceAspect.class);


    @Before("within(@com.shardingsphere.TargetDataSource *) || @annotation(com.shardingsphere.TargetDataSource)")
    public void changeDataSource(JoinPoint point) {
        MethodSignature joinPointObject = (MethodSignature) point.getSignature();
        TargetDataSource targetDataSource = null;
        if (joinPointObject.getDeclaringType().isAnnotationPresent(TargetDataSource.class)) {
            targetDataSource = (TargetDataSource) joinPointObject.getDeclaringType().getAnnotation(TargetDataSource.class);
        }
        Method method = joinPointObject.getMethod();
        if (method.isAnnotationPresent(TargetDataSource.class)) {
            targetDataSource = method.getAnnotation(TargetDataSource.class);
        }
        if (targetDataSource.isDatabaseShardingOnly()) {
            //获取当前的指定的数据源;
            DataSourceType dsId = targetDataSource.value();
            HintManager.getInstance().setDatabaseShardingValue(dsId.getIdentity());
        }

    }


    @After(value = "@annotation(com.shardingsphere.TargetDataSource)")
    public void restoreDataSource(JoinPoint point) {

        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        //HintManagerHolder.clear();

        //清理ThreadLocal中的内容
        HintManager.getInstance().close();
    }


}