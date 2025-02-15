/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.log;

import java.util.function.Function;
import org.apache.commons.logging.Log;
import org.springframework.lang.Nullable;

public abstract class LogFormatUtils {
    public static String formatValue(@Nullable Object value, boolean limitLength) {
        return LogFormatUtils.formatValue(value, limitLength ? 100 : -1, limitLength);
    }

    public static String formatValue(@Nullable Object value, int maxLength, boolean replaceNewlines) {
        String result;
        if (value == null) {
            return "";
        }
        try {
            result = value.toString();
        } catch (Throwable ex) {
            result = ex.toString();
        }
        if (maxLength != -1) {
            String string = result = result.length() > maxLength ? result.substring(0, maxLength) + " (truncated)..." : result;
        }
        if (replaceNewlines) {
            result = result.replace("\n", "<LF>").replace("\r", "<CR>");
        }
        if (value instanceof CharSequence) {
            result = "\"" + result + "\"";
        }
        return result;
    }

    public static void traceDebug(Log logger, Function<Boolean, String> messageFactory) {
        if (logger.isDebugEnabled()) {
            boolean traceEnabled = logger.isTraceEnabled();
            String logMessage = messageFactory.apply(traceEnabled);
            if (traceEnabled) {
                logger.trace(logMessage);
            } else {
                logger.debug(logMessage);
            }
        }
    }
}

