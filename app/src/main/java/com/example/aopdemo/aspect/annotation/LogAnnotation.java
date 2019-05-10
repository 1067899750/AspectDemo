package com.example.aopdemo.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author puyantao
 * @create 2019/5/10
 * @Describe
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    /**
     * 所属模块
     * @return
     */
    String module()  default "日志模块";

    /**
     * 动作描述
     * @return
     */
    String desc()  default "无动作";
}





















