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
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface EnumerationDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(EnumerationDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("enumeration052edoctype");

    public NoFixedFacet getEnumeration();

    public void setEnumeration(NoFixedFacet var1);

    public NoFixedFacet addNewEnumeration();

    public static final class Factory {
        public static EnumerationDocument newInstance() {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static EnumerationDocument newInstance(XmlOptions options) {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static EnumerationDocument parse(String xmlAsString) throws XmlException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static EnumerationDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static EnumerationDocument parse(File file) throws XmlException, IOException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static EnumerationDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static EnumerationDocument parse(URL u) throws XmlException, IOException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static EnumerationDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static EnumerationDocument parse(InputStream is) throws XmlException, IOException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static EnumerationDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static EnumerationDocument parse(Reader r) throws XmlException, IOException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static EnumerationDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static EnumerationDocument parse(XMLStreamReader sr) throws XmlException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static EnumerationDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static EnumerationDocument parse(Node node) throws XmlException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static EnumerationDocument parse(Node node, XmlOptions options) throws XmlException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static EnumerationDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static EnumerationDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (EnumerationDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

