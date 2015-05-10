package net.nostromo.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicLong counter = new AtomicLong();

    private final String baseName;

    public NamedThreadFactory(final String baseName) {
        this.baseName = baseName;
    }

    @Override
    public Thread newThread(final Runnable r) {
        final String name = String.format("%s-%04d", baseName, counter.incrementAndGet());
        return new Thread(r, name);
    }
}
