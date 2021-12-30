package com.innowisegroup.messenger.aspect;

import com.innowisegroup.messenger.model.Message;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@EnableAspectJAutoProxy
@Aspect
public class CachingRepositoryMethodGetByIdAspect {
    private static Map<Long, Message> cacheGetMessageById;

    @PostConstruct
    private void init() {
        cacheGetMessageById = new HashMap<Long, Message>();
    }

    @Pointcut("execution(com.innowisegroup.messenger.model.Message " +
            "com.innowisegroup.messenger.repository.MessageRepository.getById(Long))")
    public void cachingMethodGetById() {
    }


    @Around("cachingMethodGetById()")
    public Object cachingRepositoryMethodGetByIdExecution4(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Long id = (Long) args[0];
        if (cacheGetMessageById.get(id) != null) {
            System.out.println("нашли в кеше");
            return cacheGetMessageById.get(id);
        }
        Message message = (Message) joinPoint.proceed();
        cacheGetMessageById.put(id, message);
        System.out.println("сходили в файл");
        return message;
    }
}

