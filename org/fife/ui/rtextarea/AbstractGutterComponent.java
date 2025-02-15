/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.text.View;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextArea;

abstract class AbstractGutterComponent
extends JPanel {
    protected RTextArea textArea;
    protected int currentLineCount;

    AbstractGutterComponent(RTextArea textArea) {
        this.init();
        this.setTextArea(textArea);
    }

    protected static Rectangle getChildViewBounds(View parent, int line, Rectangle editorRect) {
        Shape alloc = parent.getChildAllocation(line, editorRect);
        if (alloc == null) {
            return new Rectangle();
        }
        return alloc instanceof Rectangle ? (Rectangle)alloc : alloc.getBounds();
    }

    protected Gutter getGutter() {
        Container parent = this.getParent();
        return parent instanceof Gutter ? (Gutter)parent : null;
    }

    abstract void handleDocumentEvent(DocumentEvent var1);

    protected void init() {
    }

    abstract void lineHeightsChanged();

    public void setTextArea(RTextArea textArea) {
        int lineCount;
        this.textArea = textArea;
        int n = lineCount = textArea == null ? 0 : textArea.getLineCount();
        if (this.currentLineCount != lineCount) {
            this.currentLineCount = lineCount;
            this.repaint();
        }
    }
}

