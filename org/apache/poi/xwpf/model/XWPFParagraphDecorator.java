/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.model;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public abstract class XWPFParagraphDecorator {
    protected XWPFParagraph paragraph;
    protected XWPFParagraphDecorator nextDecorator;

    public XWPFParagraphDecorator(XWPFParagraph paragraph) {
        this(paragraph, null);
    }

    public XWPFParagraphDecorator(XWPFParagraph paragraph, XWPFParagraphDecorator nextDecorator) {
        this.paragraph = paragraph;
        this.nextDecorator = nextDecorator;
    }

    public String getText() {
        if (this.nextDecorator != null) {
            return this.nextDecorator.getText();
        }
        return this.paragraph.getText();
    }
}

