package com.abhishek.module10aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.abhishek.module10aop.service.impl.*.*(..))")
    public void beforeShipmentServiceMethods(JoinPoint joinPoint) {
        log.info("Before method call kind : {}", joinPoint.getKind()); // method-execution
        log.info("Before method call signature : {}", joinPoint.getSignature()); // method signature
    }

    @After("myLoggingAndAopMethodsPointCut()")
    public void afterMyLoggingAndAopMethodsPointCutAdvice() {
        log.info("After My Logging Annotation Calls")
    }


    @Before("within(com.abhishek.module10aop.service.impl.*)")
    public void beforeServiceImplCalls() {
        log.info("Service Impl calls");
    }

    @Before("myLoggingAndAopMethodsPointCut()")
    public void beforeMyLoggingAnnotationCall() {
        log.info("Before MyLogging Annotation call & Aop");
    }

    @Pointcut("@annotation(com.abhishek.module10aop.aspect.MyLogging) || within(com.abhishek.module10aop.service.impl.*)")
    public void myLoggingAndAopMethodsPointCut() {}
}
