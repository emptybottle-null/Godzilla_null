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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ImportDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ImportDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("import99fedoctype");

    public Import getImport();

    public void setImport(Import var1);

    public Import addNewImport();

    public static final class Factory {
        public static ImportDocument newInstance() {
            return (ImportDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static ImportDocument newInstance(XmlOptions options) {
            return (ImportDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static ImportDocument parse(String xmlAsString) throws XmlException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static ImportDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static ImportDocument parse(File file) throws XmlException, IOException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static ImportDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static ImportDocument parse(URL u) throws XmlException, IOException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static ImportDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static ImportDocument parse(InputStream is) throws XmlException, IOException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static ImportDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static ImportDocument parse(Reader r) throws XmlException, IOException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static ImportDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static ImportDocument parse(XMLStreamReader sr) throws XmlException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static ImportDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static ImportDocument parse(Node node) throws XmlException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static ImportDocument parse(Node node, XmlOptions options) throws XmlException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static ImportDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static ImportDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (ImportDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Import
    extends Annotated {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Import.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("importe2ffelemtype");

        public String getNamespace();

        public XmlAnyURI xgetNamespace();

        public boolean isSetNamespace();

        public void setNamespace(String var1);

        public void xsetNamespace(XmlAnyURI var1);

        public void unsetNamespace();

        public String getSchemaLocation();

        public XmlAnyURI xgetSchemaLocation();

        public boolean isSetSchemaLocation();

        public void setSchemaLocation(String var1);

        public void xsetSchemaLocation(XmlAnyURI var1);

        public void unsetSchemaLocation();

        public static final class Factory {
            public static Import newInstance() {
                return (Import)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Import newInstance(XmlOptions options) {
                return (Import)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

