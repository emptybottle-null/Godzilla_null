/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.mutable;

import org.apache.xmlbeans.impl.jam.JElement;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.mutable.MSourcePosition;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public interface MElement
extends JElement {
    public JamClassLoader getClassLoader();

    public void setSimpleName(String var1);

    public MSourcePosition createSourcePosition();

    public void removeSourcePosition();

    public MSourcePosition getMutableSourcePosition();

    public void accept(MVisitor var1);

    public void setArtifact(Object var1);
}

