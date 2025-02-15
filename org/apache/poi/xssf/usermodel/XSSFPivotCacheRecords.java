/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheRecords;

public class XSSFPivotCacheRecords
extends POIXMLDocumentPart {
    private CTPivotCacheRecords ctPivotCacheRecords;

    public XSSFPivotCacheRecords() {
        this.ctPivotCacheRecords = CTPivotCacheRecords.Factory.newInstance();
    }

    protected XSSFPivotCacheRecords(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    protected void readFrom(InputStream is) throws IOException {
        try {
            XmlOptions options = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            options.setLoadReplaceDocumentElement(null);
            this.ctPivotCacheRecords = CTPivotCacheRecords.Factory.parse(is, options);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    @Internal
    public CTPivotCacheRecords getCtPivotCacheRecords() {
        return this.ctPivotCacheRecords;
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTPivotCacheRecords.type.getName().getNamespaceURI(), "pivotCacheRecords"));
        this.ctPivotCacheRecords.save(out, xmlOptions);
        out.close();
    }
}

