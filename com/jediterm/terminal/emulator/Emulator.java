/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator;

import java.io.IOException;

public interface Emulator {
    public boolean hasNext();

    public void next() throws IOException;

    public void resetEof();
}

