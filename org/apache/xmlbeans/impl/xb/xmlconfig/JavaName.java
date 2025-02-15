/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlconfig;

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
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface JavaName
extends XmlToken {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(JavaName.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("javanamee640type");

    public static final class Factory {
        public static JavaName newValue(Object obj) {
            return (JavaName)type.newValue(obj);
        }

        public static JavaName newInstance() {
            return (JavaName)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static JavaName newInstance(XmlOptions options) {
            return (JavaName)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static JavaName parse(String xmlAsString) throws XmlException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static JavaName parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static JavaName parse(File file) throws XmlException, IOException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static JavaName parse(File file, XmlOptions options) throws XmlException, IOException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static JavaName parse(URL u) throws XmlException, IOException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static JavaName parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static JavaName parse(InputStream is) throws XmlException, IOException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static JavaName parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static JavaName parse(Reader r) throws XmlException, IOException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static JavaName parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static JavaName parse(XMLStreamReader sr) throws XmlException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static JavaName parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static JavaName parse(Node node) throws XmlException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static JavaName parse(Node node, XmlOptions options) throws XmlException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static JavaName parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static JavaName parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (JavaName)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

