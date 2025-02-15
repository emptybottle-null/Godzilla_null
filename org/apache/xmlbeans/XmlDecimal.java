/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
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

public interface XmlDecimal
extends XmlAnySimpleType {
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_decimal");

    public BigDecimal getBigDecimalValue();

    public void setBigDecimalValue(BigDecimal var1);

    public BigDecimal bigDecimalValue();

    public void set(BigDecimal var1);

    public static final class Factory {
        public static XmlDecimal newInstance() {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static XmlDecimal newInstance(XmlOptions options) {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static XmlDecimal newValue(Object obj) {
            return (XmlDecimal)type.newValue(obj);
        }

        public static XmlDecimal parse(String s) throws XmlException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(s, type, null);
        }

        public static XmlDecimal parse(String s, XmlOptions options) throws XmlException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(s, type, options);
        }

        public static XmlDecimal parse(File f) throws XmlException, IOException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(f, type, null);
        }

        public static XmlDecimal parse(File f, XmlOptions options) throws XmlException, IOException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(f, type, options);
        }

        public static XmlDecimal parse(URL u) throws XmlException, IOException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static XmlDecimal parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static XmlDecimal parse(InputStream is) throws XmlException, IOException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static XmlDecimal parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static XmlDecimal parse(Reader r) throws XmlException, IOException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static XmlDecimal parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static XmlDecimal parse(Node node) throws XmlException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static XmlDecimal parse(Node node, XmlOptions options) throws XmlException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static XmlDecimal parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static XmlDecimal parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XmlDecimal parse(XMLStreamReader xsr) throws XmlException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(xsr, type, null);
        }

        public static XmlDecimal parse(XMLStreamReader xsr, XmlOptions options) throws XmlException {
            return (XmlDecimal)XmlBeans.getContextTypeLoader().parse(xsr, type, options);
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

