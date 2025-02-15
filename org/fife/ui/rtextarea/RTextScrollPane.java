/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.Arrays;
import java.util.Stack;
import javax.swing.JScrollPane;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextArea;

public class RTextScrollPane
extends JScrollPane {
    private Gutter gutter;

    public RTextScrollPane() {
        this(null, true);
    }

    public RTextScrollPane(RTextArea textArea) {
        this(textArea, true);
    }

    public RTextScrollPane(Component comp) {
        this(comp, true);
    }

    public RTextScrollPane(RTextArea textArea, boolean lineNumbers) {
        this((Component)textArea, lineNumbers, Color.GRAY);
    }

    public RTextScrollPane(Component comp, boolean lineNumbers) {
        this(comp, lineNumbers, Color.GRAY);
    }

    public RTextScrollPane(Component comp, boolean lineNumbers, Color lineNumberColor) {
        super(comp);
        RTextArea textArea = RTextScrollPane.getFirstRTextAreaDescendant(comp);
        Font defaultFont = new Font("Monospaced", 0, 12);
        this.gutter = new Gutter(textArea);
        this.gutter.setLineNumberFont(defaultFont);
        this.gutter.setLineNumberColor(lineNumberColor);
        this.setLineNumbersEnabled(lineNumbers);
        this.setVerticalScrollBarPolicy(22);
        this.setHorizontalScrollBarPolicy(30);
    }

    private void checkGutterVisibility() {
        int count = this.gutter.getComponentCount();
        if (count == 0) {
            if (this.getRowHeader() != null && this.getRowHeader().getView() == this.gutter) {
                this.setRowHeaderView(null);
            }
        } else if (this.getRowHeader() == null || this.getRowHeader().getView() == null) {
            this.setRowHeaderView(this.gutter);
        }
    }

    public Gutter getGutter() {
        return this.gutter;
    }

    public boolean getLineNumbersEnabled() {
        return this.gutter.getLineNumbersEnabled();
    }

    public RTextArea getTextArea() {
        return (RTextArea)this.getViewport().getView();
    }

    public boolean isFoldIndicatorEnabled() {
        return this.gutter.isFoldIndicatorEnabled();
    }

    public boolean isIconRowHeaderEnabled() {
        return this.gutter.isIconRowHeaderEnabled();
    }

    public void setFoldIndicatorEnabled(boolean enabled) {
        this.gutter.setFoldIndicatorEnabled(enabled);
        this.checkGutterVisibility();
    }

    public void setIconRowHeaderEnabled(boolean enabled) {
        this.gutter.setIconRowHeaderEnabled(enabled);
        this.checkGutterVisibility();
    }

    public void setLineNumbersEnabled(boolean enabled) {
        this.gutter.setLineNumbersEnabled(enabled);
        this.checkGutterVisibility();
    }

    @Override
    public void setViewportView(Component view) {
        RTextArea rtaCandidate = null;
        if (!(view instanceof RTextArea)) {
            rtaCandidate = RTextScrollPane.getFirstRTextAreaDescendant(view);
            if (rtaCandidate == null) {
                throw new IllegalArgumentException("view must be either an RTextArea or a JLayer wrapping one");
            }
        } else {
            rtaCandidate = (RTextArea)view;
        }
        super.setViewportView(view);
        if (this.gutter != null) {
            this.gutter.setTextArea(rtaCandidate);
        }
    }

    private static RTextArea getFirstRTextAreaDescendant(Component comp) {
        Stack<Component> stack = new Stack<Component>();
        stack.add(comp);
        while (!stack.isEmpty()) {
            Component current = (Component)stack.pop();
            if (current instanceof RTextArea) {
                return (RTextArea)current;
            }
            if (!(current instanceof Container)) continue;
            Container container = (Container)current;
            stack.addAll(Arrays.asList(container.getComponents()));
        }
        return null;
    }
}

