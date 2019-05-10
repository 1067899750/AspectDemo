package com.example.aopdemo.aspect;

import com.example.aopdemo.aspect.annotation.SelectAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author puyantao
 * @create 2019/5/9
 * @Describe
 */

@Aspect
public class SelectClickAspect {

    @Pointcut("execution(@com.example.aopdemo.aspect.annotation.SelectAnnotation * * (..))")
    public void log() {

    }


    /**
     * @Before() 权限验证
     * @After() 释放内存
     * @Around() 性能监控，用户行为
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("******拦截前的逻辑******");

        //sharkItOff
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        //MainActivity
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        //com.example.aopdemo.MainActivity
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        //private
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));


        //获取到所有的参数值的数组
        Object[] joinPointArgs = joinPoint.getArgs();
        System.out.println("所有的参数值的数组:" + joinPointArgs);
        for (Object o : joinPointArgs) {
            System.out.println("参数名字:" + o.toString());
        }


        //获取方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String meathodName = signature.getMethod().getName();  //sharkItOff

        //获取参数
        String[] parameterNames = signature.getParameterNames();
        System.out.println("parameterNames:" + parameterNames);
        for (String str : parameterNames) {
            System.out.println("参数类型 -- >:" + str);
        }

        //获取返回值类型
        System.out.println("返回值类型 -- >:" + signature.getMethod().getReturnType());


        //获取注解
        Class<?> classTarget = joinPoint.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        Method objMethod = classTarget.getMethod(meathodName, par);
        objMethod.setAccessible(true); //设置允许访问私有方法
        SelectAnnotation selectAnnotation = objMethod.getAnnotation(SelectAnnotation.class);
        System.out.println("获取注解:" + selectAnnotation);
        System.out.println("获取注解名字:" + selectAnnotation.name());





        Object result = joinPoint.proceed();
        return result;
    }

}
