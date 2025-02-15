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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCache;
import org.w3c.dom.Node;

public interface CTPivotCaches
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPivotCaches.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpivotcaches4f32type");

    public List<CTPivotCache> getPivotCacheList();

    @Deprecated
    public CTPivotCache[] getPivotCacheArray();

    public CTPivotCache getPivotCacheArray(int var1);

    public int sizeOfPivotCacheArray();

    public void setPivotCacheArray(CTPivotCache[] var1);

    public void setPivotCacheArray(int var1, CTPivotCache var2);

    public CTPivotCache insertNewPivotCache(int var1);

    public CTPivotCache addNewPivotCache();

    public void removePivotCache(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPivotCaches.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPivotCaches newInstance() {
            return (CTPivotCaches)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPivotCaches newInstance(XmlOptions xmlOptions) {
            return (CTPivotCaches)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPivotCaches parse(String string) throws XmlException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPivotCaches parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPivotCaches parse(File file) throws XmlException, IOException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPivotCaches parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPivotCaches parse(URL uRL) throws XmlException, IOException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPivotCaches parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPivotCaches parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPivotCaches parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPivotCaches parse(Reader reader) throws XmlException, IOException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPivotCaches parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPivotCaches parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPivotCaches parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPivotCaches parse(Node node) throws XmlException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPivotCaches parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPivotCaches parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPivotCaches parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPivotCaches)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

