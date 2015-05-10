package net.nostromo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.ZoneId;

public class Utils {

    public static final ZoneId NY = ZoneId.of("America/New_York");
    public static final Charset UTF8 = Charset.forName("utf-8");
    public static final char NL = '\n';

    public static String readClassPathFile(final String fileName) throws IOException {
        final ClassLoader cl = Utils.class.getClassLoader();

        try (final InputStream is = cl.getResourceAsStream(fileName);
             final BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF8))) {
            final StringBuilder sb = new StringBuilder(1024);

            while (true) {
                final String line = br.readLine();
                if (line == null) break;
                sb.append(line).append(NL);
            }

            return sb.toString();
        }
    }

    public static String f(final double val) {
        return String.format("%,.2f", val);
    }
}
