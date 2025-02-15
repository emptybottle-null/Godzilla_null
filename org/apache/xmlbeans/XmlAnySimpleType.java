/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface XmlAnySimpleType
extends XmlObject {
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_anySimpleType");

    public String stringValue();

    public void set(String var1);

    public String getStringValue();

    public void setStringValue(String var1);

    public static final class Factory {
        public static XmlAnySimpleType newInstance() {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static XmlAnySimpleType newInstance(XmlOptions options) {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static XmlAnySimpleType newValue(Object obj) {
            return type.newValue(obj);
        }

        public static XmlAnySimpleType parse(String s) throws XmlException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(s, type, null);
        }

        public static XmlAnySimpleType parse(String s, XmlOptions options) throws XmlException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(s, type, options);
        }

        public static XmlAnySimpleType parse(File f) throws XmlException, IOException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(f, type, null);
        }

        public static XmlAnySimpleType parse(File f, XmlOptions options) throws XmlException, IOException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(f, type, options);
        }

        public static XmlAnySimpleType parse(URL u) throws XmlException, IOException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static XmlAnySimpleType parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static XmlAnySimpleType parse(InputStream is) throws XmlException, IOException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static XmlAnySimpleType parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static XmlAnySimpleType parse(Reader r) throws XmlException, IOException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static XmlAnySimpleType parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static XmlAnySimpleType parse(Node node) throws XmlException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static XmlAnySimpleType parse(Node node, XmlOptions options) throws XmlException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static XmlAnySimpleType parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static XmlAnySimpleType parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XmlAnySimpleType parse(XMLStreamReader xsr) throws XmlException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(xsr, type, null);
        }

        public static XmlAnySimpleType parse(XMLStreamReader xsr, XmlOptions options) throws XmlException {
            return (XmlAnySimpleType)XmlBeans.getContextTypeLoader().parse(xsr, type, options);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, null);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, options);
        }

        private Factory() {
        }
    }
}

