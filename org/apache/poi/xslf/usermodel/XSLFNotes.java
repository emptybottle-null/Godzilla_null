/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.Notes;
import org.apache.poi.xslf.usermodel.XSLFNotesMaster;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesDocument;

public final class XSLFNotes
extends XSLFSheet
implements Notes<XSLFShape, XSLFTextParagraph> {
    private CTNotesSlide _notes;

    XSLFNotes() {
        this._notes = XSLFNotes.prototype();
    }

    XSLFNotes(PackagePart part) throws IOException, XmlException {
        super(part);
        NotesDocument doc = NotesDocument.Factory.parse(this.getPackagePart().getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        this._notes = doc.getNotes();
    }

    private static CTNotesSlide prototype() {
        CTNotesSlide ctNotes = CTNotesSlide.Factory.newInstance();
        CTCommonSlideData cSld = ctNotes.addNewCSld();
        cSld.addNewSpTree();
        return ctNotes;
    }

    @Override
    public CTNotesSlide getXmlObject() {
        return this._notes;
    }

    @Override
    protected String getRootElementName() {
        return "notes";
    }

    @Override
    public XSLFTheme getTheme() {
        XSLFNotesMaster m = this.getMasterSheet();
        return m != null ? m.getTheme() : null;
    }

    public XSLFNotesMaster getMasterSheet() {
        for (POIXMLDocumentPart p : this.getRelations()) {
            if (!(p instanceof XSLFNotesMaster)) continue;
            return (XSLFNotesMaster)p;
        }
        return null;
    }

    @Override
    public List<List<XSLFTextParagraph>> getTextParagraphs() {
        ArrayList<List<XSLFTextParagraph>> tp = new ArrayList<List<XSLFTextParagraph>>();
        for (XSLFShape sh : super.getShapes()) {
            if (!(sh instanceof XSLFTextShape)) continue;
            XSLFTextShape txt = (XSLFTextShape)sh;
            tp.add(txt.getTextParagraphs());
        }
        return tp;
    }

    @Override
    String mapSchemeColor(String schemeColor) {
        return this.mapSchemeColor(this._notes.getClrMapOvr(), schemeColor);
    }
}

