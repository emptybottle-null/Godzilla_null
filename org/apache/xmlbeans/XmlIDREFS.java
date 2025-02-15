/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface XmlIDREFS
extends XmlAnySimpleType {
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_IDREFS");

    public List getListValue();

    public List xgetListValue();

    public void setListValue(List var1);

    public List listValue();

    public List xlistValue();

    public void set(List var1);

    public static final class Factory {
        public static XmlIDREFS newInstance() {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static XmlIDREFS newInstance(XmlOptions options) {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static XmlIDREFS newValue(Object obj) {
            return (XmlIDREFS)type.newValue(obj);
        }

        public static XmlIDREFS parse(String s) throws XmlException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(s, type, null);
        }

        public static XmlIDREFS parse(String s, XmlOptions options) throws XmlException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(s, type, options);
        }

        public static XmlIDREFS parse(File f) throws XmlException, IOException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(f, type, null);
        }

        public static XmlIDREFS parse(File f, XmlOptions options) throws XmlException, IOException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(f, type, options);
        }

        public static XmlIDREFS parse(URL u) throws XmlException, IOException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static XmlIDREFS parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static XmlIDREFS parse(InputStream is) throws XmlException, IOException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static XmlIDREFS parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static XmlIDREFS parse(Reader r) throws XmlException, IOException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static XmlIDREFS parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static XmlIDREFS parse(Node node) throws XmlException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static XmlIDREFS parse(Node node, XmlOptions options) throws XmlException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static XmlIDREFS parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static XmlIDREFS parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XmlIDREFS parse(XMLStreamReader xsr) throws XmlException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(xsr, type, null);
        }

        public static XmlIDREFS parse(XMLStreamReader xsr, XmlOptions options) throws XmlException {
            return (XmlIDREFS)XmlBeans.getContextTypeLoader().parse(xsr, type, options);
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

