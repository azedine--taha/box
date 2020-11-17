package com.emailing.box.commons.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Order(1)
@Service
@Aspect
public class BusinessLoggableAspect {

    Logger LOG = LoggerFactory.getLogger(BusinessLoggableAspect.class);
    private static final String KEY_CLASSE_METHOD_RESOURCE = "logClassAndMethodResourceFolder";


    /**
     * All method annotated by @BusinessLoggable are intercepted
     */
    @Pointcut(value = "@annotation(com.emailing.box.commons.aspect.BusinessLoggable)")
    public void anyMethodAnnotatedByCustomAnnotation(){

    }

    @Around("anyMethodAnnotatedByCustomAnnotation()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodeName = joinPoint.getSignature().getName();

            // fill the context.
            MDC.put("logClassAndMethodResourceFolder", className + "/" + methodeName);
            return joinPoint.proceed();
        } catch (Exception e) {
            LOG.error("Error in log around : ", e);
            throw e;
        } finally {
            // remove the context
            MDC.remove(KEY_CLASSE_METHOD_RESOURCE);
        }
    }
}
