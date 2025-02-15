/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.model;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.xslf.model.PropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;

public abstract class TextBodyPropertyFetcher<T>
extends PropertyFetcher<T> {
    private static final QName[] TX_BODY = new QName[]{new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "txBody")};
    private static final QName[] BODY_PR = new QName[]{new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "bodyPr")};

    @Override
    public boolean fetch(XSLFShape shape) {
        CTTextBodyProperties props = null;
        try {
            props = shape.selectProperty(CTTextBodyProperties.class, TextBodyPropertyFetcher::parse, TX_BODY, BODY_PR);
            return props != null && this.fetch(props);
        } catch (XmlException e) {
            return false;
        }
    }

    private static CTTextBodyProperties parse(XMLStreamReader reader) throws XmlException {
        CTTextBody body = CTTextBody.Factory.parse(reader);
        return body != null ? body.getBodyPr() : null;
    }

    public abstract boolean fetch(CTTextBodyProperties var1);
}

