/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.mutable;

import java.net.URI;
import org.apache.xmlbeans.impl.jam.JSourcePosition;

public interface MSourcePosition
extends JSourcePosition {
    public void setColumn(int var1);

    public void setLine(int var1);

    public void setSourceURI(URI var1);
}

