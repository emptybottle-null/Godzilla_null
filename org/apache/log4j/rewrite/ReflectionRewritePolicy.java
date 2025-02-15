/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.rewrite;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.rewrite.RewritePolicy;
import org.apache.log4j.spi.LoggingEvent;

public class ReflectionRewritePolicy
implements RewritePolicy {
    public LoggingEvent rewrite(LoggingEvent source) {
        Object msg = source.getMessage();
        if (!(msg instanceof String)) {
            Object newMsg = msg;
            HashMap<String, Object> rewriteProps = new HashMap<String, Object>(source.getProperties());
            try {
                PropertyDescriptor[] props = Introspector.getBeanInfo(msg.getClass(), Object.class).getPropertyDescriptors();
                if (props.length > 0) {
                    for (int i = 0; i < props.length; ++i) {
                        try {
                            Object propertyValue = props[i].getReadMethod().invoke(msg, null);
                            if ("message".equalsIgnoreCase(props[i].getName())) {
                                newMsg = propertyValue;
                                continue;
                            }
                            rewriteProps.put(props[i].getName(), propertyValue);
                            continue;
                        } catch (Exception e) {
                            LogLog.warn("Unable to evaluate property " + props[i].getName(), e);
                        }
                    }
                    return new LoggingEvent(source.getFQNOfLoggerClass(), source.getLogger() != null ? source.getLogger() : Logger.getLogger(source.getLoggerName()), source.getTimeStamp(), source.getLevel(), newMsg, source.getThreadName(), source.getThrowableInformation(), source.getNDC(), source.getLocationInformation(), rewriteProps);
                }
            } catch (Exception e) {
                LogLog.warn("Unable to get property descriptors", e);
            }
        }
        return source;
    }
}

