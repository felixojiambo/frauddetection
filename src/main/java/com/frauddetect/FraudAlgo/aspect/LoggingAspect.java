package com.frauddetect.FraudAlgo.aspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Pointcut to match all methods in service packages
    @Pointcut("execution(* com.frauddetect.FraudAlgo.service*.*(..))")
    public void serviceLayer() {}

    // Before advice
    @Before("serviceLayer()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before executing: " + joinPoint.getSignature().getName());
    }

    // After Returning advice
    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After executing: " + joinPoint.getSignature().getName() + ", Result: " + result);
    }

    // After Throwing advice
    @AfterThrowing(pointcut = "serviceLayer()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("Exception in: " + joinPoint.getSignature().getName() + ", Exception: " + error);
    }

    // Around advice
    @Around("serviceLayer()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Around before: " + proceedingJoinPoint.getSignature().getName());
        Object result = proceedingJoinPoint.proceed();
        System.out.println("Around after: " + proceedingJoinPoint.getSignature().getName());
        return result;
    }
}
