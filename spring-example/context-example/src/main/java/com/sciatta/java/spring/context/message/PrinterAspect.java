package com.sciatta.java.spring.context.message;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by yangxiaoyu on 2026/9/16<br>
 * All Rights Reserved(C) 2017 - 2026 SCIATTA <br> <p/>
 * PrinterAspect
 */
@Aspect
@Component
@Slf4j
public class PrinterAspect {
    // 在什么地方执行
    @Pointcut("execution(public void com.sciatta.java.spring.context.message.Printer.print(..))")
    public void printMethods() {}

    // 什么时候执行，以及执行的内容是什么
    @Around("printMethods()")
    public Object aroundPrint(ProceedingJoinPoint pjp) throws Throwable {   // 注意异常和返回值（只有 @Around 可以控制）
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getName();
        Object[] args = pjp.getArgs();

        // 前置逻辑
        long start = System.nanoTime();
        log.info("[Printer] 开始执行 {}.print, args={}",
                pjp.getTarget().getClass().getSimpleName(),
                args);

        try {
            Object result = pjp.proceed();

            // 后置逻辑
            long cost = System.nanoTime() - start;
            log.info("[Printer] 执行完成, cost={}ms", cost / 1_000_000);

            return result;

        } catch (Throwable t) {
            // 异常增强
            log.error("[Printer] print 执行异常, method={}", methodName, t);
            throw t;
        }
    }

    @Pointcut("@annotation(printerTitleAnnotation)")
    public void printTitle(PrinterTitleAnnotation printerTitleAnnotation){}   // 通过参数类型匹配注解

    @Before(value = "printTitle(printerTitleAnnotation)", argNames = "printerTitleAnnotation")
    public void beforePrintTitle(PrinterTitleAnnotation printerTitleAnnotation) {
        boolean need = printerTitleAnnotation.isNeed();
        if (!need) {
            return;
        }

        System.out.println(printerTitleAnnotation.value());
    }

}
