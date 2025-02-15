/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.Set;
import org.apache.log4j.pattern.LogEvent;
import org.apache.log4j.spi.LoggingEvent;

public final class MDCKeySetExtractor {
    private final Method getKeySetMethod;
    public static final MDCKeySetExtractor INSTANCE = new MDCKeySetExtractor();

    private MDCKeySetExtractor() {
        Method getMethod = null;
        try {
            getMethod = LoggingEvent.class.getMethod("getPropertyKeySet", null);
        } catch (Exception ex) {
            getMethod = null;
        }
        this.getKeySetMethod = getMethod;
    }

    public Set getPropertyKeySet(LoggingEvent event) throws Exception {
        Set keySet = null;
        if (this.getKeySetMethod != null) {
            keySet = (Set)this.getKeySetMethod.invoke(event, null);
        } else {
            ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outBytes);
            os.writeObject(event);
            os.close();
            byte[] raw = outBytes.toByteArray();
            String subClassName = LogEvent.class.getName();
            if (raw[6] == 0 || raw[7] == subClassName.length()) {
                for (int i = 0; i < subClassName.length(); ++i) {
                    raw[8 + i] = (byte)subClassName.charAt(i);
                }
                ByteArrayInputStream inBytes = new ByteArrayInputStream(raw);
                ObjectInputStream is = new ObjectInputStream(inBytes);
                Object cracked = is.readObject();
                if (cracked instanceof LogEvent) {
                    keySet = ((LogEvent)cracked).getPropertyKeySet();
                }
                is.close();
            }
        }
        return keySet;
    }
}

