/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.substwsdl;

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

public interface TImport
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TImport.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("timport22datype");

    public String getNamespace();

    public XmlAnyURI xgetNamespace();

    public void setNamespace(String var1);

    public void xsetNamespace(XmlAnyURI var1);

    public String getLocation();

    public XmlAnyURI xgetLocation();

    public void setLocation(String var1);

    public void xsetLocation(XmlAnyURI var1);

    public static final class Factory {
        public static TImport newInstance() {
            return (TImport)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static TImport newInstance(XmlOptions options) {
            return (TImport)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static TImport parse(String xmlAsString) throws XmlException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static TImport parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static TImport parse(File file) throws XmlException, IOException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static TImport parse(File file, XmlOptions options) throws XmlException, IOException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static TImport parse(URL u) throws XmlException, IOException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static TImport parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static TImport parse(InputStream is) throws XmlException, IOException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static TImport parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static TImport parse(Reader r) throws XmlException, IOException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static TImport parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static TImport parse(XMLStreamReader sr) throws XmlException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static TImport parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static TImport parse(Node node) throws XmlException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static TImport parse(Node node, XmlOptions options) throws XmlException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static TImport parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static TImport parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (TImport)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

