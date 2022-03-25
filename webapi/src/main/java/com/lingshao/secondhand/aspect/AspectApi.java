package com.lingshao.secondhand.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @deprecated  装饰器模式
 * @author LingShao
 * @date 2021/4/18
 */
public interface AspectApi {

     Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)throws Throwable;

}
