/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import org.springframework.lang.Nullable;

public final class SpringProperties {
    private static final String PROPERTIES_RESOURCE_LOCATION = "spring.properties";
    private static final Properties localProperties;

    private SpringProperties() {
    }

    public static void setProperty(String key, @Nullable String value) {
        if (value != null) {
            localProperties.setProperty(key, value);
        } else {
            localProperties.remove(key);
        }
    }

    @Nullable
    public static String getProperty(String key) {
        String value = localProperties.getProperty(key);
        if (value == null) {
            try {
                value = System.getProperty(key);
            } catch (Throwable ex) {
                System.err.println("Could not retrieve system property '" + key + "': " + ex);
            }
        }
        return value;
    }

    public static void setFlag(String key) {
        localProperties.put(key, Boolean.TRUE.toString());
    }

    public static boolean getFlag(String key) {
        return Boolean.parseBoolean(SpringProperties.getProperty(key));
    }

    static {
        block14: {
            localProperties = new Properties();
            try {
                URL url;
                ClassLoader cl = SpringProperties.class.getClassLoader();
                URL uRL = url = cl != null ? cl.getResource(PROPERTIES_RESOURCE_LOCATION) : ClassLoader.getSystemResource(PROPERTIES_RESOURCE_LOCATION);
                if (url == null) break block14;
                try (InputStream is = url.openStream();){
                    localProperties.load(is);
                }
            } catch (IOException ex) {
                System.err.println("Could not load 'spring.properties' file from local classpath: " + ex);
            }
        }
    }
}

