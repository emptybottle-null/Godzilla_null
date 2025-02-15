/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public abstract class EscapableDialog
extends JDialog {
    private static final String ESCAPE_KEY = "OnEsc";

    public EscapableDialog() {
        this.init();
    }

    public EscapableDialog(Dialog owner) {
        super(owner);
        this.init();
    }

    public EscapableDialog(Dialog owner, boolean modal) {
        super(owner, modal);
        this.init();
    }

    public EscapableDialog(Dialog owner, String title) {
        super(owner, title);
        this.init();
    }

    public EscapableDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
        this.init();
    }

    public EscapableDialog(Frame owner) {
        super(owner);
        this.init();
    }

    public EscapableDialog(Frame owner, boolean modal) {
        super(owner, modal);
        this.init();
    }

    public EscapableDialog(Frame owner, String title) {
        super(owner, title);
        this.init();
    }

    public EscapableDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.init();
    }

    protected void escapePressed() {
        this.setVisible(false);
    }

    private void init() {
        this.setEscapeClosesDialog(true);
    }

    public void setEscapeClosesDialog(boolean closes) {
        JRootPane rootPane = this.getRootPane();
        InputMap im = rootPane.getInputMap(2);
        ActionMap actionMap = rootPane.getActionMap();
        KeyStroke ks = KeyStroke.getKeyStroke(27, 0);
        if (closes) {
            im.put(ks, ESCAPE_KEY);
            actionMap.put(ESCAPE_KEY, new AbstractAction(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    EscapableDialog.this.escapePressed();
                }
            });
        } else {
            im.remove(ks);
            actionMap.remove(ESCAPE_KEY);
        }
    }
}

