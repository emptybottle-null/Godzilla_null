/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

import com.jediterm.terminal.TerminalDisplay;
import com.jediterm.terminal.TtyConnector;
import com.jediterm.terminal.ui.JediTermWidget;
import com.jediterm.terminal.ui.TerminalPanelListener;
import com.jediterm.terminal.ui.TerminalSession;
import com.jediterm.terminal.ui.TerminalWidgetListener;
import java.awt.Dimension;
import javax.swing.JComponent;

public interface TerminalWidget {
    public JediTermWidget createTerminalSession(TtyConnector var1);

    public JComponent getComponent();

    default public JComponent getPreferredFocusableComponent() {
        return this.getComponent();
    }

    public boolean canOpenSession();

    public void setTerminalPanelListener(TerminalPanelListener var1);

    public Dimension getPreferredSize();

    public TerminalSession getCurrentSession();

    public TerminalDisplay getTerminalDisplay();

    public void addListener(TerminalWidgetListener var1);

    public void removeListener(TerminalWidgetListener var1);
}

