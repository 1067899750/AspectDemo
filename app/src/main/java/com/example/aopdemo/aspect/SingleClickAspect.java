package com.example.aopdemo.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author puyantao
 * @create 2019/5/8
 * @Describe
 */
@Aspect
public class SingleClickAspect {

    @Pointcut("execution(@com.example.aopdemo.aspect.annotation.SingleClick * * (..))")
    public void methodAnnotated() { // 定义切点

    }


    /**
     * @Before() 权限验证
     * @After() 释放内存
     * @Around() 性能监控，用户行为
     */
    @Around("methodAnnotated()")
    public Object aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        long currentTime = System.currentTimeMillis();
        Log.i("--->", "currentTime:" + currentTime);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        method.setAccessible(true);
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        Log.i("--->", "Annotation:" + declaredAnnotations[0]);
        Object result = joinPoint.proceed();
        long lastTime = System.currentTimeMillis();
        Log.i("--->", "lastTime:" + lastTime);
        return result;
    }


}














