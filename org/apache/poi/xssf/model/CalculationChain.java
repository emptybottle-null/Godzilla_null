/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcChain;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CalcChainDocument;

public class CalculationChain
extends POIXMLDocumentPart {
    private CTCalcChain chain;

    public CalculationChain() {
        this.chain = CTCalcChain.Factory.newInstance();
    }

    public CalculationChain(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            CalcChainDocument doc = CalcChainDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.chain = doc.getCalcChain();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public void writeTo(OutputStream out) throws IOException {
        CalcChainDocument doc = CalcChainDocument.Factory.newInstance();
        doc.setCalcChain(this.chain);
        doc.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.writeTo(out);
        out.close();
    }

    public CTCalcChain getCTCalcChain() {
        return this.chain;
    }

    public void removeItem(int sheetId, String ref) {
        int id = -1;
        CTCalcCell[] c = this.chain.getCArray();
        for (int i = 0; i < c.length; ++i) {
            if (c[i].isSetI()) {
                id = c[i].getI();
            }
            if (id != sheetId || !c[i].getR().equals(ref)) continue;
            if (c[i].isSetI() && i < c.length - 1 && !c[i + 1].isSetI()) {
                c[i + 1].setI(id);
            }
            this.chain.removeC(i);
            break;
        }
    }
}

