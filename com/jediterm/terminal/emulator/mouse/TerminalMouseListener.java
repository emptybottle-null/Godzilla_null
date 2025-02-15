/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.emulator.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface TerminalMouseListener {
    public void mousePressed(int var1, int var2, MouseEvent var3);

    public void mouseReleased(int var1, int var2, MouseEvent var3);

    public void mouseMoved(int var1, int var2, MouseEvent var3);

    public void mouseDragged(int var1, int var2, MouseEvent var3);

    public void mouseWheelMoved(int var1, int var2, MouseWheelEvent var3);
}

