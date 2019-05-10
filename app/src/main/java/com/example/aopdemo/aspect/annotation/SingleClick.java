package com.example.aopdemo.aspect.annotation;

/**
 * @author puyantao
 * @create 2019/5/8
 * @Describe
 */


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ANNOTATION_TYPE,//给注解注解（这貌似把自己不当类来看）
 * ElementType.FIELD  //注解作用于变量
 * ElementType.METHOD //注解作用于方法
 * ElementType.PARAMETER //注解作用于参数
 * ElementType.CONSTRUCTOR //注解作用于构造方法
 * ElementType.LOCAL_VARIABLE //注解作用于局部变量
 * ElementType.PACKAGE //注解作用于包
 */

@Target(ElementType.METHOD)
/**
 *  SOURCE, //源码状态运行，
 *  CLASS, //编译类文件时运行
 *  RUNTIME //运行时运行
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SingleClick {
}
