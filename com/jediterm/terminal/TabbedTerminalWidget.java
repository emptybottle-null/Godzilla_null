/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal;

import com.jediterm.terminal.ui.AbstractTabbedTerminalWidget;
import com.jediterm.terminal.ui.AbstractTabs;
import com.jediterm.terminal.ui.JediTermWidget;
import com.jediterm.terminal.ui.TerminalTabsImpl;
import com.jediterm.terminal.ui.settings.TabbedSettingsProvider;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;

public class TabbedTerminalWidget
extends AbstractTabbedTerminalWidget<JediTermWidget> {
    public TabbedTerminalWidget(@NotNull TabbedSettingsProvider settingsProvider, @NotNull Function<AbstractTabbedTerminalWidget, JediTermWidget> createNewSessionAction) {
        if (settingsProvider == null) {
            TabbedTerminalWidget.$$$reportNull$$$0(0);
        }
        if (createNewSessionAction == null) {
            TabbedTerminalWidget.$$$reportNull$$$0(1);
        }
        super(settingsProvider, createNewSessionAction::apply);
    }

    @Override
    public JediTermWidget createInnerTerminalWidget() {
        return new JediTermWidget(this.getSettingsProvider());
    }

    @Override
    protected AbstractTabs<JediTermWidget> createTabbedPane() {
        return new TerminalTabsImpl();
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        Object[] objectArray;
        Object[] objectArray2 = new Object[3];
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[0] = "settingsProvider";
                break;
            }
            case 1: {
                objectArray = objectArray2;
                objectArray2[0] = "createNewSessionAction";
                break;
            }
        }
        objectArray[1] = "com/jediterm/terminal/TabbedTerminalWidget";
        objectArray[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objectArray));
    }
}

