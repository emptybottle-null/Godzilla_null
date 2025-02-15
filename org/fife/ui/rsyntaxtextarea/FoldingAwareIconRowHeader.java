/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.Icon;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.folding.FoldManager;
import org.fife.ui.rtextarea.IconRowHeader;

public class FoldingAwareIconRowHeader
extends IconRowHeader {
    public FoldingAwareIconRowHeader(RSyntaxTextArea textArea) {
        super(textArea);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (this.textArea == null) {
            return;
        }
        RSyntaxTextArea rsta = (RSyntaxTextArea)this.textArea;
        FoldManager fm = rsta.getFoldManager();
        if (!fm.isCodeFoldingSupportedAndEnabled()) {
            super.paintComponent(g);
            return;
        }
        this.visibleRect = g.getClipBounds(this.visibleRect);
        if (this.visibleRect == null) {
            this.visibleRect = this.getVisibleRect();
        }
        if (this.visibleRect == null) {
            return;
        }
        this.paintBackgroundImpl(g, this.visibleRect);
        if (this.textArea.getLineWrap()) {
            this.paintComponentWrapped(g);
            return;
        }
        Document doc = this.textArea.getDocument();
        Element root = doc.getDefaultRootElement();
        this.textAreaInsets = this.textArea.getInsets(this.textAreaInsets);
        if (this.visibleRect.y < this.textAreaInsets.top) {
            this.visibleRect.height -= this.textAreaInsets.top - this.visibleRect.y;
            this.visibleRect.y = this.textAreaInsets.top;
        }
        int cellHeight = this.textArea.getLineHeight();
        int topLine = (this.visibleRect.y - this.textAreaInsets.top) / cellHeight;
        int y = topLine * cellHeight + this.textAreaInsets.top;
        topLine += fm.getHiddenLineCountAbove(topLine, true);
        if (this.activeLineRangeStart > -1 && this.activeLineRangeEnd > -1) {
            Color activeLineRangeColor = this.getActiveLineRangeColor();
            g.setColor(activeLineRangeColor);
            try {
                int realY1 = rsta.yForLine(this.activeLineRangeStart);
                if (realY1 > -1) {
                    int y1 = realY1;
                    int y2 = rsta.yForLine(this.activeLineRangeEnd);
                    if (y2 == -1) {
                        y2 = y1;
                    }
                    if ((y2 += cellHeight - 1) < this.visibleRect.y || y1 > this.visibleRect.y + this.visibleRect.height) {
                        return;
                    }
                    y1 = Math.max(y, realY1);
                    y2 = Math.min(y2, this.visibleRect.y + this.visibleRect.height);
                    for (int j = y1; j <= y2; j += 2) {
                        int yEnd = Math.min(y2, j + this.getWidth());
                        int xEnd = yEnd - j;
                        g.drawLine(0, j, xEnd, yEnd);
                    }
                    for (int i = 2; i < this.getWidth(); i += 2) {
                        int yEnd = y1 + this.getWidth() - i;
                        g.drawLine(i, y1, this.getWidth(), yEnd);
                    }
                    if (realY1 >= y && realY1 < this.visibleRect.y + this.visibleRect.height) {
                        g.drawLine(0, realY1, this.getWidth(), realY1);
                    }
                    if (y2 >= y && y2 < this.visibleRect.y + this.visibleRect.height) {
                        g.drawLine(0, y2, this.getWidth(), y2);
                    }
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        if (this.trackingIcons != null) {
            int lastLine = this.textArea.getLineCount() - 1;
            for (int i = this.trackingIcons.size() - 1; i >= 0; --i) {
                IconRowHeader.GutterIconImpl ti = this.getTrackingIcon(i);
                int offs = ti.getMarkedOffset();
                if (offs < 0 || offs > doc.getLength()) continue;
                int line = root.getElementIndex(offs);
                if (line <= lastLine && line >= topLine) {
                    try {
                        int lineY;
                        Icon icon = ti.getIcon();
                        if (icon == null || (lineY = rsta.yForLine(line)) < y || lineY > this.visibleRect.y + this.visibleRect.height) continue;
                        int y2 = lineY + (cellHeight - icon.getIconHeight()) / 2;
                        icon.paintIcon(this, g, 0, y2);
                        lastLine = line - 1;
                    } catch (BadLocationException ble) {
                        ble.printStackTrace();
                    }
                    continue;
                }
                if (line < topLine) break;
            }
        }
    }

    private void paintComponentWrapped(Graphics g) {
        RSyntaxTextArea rsta = (RSyntaxTextArea)this.textArea;
        Document doc = this.textArea.getDocument();
        Element root = doc.getDefaultRootElement();
        int topPosition = this.textArea.viewToModel(new Point(this.visibleRect.x, this.visibleRect.y));
        int topLine = root.getElementIndex(topPosition);
        int topY = this.visibleRect.y;
        int bottomY = this.visibleRect.y + this.visibleRect.height;
        int cellHeight = this.textArea.getLineHeight();
        if (this.trackingIcons != null) {
            int lastLine = this.textArea.getLineCount() - 1;
            for (int i = this.trackingIcons.size() - 1; i >= 0; --i) {
                IconRowHeader.GutterIconImpl ti = this.getTrackingIcon(i);
                Icon icon = ti.getIcon();
                if (icon == null) continue;
                int iconH = icon.getIconHeight();
                int offs = ti.getMarkedOffset();
                if (offs < 0 || offs > doc.getLength()) continue;
                int line = root.getElementIndex(offs);
                if (line <= lastLine && line >= topLine) {
                    try {
                        int lineY = rsta.yForLine(line);
                        if (lineY > bottomY || lineY + iconH < topY) continue;
                        int y2 = lineY + (cellHeight - iconH) / 2;
                        ti.getIcon().paintIcon(this, g, 0, y2);
                        lastLine = line - 1;
                    } catch (BadLocationException ble) {
                        ble.printStackTrace();
                    }
                    continue;
                }
                if (line < topLine) break;
            }
        }
    }
}

