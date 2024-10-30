package ru.gb.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.gb.timesheet.model.Timesheet;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    // Before - исполняктся до выполнения метода
    // After - исполняется после выполнения метода = AfterThrowing + AfterReterning
    // AfterThrowing - выполняется при пробрасывании исключения
    // AfterReturning - выполняется при срабатывании метода без получения exceptiona
    // Around ->

    // Bean = TimesheetService, obj = timesheetService
    // proxyTimesheetService(obj)

    // Pointcut - точка входа в аспект
    @Pointcut("execution(* ru.gb.timesheet.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointcut(){

    }

    @Before(value = "timesheetServiceMethodsPointcut()")
    public void beforeTimesheetServiceFindById(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        log.info("\n Before -> TimesheetService#{}",methodName);
    }



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


        log.info("\n After -> {} = {}", methodName, argument);
        //Например (пример вывода): TimesheetService.findById(Long = 3)
    }

    //получаем инфо и лог при исключении
//    @AfterThrowing(value = "timesheetServiceMethodsPointcut()", throwing = "ex")
//    public void afterTimesheetServiceFindById(JoinPoint joinPoint, Exception ex){
//        String methodName = joinPoint.getSignature().getName();
//        log.info("After -> TimesheetService#{} -> {}",methodName, ex.getClass().getName());
//    }


}
