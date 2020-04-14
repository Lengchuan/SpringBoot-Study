package com.lengchuan.springBoot.jpa.config.ReadAndWrite.aspect;


import com.lengchuan.springBoot.jpa.config.ReadAndWrite.annotation.TargetDataSource;
import com.lengchuan.springBoot.jpa.config.ReadAndWrite.config.DynamicDataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * aop 配置动态切换数据源
 *
 * @author lengchuan <lishuijun1992@gmail.com>
 * @date 17-4-5
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    @Around("execution(public * com.lengchuan.springBoot.jpa.service..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        if (targetMethod.isAnnotationPresent(TargetDataSource.class)) {
            String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).dataSource();
            System.out.println("----------数据源是:" + targetDataSource + "------");
            DynamicDataSourceHolder.setDataSource(targetDataSource);
        }
        Object result = pjp.proceed();//执行方法
        return result;
    }
}
