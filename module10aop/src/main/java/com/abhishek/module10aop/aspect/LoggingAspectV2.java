package com.abhishek.module10aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectV2 {

    @Before("allServiceMethodsPointCut()")
    public void beforeServiceMethodCall(JoinPoint joinPoint) {
        log.info("Before Advice Method Call {}", joinPoint.getSignature());
    }

//    @After("allServiceMethodsPointCut()")
//    @AfterReturning(value = "allServiceMethodsPointCut()", returning = "returnedObj")
    public void afterServiceMethodCall(JoinPoint joinPoint, Object returnedObj) {
        log.info("After Advice Method Call: {}", joinPoint.getSignature());
        log.info("After returning returned value: {}", returnedObj);
    }

    @AfterThrowing("allServiceMethodsPointCut()")
    public void afterServiceMethodCallThrows(JoinPoint joinPoint) {
        log.info("After Throwing Advice Method Call: {}", joinPoint.getSignature());

    }

    @Around("allServiceMethodsPointCut()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object returnValue = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();

        Long diff = endTime - startTime;
        log.info("Execution time for {} in {} ms", joinPoint.getSignature(), diff);
        return returnValue;
    }

    @Pointcut("execution(* com.abhishek.module10aop.service.impl.*.*(..))")
    public void allServiceMethodsPointCut() {}
}
