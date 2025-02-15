/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.NotesMasterDocument;

public class XSLFNotesMaster
extends XSLFSheet
implements MasterSheet<XSLFShape, XSLFTextParagraph> {
    private CTNotesMaster _slide;

    XSLFNotesMaster() {
        this._slide = XSLFNotesMaster.prototype();
    }

    protected XSLFNotesMaster(PackagePart part) throws IOException, XmlException {
        super(part);
        NotesMasterDocument doc = NotesMasterDocument.Factory.parse(this.getPackagePart().getInputStream(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        this._slide = doc.getNotesMaster();
    }

    private static CTNotesMaster prototype() {
        CTNotesMaster cTNotesMaster;
        InputStream is = XSLFNotesMaster.class.getResourceAsStream("notesMaster.xml");
        if (is == null) {
            throw new POIXMLException("Missing resource 'notesMaster.xml'");
        }
        try {
            NotesMasterDocument doc = NotesMasterDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            cTNotesMaster = doc.getNotesMaster();
        } catch (Throwable throwable) {
            try {
                is.close();
                throw throwable;
            } catch (Exception e) {
                throw new POIXMLException("Can't initialize NotesMaster", e);
            }
        }
        is.close();
        return cTNotesMaster;
    }

    @Override
    public CTNotesMaster getXmlObject() {
        return this._slide;
    }

    @Override
    protected String getRootElementName() {
        return "notesMaster";
    }

    @Override
    public MasterSheet<XSLFShape, XSLFTextParagraph> getMasterSheet() {
        return null;
    }

    @Override
    boolean isSupportTheme() {
        return true;
    }

    @Override
    String mapSchemeColor(String schemeColor) {
        String notesMasterColor = this.mapSchemeColor(this._slide.getClrMap(), schemeColor);
        return notesMasterColor == null ? schemeColor : notesMasterColor;
    }
}

