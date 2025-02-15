/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.ui.TerminalAction;
import java.util.List;

public interface TerminalActionProvider {
    public List<TerminalAction> getActions();

    public TerminalActionProvider getNextProvider();

    public void setNextProvider(TerminalActionProvider var1);
}

