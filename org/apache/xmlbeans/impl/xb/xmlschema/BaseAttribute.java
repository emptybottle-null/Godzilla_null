/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlschema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface BaseAttribute
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(BaseAttribute.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLLANG").resolveHandle("basece23attrtypetype");

    public String getBase();

    public XmlAnyURI xgetBase();

    public boolean isSetBase();

    public void setBase(String var1);

    public void xsetBase(XmlAnyURI var1);

    public void unsetBase();

    public static final class Factory {
        public static BaseAttribute newInstance() {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static BaseAttribute newInstance(XmlOptions options) {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static BaseAttribute parse(String xmlAsString) throws XmlException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static BaseAttribute parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static BaseAttribute parse(File file) throws XmlException, IOException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static BaseAttribute parse(File file, XmlOptions options) throws XmlException, IOException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static BaseAttribute parse(URL u) throws XmlException, IOException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static BaseAttribute parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static BaseAttribute parse(InputStream is) throws XmlException, IOException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static BaseAttribute parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static BaseAttribute parse(Reader r) throws XmlException, IOException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static BaseAttribute parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static BaseAttribute parse(XMLStreamReader sr) throws XmlException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static BaseAttribute parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static BaseAttribute parse(Node node) throws XmlException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static BaseAttribute parse(Node node, XmlOptions options) throws XmlException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static BaseAttribute parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static BaseAttribute parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (BaseAttribute)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

