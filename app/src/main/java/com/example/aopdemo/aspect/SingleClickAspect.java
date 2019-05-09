package com.example.aopdemo.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author puyantao
 * @create 2019/5/8
 * @Describe
 */
@Aspect
public class SingleClickAspect {

    @Pointcut("execution(@com.example.aopdemo.aspect.SingleClick * * (..))")
    public void methodAnnotated() {

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
        Object result = joinPoint.proceed();
        long lastTime = System.currentTimeMillis();
        Log.i("--->", "lastTime:" + lastTime);
        return result;
    }


}














