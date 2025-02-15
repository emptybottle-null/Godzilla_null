/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JElement;

public interface JAnnotation
extends JElement {
    public static final String SINGLE_VALUE_NAME = "value";

    @Override
    public String getSimpleName();

    public Object getProxy();

    public JAnnotationValue[] getValues();

    public JAnnotationValue getValue(String var1);

    public Object getAnnotationInstance();
}

