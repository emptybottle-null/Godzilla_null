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
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface DownloadedSchemaEntry
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(DownloadedSchemaEntry.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("downloadedschemaentry1c75type");

    public String getFilename();

    public XmlToken xgetFilename();

    public void setFilename(String var1);

    public void xsetFilename(XmlToken var1);

    public String getSha1();

    public XmlToken xgetSha1();

    public void setSha1(String var1);

    public void xsetSha1(XmlToken var1);

    public String[] getSchemaLocationArray();

    public String getSchemaLocationArray(int var1);

    public XmlAnyURI[] xgetSchemaLocationArray();

    public XmlAnyURI xgetSchemaLocationArray(int var1);

    public int sizeOfSchemaLocationArray();

    public void setSchemaLocationArray(String[] var1);

    public void setSchemaLocationArray(int var1, String var2);

    public void xsetSchemaLocationArray(XmlAnyURI[] var1);

    public void xsetSchemaLocationArray(int var1, XmlAnyURI var2);

    public void insertSchemaLocation(int var1, String var2);

    public void addSchemaLocation(String var1);

    public XmlAnyURI insertNewSchemaLocation(int var1);

    public XmlAnyURI addNewSchemaLocation();

    public void removeSchemaLocation(int var1);

    public String getNamespace();

    public XmlAnyURI xgetNamespace();

    public boolean isSetNamespace();

    public void setNamespace(String var1);

    public void xsetNamespace(XmlAnyURI var1);

    public void unsetNamespace();

    public static final class Factory {
        public static DownloadedSchemaEntry newInstance() {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static DownloadedSchemaEntry newInstance(XmlOptions options) {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static DownloadedSchemaEntry parse(String xmlAsString) throws XmlException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static DownloadedSchemaEntry parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static DownloadedSchemaEntry parse(File file) throws XmlException, IOException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static DownloadedSchemaEntry parse(File file, XmlOptions options) throws XmlException, IOException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static DownloadedSchemaEntry parse(URL u) throws XmlException, IOException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static DownloadedSchemaEntry parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static DownloadedSchemaEntry parse(InputStream is) throws XmlException, IOException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static DownloadedSchemaEntry parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static DownloadedSchemaEntry parse(Reader r) throws XmlException, IOException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static DownloadedSchemaEntry parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static DownloadedSchemaEntry parse(XMLStreamReader sr) throws XmlException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static DownloadedSchemaEntry parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static DownloadedSchemaEntry parse(Node node) throws XmlException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static DownloadedSchemaEntry parse(Node node, XmlOptions options) throws XmlException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static DownloadedSchemaEntry parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static DownloadedSchemaEntry parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (DownloadedSchemaEntry)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

