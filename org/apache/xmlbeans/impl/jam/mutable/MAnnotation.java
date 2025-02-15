/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.mutable;

import org.apache.xmlbeans.impl.jam.JAnnotation;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.mutable.MElement;

public interface MAnnotation
extends JAnnotation,
MElement {
    public void setAnnotationInstance(Object var1);

    public void setSimpleValue(String var1, Object var2, JClass var3);

    public MAnnotation createNestedValue(String var1, String var2);

    public MAnnotation[] createNestedValueArray(String var1, String var2, int var3);
}

