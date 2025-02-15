/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import java.net.URI;

public interface JSourcePosition {
    public int getColumn();

    public int getLine();

    public URI getSourceURI();
}

