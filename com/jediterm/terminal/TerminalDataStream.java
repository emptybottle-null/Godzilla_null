/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import java.io.IOException;

public interface TerminalDataStream {
    public char getChar() throws IOException;

    public void pushChar(char var1) throws IOException;

    public String readNonControlCharacters(int var1) throws IOException;

    public void pushBackBuffer(char[] var1, int var2) throws IOException;

    public boolean isEmpty();

    public static class EOF
    extends IOException {
        public EOF() {
            super("EOF: There is no more data or connection is lost");
        }
    }
}

