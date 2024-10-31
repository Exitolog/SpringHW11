package ru.gb.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimerAspect {


    @Pointcut("@annotation(ru.gb.timesheet.aspect.Timer)") //method
    public void timerPointcut(){}

//    @Pointcut("@within(ru.gb.timesheet.aspect.Timer)") // class
//    public void timerTypePointcut() {}


//    @Around(value = "timerPointcut()")
//    public Object aroundTimesheetServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
//        //проверка на активную аннотацию таймер
//        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
//        if(!methodSignature.getMethod().getAnnotation(Timer.class).enabled()){
//            return pjp.proceed();
//        }
//        //конец проверки
//        long start = System.currentTimeMillis();
//        try {
//            return pjp.proceed();
//        } finally {
//            long duration = System.currentTimeMillis() - start;
//            log.info("TimesheetService#{} duration = {}ms", pjp.getSignature().getName(), duration);
//        }
//    }


}
