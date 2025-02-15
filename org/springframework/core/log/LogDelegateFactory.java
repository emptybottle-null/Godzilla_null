/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.log;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.log.CompositeLog;

public final class LogDelegateFactory {
    private LogDelegateFactory() {
    }

    public static Log getCompositeLog(Log primaryLogger, Log secondaryLogger, Log ... tertiaryLoggers) {
        ArrayList<Log> loggers = new ArrayList<Log>(2 + tertiaryLoggers.length);
        loggers.add(primaryLogger);
        loggers.add(secondaryLogger);
        Collections.addAll(loggers, tertiaryLoggers);
        return new CompositeLog(loggers);
    }

    public static Log getHiddenLog(Class<?> clazz) {
        return LogDelegateFactory.getHiddenLog(clazz.getName());
    }

    public static Log getHiddenLog(String category) {
        return LogFactory.getLog("_" + category);
    }
}

