/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCache;

public class XSSFPivotCache
extends POIXMLDocumentPart {
    private CTPivotCache ctPivotCache;

    public XSSFPivotCache() {
        this.ctPivotCache = CTPivotCache.Factory.newInstance();
    }

    public XSSFPivotCache(CTPivotCache ctPivotCache) {
        this.ctPivotCache = ctPivotCache;
    }

    protected XSSFPivotCache(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    protected void readFrom(InputStream is) throws IOException {
        try {
            XmlOptions options = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            options.setLoadReplaceDocumentElement(null);
            this.ctPivotCache = CTPivotCache.Factory.parse(is, options);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public CTPivotCache getCTPivotCache() {
        return this.ctPivotCache;
    }
}

