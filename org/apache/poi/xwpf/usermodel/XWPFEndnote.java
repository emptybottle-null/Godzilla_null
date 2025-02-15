/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xwpf.usermodel.XWPFAbstractFootnoteEndnote;
import org.apache.poi.xwpf.usermodel.XWPFAbstractFootnotesEndnotes;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

public class XWPFEndnote
extends XWPFAbstractFootnoteEndnote {
    public XWPFEndnote() {
    }

    @Internal
    public XWPFEndnote(XWPFDocument document, CTFtnEdn body) {
        super(document, body);
    }

    @Internal
    public XWPFEndnote(CTFtnEdn note, XWPFAbstractFootnotesEndnotes footnotes) {
        super(note, footnotes);
    }

    @Override
    public void ensureFootnoteRef(XWPFParagraph p) {
        XWPFRun r = null;
        if (p.getRuns().size() > 0) {
            r = p.getRuns().get(0);
        }
        if (r == null) {
            r = p.createRun();
        }
        CTR ctr = r.getCTR();
        boolean foundRef = false;
        for (CTFtnEdnRef ref : ctr.getEndnoteReferenceList()) {
            if (!this.getId().equals(ref.getId())) continue;
            foundRef = true;
            break;
        }
        if (!foundRef) {
            ctr.addNewRPr().addNewRStyle().setVal("FootnoteReference");
            ctr.addNewEndnoteRef();
        }
    }
}

