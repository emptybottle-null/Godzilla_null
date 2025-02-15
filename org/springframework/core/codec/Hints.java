/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.codec;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

public abstract class Hints {
    public static final String LOG_PREFIX_HINT = Log.class.getName() + ".PREFIX";
    public static final String SUPPRESS_LOGGING_HINT = Log.class.getName() + ".SUPPRESS_LOGGING";

    public static Map<String, Object> from(String hintName, Object value) {
        return Collections.singletonMap(hintName, value);
    }

    public static Map<String, Object> none() {
        return Collections.emptyMap();
    }

    public static <T> T getRequiredHint(@Nullable Map<String, Object> hints, String hintName) {
        if (hints == null) {
            throw new IllegalArgumentException("No hints map for required hint '" + hintName + "'");
        }
        Object hint = hints.get(hintName);
        if (hint == null) {
            throw new IllegalArgumentException("Hints map must contain the hint '" + hintName + "'");
        }
        return (T)hint;
    }

    public static String getLogPrefix(@Nullable Map<String, Object> hints) {
        return hints != null ? (String)hints.getOrDefault(LOG_PREFIX_HINT, "") : "";
    }

    public static boolean isLoggingSuppressed(@Nullable Map<String, Object> hints) {
        return hints != null && (Boolean)hints.getOrDefault(SUPPRESS_LOGGING_HINT, false) != false;
    }

    public static Map<String, Object> merge(Map<String, Object> hints1, Map<String, Object> hints2) {
        if (hints1.isEmpty() && hints2.isEmpty()) {
            return Collections.emptyMap();
        }
        if (hints2.isEmpty()) {
            return hints1;
        }
        if (hints1.isEmpty()) {
            return hints2;
        }
        HashMap<String, Object> result = CollectionUtils.newHashMap(hints1.size() + hints2.size());
        result.putAll(hints1);
        result.putAll(hints2);
        return result;
    }

    public static Map<String, Object> merge(Map<String, Object> hints, String hintName, Object hintValue) {
        if (hints.isEmpty()) {
            return Collections.singletonMap(hintName, hintValue);
        }
        HashMap<String, Object> result = CollectionUtils.newHashMap(hints.size() + 1);
        result.putAll(hints);
        result.put(hintName, hintValue);
        return result;
    }

    public static void touchDataBuffer(DataBuffer buffer, @Nullable Map<String, Object> hints, Log logger) {
        Object logPrefix;
        if (logger.isDebugEnabled() && hints != null && (logPrefix = hints.get(LOG_PREFIX_HINT)) != null) {
            DataBufferUtils.touch(buffer, logPrefix);
        }
    }
}

