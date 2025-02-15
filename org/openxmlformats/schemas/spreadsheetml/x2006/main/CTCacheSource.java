/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConsolidation
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConsolidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.w3c.dom.Node;

public interface CTCacheSource
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCacheSource.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcachesource00dctype");

    public CTWorksheetSource getWorksheetSource();

    public boolean isSetWorksheetSource();

    public void setWorksheetSource(CTWorksheetSource var1);

    public CTWorksheetSource addNewWorksheetSource();

    public void unsetWorksheetSource();

    public CTConsolidation getConsolidation();

    public boolean isSetConsolidation();

    public void setConsolidation(CTConsolidation var1);

    public CTConsolidation addNewConsolidation();

    public void unsetConsolidation();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public STSourceType.Enum getType();

    public STSourceType xgetType();

    public void setType(STSourceType.Enum var1);

    public void xsetType(STSourceType var1);

    public long getConnectionId();

    public XmlUnsignedInt xgetConnectionId();

    public boolean isSetConnectionId();

    public void setConnectionId(long var1);

    public void xsetConnectionId(XmlUnsignedInt var1);

    public void unsetConnectionId();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCacheSource.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCacheSource newInstance() {
            return (CTCacheSource)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCacheSource newInstance(XmlOptions xmlOptions) {
            return (CTCacheSource)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCacheSource parse(String string) throws XmlException {
            return (CTCacheSource)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCacheSource parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCacheSource)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCacheSource parse(File file) throws XmlException, IOException {
            return (CTCacheSource)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCacheSource parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCacheSource)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCacheSource parse(URL uRL) throws XmlException, IOException {
            return (CTCacheSource)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCacheSource parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCacheSource)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCacheSource parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCacheSource)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCacheSource parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCacheSource)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCacheSource parse(Reader reader) throws XmlException, IOException {
            return (CTCacheSource)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCacheSource parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCacheSource)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCacheSource parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCacheSource)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCacheSource parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCacheSource)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCacheSource parse(Node node) throws XmlException {
            return (CTCacheSource)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCacheSource parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCacheSource)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCacheSource parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCacheSource)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCacheSource parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCacheSource)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

