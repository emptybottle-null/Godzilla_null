/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.annotation.AnnotationProxy;
import org.apache.xmlbeans.impl.jam.internal.elements.AnnotationValueImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;

public abstract class TypedAnnotationProxyBase
extends AnnotationProxy {
    private List mValues = null;

    protected TypedAnnotationProxyBase() {
    }

    @Override
    public void setValue(String name, Object value, JClass type) {
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        if (this.mValues == null) {
            this.mValues = new ArrayList();
        }
        this.mValues.add(new AnnotationValueImpl((ElementContext)((Object)this.mContext), name, value, type));
        Method m = this.getSetterFor(name, value.getClass());
        if (m == null) {
            return;
        }
        try {
            m.invoke(this, value);
        } catch (IllegalAccessException e) {
            this.getLogger().warning(e);
        } catch (InvocationTargetException e) {
            this.getLogger().warning(e);
        }
    }

    @Override
    public JAnnotationValue[] getValues() {
        if (this.mValues == null) {
            return new JAnnotationValue[0];
        }
        JAnnotationValue[] out = new JAnnotationValue[this.mValues.size()];
        this.mValues.toArray(out);
        return out;
    }

    protected Method getSetterFor(String memberName, Class valueType) {
        try {
            return this.getClass().getMethod("set" + memberName, valueType);
        } catch (NoSuchMethodException nsme) {
            this.getLogger().warning(nsme);
            return null;
        }
    }
}

