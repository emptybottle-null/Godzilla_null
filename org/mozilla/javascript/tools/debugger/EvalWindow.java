/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import org.mozilla.javascript.tools.debugger.EvalTextArea;
import org.mozilla.javascript.tools.debugger.SwingGui;

class EvalWindow
extends JInternalFrame
implements ActionListener {
    private static final long serialVersionUID = -2860585845212160176L;
    private EvalTextArea evalTextArea;

    public EvalWindow(String name, SwingGui debugGui) {
        super(name, true, false, true, true);
        this.evalTextArea = new EvalTextArea(debugGui);
        this.evalTextArea.setRows(24);
        this.evalTextArea.setColumns(80);
        JScrollPane scroller = new JScrollPane(this.evalTextArea);
        this.setContentPane(scroller);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        this.evalTextArea.setEnabled(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Cut")) {
            this.evalTextArea.cut();
        } else if (cmd.equals("Copy")) {
            this.evalTextArea.copy();
        } else if (cmd.equals("Paste")) {
            this.evalTextArea.paste();
        }
    }
}

