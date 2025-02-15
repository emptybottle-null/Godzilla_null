/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.log4j.Appender;
import org.apache.log4j.DefaultCategoryFactory;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.NameValue;
import org.apache.log4j.PropertyWatchdog;
import org.apache.log4j.SortedKeyEnumeration;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.or.RendererMap;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.OptionHandler;
import org.apache.log4j.spi.RendererSupport;
import org.apache.log4j.spi.ThrowableRenderer;
import org.apache.log4j.spi.ThrowableRendererSupport;

public class PropertyConfigurator
implements Configurator {
    protected Hashtable registry = new Hashtable(11);
    private LoggerRepository repository;
    protected LoggerFactory loggerFactory = new DefaultCategoryFactory();
    static final String CATEGORY_PREFIX = "log4j.category.";
    static final String LOGGER_PREFIX = "log4j.logger.";
    static final String FACTORY_PREFIX = "log4j.factory";
    static final String ADDITIVITY_PREFIX = "log4j.additivity.";
    static final String ROOT_CATEGORY_PREFIX = "log4j.rootCategory";
    static final String ROOT_LOGGER_PREFIX = "log4j.rootLogger";
    static final String APPENDER_PREFIX = "log4j.appender.";
    static final String RENDERER_PREFIX = "log4j.renderer.";
    static final String THRESHOLD_PREFIX = "log4j.threshold";
    private static final String THROWABLE_RENDERER_PREFIX = "log4j.throwableRenderer";
    private static final String LOGGER_REF = "logger-ref";
    private static final String ROOT_REF = "root-ref";
    private static final String APPENDER_REF_TAG = "appender-ref";
    public static final String LOGGER_FACTORY_KEY = "log4j.loggerFactory";
    private static final String RESET_KEY = "log4j.reset";
    private static final String INTERNAL_ROOT_NAME = "root";
    static /* synthetic */ Class class$org$apache$log4j$spi$ThrowableRenderer;
    static /* synthetic */ Class class$org$apache$log4j$spi$Filter;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void doConfigure(String configFileName, LoggerRepository hierarchy) {
        Properties props = new Properties();
        FileInputStream istream = null;
        try {
            istream = new FileInputStream(configFileName);
            props.load(istream);
            istream.close();
        } catch (Exception e) {
            if (e instanceof InterruptedIOException || e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not read configuration file [" + configFileName + "].", e);
            LogLog.error("Ignoring configuration file [" + configFileName + "].");
            return;
        } finally {
            if (istream != null) {
                try {
                    istream.close();
                } catch (InterruptedIOException ignore) {
                    Thread.currentThread().interrupt();
                } catch (Throwable ignore) {}
            }
        }
        this.doConfigure(props, hierarchy);
    }

    public static void configure(String configFilename) {
        new PropertyConfigurator().doConfigure(configFilename, LogManager.getLoggerRepository());
    }

    public static void configure(URL configURL) {
        new PropertyConfigurator().doConfigure(configURL, LogManager.getLoggerRepository());
    }

    public static void configure(InputStream inputStream) {
        new PropertyConfigurator().doConfigure(inputStream, LogManager.getLoggerRepository());
    }

    public static void configure(Properties properties) {
        new PropertyConfigurator().doConfigure(properties, LogManager.getLoggerRepository());
    }

    public static void configureAndWatch(String configFilename) {
        PropertyConfigurator.configureAndWatch(configFilename, 60000L);
    }

    public static void configureAndWatch(String configFilename, long delay) {
        PropertyWatchdog pdog = new PropertyWatchdog(configFilename);
        pdog.setDelay(delay);
        pdog.start();
    }

    public void doConfigure(Properties properties, LoggerRepository hierarchy) {
        String thresholdStr;
        String reset;
        this.repository = hierarchy;
        String value = properties.getProperty("log4j.debug");
        if (value == null && (value = properties.getProperty("log4j.configDebug")) != null) {
            LogLog.warn("[log4j.configDebug] is deprecated. Use [log4j.debug] instead.");
        }
        if (value != null) {
            LogLog.setInternalDebugging(OptionConverter.toBoolean(value, true));
        }
        if ((reset = properties.getProperty(RESET_KEY)) != null && OptionConverter.toBoolean(reset, false)) {
            hierarchy.resetConfiguration();
        }
        if ((thresholdStr = OptionConverter.findAndSubst(THRESHOLD_PREFIX, properties)) != null) {
            hierarchy.setThreshold(OptionConverter.toLevel(thresholdStr, Level.ALL));
            LogLog.debug("Hierarchy threshold set to [" + hierarchy.getThreshold() + "].");
        }
        this.configureRootCategory(properties, hierarchy);
        this.configureLoggerFactory(properties);
        this.parseCatsAndRenderers(properties, hierarchy);
        LogLog.debug("Finished configuring.");
        this.registry.clear();
    }

    public void doConfigure(InputStream inputStream, LoggerRepository hierarchy) {
        Properties props = new Properties();
        try {
            props.load(inputStream);
        } catch (IOException e) {
            if (e instanceof InterruptedIOException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not read configuration file from InputStream [" + inputStream + "].", e);
            LogLog.error("Ignoring configuration InputStream [" + inputStream + "].");
            return;
        }
        this.doConfigure(props, hierarchy);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void doConfigure(URL configURL, LoggerRepository hierarchy) {
        Properties props = new Properties();
        LogLog.debug("Reading configuration from URL " + configURL);
        InputStream istream = null;
        URLConnection uConn = null;
        try {
            uConn = configURL.openConnection();
            uConn.setUseCaches(false);
            istream = uConn.getInputStream();
            props.load(istream);
        } catch (Exception e) {
            if (e instanceof InterruptedIOException || e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            LogLog.error("Could not read configuration file from URL [" + configURL + "].", e);
            LogLog.error("Ignoring configuration file [" + configURL + "].");
            return;
        } finally {
            if (istream != null) {
                try {
                    istream.close();
                } catch (InterruptedIOException ignore) {
                    Thread.currentThread().interrupt();
                } catch (IOException ignore) {
                } catch (RuntimeException ignore) {}
            }
        }
        this.doConfigure(props, hierarchy);
    }

    protected void configureLoggerFactory(Properties props) {
        String factoryClassName = OptionConverter.findAndSubst(LOGGER_FACTORY_KEY, props);
        if (factoryClassName != null) {
            LogLog.debug("Setting category factory to [" + factoryClassName + "].");
            this.loggerFactory = (LoggerFactory)OptionConverter.instantiateByClassName(factoryClassName, LoggerFactory.class, this.loggerFactory);
            PropertySetter.setProperties(this.loggerFactory, props, "log4j.factory.");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void configureRootCategory(Properties props, LoggerRepository hierarchy) {
        String effectiveFrefix = ROOT_LOGGER_PREFIX;
        String value = OptionConverter.findAndSubst(ROOT_LOGGER_PREFIX, props);
        if (value == null) {
            value = OptionConverter.findAndSubst(ROOT_CATEGORY_PREFIX, props);
            effectiveFrefix = ROOT_CATEGORY_PREFIX;
        }
        if (value == null) {
            LogLog.debug("Could not find root logger information. Is this OK?");
        } else {
            Logger root;
            Logger logger = root = hierarchy.getRootLogger();
            synchronized (logger) {
                this.parseCategory(props, root, effectiveFrefix, INTERNAL_ROOT_NAME, value);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void parseCatsAndRenderers(Properties props, LoggerRepository hierarchy) {
        Enumeration<?> enumeration = props.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String)enumeration.nextElement();
            if (key.startsWith(CATEGORY_PREFIX) || key.startsWith(LOGGER_PREFIX)) {
                Logger logger;
                String loggerName = null;
                if (key.startsWith(CATEGORY_PREFIX)) {
                    loggerName = key.substring(CATEGORY_PREFIX.length());
                } else if (key.startsWith(LOGGER_PREFIX)) {
                    loggerName = key.substring(LOGGER_PREFIX.length());
                }
                String value = OptionConverter.findAndSubst(key, props);
                Logger logger2 = logger = hierarchy.getLogger(loggerName, this.loggerFactory);
                synchronized (logger2) {
                    this.parseCategory(props, logger, key, loggerName, value);
                    this.parseAdditivityForLogger(props, logger, loggerName);
                    continue;
                }
            }
            if (key.startsWith(RENDERER_PREFIX)) {
                String renderedClass = key.substring(RENDERER_PREFIX.length());
                String renderingClass = OptionConverter.findAndSubst(key, props);
                if (!(hierarchy instanceof RendererSupport)) continue;
                RendererMap.addRenderer((RendererSupport)((Object)hierarchy), renderedClass, renderingClass);
                continue;
            }
            if (!key.equals(THROWABLE_RENDERER_PREFIX) || !(hierarchy instanceof ThrowableRendererSupport)) continue;
            ThrowableRenderer tr = (ThrowableRenderer)OptionConverter.instantiateByKey(props, THROWABLE_RENDERER_PREFIX, class$org$apache$log4j$spi$ThrowableRenderer == null ? PropertyConfigurator.class$("org.apache.log4j.spi.ThrowableRenderer") : class$org$apache$log4j$spi$ThrowableRenderer, null);
            if (tr == null) {
                LogLog.error("Could not instantiate throwableRenderer.");
                continue;
            }
            PropertySetter setter = new PropertySetter(tr);
            setter.setProperties(props, "log4j.throwableRenderer.");
            ((ThrowableRendererSupport)((Object)hierarchy)).setThrowableRenderer(tr);
        }
    }

    void parseAdditivityForLogger(Properties props, Logger cat, String loggerName) {
        String value = OptionConverter.findAndSubst(ADDITIVITY_PREFIX + loggerName, props);
        LogLog.debug("Handling log4j.additivity." + loggerName + "=[" + value + "]");
        if (value != null && !value.equals("")) {
            boolean additivity = OptionConverter.toBoolean(value, true);
            LogLog.debug("Setting additivity for \"" + loggerName + "\" to " + additivity);
            cat.setAdditivity(additivity);
        }
    }

    void parseCategory(Properties props, Logger logger, String optionKey, String loggerName, String value) {
        LogLog.debug("Parsing for [" + loggerName + "] with value=[" + value + "].");
        StringTokenizer st = new StringTokenizer(value, ",");
        if (!value.startsWith(",") && !value.equals("")) {
            if (!st.hasMoreTokens()) {
                return;
            }
            String levelStr = st.nextToken();
            LogLog.debug("Level token is [" + levelStr + "].");
            if ("inherited".equalsIgnoreCase(levelStr) || "null".equalsIgnoreCase(levelStr)) {
                if (loggerName.equals(INTERNAL_ROOT_NAME)) {
                    LogLog.warn("The root logger cannot be set to null.");
                } else {
                    logger.setLevel(null);
                }
            } else {
                logger.setLevel(OptionConverter.toLevel(levelStr, Level.DEBUG));
            }
            LogLog.debug("Category " + loggerName + " set to " + logger.getLevel());
        }
        logger.removeAllAppenders();
        while (st.hasMoreTokens()) {
            String appenderName = st.nextToken().trim();
            if (appenderName == null || appenderName.equals(",")) continue;
            LogLog.debug("Parsing appender named \"" + appenderName + "\".");
            Appender appender = this.parseAppender(props, appenderName);
            if (appender == null) continue;
            logger.addAppender(appender);
        }
    }

    Appender parseAppender(Properties props, String appenderName) {
        Appender appender = this.registryGet(appenderName);
        if (appender != null) {
            LogLog.debug("Appender \"" + appenderName + "\" was already parsed.");
            return appender;
        }
        String prefix = APPENDER_PREFIX + appenderName;
        String layoutPrefix = prefix + ".layout";
        appender = (Appender)OptionConverter.instantiateByKey(props, prefix, Appender.class, null);
        if (appender == null) {
            LogLog.error("Could not instantiate appender named \"" + appenderName + "\".");
            return null;
        }
        appender.setName(appenderName);
        if (appender instanceof OptionHandler) {
            ErrorHandler eh;
            String errorHandlerPrefix;
            String errorHandlerClass;
            Layout layout;
            if (appender.requiresLayout() && (layout = (Layout)OptionConverter.instantiateByKey(props, layoutPrefix, Layout.class, null)) != null) {
                appender.setLayout(layout);
                LogLog.debug("Parsing layout options for \"" + appenderName + "\".");
                PropertySetter.setProperties(layout, props, layoutPrefix + ".");
                LogLog.debug("End of parsing for \"" + appenderName + "\".");
            }
            if ((errorHandlerClass = OptionConverter.findAndSubst(errorHandlerPrefix = prefix + ".errorhandler", props)) != null && (eh = (ErrorHandler)OptionConverter.instantiateByKey(props, errorHandlerPrefix, ErrorHandler.class, null)) != null) {
                appender.setErrorHandler(eh);
                LogLog.debug("Parsing errorhandler options for \"" + appenderName + "\".");
                this.parseErrorHandler(eh, errorHandlerPrefix, props, this.repository);
                Properties edited = new Properties();
                String[] keys = new String[]{errorHandlerPrefix + "." + ROOT_REF, errorHandlerPrefix + "." + LOGGER_REF, errorHandlerPrefix + "." + APPENDER_REF_TAG};
                Iterator iter = props.entrySet().iterator();
                while (iter.hasNext()) {
                    int i;
                    Map.Entry entry = iter.next();
                    for (i = 0; i < keys.length && !keys[i].equals(entry.getKey()); ++i) {
                    }
                    if (i != keys.length) continue;
                    edited.put(entry.getKey(), entry.getValue());
                }
                PropertySetter.setProperties(eh, edited, errorHandlerPrefix + ".");
                LogLog.debug("End of errorhandler parsing for \"" + appenderName + "\".");
            }
            PropertySetter.setProperties(appender, props, prefix + ".");
            LogLog.debug("Parsed \"" + appenderName + "\" options.");
        }
        this.parseAppenderFilters(props, appenderName, appender);
        this.registryPut(appender);
        return appender;
    }

    private void parseErrorHandler(ErrorHandler eh, String errorHandlerPrefix, Properties props, LoggerRepository hierarchy) {
        Appender backup;
        String appenderName;
        String loggerName;
        boolean rootRef = OptionConverter.toBoolean(OptionConverter.findAndSubst(errorHandlerPrefix + ROOT_REF, props), false);
        if (rootRef) {
            eh.setLogger(hierarchy.getRootLogger());
        }
        if ((loggerName = OptionConverter.findAndSubst(errorHandlerPrefix + LOGGER_REF, props)) != null) {
            Logger logger = this.loggerFactory == null ? hierarchy.getLogger(loggerName) : hierarchy.getLogger(loggerName, this.loggerFactory);
            eh.setLogger(logger);
        }
        if ((appenderName = OptionConverter.findAndSubst(errorHandlerPrefix + APPENDER_REF_TAG, props)) != null && (backup = this.parseAppender(props, appenderName)) != null) {
            eh.setBackupAppender(backup);
        }
    }

    void parseAppenderFilters(Properties props, String appenderName, Appender appender) {
        String filterPrefix = APPENDER_PREFIX + appenderName + ".filter.";
        int fIdx = filterPrefix.length();
        Hashtable filters = new Hashtable();
        Enumeration e = props.keys();
        String name = "";
        while (e.hasMoreElements()) {
            Vector<NameValue> filterOpts;
            String key = (String)e.nextElement();
            if (!key.startsWith(filterPrefix)) continue;
            int dotIdx = key.indexOf(46, fIdx);
            String filterKey = key;
            if (dotIdx != -1) {
                filterKey = key.substring(0, dotIdx);
                name = key.substring(dotIdx + 1);
            }
            if ((filterOpts = (Vector<NameValue>)filters.get(filterKey)) == null) {
                filterOpts = new Vector<NameValue>();
                filters.put(filterKey, filterOpts);
            }
            if (dotIdx == -1) continue;
            String value = OptionConverter.findAndSubst(key, props);
            filterOpts.add(new NameValue(name, value));
        }
        SortedKeyEnumeration g = new SortedKeyEnumeration(filters);
        while (g.hasMoreElements()) {
            String key = (String)g.nextElement();
            String clazz = props.getProperty(key);
            if (clazz != null) {
                LogLog.debug("Filter key: [" + key + "] class: [" + props.getProperty(key) + "] props: " + filters.get(key));
                Filter filter = (Filter)OptionConverter.instantiateByClassName(clazz, class$org$apache$log4j$spi$Filter == null ? PropertyConfigurator.class$("org.apache.log4j.spi.Filter") : class$org$apache$log4j$spi$Filter, null);
                if (filter == null) continue;
                PropertySetter propSetter = new PropertySetter(filter);
                Vector v = (Vector)filters.get(key);
                Enumeration filterProps = v.elements();
                while (filterProps.hasMoreElements()) {
                    NameValue kv = (NameValue)filterProps.nextElement();
                    propSetter.setProperty(kv.key, kv.value);
                }
                propSetter.activate();
                LogLog.debug("Adding filter of type [" + filter.getClass() + "] to appender named [" + appender.getName() + "].");
                appender.addFilter(filter);
                continue;
            }
            LogLog.warn("Missing class definition for filter: [" + key + "]");
        }
    }

    void registryPut(Appender appender) {
        this.registry.put(appender.getName(), appender);
    }

    Appender registryGet(String name) {
        return (Appender)this.registry.get(name);
    }
}

