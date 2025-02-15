/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.util.HashMap;
import java.util.Map;
import org.apache.xmlbeans.impl.common.NullLogger;
import org.apache.xmlbeans.impl.common.XBLogger;

public final class XBLogFactory {
    private static final Map<String, XBLogger> _loggers = new HashMap<String, XBLogger>();
    private static final XBLogger _nullLogger = new NullLogger();
    static String _loggerClassName = null;

    private XBLogFactory() {
    }

    public static XBLogger getLogger(Class<?> theclass) {
        return XBLogFactory.getLogger(theclass.getName());
    }

    public static XBLogger getLogger(String cat) {
        if (_loggerClassName == null) {
            try {
                _loggerClassName = System.getProperty("org.apache.xmlbeans.impl.store.XBLogger");
            } catch (Exception e) {
                // empty catch block
            }
            if (_loggerClassName == null) {
                _loggerClassName = _nullLogger.getClass().getName();
            }
        }
        if (_loggerClassName.equals(_nullLogger.getClass().getName())) {
            return _nullLogger;
        }
        XBLogger logger = _loggers.get(cat);
        if (logger == null) {
            try {
                Class<?> loggerClass = Class.forName(_loggerClassName);
                logger = (XBLogger)loggerClass.newInstance();
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

