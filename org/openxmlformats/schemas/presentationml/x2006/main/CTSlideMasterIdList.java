/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdListEntry;
import org.w3c.dom.Node;

public interface CTSlideMasterIdList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSlideMasterIdList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctslidemasteridlist0b63type");

    public List<CTSlideMasterIdListEntry> getSldMasterIdList();

    @Deprecated
    public CTSlideMasterIdListEntry[] getSldMasterIdArray();

    public CTSlideMasterIdListEntry getSldMasterIdArray(int var1);

    public int sizeOfSldMasterIdArray();

    public void setSldMasterIdArray(CTSlideMasterIdListEntry[] var1);

    public void setSldMasterIdArray(int var1, CTSlideMasterIdListEntry var2);

    public CTSlideMasterIdListEntry insertNewSldMasterId(int var1);

    public CTSlideMasterIdListEntry addNewSldMasterId();

    public void removeSldMasterId(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSlideMasterIdList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSlideMasterIdList newInstance() {
            return (CTSlideMasterIdList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSlideMasterIdList newInstance(XmlOptions xmlOptions) {
            return (CTSlideMasterIdList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSlideMasterIdList parse(String string) throws XmlException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSlideMasterIdList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSlideMasterIdList parse(File file) throws XmlException, IOException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSlideMasterIdList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSlideMasterIdList parse(URL uRL) throws XmlException, IOException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSlideMasterIdList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSlideMasterIdList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSlideMasterIdList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSlideMasterIdList parse(Reader reader) throws XmlException, IOException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSlideMasterIdList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSlideMasterIdList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSlideMasterIdList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSlideMasterIdList parse(Node node) throws XmlException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSlideMasterIdList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSlideMasterIdList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSlideMasterIdList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSlideMasterIdList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

