/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.gjt.xpp.XmlPullParserException
 *  org.gjt.xpp.XmlStartTag
 */
package org.dom4j.xpp;

import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.AbstractElement;
import org.gjt.xpp.XmlPullParserException;
import org.gjt.xpp.XmlStartTag;

public class ProxyXmlStartTag
implements XmlStartTag {
    private Element element;
    private DocumentFactory factory = DocumentFactory.getInstance();

    public ProxyXmlStartTag() {
    }

    public ProxyXmlStartTag(Element element) {
        this.element = element;
    }

    public void resetStartTag() {
        this.element = null;
    }

    public int getAttributeCount() {
        return this.element != null ? this.element.attributeCount() : 0;
    }

    public String getAttributeNamespaceUri(int index) {
        Attribute attribute;
        if (this.element != null && (attribute = this.element.attribute(index)) != null) {
            return attribute.getNamespaceURI();
        }
        return null;
    }

    public String getAttributeLocalName(int index) {
        Attribute attribute;
        if (this.element != null && (attribute = this.element.attribute(index)) != null) {
            return attribute.getName();
        }
        return null;
    }

    public String getAttributePrefix(int index) {
        String prefix;
        Attribute attribute;
        if (this.element != null && (attribute = this.element.attribute(index)) != null && (prefix = attribute.getNamespacePrefix()) != null && prefix.length() > 0) {
            return prefix;
        }
        return null;
    }

    public String getAttributeRawName(int index) {
        Attribute attribute;
        if (this.element != null && (attribute = this.element.attribute(index)) != null) {
            return attribute.getQualifiedName();
        }
        return null;
    }

    public String getAttributeValue(int index) {
        Attribute attribute;
        if (this.element != null && (attribute = this.element.attribute(index)) != null) {
            return attribute.getValue();
        }
        return null;
    }

    public String getAttributeValueFromRawName(String rawName) {
        if (this.element != null) {
            Iterator<Attribute> iter = this.element.attributeIterator();
            while (iter.hasNext()) {
                Attribute attribute = iter.next();
                if (!rawName.equals(attribute.getQualifiedName())) continue;
                return attribute.getValue();
            }
        }
        return null;
    }

    public String getAttributeValueFromName(String namespaceURI, String localName) {
        if (this.element != null) {
            Iterator<Attribute> iter = this.element.attributeIterator();
            while (iter.hasNext()) {
                Attribute attribute = iter.next();
                if (!namespaceURI.equals(attribute.getNamespaceURI()) || !localName.equals(attribute.getName())) continue;
                return attribute.getValue();
            }
        }
        return null;
    }

    public boolean isAttributeNamespaceDeclaration(int index) {
        Attribute attribute;
        if (this.element != null && (attribute = this.element.attribute(index)) != null) {
            return "xmlns".equals(attribute.getNamespacePrefix());
        }
        return false;
    }

    public void addAttribute(String namespaceURI, String localName, String rawName, String value) throws XmlPullParserException {
        QName qname = QName.get(rawName, namespaceURI);
        this.element.addAttribute(qname, value);
    }

    public void addAttribute(String namespaceURI, String localName, String rawName, String value, boolean isNamespaceDeclaration) throws XmlPullParserException {
        if (isNamespaceDeclaration) {
            String prefix = "";
            int idx = rawName.indexOf(58);
            if (idx > 0) {
                prefix = rawName.substring(0, idx);
            }
            this.element.addNamespace(prefix, namespaceURI);
        } else {
            QName qname = QName.get(rawName, namespaceURI);
            this.element.addAttribute(qname, value);
        }
    }

    public void ensureAttributesCapacity(int minCapacity) throws XmlPullParserException {
        if (this.element instanceof AbstractElement) {
            AbstractElement elementImpl = (AbstractElement)this.element;
            elementImpl.ensureAttributesCapacity(minCapacity);
        }
    }

    public void removeAtttributes() throws XmlPullParserException {
        if (this.element != null) {
            this.element.setAttributes(new ArrayList<Attribute>());
        }
    }

    public String getLocalName() {
        return this.element.getName();
    }

    public String getNamespaceUri() {
        return this.element.getNamespaceURI();
    }

    public String getPrefix() {
        return this.element.getNamespacePrefix();
    }

    public String getRawName() {
        return this.element.getQualifiedName();
    }

    public void modifyTag(String namespaceURI, String lName, String rawName) {
        this.element = this.factory.createElement(rawName, namespaceURI);
    }

    public void resetTag() {
        this.element = null;
    }

    public DocumentFactory getDocumentFactory() {
        return this.factory;
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        this.factory = documentFactory;
    }

    public Element getElement() {
        return this.element;
    }
}

