package net.nostromo.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class NostromoProperties extends Properties {

    public NostromoProperties(final String propFileName) throws IOException {
        final String propStr = Utils.readClassPathFile(propFileName);
        load(new StringReader(propStr));
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
