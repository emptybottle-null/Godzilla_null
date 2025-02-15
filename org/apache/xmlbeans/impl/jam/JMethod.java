/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JInvokable;

public interface JMethod
extends JInvokable {
    public JClass getReturnType();

    public boolean isFinal();

    public boolean isStatic();

    public boolean isAbstract();

    public boolean isNative();

    public boolean isSynchronized();

    @Override
    public String getQualifiedName();
}

