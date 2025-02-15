/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Caret;
import org.fife.ui.rsyntaxtextarea.OccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaHighlighter;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.SmartHighlightPainter;

class MarkOccurrencesSupport
implements CaretListener,
ActionListener {
    private RSyntaxTextArea textArea;
    private Timer timer;
    private SmartHighlightPainter p;
    static final Color DEFAULT_COLOR = new Color(224, 224, 224);
    static final int DEFAULT_DELAY_MS = 1000;

    MarkOccurrencesSupport() {
        this(1000);
    }

    MarkOccurrencesSupport(int delay) {
        this(delay, DEFAULT_COLOR);
    }

    MarkOccurrencesSupport(int delay, Color color) {
        this.timer = new Timer(delay, this);
        this.timer.setRepeats(false);
        this.p = new SmartHighlightPainter();
        this.setColor(color);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Caret c = this.textArea.getCaret();
        if (c.getDot() != c.getMark()) {
            return;
        }
        RSyntaxDocument doc = (RSyntaxDocument)this.textArea.getDocument();
        OccurrenceMarker occurrenceMarker = doc.getOccurrenceMarker();
        boolean occurrencesChanged = false;
        if (occurrenceMarker != null) {
            doc.readLock();
            try {
                Token t = occurrenceMarker.getTokenToMark(this.textArea);
                if (t != null && occurrenceMarker.isValidType(this.textArea, t) && !RSyntaxUtilities.isNonWordChar(t)) {
                    this.clear();
                    RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.textArea.getHighlighter();
                    occurrenceMarker.markOccurrences(doc, t, h, this.p);
                    occurrencesChanged = true;
                } else {
                    this.clear();
                }
            } finally {
                doc.readUnlock();
            }
        }
        if (occurrencesChanged) {
            this.textArea.fireMarkedOccurrencesChanged();
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        this.timer.restart();
    }

    void clear() {
        if (this.textArea != null) {
            RSyntaxTextAreaHighlighter h = (RSyntaxTextAreaHighlighter)this.textArea.getHighlighter();
            h.clearMarkOccurrencesHighlights();
        }
    }

    public void doMarkOccurrences() {
        this.timer.stop();
        this.actionPerformed(null);
    }

    public Color getColor() {
        return (Color)this.p.getPaint();
    }

    public int getDelay() {
        return this.timer.getDelay();
    }

    public boolean getPaintBorder() {
        return this.p.getPaintBorder();
    }

    public void install(RSyntaxTextArea textArea) {
        if (this.textArea != null) {
            this.uninstall();
        }
        this.textArea = textArea;
        textArea.addCaretListener(this);
        if (textArea.getMarkOccurrencesColor() != null) {
            this.setColor(textArea.getMarkOccurrencesColor());
        }
    }

    public void setColor(Color color) {
        this.p.setPaint(color);
        if (this.textArea != null) {
            this.clear();
            this.caretUpdate(null);
        }
    }

    public void setDelay(int delay) {
        this.timer.setInitialDelay(delay);
    }

    public void setPaintBorder(boolean paint) {
        if (paint != this.p.getPaintBorder()) {
            this.p.setPaintBorder(paint);
            if (this.textArea != null) {
                this.textArea.repaint();
            }
        }
    }

    public void uninstall() {
        if (this.textArea != null) {
            this.clear();
            this.textArea.removeCaretListener(this);
        }
    }
}

