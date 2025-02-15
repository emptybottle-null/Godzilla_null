/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.usermodel.FootnoteEndnoteIdManager;
import org.apache.poi.xwpf.usermodel.XWPFAbstractFootnoteEndnote;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public abstract class XWPFAbstractFootnotesEndnotes
extends POIXMLDocumentPart {
    protected XWPFDocument document;
    protected List<XWPFAbstractFootnoteEndnote> listFootnote = new ArrayList<XWPFAbstractFootnoteEndnote>();
    private FootnoteEndnoteIdManager idManager;

    public XWPFAbstractFootnotesEndnotes(OPCPackage pkg) {
        super(pkg);
    }

    public XWPFAbstractFootnotesEndnotes(OPCPackage pkg, String coreDocumentRel) {
        super(pkg, coreDocumentRel);
    }

    public XWPFAbstractFootnotesEndnotes() {
    }

    public XWPFAbstractFootnotesEndnotes(PackagePart part) {
        super(part);
    }

    public XWPFAbstractFootnotesEndnotes(POIXMLDocumentPart parent, PackagePart part) {
        super(parent, part);
    }

    public XWPFAbstractFootnoteEndnote getFootnoteById(int id) {
        for (XWPFAbstractFootnoteEndnote note : this.listFootnote) {
            if (note.getCTFtnEdn().getId().intValue() != id) continue;
            return note;
        }
        return null;
    }

    public XWPFDocument getXWPFDocument() {
        if (this.document != null) {
            return this.document;
        }
        return (XWPFDocument)this.getParent();
    }

    public void setXWPFDocument(XWPFDocument doc) {
        this.document = doc;
    }

    public void setIdManager(FootnoteEndnoteIdManager footnoteIdManager) {
        this.idManager = footnoteIdManager;
    }

    public FootnoteEndnoteIdManager getIdManager() {
        return this.idManager;
    }
}

