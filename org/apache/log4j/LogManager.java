/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.DefaultRepositorySelector;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.NOPLoggerRepository;
import org.apache.log4j.spi.RepositorySelector;
import org.apache.log4j.spi.RootLogger;

public class LogManager {
    public static final String DEFAULT_CONFIGURATION_FILE = "log4j.properties";
    static final String DEFAULT_XML_CONFIGURATION_FILE = "log4j.xml";
    public static final String DEFAULT_CONFIGURATION_KEY = "log4j.configuration";
    public static final String CONFIGURATOR_CLASS_KEY = "log4j.configuratorClass";
    public static final String DEFAULT_INIT_OVERRIDE_KEY = "log4j.defaultInitOverride";
    private static Object guard = null;
    private static RepositorySelector repositorySelector;

    public static void setRepositorySelector(RepositorySelector selector, Object guard) throws IllegalArgumentException {
        if (LogManager.guard != null && LogManager.guard != guard) {
            throw new IllegalArgumentException("Attempted to reset the LoggerFactory without possessing the guard.");
        }
        if (selector == null) {
            throw new IllegalArgumentException("RepositorySelector must be non-null.");
        }
        LogManager.guard = guard;
        repositorySelector = selector;
    }

    private static boolean isLikelySafeScenario(Exception ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        String msg = stringWriter.toString();
        return msg.indexOf("org.apache.catalina.loader.WebappClassLoader.stop") != -1;
    }

    public static LoggerRepository getLoggerRepository() {
        if (repositorySelector == null) {
            repositorySelector = new DefaultRepositorySelector(new NOPLoggerRepository());
            guard = null;
            IllegalStateException ex = new IllegalStateException("Class invariant violation");
            String msg = "log4j called after unloading, see http://logging.apache.org/log4j/1.2/faq.html#unload.";
            if (LogManager.isLikelySafeScenario(ex)) {
                LogLog.debug(msg, ex);
            } else {
                LogLog.error(msg, ex);
            }
        }
        return repositorySelector.getLoggerRepository();
    }

    public static Logger getRootLogger() {
        return LogManager.getLoggerRepository().getRootLogger();
    }

    public static Logger getLogger(String name) {
        return LogManager.getLoggerRepository().getLogger(name);
    }

    public static Logger getLogger(Class clazz) {
        return LogManager.getLoggerRepository().getLogger(clazz.getName());
    }

    public static Logger getLogger(String name, LoggerFactory factory) {
        return LogManager.getLoggerRepository().getLogger(name, factory);
    }

    public static Logger exists(String name) {
        return LogManager.getLoggerRepository().exists(name);
    }

    public static Enumeration getCurrentLoggers() {
        return LogManager.getLoggerRepository().getCurrentLoggers();
    }

    public static void shutdown() {
        LogManager.getLoggerRepository().shutdown();
    }

    public static void resetConfiguration() {
        LogManager.getLoggerRepository().resetConfiguration();
    }

    static {
        Hierarchy h = new Hierarchy(new RootLogger(Level.DEBUG));
        repositorySelector = new DefaultRepositorySelector(h);
        String override = OptionConverter.getSystemProperty(DEFAULT_INIT_OVERRIDE_KEY, null);
        if (override == null || "false".equalsIgnoreCase(override)) {
            String configurationOptionStr = OptionConverter.getSystemProperty(DEFAULT_CONFIGURATION_KEY, null);
            String configuratorClassName = OptionConverter.getSystemProperty(CONFIGURATOR_CLASS_KEY, null);
            URL url = null;
            if (configurationOptionStr == null) {
                url = Loader.getResource(DEFAULT_XML_CONFIGURATION_FILE);
                if (url == null) {
                    url = Loader.getResource(DEFAULT_CONFIGURATION_FILE);
                }
            } else {
                try {
                    url = new URL(configurationOptionStr);
                } catch (MalformedURLException ex) {
                    url = Loader.getResource(configurationOptionStr);
                }
            }
            if (url != null) {
                LogLog.debug("Using URL [" + url + "] for automatic log4j configuration.");
                try {
                    OptionConverter.selectAndConfigure(url, configuratorClassName, LogManager.getLoggerRepository());
                } catch (NoClassDefFoundError e) {
                    LogLog.warn("Error during default initialization", e);
                }
            } else {
                LogLog.debug("Could not find resource: [" + configurationOptionStr + "].");
            }
        } else {
            LogLog.debug("Default initialization of overridden by log4j.defaultInitOverrideproperty.");
        }
    }
}

