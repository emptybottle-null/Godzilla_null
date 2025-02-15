/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.apache.xmlbeans.impl.jam.visitor.JVisitor;

public interface JElement {
    public JElement getParent();

    public String getSimpleName();

    public String getQualifiedName();

    public JSourcePosition getSourcePosition();

    public void accept(JVisitor var1);

    public Object getArtifact();

    public String toString();
}

