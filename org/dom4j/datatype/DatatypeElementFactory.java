/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.sun.msv.datatype.xsd.XSDatatype
 */
package org.dom4j.datatype;

import com.sun.msv.datatype.xsd.XSDatatype;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.datatype.DatatypeAttribute;
import org.dom4j.datatype.DatatypeElement;

public class DatatypeElementFactory
extends DocumentFactory {
    private QName elementQName;
    private Map<QName, XSDatatype> attributeXSDatatypes = new HashMap<QName, XSDatatype>();
    private Map<QName, XSDatatype> childrenXSDatatypes = new HashMap<QName, XSDatatype>();

    public DatatypeElementFactory(QName elementQName) {
        this.elementQName = elementQName;
    }

    public QName getQName() {
        return this.elementQName;
    }

    public XSDatatype getAttributeXSDatatype(QName attributeQName) {
        return this.attributeXSDatatypes.get(attributeQName);
    }

    public void setAttributeXSDatatype(QName attributeQName, XSDatatype type) {
        this.attributeXSDatatypes.put(attributeQName, type);
    }

    public XSDatatype getChildElementXSDatatype(QName qname) {
        return this.childrenXSDatatypes.get(qname);
    }

    public void setChildElementXSDatatype(QName qname, XSDatatype dataType) {
        this.childrenXSDatatypes.put(qname, dataType);
    }

    @Override
    public Element createElement(QName qname) {
        DatatypeElementFactory dtFactory;
        XSDatatype dataType = this.getChildElementXSDatatype(qname);
        if (dataType != null) {
            return new DatatypeElement(qname, dataType);
        }
        DocumentFactory factory = qname.getDocumentFactory();
        if (factory instanceof DatatypeElementFactory && (dataType = (dtFactory = (DatatypeElementFactory)factory).getChildElementXSDatatype(qname)) != null) {
            return new DatatypeElement(qname, dataType);
        }
        return super.createElement(qname);
    }

    @Override
    public Attribute createAttribute(Element owner, QName qname, String value) {
        XSDatatype dataType = this.getAttributeXSDatatype(qname);
        if (dataType == null) {
            return super.createAttribute(owner, qname, value);
        }
        return new DatatypeAttribute(qname, dataType, value);
    }
}

