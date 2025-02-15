/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui.settings;

import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.ui.TerminalActionPresentation;
import com.jediterm.terminal.ui.UIUtil;
import com.jediterm.terminal.ui.settings.DefaultSettingsProvider;
import com.jediterm.terminal.ui.settings.TabbedSettingsProvider;
import javax.swing.KeyStroke;
import org.jetbrains.annotations.NotNull;

public class DefaultTabbedSettingsProvider
extends DefaultSettingsProvider
implements TabbedSettingsProvider {
    @Override
    public boolean shouldCloseTabOnLogout(TtyConnector ttyConnector) {
        return true;
    }

    @Override
    public String tabName(TtyConnector ttyConnector, String sessionName) {
        return sessionName;
    }

    @Override
    @NotNull
    public TerminalActionPresentation getPreviousTabActionPresentation() {
        return new TerminalActionPresentation("Previous Tab", UIUtil.isMac ? KeyStroke.getKeyStroke(37, 128) : KeyStroke.getKeyStroke(37, 512));
    }

    @Override
    @NotNull
    public TerminalActionPresentation getNextTabActionPresentation() {
        return new TerminalActionPresentation("Next Tab", UIUtil.isMac ? KeyStroke.getKeyStroke(39, 128) : KeyStroke.getKeyStroke(39, 512));
    }
}

