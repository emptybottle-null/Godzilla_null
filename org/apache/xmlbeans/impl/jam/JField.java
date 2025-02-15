/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JMember;

public interface JField
extends JMember {
    public JClass getType();

    public boolean isFinal();

    public boolean isStatic();

    public boolean isVolatile();

    public boolean isTransient();

    @Override
    public String getQualifiedName();
}

