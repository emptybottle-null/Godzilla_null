/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;

public class DefaultLF5Configurator
implements Configurator {
    private DefaultLF5Configurator() {
    }

    public static void configure() throws IOException {
        String resource;
        URL configFileResource = DefaultLF5Configurator.class.getResource(resource = "/org/apache/log4j/lf5/config/defaultconfig.properties");
        if (configFileResource == null) {
            throw new IOException("Error: Unable to open the resource" + resource);
        }
        PropertyConfigurator.configure(configFileResource);
    }

    public void doConfigure(InputStream inputStream, LoggerRepository repository) {
        throw new IllegalStateException("This class should NOT be instantiated!");
    }

    public void doConfigure(URL configURL, LoggerRepository repository) {
        throw new IllegalStateException("This class should NOT be instantiated!");
    }
}

