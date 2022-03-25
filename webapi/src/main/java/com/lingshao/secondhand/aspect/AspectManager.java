package com.lingshao.secondhand.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * @author LingShao
 * @date 2021/4/18
 */
public abstract class AspectManager implements AspectApi {

    @Override
    public abstract Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)throws Throwable;

}
