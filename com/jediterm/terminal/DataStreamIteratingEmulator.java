/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.Terminal;
import com.jediterm.terminal.TerminalDataStream;
import com.jediterm.terminal.emulator.Emulator;
import java.io.IOException;

public abstract class DataStreamIteratingEmulator
implements Emulator {
    protected final TerminalDataStream myDataStream;
    protected final Terminal myTerminal;
    private boolean myEof = false;

    public DataStreamIteratingEmulator(TerminalDataStream dataStream, Terminal terminal) {
        this.myDataStream = dataStream;
        this.myTerminal = terminal;
    }

    @Override
    public boolean hasNext() {
        return !this.myEof;
    }

    @Override
    public void resetEof() {
        this.myEof = false;
    }

    @Override
    public void next() throws IOException {
        try {
            char b = this.myDataStream.getChar();
            this.processChar(b, this.myTerminal);
        } catch (TerminalDataStream.EOF e) {
            this.myEof = true;
        }
    }

    protected abstract void processChar(char var1, Terminal var2) throws IOException;
}

