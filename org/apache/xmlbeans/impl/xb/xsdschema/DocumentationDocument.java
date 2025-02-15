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
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlLanguage;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface DocumentationDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(DocumentationDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("documentation6cdbdoctype");

    public Documentation getDocumentation();

    public void setDocumentation(Documentation var1);

    public Documentation addNewDocumentation();

    public static final class Factory {
        public static DocumentationDocument newInstance() {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static DocumentationDocument newInstance(XmlOptions options) {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static DocumentationDocument parse(String xmlAsString) throws XmlException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static DocumentationDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static DocumentationDocument parse(File file) throws XmlException, IOException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static DocumentationDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static DocumentationDocument parse(URL u) throws XmlException, IOException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static DocumentationDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static DocumentationDocument parse(InputStream is) throws XmlException, IOException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static DocumentationDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static DocumentationDocument parse(Reader r) throws XmlException, IOException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static DocumentationDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static DocumentationDocument parse(XMLStreamReader sr) throws XmlException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static DocumentationDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static DocumentationDocument parse(Node node) throws XmlException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static DocumentationDocument parse(Node node, XmlOptions options) throws XmlException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static DocumentationDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static DocumentationDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (DocumentationDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Documentation
    extends XmlObject {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Documentation.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("documentationa475elemtype");

        public String getSource();

        public XmlAnyURI xgetSource();

        public boolean isSetSource();

        public void setSource(String var1);

        public void xsetSource(XmlAnyURI var1);

        public void unsetSource();

        public String getLang();

        public XmlLanguage xgetLang();

        public boolean isSetLang();

        public void setLang(String var1);

        public void xsetLang(XmlLanguage var1);

        public void unsetLang();

        public static final class Factory {
            public static Documentation newInstance() {
                return (Documentation)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Documentation newInstance(XmlOptions options) {
                return (Documentation)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

