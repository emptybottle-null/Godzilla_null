/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.xml.stream.XMLStreamException;

public class XMLStreamValidationException
extends XMLStreamException {
    private XmlError _xmlError;

    public XMLStreamValidationException(XmlError xmlError) {
        super(xmlError.toString());
        this._xmlError = xmlError;
    }

    public XmlError getXmlError() {
        return this._xmlError;
    }
}

