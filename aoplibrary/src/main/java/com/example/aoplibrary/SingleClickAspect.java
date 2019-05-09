package com.example.aoplibrary;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;

/**
 * @author puyantao
 * @create 2019/5/8
 * @Describe
 */
@Aspect
public class SingleClickAspect {
    private String TAG = "SingleClickAspect";
    private static int MIN_CLICK_DELAY_TIME = 600;
    private Long lastClickTime = 0L;

    @Pointcut("execution(@com.example.aoplibrary.SingleClick * * (..))")
    public void methodAnnotated() {

    }


    /**
     * @Before() 权限验证
     * @After() 释放内存
     * @Around() 性能监控，用户行为
     */
    @Around("methodAnnotated")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME){
            if (BuildConfig.DEBUG){
                Log.d(TAG, "currentTime:" + currentTime);
            }
            lastClickTime = currentTime;
            try {//执行原方法
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

}














