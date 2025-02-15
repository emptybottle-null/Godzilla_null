/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.ui.TerminalActionProvider;

public abstract class TerminalActionProviderBase
implements TerminalActionProvider {
    @Override
    public TerminalActionProvider getNextProvider() {
        return null;
    }

    @Override
    public void setNextProvider(TerminalActionProvider provider) {
    }
}

