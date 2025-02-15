/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.RequestOrigin;
import com.jediterm.terminal.ui.TerminalSession;
import org.jetbrains.annotations.NotNull;

public interface TerminalPanelListener {
    public void onPanelResize(@NotNull RequestOrigin var1);

    public void onSessionChanged(TerminalSession var1);

    public void onTitleChanged(String var1);
}

