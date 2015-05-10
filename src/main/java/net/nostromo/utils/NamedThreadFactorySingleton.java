package net.nostromo.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class NamedThreadFactorySingleton {

    private static final Map<String, ThreadFactory> map = new HashMap<>();

    public static synchronized ThreadFactory getInstance(final String name) {
        ThreadFactory factory = map.get(name);
        if (factory == null) {
            factory = new NamedThreadFactory(name);
            map.put(name, factory);
        }
        return factory;
    }

    private static class NamedThreadFactory implements ThreadFactory {
        private final AtomicLong counter = new AtomicLong();

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
}
