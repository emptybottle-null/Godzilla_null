/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JAnnotatedElement;
import org.apache.xmlbeans.impl.jam.JClass;

public interface JMember
extends JAnnotatedElement {
    public JClass getContainingClass();

    public int getModifiers();

    public boolean isPackagePrivate();

    public boolean isPrivate();

    public boolean isProtected();

    public boolean isPublic();
}

