/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.Questioner;
import java.awt.Dimension;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;

public interface TtyConnector {
    public boolean init(Questioner var1);

    public void close();

    default public void resize(@NotNull Dimension termWinSize) {
        if (termWinSize == null) {
            TtyConnector.$$$reportNull$$$0(0);
        }
        this.resize(termWinSize, new Dimension(0, 0));
    }

    @Deprecated
    default public void resize(Dimension termWinSize, Dimension pixelSize) {
        this.resize(termWinSize);
    }

    public String getName();

    public int read(char[] var1, int var2, int var3) throws IOException;

    public void write(byte[] var1) throws IOException;

    public boolean isConnected();

    public void write(String var1) throws IOException;

    public int waitFor() throws InterruptedException;

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "termWinSize", "com/jediterm/terminal/TtyConnector", "resize"));
    }
}

