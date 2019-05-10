package com.example.aopdemo.aspect;

import com.example.aopdemo.aspect.annotation.AsyncAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * @author puyantao
 * @create 2019/5/10
 * @Describe
 */
@Aspect
public class AsyncAspec {

    @Pointcut("execution(@com.example.aopdemo.aspect.annotation.AsyncAnnotation * * (..))")
    public void doAsyncThread(){

    }






    @Before("doAsyncThread()&&@annotation(asyncAnnotation)")
    public void doBefore(final ProceedingJoinPoint joinPoint, AsyncAnnotation asyncAnnotation){
        System.out.println("doBefore -->" + Thread.currentThread());
//        Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
//
//            }
//        }).subscribeOn(Schedulers.io()).subscribe();
    }

    @Around("doAsyncThread()&&@annotation(asyncAnnotation)")
    public Object doAround(final ProceedingJoinPoint joinPoint, AsyncAnnotation asyncAnnotation)  {
        final Object[] o = {null};
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                try {
                    o[0] = joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()).subscribe();
        return o[0];
    }


    @After("doAsyncThread()")
    public void doAfter(ProceedingJoinPoint joinPoint){
        System.out.println("doAfter -->" + Thread.currentThread());
    }




}













