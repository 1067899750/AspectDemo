package com.example.aopdemo.aspect;

import com.example.aopdemo.aspect.annotation.LogAnnotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.Modifier;

/**
 * @author puyantao
 * @create 2019/5/10
 * @Describe
 */
@Aspect
public class LogAspect {

    @Pointcut("execution(@com.example.aopdemo.aspect.annotation.LogAnnotation * * (..))")
    public void log(){

    }



    @Before("log()&&@annotation(logAnnotation)")
    public void doBefore(JoinPoint joinPoint, LogAnnotation logAnnotation){
        System.out.println("******拦截前的逻辑******");
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());

        System.out.println("拦截的注解的参数：");
        System.out.println(logAnnotation.module());
        System.out.println(logAnnotation.desc());
    }


    @Around("log()&&@annotation(logAnnotation)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint, LogAnnotation logAnnotation) throws Throwable {
        System.out.println("环绕通知：");
        System.out.println(logAnnotation.module());
        System.out.println(logAnnotation.desc());
        Object result = null;
        result = proceedingJoinPoint.proceed();
        return result;
    }


    @After("log()")
    public void doAfter(){
        System.out.println("******拦截后的逻辑******");
    }





}



















