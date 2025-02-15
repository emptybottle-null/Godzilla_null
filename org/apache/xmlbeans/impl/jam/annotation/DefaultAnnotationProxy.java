/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.annotation;

import java.util.ArrayList;
import java.util.List;
import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.annotation.AnnotationProxy;
import org.apache.xmlbeans.impl.jam.internal.elements.AnnotationValueImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;

public class DefaultAnnotationProxy
extends AnnotationProxy {
    private List mValues = new ArrayList();

    @Override
    public JAnnotationValue[] getValues() {
        JAnnotationValue[] out = new JAnnotationValue[this.mValues.size()];
        this.mValues.toArray(out);
        return out;
    }

    @Override
    public void setValue(String name, Object value, JClass type) {
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (type == null) {
            throw new IllegalArgumentException("null type");
        }
        if (value == null) {
            throw new IllegalArgumentException("null value");
        }
        name = name.trim();
        this.mValues.add(new AnnotationValueImpl((ElementContext)this.getLogger(), name, value, type));
    }
}

