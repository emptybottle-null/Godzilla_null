/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuBarUI;

public class FlatMenuBarUI
extends BasicMenuBarUI {
    public static ComponentUI createUI(JComponent c) {
        return new FlatMenuBarUI();
    }

    @Override
    protected void installKeyboardActions() {
        super.installKeyboardActions();
        ActionMap map = SwingUtilities.getUIActionMap(this.menuBar);
        if (map == null) {
            map = new ActionMapUIResource();
            SwingUtilities.replaceUIActionMap(this.menuBar, map);
        }
        map.put("takeFocus", new TakeFocus());
    }

    private static class TakeFocus
    extends AbstractAction {
        private TakeFocus() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuBar menuBar = (JMenuBar)e.getSource();
            JMenu menu = menuBar.getMenu(0);
            if (menu != null) {
                MenuElement[] menuElementArray;
                MenuSelectionManager menuSelectionManager = MenuSelectionManager.defaultManager();
                if (SystemInfo.isWindows) {
                    MenuElement[] menuElementArray2 = new MenuElement[2];
                    menuElementArray2[0] = menuBar;
                    menuElementArray = menuElementArray2;
                    menuElementArray2[1] = menu;
                } else {
                    MenuElement[] menuElementArray3 = new MenuElement[3];
                    menuElementArray3[0] = menuBar;
                    menuElementArray3[1] = menu;
                    menuElementArray = menuElementArray3;
                    menuElementArray3[2] = menu.getPopupMenu();
                }
                menuSelectionManager.setSelectedPath(menuElementArray);
                FlatLaf.showMnemonics(menuBar);
            }
        }
    }
}

