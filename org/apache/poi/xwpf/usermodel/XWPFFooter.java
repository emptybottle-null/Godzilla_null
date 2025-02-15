/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.usermodel.BodyType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FtrDocument;

public class XWPFFooter
extends XWPFHeaderFooter {
    public XWPFFooter() {
    }

    public XWPFFooter(XWPFDocument doc, CTHdrFtr hdrFtr) throws IOException {
        super(doc, hdrFtr);
        XmlCursor cursor = this.headerFooter.newCursor();
        cursor.selectPath("./*");
        while (cursor.toNextSelection()) {
            XmlObject o = cursor.getObject();
            if (o instanceof CTP) {
                XWPFParagraph p = new XWPFParagraph((CTP)o, this);
                this.paragraphs.add(p);
                this.bodyElements.add(p);
            }
            if (!(o instanceof CTTbl)) continue;
            XWPFTable t = new XWPFTable((CTTbl)o, this);
            this.tables.add(t);
            this.bodyElements.add(t);
        }
        cursor.dispose();
    }

    public XWPFFooter(POIXMLDocumentPart parent, PackagePart part) throws IOException {
        super(parent, part);
    }

    @Override
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTNumbering.type.getName().getNamespaceURI(), "ftr"));
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        super._getHdrFtr().save(out, xmlOptions);
        out.close();
    }

    @Override
    protected void onDocumentRead() throws IOException {
        super.onDocumentRead();
        FtrDocument ftrDocument = null;
        try (InputStream is = this.getPackagePart().getInputStream();){
            ftrDocument = FtrDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.headerFooter = ftrDocument.getFtr();
            XmlCursor cursor = this.headerFooter.newCursor();
            cursor.selectPath("./*");
            while (cursor.toNextSelection()) {
                XmlObject o = cursor.getObject();
                if (o instanceof CTP) {
                    XWPFParagraph p = new XWPFParagraph((CTP)o, this);
                    this.paragraphs.add(p);
                    this.bodyElements.add(p);
                }
                if (o instanceof CTTbl) {
                    XWPFTable t = new XWPFTable((CTTbl)o, this);
                    this.tables.add(t);
                    this.bodyElements.add(t);
                }
                if (!(o instanceof CTSdtBlock)) continue;
                XWPFSDT c = new XWPFSDT((CTSdtBlock)o, (IBody)this);
                this.bodyElements.add(c);
            }
            cursor.dispose();
        } catch (Exception e) {
            throw new POIXMLException(e);
        }
    }

    @Override
    public BodyType getPartType() {
        return BodyType.FOOTER;
    }
}

