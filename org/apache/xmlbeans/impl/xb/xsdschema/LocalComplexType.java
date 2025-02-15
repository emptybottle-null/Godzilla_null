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
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexType;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface LocalComplexType
extends ComplexType {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(LocalComplexType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("localcomplextype6494type");

    public static final class Factory {
        public static LocalComplexType newInstance() {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static LocalComplexType newInstance(XmlOptions options) {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static LocalComplexType parse(String xmlAsString) throws XmlException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static LocalComplexType parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static LocalComplexType parse(File file) throws XmlException, IOException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static LocalComplexType parse(File file, XmlOptions options) throws XmlException, IOException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static LocalComplexType parse(URL u) throws XmlException, IOException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static LocalComplexType parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static LocalComplexType parse(InputStream is) throws XmlException, IOException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static LocalComplexType parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static LocalComplexType parse(Reader r) throws XmlException, IOException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static LocalComplexType parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static LocalComplexType parse(XMLStreamReader sr) throws XmlException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static LocalComplexType parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static LocalComplexType parse(Node node) throws XmlException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static LocalComplexType parse(Node node, XmlOptions options) throws XmlException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static LocalComplexType parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static LocalComplexType parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (LocalComplexType)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

