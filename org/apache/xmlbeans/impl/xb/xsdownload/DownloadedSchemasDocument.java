/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdownload;

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
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface DownloadedSchemasDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(DownloadedSchemasDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("downloadedschemas2dd7doctype");

    public DownloadedSchemas getDownloadedSchemas();

    public void setDownloadedSchemas(DownloadedSchemas var1);

    public DownloadedSchemas addNewDownloadedSchemas();

    public static final class Factory {
        public static DownloadedSchemasDocument newInstance() {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static DownloadedSchemasDocument newInstance(XmlOptions options) {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static DownloadedSchemasDocument parse(String xmlAsString) throws XmlException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static DownloadedSchemasDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static DownloadedSchemasDocument parse(File file) throws XmlException, IOException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static DownloadedSchemasDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static DownloadedSchemasDocument parse(URL u) throws XmlException, IOException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static DownloadedSchemasDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static DownloadedSchemasDocument parse(InputStream is) throws XmlException, IOException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static DownloadedSchemasDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static DownloadedSchemasDocument parse(Reader r) throws XmlException, IOException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static DownloadedSchemasDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static DownloadedSchemasDocument parse(XMLStreamReader sr) throws XmlException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static DownloadedSchemasDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static DownloadedSchemasDocument parse(Node node) throws XmlException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static DownloadedSchemasDocument parse(Node node, XmlOptions options) throws XmlException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static DownloadedSchemasDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static DownloadedSchemasDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (DownloadedSchemasDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface DownloadedSchemas
    extends XmlObject {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(DownloadedSchemas.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("downloadedschemasb3efelemtype");

        public DownloadedSchemaEntry[] getEntryArray();

        public DownloadedSchemaEntry getEntryArray(int var1);

        public int sizeOfEntryArray();

        public void setEntryArray(DownloadedSchemaEntry[] var1);

        public void setEntryArray(int var1, DownloadedSchemaEntry var2);

        public DownloadedSchemaEntry insertNewEntry(int var1);

        public DownloadedSchemaEntry addNewEntry();

        public void removeEntry(int var1);

        public String getDefaultDirectory();

        public XmlToken xgetDefaultDirectory();

        public boolean isSetDefaultDirectory();

        public void setDefaultDirectory(String var1);

        public void xsetDefaultDirectory(XmlToken var1);

        public void unsetDefaultDirectory();

        public static final class Factory {
            public static DownloadedSchemas newInstance() {
                return (DownloadedSchemas)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static DownloadedSchemas newInstance(XmlOptions options) {
                return (DownloadedSchemas)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

