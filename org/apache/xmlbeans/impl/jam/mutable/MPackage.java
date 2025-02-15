/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.mutable;

import org.apache.xmlbeans.impl.jam.JPackage;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.apache.xmlbeans.impl.jam.mutable.MClass;

public interface MPackage
extends JPackage,
MAnnotatedElement {
    public MClass[] getMutableClasses();
}

