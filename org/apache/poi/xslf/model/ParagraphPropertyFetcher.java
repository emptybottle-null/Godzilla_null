/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.model;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.xslf.model.PropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public abstract class ParagraphPropertyFetcher<T>
extends PropertyFetcher<T> {
    static final String PML_NS = "http://schemas.openxmlformats.org/presentationml/2006/main";
    static final String DML_NS = "http://schemas.openxmlformats.org/drawingml/2006/main";
    private static final QName[] TX_BODY = new QName[]{new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "txBody")};
    private static final QName[] LST_STYLE = new QName[]{new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lstStyle")};
    int _level;

    public ParagraphPropertyFetcher(int level) {
        this._level = level;
    }

    @Override
    public boolean fetch(XSLFShape shape) {
        QName[] lvlProp = new QName[]{new QName(DML_NS, "lvl" + (this._level + 1) + "pPr")};
        CTTextParagraphProperties props = null;
        try {
            props = shape.selectProperty(CTTextParagraphProperties.class, ParagraphPropertyFetcher::parse, TX_BODY, LST_STYLE, lvlProp);
            return props != null && this.fetch(props);
        } catch (XmlException e) {
            return false;
        }
    }

    private static CTTextParagraphProperties parse(XMLStreamReader reader) throws XmlException {
        CTTextParagraph para = CTTextParagraph.Factory.parse(reader);
        return para != null && para.isSetPPr() ? para.getPPr() : null;
    }

    public abstract boolean fetch(CTTextParagraphProperties var1);
}

