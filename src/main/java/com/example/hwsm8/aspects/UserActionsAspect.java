package com.example.hwsm8.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserActionsAspect {
    /**
     * @Pointcut указывает место, где внедряется аспект
     */
    @Pointcut("@annotation(com.example.hwsm8.aspects.TrackUserAction)")
    public void logPointcut() {
    }

    /**
     * Аспект, который выполняется после целевого метода, выводит информацию в консоль о целевом методе,
     * который уже выполнился
     *
     * @param joinPoint - точка вызова метода
     * @throws Throwable
     */
    /**
     * Аннотация @TrackUserAction поставлена в методах класса NoteService
     */
    @AfterReturning("logPointcut()")
    public void logExecutionMethod(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        System.out.println("Execution class " + className + "method " + methodName);
    }

    /**
     * Аспект, который выполняется после выброса исключения, выводит информацию в консоль об ошибке
     * в целевом методе
     *
     * @param joinPoint - точка вызова метода
     * @param ex        - исключение
     */
    /**
     * Аннотация @TrackUserAction поставлена в методах класса NoteController
     */
    @AfterThrowing(value = "logPointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        System.out.println("Error execution class " + className + " method " + methodName + " exception " + ex);
    }
}
