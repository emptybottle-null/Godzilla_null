/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.rewrite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.apache.log4j.rewrite.RewritePolicy;
import org.apache.log4j.spi.LoggingEvent;

public class PropertyRewritePolicy
implements RewritePolicy {
    private Map properties = Collections.EMPTY_MAP;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setProperties(String props) {
        HashMap<String, String> hashTable = new HashMap<String, String>();
        StringTokenizer pairs = new StringTokenizer(props, ",");
        while (pairs.hasMoreTokens()) {
            StringTokenizer entry = new StringTokenizer(pairs.nextToken(), "=");
            hashTable.put(entry.nextElement().toString().trim(), entry.nextElement().toString().trim());
        }
        PropertyRewritePolicy propertyRewritePolicy = this;
        synchronized (propertyRewritePolicy) {
            this.properties = hashTable;
        }
    }

    public LoggingEvent rewrite(LoggingEvent source) {
        if (!this.properties.isEmpty()) {
            HashMap rewriteProps = new HashMap(source.getProperties());
            Iterator iter = this.properties.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = iter.next();
                if (rewriteProps.containsKey(entry.getKey())) continue;
                rewriteProps.put(entry.getKey(), entry.getValue());
            }
            return new LoggingEvent(source.getFQNOfLoggerClass(), source.getLogger() != null ? source.getLogger() : Logger.getLogger(source.getLoggerName()), source.getTimeStamp(), source.getLevel(), source.getMessage(), source.getThreadName(), source.getThrowableInformation(), source.getNDC(), source.getLocationInformation(), rewriteProps);
        }
        return source;
    }
}

