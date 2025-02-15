/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import javax.swing.plaf.UIResource;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class FlatCaret
extends DefaultCaret
implements UIResource {
    private final String selectAllOnFocusPolicy;
    private final boolean selectAllOnMouseClick;
    private boolean wasFocused;
    private boolean wasTemporaryLost;
    private boolean isMousePressed;

    public FlatCaret(String selectAllOnFocusPolicy, boolean selectAllOnMouseClick) {
        this.selectAllOnFocusPolicy = selectAllOnFocusPolicy;
        this.selectAllOnMouseClick = selectAllOnMouseClick;
    }

    @Override
    public void install(JTextComponent c) {
        int length;
        super.install(c);
        Document doc = c.getDocument();
        if (doc != null && this.getDot() == 0 && this.getMark() == 0 && (length = doc.getLength()) > 0) {
            this.setDot(length);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (!(this.wasTemporaryLost || this.isMousePressed && !this.selectAllOnMouseClick)) {
            this.selectAllOnFocusGained();
        }
        this.wasTemporaryLost = false;
        this.wasFocused = true;
        super.focusGained(e);
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.wasTemporaryLost = e.isTemporary();
        super.focusLost(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.isMousePressed = true;
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.isMousePressed = false;
        super.mouseReleased(e);
    }

    protected void selectAllOnFocusGained() {
        JTextComponent c = this.getComponent();
        Document doc = c.getDocument();
        if (doc == null || !c.isEnabled() || !c.isEditable()) {
            return;
        }
        Object selectAllOnFocusPolicy = c.getClientProperty("JTextField.selectAllOnFocusPolicy");
        if (selectAllOnFocusPolicy == null) {
            selectAllOnFocusPolicy = this.selectAllOnFocusPolicy;
        }
        if ("never".equals(selectAllOnFocusPolicy)) {
            return;
        }
        if (!"always".equals(selectAllOnFocusPolicy)) {
            int mark;
            if (this.wasFocused) {
                return;
            }
            int dot = this.getDot();
            if (dot != (mark = this.getMark()) || dot != doc.getLength()) {
                return;
            }
        }
        if (c instanceof JFormattedTextField) {
            EventQueue.invokeLater(() -> {
                this.setDot(0);
                this.moveDot(doc.getLength());
            });
        } else {
            this.setDot(0);
            this.moveDot(doc.getLength());
        }
    }
}

