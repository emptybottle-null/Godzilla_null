/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideMasterId;
import org.w3c.dom.Node;

public interface CTSlideMasterIdListEntry
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSlideMasterIdListEntry.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctslidemasteridlistentryae7ftype");

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getId();

    public STSlideMasterId xgetId();

    public boolean isSetId();

    public void setId(long var1);

    public void xsetId(STSlideMasterId var1);

    public void unsetId();

    public String getId2();

    public STRelationshipId xgetId2();

    public void setId2(String var1);

    public void xsetId2(STRelationshipId var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSlideMasterIdListEntry.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSlideMasterIdListEntry newInstance() {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSlideMasterIdListEntry newInstance(XmlOptions xmlOptions) {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSlideMasterIdListEntry parse(String string) throws XmlException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSlideMasterIdListEntry parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSlideMasterIdListEntry parse(File file) throws XmlException, IOException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSlideMasterIdListEntry parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSlideMasterIdListEntry parse(URL uRL) throws XmlException, IOException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSlideMasterIdListEntry parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSlideMasterIdListEntry parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSlideMasterIdListEntry parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSlideMasterIdListEntry parse(Reader reader) throws XmlException, IOException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSlideMasterIdListEntry parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSlideMasterIdListEntry parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSlideMasterIdListEntry parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSlideMasterIdListEntry parse(Node node) throws XmlException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSlideMasterIdListEntry parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSlideMasterIdListEntry parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSlideMasterIdListEntry parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSlideMasterIdListEntry)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

