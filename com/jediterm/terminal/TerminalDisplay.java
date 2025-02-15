/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.CursorShape;
import com.jediterm.terminal.RequestOrigin;
import com.jediterm.terminal.emulator.mouse.MouseMode;
import com.jediterm.terminal.model.JediTerminal;
import com.jediterm.terminal.model.TerminalSelection;
import java.awt.Dimension;
import org.jetbrains.annotations.NotNull;

public interface TerminalDisplay {
    public int getRowCount();

    public int getColumnCount();

    public void setCursor(int var1, int var2);

    public void setCursorShape(CursorShape var1);

    public void beep();

    public void requestResize(@NotNull Dimension var1, RequestOrigin var2, int var3, int var4, JediTerminal.ResizeHandler var5);

    public void scrollArea(int var1, int var2, int var3);

    public void setCursorVisible(boolean var1);

    public void setScrollingEnabled(boolean var1);

    public void setBlinkingCursor(boolean var1);

    public void setWindowTitle(String var1);

    public void setCurrentPath(String var1);

    public void terminalMouseModeSet(MouseMode var1);

    public TerminalSelection getSelection();

    public boolean ambiguousCharsAreDoubleWidth();
}

