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
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface XmlHexBinary
extends XmlAnySimpleType {
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_hexBinary");

    public byte[] byteArrayValue();

    public void set(byte[] var1);

    public byte[] getByteArrayValue();

    public void setByteArrayValue(byte[] var1);

    public static final class Factory {
        public static XmlHexBinary newInstance() {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static XmlHexBinary newInstance(XmlOptions options) {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static XmlHexBinary newValue(Object obj) {
            return (XmlHexBinary)type.newValue(obj);
        }

        public static XmlHexBinary parse(String s) throws XmlException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(s, type, null);
        }

        public static XmlHexBinary parse(String s, XmlOptions options) throws XmlException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(s, type, options);
        }

        public static XmlHexBinary parse(File f) throws XmlException, IOException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(f, type, null);
        }

        public static XmlHexBinary parse(File f, XmlOptions options) throws XmlException, IOException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(f, type, options);
        }

        public static XmlHexBinary parse(URL u) throws XmlException, IOException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static XmlHexBinary parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static XmlHexBinary parse(InputStream is) throws XmlException, IOException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static XmlHexBinary parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static XmlHexBinary parse(Reader r) throws XmlException, IOException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static XmlHexBinary parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static XmlHexBinary parse(Node node) throws XmlException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static XmlHexBinary parse(Node node, XmlOptions options) throws XmlException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static XmlHexBinary parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static XmlHexBinary parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XmlHexBinary parse(XMLStreamReader xsr) throws XmlException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(xsr, type, null);
        }

        public static XmlHexBinary parse(XMLStreamReader xsr, XmlOptions options) throws XmlException {
            return (XmlHexBinary)XmlBeans.getContextTypeLoader().parse(xsr, type, options);
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

