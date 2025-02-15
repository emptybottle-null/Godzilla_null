/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.Reader;
import org.apache.commons.io.input.ClosedReader;
import org.apache.commons.io.input.ProxyReader;

public class CloseShieldReader
extends ProxyReader {
    public static CloseShieldReader wrap(Reader reader) {
        return new CloseShieldReader(reader);
    }

    @Deprecated
    public CloseShieldReader(Reader reader) {
        super(reader);
    }

    @Override
    public void close() {
        this.in = ClosedReader.CLOSED_READER;
    }
}

