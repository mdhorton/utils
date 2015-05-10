package net.nostromo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class PropertiesSingleton extends Properties {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesSingleton.class);

    private PropertiesSingleton() {
        try {
            final String propStr = Utils.readClassPathFile("app.properties");
            load(new StringReader(propStr));
        } catch (final IOException e) {
            LOG.error("error", e);
        }
    }

    private static class Singleton {
        private static final PropertiesSingleton INSTANCE = new PropertiesSingleton();
    }

    public static PropertiesSingleton getInstance() {
        return Singleton.INSTANCE;
    }

    public String get(final String name) {
        return getProperty(name);
    }

    public int getInt(final String name) {
        return Integer.parseInt(getProperty(name));
    }

    public long getLong(final String name) {
        return Long.parseLong(getProperty(name));
    }

    public boolean getBoolean(final String name) {
        return Boolean.parseBoolean(getProperty(name));
    }
}
