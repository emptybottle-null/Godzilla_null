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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedLong;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface XmlUnsignedInt
extends XmlUnsignedLong {
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_unsignedInt");

    public long getLongValue();

    public void setLongValue(long var1);

    public long longValue();

    public void set(long var1);

    public static final class Factory {
        public static XmlUnsignedInt newInstance() {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static XmlUnsignedInt newInstance(XmlOptions options) {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static XmlUnsignedInt newValue(Object obj) {
            return (XmlUnsignedInt)type.newValue(obj);
        }

        public static XmlUnsignedInt parse(String s) throws XmlException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(s, type, null);
        }

        public static XmlUnsignedInt parse(String s, XmlOptions options) throws XmlException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(s, type, options);
        }

        public static XmlUnsignedInt parse(File f) throws XmlException, IOException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(f, type, null);
        }

        public static XmlUnsignedInt parse(File f, XmlOptions options) throws XmlException, IOException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(f, type, options);
        }

        public static XmlUnsignedInt parse(URL u) throws XmlException, IOException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static XmlUnsignedInt parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static XmlUnsignedInt parse(InputStream is) throws XmlException, IOException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static XmlUnsignedInt parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static XmlUnsignedInt parse(Reader r) throws XmlException, IOException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static XmlUnsignedInt parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static XmlUnsignedInt parse(Node node) throws XmlException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static XmlUnsignedInt parse(Node node, XmlOptions options) throws XmlException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static XmlUnsignedInt parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static XmlUnsignedInt parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XmlUnsignedInt parse(XMLStreamReader xsr) throws XmlException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(xsr, type, null);
        }

        public static XmlUnsignedInt parse(XMLStreamReader xsr, XmlOptions options) throws XmlException {
            return (XmlUnsignedInt)XmlBeans.getContextTypeLoader().parse(xsr, type, options);
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

