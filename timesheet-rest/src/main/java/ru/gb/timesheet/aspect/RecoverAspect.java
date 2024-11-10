package ru.gb.timesheet.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.repository.query.ReturnedType;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.JobKOctets;

@Aspect
@Component
@Slf4j
public class RecoverAspect {

    @Pointcut("@annotation(ru.gb.timesheet.aspect.Recover)")
    public void recoverPointcut() {
    }

    //@AfterThrowing(value = "recoverPointcut()", throwing = "ex")
//    public void afterThrowingMethodAspect(JoinPoint joinPoint, Throwable ex){
//        String methodName = joinPoint.getSignature().toShortString();
//        log.info("Recover {} after Exception[{},  {}]", methodName, ex.getClass().getName(), ex.getMessage());
//        //throw  new RuntimeException("Произошла ошибка");
//    }

    @Around(value = "recoverPointcut()")
    public Object afterReturningMethodAspect(ProceedingJoinPoint joinPoint){
        try {
           return joinPoint.proceed();
        } catch (Throwable ex) {
            String methodName = joinPoint.getSignature().toShortString();
            String clazz = joinPoint.getSignature().toString().split(" ")[0];
            // FIXME доделать логику, чтобы достать тип возвращаемого значения
            Object result = clazz; ////;
            log.info("Recover {} after Exception[{},  {}]", methodName, ex.getClass().getName(), ex.getMessage());
            if(result.getClass().isInstance(Object.class)) return null;
            else return 0;
        }
    }

//    @Around(value = "recoverPointcut()")
//    public Object aroundRecoverAspect(ProceedingJoinPoint proceedingJoinPoint){
//        try {
//            return proceedingJoinPoint.proceed();
//        } catch (Throwable th) {
//           return afterThrowingMethodAspect(proceedingJoinPoint, th);
//        }
//            String methodName = proceedingJoinPoint.getSignature().toShortString();
//            log.info("Recover {} after Exception[{},  {}]", methodName, th.getClass().getName(), th.getMessage());



    //Не удалось поиграться, чтобы узнать тип возвращаемого значения, не добавляя returning
    // , которое есть только у аннотации AfterReturning.
//    @Around(value = "recoverPointcut()")//, returning = "result")
//    public Object afterThrowingAspect(ProceedingJoinPoint joinPoint, Object result) {
//        //String exceptionClass = ex.getClass().toString();
//        //String exceptionMessage = ex.getMessage();
//        String methodName = joinPoint.getSignature().toShortString();
//        try {
//            return joinPoint.proceed();
//        } catch (Throwable throwable) {
//            String exClass = throwable.getClass().getName();
//            String exMessage = throwable.getMessage();
//            //("Recovering TimesheetService#findById after Exception[RuntimeException.class, "exception message"])
//            log.info("Recover {} after Exception[{}, {}", methodName, exClass, exMessage);
//            if(result.getClass().isInstance(Object.class)) return null;
//            if(!result.getClass().isInstance(Object.class)) return 0;
//        }
//        return null;
//    }


    }