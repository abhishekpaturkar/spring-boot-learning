package com.abhishek.module10aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspect {

    @Around("allServiceMethodsPointCut()")
    public Object validateOrderId(ProceedingJoinPoint joinPoint) throws Throwable {
        Object args[] = joinPoint.getArgs();
        Long orderId = (Long) args[0];

        if(orderId > 0) return joinPoint.proceed();

        return "Cannot call with negative order id";
    }

    @Pointcut("execution(* com.abhishek.module10aop.service.impl.*.*(..))")
    public void allServiceMethodsPointCut() {}
}
