package com.youmayon.lebang.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志切面类
 * Created by Jawinton on 2017/3/3.
 */
@Aspect
public class LoggingAspect {
    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * service执行时间超过该时间，需要记录慢日志
     */
    private static final int MIN_WARN_MILLISECONDS = 100;

    /**
     * 切入点，service的所有实现类的所有方法
     */
    @Pointcut("execution(* *.service.impl.*.*(..))")
    public void service() {}

    /**
     * 记录service类方法的执行时间
     * @param jp
     * @return service方法返回对象
     * @throws Throwable service方法抛出的异常记录日志后直接抛出
     */
    @Around("service()")
    public Object logServiceExecuteTime(ProceedingJoinPoint jp) throws Throwable {
        Object result;
        Signature signature = jp.getSignature();
        try {
            long beforeTimestamp = System.currentTimeMillis();
            result = jp.proceed();
            long afterTimestamp = System.currentTimeMillis();
            long executeTime = afterTimestamp - beforeTimestamp;
            if (executeTime > LoggingAspect.MIN_WARN_MILLISECONDS) {
                logger.warn("Execute Time: {}, Class Name: {}, Method Name: {}", executeTime, signature.getDeclaringTypeName(), signature.getName());
            } else {
                logger.debug("{}.{}", signature.getDeclaringTypeName(), signature.getName());
            }
        } catch (Throwable e) {
            logger.error("Call {}.{} error: {}", signature.getDeclaringTypeName(), signature.getName(), e.getMessage());
            throw e;
        }
        return result;
    }
}
