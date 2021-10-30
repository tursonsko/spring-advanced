package pl.strefakursow.springadvanced.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggingAspect {

    /**
     *         '..' ->> oznacza ze podlaczony aspect bedzie do wszystkich klas w tym pakiecie
     *         '*' ->> oznacza wszysrkie metody
     *         '(**)' ->> oznacza wszystkie parametry w metodzie/metodach (kazda ilosc)
     */
    @Pointcut("execution(* pl.strefakursow..*(..))")
    private void anyPublicMethod() {
        log.info("testlog");
    }

    @Before("anyPublicMethod()")
    public void beforeAnyPublicMethod(JoinPoint joinPoint) {
        log.info("BEFORE :: " + joinPoint.getSignature().getName());
    }

    @After("anyPublicMethod()")
    public void afterAnyPublicMethod(JoinPoint joinPoint) {
        log.info("AFTER :: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* pl.strefakursow.springadvanced.controller..*(..))")
    public Object arountControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        Object proceed = joinPoint.proceed();
        log.info("TIME ::" + (System.nanoTime() - start));
        return proceed;
    }
}