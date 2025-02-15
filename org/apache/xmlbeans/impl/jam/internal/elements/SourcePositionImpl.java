/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import java.net.URI;
import org.apache.xmlbeans.impl.jam.mutable.MSourcePosition;

public final class SourcePositionImpl
implements MSourcePosition {
    private int mColumn = -1;
    private int mLine = -1;
    private URI mURI = null;

    SourcePositionImpl() {
    }

    @Override
    public void setColumn(int col) {
        this.mColumn = col;
    }

    @Override
    public void setLine(int line) {
        this.mLine = line;
    }

    @Override
    public void setSourceURI(URI uri) {
        this.mURI = uri;
    }

    @Override
    public int getColumn() {
        return this.mColumn;
    }

    @Override
    public int getLine() {
        return this.mLine;
    }

    @Override
    public URI getSourceURI() {
        return this.mURI;
    }
}

