/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

public class XWPFHyperlinkRun
extends XWPFRun {
    private CTHyperlink hyperlink;

    public XWPFHyperlinkRun(CTHyperlink hyperlink, CTR run, IRunBody p) {
        super(run, p);
        this.hyperlink = hyperlink;
    }

    @Internal
    public CTHyperlink getCTHyperlink() {
        return this.hyperlink;
    }

    public String getAnchor() {
        return this.hyperlink.getAnchor();
    }

    public String getHyperlinkId() {
        return this.hyperlink.getId();
    }

    public void setHyperlinkId(String id) {
        this.hyperlink.setId(id);
    }

    public XWPFHyperlink getHyperlink(XWPFDocument document) {
        String id = this.getHyperlinkId();
        if (id == null) {
            return null;
        }
        return document.getHyperlinkByID(id);
    }
}

