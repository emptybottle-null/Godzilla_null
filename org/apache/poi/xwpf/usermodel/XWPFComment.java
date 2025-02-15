/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTComment;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

public class XWPFComment {
    protected String id;
    protected String author;
    protected StringBuilder text = new StringBuilder(64);

    public XWPFComment(CTComment comment, XWPFDocument document) {
        this.id = comment.getId().toString();
        this.author = comment.getAuthor();
        for (CTP ctp : comment.getPArray()) {
            if (this.text.length() > 0) {
                this.text.append("\n");
            }
            XWPFParagraph p = new XWPFParagraph(ctp, document);
            this.text.append(p.getText());
        }
    }

    public String getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getText() {
        return this.text.toString();
    }
}

