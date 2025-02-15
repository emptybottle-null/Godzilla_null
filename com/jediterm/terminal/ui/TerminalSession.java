/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.Terminal;
import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.debug.DebugBufferType;
import com.jediterm.terminal.model.TerminalTextBuffer;

public interface TerminalSession {
    public void start();

    public String getBufferText(DebugBufferType var1);

    public TerminalTextBuffer getTerminalTextBuffer();

    public Terminal getTerminal();

    public TtyConnector getTtyConnector();

    public String getSessionName();

    public void close();
}

