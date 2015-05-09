/*
 * Copyright (c) 2013-2015 by Mark D. Horton. All rights reserved.
 */

package net.nostromo.utils.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Aspect
public class LogAspect {

    @Around("execution(@net.nostromo.utils.aop.LogTime * *(..))")
    public Object time(final ProceedingJoinPoint pjp) throws Throwable {
        final long start = System.nanoTime();

        try {
            return pjp.proceed();
        } finally {
            final long elapsed = System.nanoTime() - start;
            final String formatted = String.format("%,.3f", elapsed / 1000000.0);

            final Class clazz = pjp.getTarget().getClass();
            final String method = pjp.getSignature().getName();
            LoggerFactory.getLogger(clazz).debug("{}: {} millis", method, formatted);
        }
    }

    @Around("execution(@net.nostromo.utils.aop.LogSize * *(..))")
    public Object size(final ProceedingJoinPoint pjp) throws Throwable {
        final Object obj = pjp.proceed();

        final Collection col = (Collection) obj;
        final String formatted = String.format("%,d", col.size());

        final Class clazz = pjp.getTarget().getClass();
        final String method = pjp.getSignature().getName();
        LoggerFactory.getLogger(clazz).debug("{}: {} items", method, formatted);

        return obj;
    }
}
