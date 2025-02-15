/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema;

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
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface NumFacet
extends Facet {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(NumFacet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("numfacet93a2type");

    public static final class Factory {
        public static NumFacet newInstance() {
            return (NumFacet)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static NumFacet newInstance(XmlOptions options) {
            return (NumFacet)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static NumFacet parse(String xmlAsString) throws XmlException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static NumFacet parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static NumFacet parse(File file) throws XmlException, IOException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static NumFacet parse(File file, XmlOptions options) throws XmlException, IOException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static NumFacet parse(URL u) throws XmlException, IOException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static NumFacet parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static NumFacet parse(InputStream is) throws XmlException, IOException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static NumFacet parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static NumFacet parse(Reader r) throws XmlException, IOException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static NumFacet parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static NumFacet parse(XMLStreamReader sr) throws XmlException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static NumFacet parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static NumFacet parse(Node node) throws XmlException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static NumFacet parse(Node node, XmlOptions options) throws XmlException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static NumFacet parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static NumFacet parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (NumFacet)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

