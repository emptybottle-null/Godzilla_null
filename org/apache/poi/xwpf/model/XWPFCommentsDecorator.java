/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.model;

import org.apache.poi.xwpf.model.XWPFParagraphDecorator;
import org.apache.poi.xwpf.usermodel.XWPFComment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;

public class XWPFCommentsDecorator
extends XWPFParagraphDecorator {
    private StringBuilder commentText = new StringBuilder(64);

    public XWPFCommentsDecorator(XWPFParagraphDecorator nextDecorator) {
        this(nextDecorator.paragraph, nextDecorator);
    }

    public XWPFCommentsDecorator(XWPFParagraph paragraph, XWPFParagraphDecorator nextDecorator) {
        super(paragraph, nextDecorator);
        for (CTMarkupRange anchor : paragraph.getCTP().getCommentRangeStartArray()) {
            XWPFComment comment = paragraph.getDocument().getCommentByID(anchor.getId().toString());
            if (comment == null) continue;
            this.commentText.append("\tComment by ").append(comment.getAuthor()).append(": ").append(comment.getText());
        }
    }

    public String getCommentText() {
        return this.commentText.toString();
    }

    @Override
    public String getText() {
        return super.getText() + this.commentText;
    }
}

