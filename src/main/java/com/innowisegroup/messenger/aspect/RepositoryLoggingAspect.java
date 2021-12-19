package com.innowisegroup.messenger.aspect;

import com.innowisegroup.messenger.logger.Logger;
import com.innowisegroup.messenger.model.Message;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableAspectJAutoProxy
@Aspect
public class RepositoryLoggingAspect {
    private static Logger logger;


    @Autowired
    public RepositoryLoggingAspect(Logger logger) {
        this.logger = logger;
    }

    @Pointcut("@target(org.springframework.stereotype.Repository)")
    public void repositoryLoggingPointcut() {
    }

    @Before("repositoryLoggingPointcut()")
    public void cashingRepositoryMethodInvocation(JoinPoint joinPoint) {
        logger.log("Invocation method: \n" + joinPoint.getSignature());
        logger.log("Class " + joinPoint.getSourceLocation().getWithinType().getName());
        logger.log("*********************");
    }

    @Around("repositoryLoggingPointcut()")
    public Object loggingRepositoryMethodExecution4(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object results = joinPoint.proceed();
        if (args.length == 0 || results == null) {
            return results;
        }
        logger.log("Invocation method with arguments: \n" + joinPoint.getSignature());
        logger.log("Class " + joinPoint.getSourceLocation().getWithinType().getName());
        logger.log("Arguments: ");
        for (Object arg : args) {
            logger.log(arg.toString());
        }
        logger.log("Results: " + results.toString());
        logger.log("*********************");
        return results;
    }
}
