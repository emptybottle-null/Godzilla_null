/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.util.Internal;
import org.apache.poi.util.NullLogger;
import org.apache.poi.util.POILogger;

@Internal
public final class POILogFactory {
    private static final Map<String, POILogger> _loggers = new HashMap<String, POILogger>();
    private static final POILogger _nullLogger = new NullLogger();
    static String _loggerClassName;

    private POILogFactory() {
    }

    public static POILogger getLogger(Class<?> theclass) {
        return POILogFactory.getLogger(theclass.getName());
    }

    public static POILogger getLogger(String cat) {
        if (_loggerClassName == null) {
            try {
                _loggerClassName = System.getProperty("org.apache.poi.util.POILogger");
            } catch (Exception exception) {
                // empty catch block
            }
            if (_loggerClassName == null) {
                _loggerClassName = _nullLogger.getClass().getName();
            }
        }
        if (_loggerClassName.equals(_nullLogger.getClass().getName())) {
            return _nullLogger;
        }
        POILogger logger = _loggers.get(cat);
        if (logger == null) {
            try {
                Class<?> loggerClass = Class.forName(_loggerClassName);
                logger = (POILogger)loggerClass.getConstructor(new Class[0]).newInstance(new Object[0]);
                logger.initialize(cat);
            } catch (Exception e) {
                logger = _nullLogger;
                _loggerClassName = _nullLogger.getClass().getName();
            }
            _loggers.put(cat, logger);
        }
        return logger;
    }
}

