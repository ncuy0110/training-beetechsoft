package com.beetech.mvcspringboot.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.beetech.mvcspringboot..*Controller.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Received request: " + joinPoint.getSignature());
    }

    @AfterThrowing(pointcut = "execution(* com.beetech.mvcspringboot..*Controller.*(..))", throwing = "ex")
    public void logError(Exception ex) {
        logger.error("An error occurred: " + ex.getMessage(), ex);
    }

    @AfterReturning(pointcut = "execution(* com.beetech.mvcspringboot..*Controller.*(..))", returning = "result")
    public void logSuccess(Object result) {
        logger.info("Method executed successfully with result: " + result);
    }
}