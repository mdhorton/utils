/*
 * Copyright (c) 2013-2015 by Mark D. Horton. All rights reserved.
 */

package net.nostromo.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;

@Aspect
public class TimerAspect {

    @Around("execution(@net.nostromo.utils.LogTime * *(..))")
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
}
