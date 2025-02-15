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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.Facet;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface MaxExclusiveDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(MaxExclusiveDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("maxexclusive6d69doctype");

    public Facet getMaxExclusive();

    public void setMaxExclusive(Facet var1);

    public Facet addNewMaxExclusive();

    public static final class Factory {
        public static MaxExclusiveDocument newInstance() {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static MaxExclusiveDocument newInstance(XmlOptions options) {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static MaxExclusiveDocument parse(String xmlAsString) throws XmlException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static MaxExclusiveDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static MaxExclusiveDocument parse(File file) throws XmlException, IOException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static MaxExclusiveDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static MaxExclusiveDocument parse(URL u) throws XmlException, IOException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static MaxExclusiveDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static MaxExclusiveDocument parse(InputStream is) throws XmlException, IOException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static MaxExclusiveDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static MaxExclusiveDocument parse(Reader r) throws XmlException, IOException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static MaxExclusiveDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static MaxExclusiveDocument parse(XMLStreamReader sr) throws XmlException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static MaxExclusiveDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static MaxExclusiveDocument parse(Node node) throws XmlException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static MaxExclusiveDocument parse(Node node, XmlOptions options) throws XmlException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static MaxExclusiveDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static MaxExclusiveDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (MaxExclusiveDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

