/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.PrintStream;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import org.mozilla.javascript.tools.shell.ConsoleTextArea;

class JSInternalConsole
extends JInternalFrame
implements ActionListener {
    private static final long serialVersionUID = -5523468828771087292L;
    ConsoleTextArea consoleTextArea = new ConsoleTextArea(null);

    public JSInternalConsole(String name) {
        super(name, true, false, true, true);
        this.consoleTextArea.setRows(24);
        this.consoleTextArea.setColumns(80);
        JScrollPane scroller = new JScrollPane(this.consoleTextArea);
        this.setContentPane(scroller);
        this.pack();
        this.addInternalFrameListener(new InternalFrameAdapter(){

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                if (JSInternalConsole.this.consoleTextArea.hasFocus()) {
                    JSInternalConsole.this.consoleTextArea.getCaret().setVisible(false);
                    JSInternalConsole.this.consoleTextArea.getCaret().setVisible(true);
                }
            }
        });
    }

    public InputStream getIn() {
        return this.consoleTextArea.getIn();
    }

    public PrintStream getOut() {
        return this.consoleTextArea.getOut();
    }

    public PrintStream getErr() {
        return this.consoleTextArea.getErr();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Cut")) {
            this.consoleTextArea.cut();
        } else if (cmd.equals("Copy")) {
            this.consoleTextArea.copy();
        } else if (cmd.equals("Paste")) {
            this.consoleTextArea.paste();
        }
    }
}

