package com.example.aopdemo.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @describe 指定方法切入
 * @euthor puyantao
 * @email puyantao@purang.com
 * @create 2019/8/21 10:56
 */
@Aspect
public class AspectNew {


    @Around("call(* com.example.aopdemo.MainActivity.setContentView(..))")
    public void invokeSetContentView(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Log.e("--->", "执行setContentView方法前:" + System.currentTimeMillis());
        proceedingJoinPoint.proceed();
        Log.e("--->", "执行setContentView方法后:" + System.currentTimeMillis());
    }

}
