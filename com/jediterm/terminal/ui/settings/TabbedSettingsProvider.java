/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui.settings;

import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.ui.TerminalActionPresentation;
import com.jediterm.terminal.ui.settings.SettingsProvider;
import org.jetbrains.annotations.NotNull;

public interface TabbedSettingsProvider
extends SettingsProvider {
    public boolean shouldCloseTabOnLogout(TtyConnector var1);

    public String tabName(TtyConnector var1, String var2);

    @NotNull
    public TerminalActionPresentation getPreviousTabActionPresentation();

    @NotNull
    public TerminalActionPresentation getNextTabActionPresentation();
}

