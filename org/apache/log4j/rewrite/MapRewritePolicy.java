/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.rewrite;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.rewrite.RewritePolicy;
import org.apache.log4j.spi.LoggingEvent;

public class MapRewritePolicy
implements RewritePolicy {
    public LoggingEvent rewrite(LoggingEvent source) {
        Object msg = source.getMessage();
        if (msg instanceof Map) {
            HashMap props = new HashMap(source.getProperties());
            Map eventProps = (Map)msg;
            Object newMsg = eventProps.get("message");
            if (newMsg == null) {
                newMsg = msg;
            }
            Iterator iter = eventProps.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = iter.next();
                if ("message".equals(entry.getKey())) continue;
                props.put(entry.getKey(), entry.getValue());
            }
            return new LoggingEvent(source.getFQNOfLoggerClass(), source.getLogger() != null ? source.getLogger() : Logger.getLogger(source.getLoggerName()), source.getTimeStamp(), source.getLevel(), newMsg, source.getThreadName(), source.getThrowableInformation(), source.getNDC(), source.getLocationInformation(), props);
        }
        return source;
    }
}

