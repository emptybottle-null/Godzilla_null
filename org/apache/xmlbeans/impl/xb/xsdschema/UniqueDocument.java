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
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface UniqueDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(UniqueDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("unique3752doctype");

    public Keybase getUnique();

    public void setUnique(Keybase var1);

    public Keybase addNewUnique();

    public static final class Factory {
        public static UniqueDocument newInstance() {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static UniqueDocument newInstance(XmlOptions options) {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static UniqueDocument parse(String xmlAsString) throws XmlException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static UniqueDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static UniqueDocument parse(File file) throws XmlException, IOException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static UniqueDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static UniqueDocument parse(URL u) throws XmlException, IOException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static UniqueDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static UniqueDocument parse(InputStream is) throws XmlException, IOException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static UniqueDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static UniqueDocument parse(Reader r) throws XmlException, IOException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static UniqueDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static UniqueDocument parse(XMLStreamReader sr) throws XmlException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static UniqueDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static UniqueDocument parse(Node node) throws XmlException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static UniqueDocument parse(Node node, XmlOptions options) throws XmlException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static UniqueDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static UniqueDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (UniqueDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

