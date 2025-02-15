/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui.settings;

import com.jediterm.terminal.ui.TerminalActionPresentation;
import org.jetbrains.annotations.NotNull;

public interface SystemSettingsProvider {
    @NotNull
    public TerminalActionPresentation getNewSessionActionPresentation();

    @NotNull
    public TerminalActionPresentation getOpenUrlActionPresentation();

    @NotNull
    public TerminalActionPresentation getCopyActionPresentation();

    @NotNull
    public TerminalActionPresentation getPasteActionPresentation();

    @NotNull
    public TerminalActionPresentation getClearBufferActionPresentation();

    @NotNull
    public TerminalActionPresentation getPageUpActionPresentation();

    @NotNull
    public TerminalActionPresentation getPageDownActionPresentation();

    @NotNull
    public TerminalActionPresentation getLineUpActionPresentation();

    @NotNull
    public TerminalActionPresentation getLineDownActionPresentation();

    @NotNull
    public TerminalActionPresentation getCloseSessionActionPresentation();

    @NotNull
    public TerminalActionPresentation getFindActionPresentation();

    @NotNull
    public TerminalActionPresentation getSelectAllActionPresentation();
}

