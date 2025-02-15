/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.lang.Nullable;

enum IntrospectionFailureLogger {
    DEBUG{

        @Override
        public boolean isEnabled() {
            return IntrospectionFailureLogger.getLogger().isDebugEnabled();
        }

        @Override
        public void log(String message) {
            IntrospectionFailureLogger.getLogger().debug(message);
        }
    }
    ,
    INFO{

        @Override
        public boolean isEnabled() {
            return IntrospectionFailureLogger.getLogger().isInfoEnabled();
        }

        @Override
        public void log(String message) {
            IntrospectionFailureLogger.getLogger().info(message);
        }
    };

    @Nullable
    private static Log logger;

    void log(String message, @Nullable Object source, Exception ex) {
        String on = source != null ? " on " + source : "";
        this.log(message + on + ": " + ex);
    }

    abstract boolean isEnabled();

    abstract void log(String var1);

    private static Log getLogger() {
        Log logger = IntrospectionFailureLogger.logger;
        if (logger == null) {
            IntrospectionFailureLogger.logger = logger = LogFactory.getLog(MergedAnnotation.class);
        }
        return logger;
    }
}

