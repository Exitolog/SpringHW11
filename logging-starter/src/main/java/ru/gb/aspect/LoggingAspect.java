package ru.gb.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


import java.util.Arrays;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {

    private final LoggingProperties properties;

    // Before - исполняктся до выполнения метода
    // After - исполняется после выполнения метода = AfterThrowing + AfterReterning
    // AfterThrowing - выполняется при пробрасывании исключения
    // AfterReturning - выполняется при срабатывании метода без получения exceptiona
    // Around ->

    // Bean = TimesheetService, obj = timesheetService
    // proxyTimesheetService(obj)
    // Pointcut - точка входа в аспект

//    @Pointcut("execution(* ru.gb.timesheet.service.TimesheetService.*(..))")
//    public void timesheetServiceMethodsPointcut(){
//    }

    @Pointcut("@annotation(ru.gb.aspect.Logging)") //method
    public void loggingMethodsPointcut(){}

    @Pointcut("@within(ru.gb.aspect.Logging)") // class
    public void loggingTypePointcut() {}

    @Around(value = "loggingMethodsPointcut() || loggingTypePointcut()")
    public Object loggingMethod(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String argument = Arrays.toString(pjp.getArgs());
        if(properties.getPrintArgs()) {
            log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{}, argument = {}",methodName, argument);
        } else {
            log.atLevel(properties.getLevel()).log("Before -> TimesheetService#{}", methodName);
        }
        try {
            return pjp.proceed();
        } finally {
            if(properties.getPrintArgs()) {
                log.atLevel(properties.getLevel()).log("After -> TimesheetService#{}, argument = {}",methodName, argument);
            } else {
                log.atLevel(properties.getLevel()).log("After -> TimesheetService#{}", methodName);
            }
        }
    }




//    @Before(value = "timesheetServiceMethodsPointcut()")
//    public void beforeTimesheetServiceFindById(JoinPoint joinPoint){
//        String methodName = joinPoint.getSignature().getName();
//        log.info("\n Before -> TimesheetService#{}",methodName);
//    }



    // Мой код для ДЗ
    @After(value = "timesheetServiceMethodsPointcut()")
    public void afterTimesheetServiceFindById(JoinPoint joinPoint){

        //Название класса c его точным путем
        //String classLocation = joinPoint.getSignature().getDeclaringType().getName();

        //Название метода
        //String methodName2 = joinPoint.getSignature().getName();

        //Название клссса + метода
        //String classNameAndMethodName = joinPoint.getSignature().toShortString();

        //Тип возвращаемого значения + Путь к классу + Название метода
        String  methodName = joinPoint.getSignature().toString();

        //Значение аргумента, который запрашиваем
        String argument = Arrays.toString(joinPoint.getArgs());

        //Тип возвращаемого значения
        //String typeName = result.getClass().getName();




        log.info("After -> {} = {}", methodName, argument);
        //Например (пример вывода): TimesheetService.findById(Long = 3)
    }

    //получаем инфо и лог при исключении
//    @AfterThrowing(value = "timesheetServiceMethodsPointcut()", throwing = "ex")
//    public void afterTimesheetServiceFindById(JoinPoint joinPoint, Exception ex){
//        String methodName = joinPoint.getSignature().getName();
//        log.info("AfterThrowing -> TimesheetService#{} -> {}",methodName, ex.getClass().getName());
//    }


}
