/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JElement;
import org.apache.xmlbeans.impl.jam.JSourcePosition;

public interface JComment
extends JElement {
    public String getText();

    @Override
    public JSourcePosition getSourcePosition();
}

