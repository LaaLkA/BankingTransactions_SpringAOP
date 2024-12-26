package org.laalka.bankingtransactions_springaop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class TimingAspect {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Around("@annotation(TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        long durationTime = endTime - startTime;
        logger.info("Method " + joinPoint.getSignature().getName()
                + " executed in " + durationTime + " ms");
        return result;
    }
}
