/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.mozilla.javascript.tools.debugger.FileTextArea;

class FilePopupMenu
extends JPopupMenu {
    private static final long serialVersionUID = 3589525009546013565L;
    int x;
    int y;

    public FilePopupMenu(FileTextArea w) {
        JMenuItem item = new JMenuItem("Set Breakpoint");
        this.add(item);
        item.addActionListener(w);
        item = new JMenuItem("Clear Breakpoint");
        this.add(item);
        item.addActionListener(w);
        item = new JMenuItem("Run");
        this.add(item);
        item.addActionListener(w);
    }

    public void show(JComponent comp, int x, int y) {
        this.x = x;
        this.y = y;
        super.show(comp, x, y);
    }
}

