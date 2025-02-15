/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.usermodel.PositionInParagraph;

public class TextSegment {
    private PositionInParagraph beginPos;
    private PositionInParagraph endPos;

    public TextSegment() {
        this.beginPos = new PositionInParagraph();
        this.endPos = new PositionInParagraph();
    }

    public TextSegment(int beginRun, int endRun, int beginText, int endText, int beginChar, int endChar) {
        PositionInParagraph beginPos = new PositionInParagraph(beginRun, beginText, beginChar);
        PositionInParagraph endPos = new PositionInParagraph(endRun, endText, endChar);
        this.beginPos = beginPos;
        this.endPos = endPos;
    }

    public TextSegment(PositionInParagraph beginPos, PositionInParagraph endPos) {
        this.beginPos = beginPos;
        this.endPos = endPos;
    }

    public PositionInParagraph getBeginPos() {
        return this.beginPos;
    }

    public PositionInParagraph getEndPos() {
        return this.endPos;
    }

    public int getBeginRun() {
        return this.beginPos.getRun();
    }

    public void setBeginRun(int beginRun) {
        this.beginPos.setRun(beginRun);
    }

    public int getBeginText() {
        return this.beginPos.getText();
    }

    public void setBeginText(int beginText) {
        this.beginPos.setText(beginText);
    }

    public int getBeginChar() {
        return this.beginPos.getChar();
    }

    public void setBeginChar(int beginChar) {
        this.beginPos.setChar(beginChar);
    }

    public int getEndRun() {
        return this.endPos.getRun();
    }

    public void setEndRun(int endRun) {
        this.endPos.setRun(endRun);
    }

    public int getEndText() {
        return this.endPos.getText();
    }

    public void setEndText(int endText) {
        this.endPos.setText(endText);
    }

    public int getEndChar() {
        return this.endPos.getChar();
    }

    public void setEndChar(int endChar) {
        this.endPos.setChar(endChar);
    }
}

