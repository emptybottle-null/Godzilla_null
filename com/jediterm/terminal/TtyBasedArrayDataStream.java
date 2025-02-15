/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.ArrayTerminalDataStream;
import com.jediterm.terminal.TerminalDataStream;
import com.jediterm.terminal.TtyConnector;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;

public class TtyBasedArrayDataStream
extends ArrayTerminalDataStream {
    private final TtyConnector myTtyConnector;

    public TtyBasedArrayDataStream(TtyConnector ttyConnector) {
        super(new char[1024], 0, 0);
        this.myTtyConnector = ttyConnector;
    }

    private void fillBuf() throws IOException {
        this.myOffset = 0;
        this.myLength = this.myTtyConnector.read(this.myBuf, this.myOffset, this.myBuf.length);
        if (this.myLength <= 0) {
            this.myLength = 0;
            throw new TerminalDataStream.EOF();
        }
    }

    @Override
    public char getChar() throws IOException {
        if (this.myLength == 0) {
            this.fillBuf();
        }
        return super.getChar();
    }

    @Override
    public String readNonControlCharacters(int maxChars) throws IOException {
        if (this.myLength == 0) {
            this.fillBuf();
        }
        return super.readNonControlCharacters(maxChars);
    }

    public String toString() {
        return this.getDebugText();
    }

    @NotNull
    private String getDebugText() {
        String s = new String(this.myBuf, this.myOffset, this.myLength);
        String string = s.replace("\u001b", "ESC").replace("\n", "\\n").replace("\r", "\\r").replace("\u0007", "BEL").replace(" ", "<S>");
        if (string == null) {
            TtyBasedArrayDataStream.$$$reportNull$$$0(0);
        }
        return string;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jediterm/terminal/TtyBasedArrayDataStream", "getDebugText"));
    }
}

