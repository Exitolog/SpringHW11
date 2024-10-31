package ru.gb.timesheet.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RecoverAspect {

    @Pointcut("@annotation(ru.gb.timesheet.aspect.Recover)")
    public void recoverPointcut() {
    }


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
