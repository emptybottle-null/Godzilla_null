/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlSaxHandler;
import org.apache.xmlbeans.XmlTokenSource;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;

public interface XmlObject
extends XmlTokenSource {
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_anyType");
    public static final int LESS_THAN = -1;
    public static final int EQUAL = 0;
    public static final int GREATER_THAN = 1;
    public static final int NOT_EQUAL = 2;

    public SchemaType schemaType();

    public boolean validate();

    public boolean validate(XmlOptions var1);

    public XmlObject[] selectPath(String var1);

    public XmlObject[] selectPath(String var1, XmlOptions var2);

    public XmlObject[] execQuery(String var1);

    public XmlObject[] execQuery(String var1, XmlOptions var2);

    public XmlObject changeType(SchemaType var1);

    public XmlObject substitute(QName var1, SchemaType var2);

    public boolean isNil();

    public void setNil();

    public String toString();

    public boolean isImmutable();

    public XmlObject set(XmlObject var1);

    public XmlObject copy();

    public XmlObject copy(XmlOptions var1);

    public boolean valueEquals(XmlObject var1);

    public int valueHashCode();

    public int compareTo(Object var1);

    public int compareValue(XmlObject var1);

    public XmlObject[] selectChildren(QName var1);

    public XmlObject[] selectChildren(String var1, String var2);

    public XmlObject[] selectChildren(QNameSet var1);

    public XmlObject selectAttribute(QName var1);

    public XmlObject selectAttribute(String var1, String var2);

    public XmlObject[] selectAttributes(QNameSet var1);

    public static final class Factory {
        public static XmlObject newInstance() {
            return XmlBeans.getContextTypeLoader().newInstance(null, null);
        }

        public static XmlObject newInstance(XmlOptions options) {
            return XmlBeans.getContextTypeLoader().newInstance(null, options);
        }

        public static XmlObject newValue(Object obj) {
            return type.newValue(obj);
        }

        public static XmlObject parse(String xmlAsString) throws XmlException {
            return XmlBeans.getContextTypeLoader().parse(xmlAsString, null, null);
        }

        public static XmlObject parse(String xmlAsString, XmlOptions options) throws XmlException {
            return XmlBeans.getContextTypeLoader().parse(xmlAsString, null, options);
        }

        public static XmlObject parse(File file) throws XmlException, IOException {
            return XmlBeans.getContextTypeLoader().parse(file, null, null);
        }

        public static XmlObject parse(File file, XmlOptions options) throws XmlException, IOException {
            return XmlBeans.getContextTypeLoader().parse(file, null, options);
        }

        public static XmlObject parse(URL u) throws XmlException, IOException {
            return XmlBeans.getContextTypeLoader().parse(u, null, null);
        }

        public static XmlObject parse(URL u, XmlOptions options) throws XmlException, IOException {
            return XmlBeans.getContextTypeLoader().parse(u, null, options);
        }

        public static XmlObject parse(InputStream is) throws XmlException, IOException {
            return XmlBeans.getContextTypeLoader().parse(is, null, null);
        }

        public static XmlObject parse(XMLStreamReader xsr) throws XmlException {
            return XmlBeans.getContextTypeLoader().parse(xsr, null, null);
        }

        public static XmlObject parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return XmlBeans.getContextTypeLoader().parse(is, null, options);
        }

        public static XmlObject parse(XMLStreamReader xsr, XmlOptions options) throws XmlException {
            return XmlBeans.getContextTypeLoader().parse(xsr, null, options);
        }

        public static XmlObject parse(Reader r) throws XmlException, IOException {
            return XmlBeans.getContextTypeLoader().parse(r, null, null);
        }

        public static XmlObject parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return XmlBeans.getContextTypeLoader().parse(r, null, options);
        }

        public static XmlObject parse(Node node) throws XmlException {
            return XmlBeans.getContextTypeLoader().parse(node, null, null);
        }

        public static XmlObject parse(Node node, XmlOptions options) throws XmlException {
            return XmlBeans.getContextTypeLoader().parse(node, null, options);
        }

        public static XmlObject parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().parse(xis, null, null);
        }

        public static XmlObject parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().parse(xis, null, options);
        }

        public static XmlSaxHandler newXmlSaxHandler() {
            return XmlBeans.getContextTypeLoader().newXmlSaxHandler(null, null);
        }

        public static XmlSaxHandler newXmlSaxHandler(XmlOptions options) {
            return XmlBeans.getContextTypeLoader().newXmlSaxHandler(null, options);
        }

        public static DOMImplementation newDomImplementation() {
            return XmlBeans.getContextTypeLoader().newDomImplementation(null);
        }

        public static DOMImplementation newDomImplementation(XmlOptions options) {
            return XmlBeans.getContextTypeLoader().newDomImplementation(options);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, null, null);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, null, options);
        }

        private Factory() {
        }
    }
}

