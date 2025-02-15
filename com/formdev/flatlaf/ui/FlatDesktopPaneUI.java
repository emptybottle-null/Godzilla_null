/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatDesktopIconUI;
import javax.swing.DefaultDesktopManager;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicDesktopPaneUI;

public class FlatDesktopPaneUI
extends BasicDesktopPaneUI {
    public static ComponentUI createUI(JComponent c) {
        return new FlatDesktopPaneUI();
    }

    @Override
    protected void installDesktopManager() {
        this.desktopManager = this.desktop.getDesktopManager();
        if (this.desktopManager == null) {
            this.desktopManager = new FlatDesktopManager();
            this.desktop.setDesktopManager(this.desktopManager);
        }
    }

    private class FlatDesktopManager
    extends DefaultDesktopManager
    implements UIResource {
        private FlatDesktopManager() {
        }

        @Override
        public void iconifyFrame(JInternalFrame f) {
            super.iconifyFrame(f);
            ((FlatDesktopIconUI)f.getDesktopIcon().getUI()).updateDockIcon();
        }
    }
}

