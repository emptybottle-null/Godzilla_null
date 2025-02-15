/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSchema;
import org.w3c.dom.Node;

public interface CTMapInfo
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTMapInfo.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctmapinfo1a09type");

    public List<CTSchema> getSchemaList();

    @Deprecated
    public CTSchema[] getSchemaArray();

    public CTSchema getSchemaArray(int var1);

    public int sizeOfSchemaArray();

    public void setSchemaArray(CTSchema[] var1);

    public void setSchemaArray(int var1, CTSchema var2);

    public CTSchema insertNewSchema(int var1);

    public CTSchema addNewSchema();

    public void removeSchema(int var1);

    public List<CTMap> getMapList();

    @Deprecated
    public CTMap[] getMapArray();

    public CTMap getMapArray(int var1);

    public int sizeOfMapArray();

    public void setMapArray(CTMap[] var1);

    public void setMapArray(int var1, CTMap var2);

    public CTMap insertNewMap(int var1);

    public CTMap addNewMap();

    public void removeMap(int var1);

    public String getSelectionNamespaces();

    public XmlString xgetSelectionNamespaces();

    public void setSelectionNamespaces(String var1);

    public void xsetSelectionNamespaces(XmlString var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTMapInfo.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTMapInfo newInstance() {
            return (CTMapInfo)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTMapInfo newInstance(XmlOptions xmlOptions) {
            return (CTMapInfo)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTMapInfo parse(String string) throws XmlException {
            return (CTMapInfo)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTMapInfo parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTMapInfo)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTMapInfo parse(File file) throws XmlException, IOException {
            return (CTMapInfo)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTMapInfo parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMapInfo)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTMapInfo parse(URL uRL) throws XmlException, IOException {
            return (CTMapInfo)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTMapInfo parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMapInfo)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTMapInfo parse(InputStream inputStream) throws XmlException, IOException {
            return (CTMapInfo)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTMapInfo parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMapInfo)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTMapInfo parse(Reader reader) throws XmlException, IOException {
            return (CTMapInfo)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTMapInfo parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMapInfo)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTMapInfo parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTMapInfo)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTMapInfo parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTMapInfo)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTMapInfo parse(Node node) throws XmlException {
            return (CTMapInfo)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTMapInfo parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTMapInfo)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTMapInfo parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTMapInfo)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTMapInfo parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTMapInfo)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

