/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.annotation;

import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.provider.JamLogger;
import org.apache.xmlbeans.impl.jam.provider.JamServiceContext;

public abstract class AnnotationProxy {
    public static final String SINGLE_MEMBER_NAME = "value";
    private static final String DEFAULT_NVPAIR_DELIMS = "\n\r";
    protected JamServiceContext mContext;

    public void init(JamServiceContext ctx) {
        if (ctx == null) {
            throw new IllegalArgumentException("null logger");
        }
        this.mContext = ctx;
    }

    public abstract void setValue(String var1, Object var2, JClass var3);

    public abstract JAnnotationValue[] getValues();

    public JAnnotationValue getValue(String named) {
        if (named == null) {
            throw new IllegalArgumentException("null name");
        }
        named = named.trim();
        JAnnotationValue[] values = this.getValues();
        for (int i = 0; i < values.length; ++i) {
            if (!named.equals(values[i].getName())) continue;
            return values[i];
        }
        return null;
    }

    protected JamLogger getLogger() {
        return this.mContext.getLogger();
    }
}

