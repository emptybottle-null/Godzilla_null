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
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdListEntry;
import org.w3c.dom.Node;

public interface CTNotesMasterIdList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNotesMasterIdList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnotesmasteridlist2853type");

    public CTNotesMasterIdListEntry getNotesMasterId();

    public boolean isSetNotesMasterId();

    public void setNotesMasterId(CTNotesMasterIdListEntry var1);

    public CTNotesMasterIdListEntry addNewNotesMasterId();

    public void unsetNotesMasterId();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNotesMasterIdList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNotesMasterIdList newInstance() {
            return (CTNotesMasterIdList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNotesMasterIdList newInstance(XmlOptions xmlOptions) {
            return (CTNotesMasterIdList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNotesMasterIdList parse(String string) throws XmlException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNotesMasterIdList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNotesMasterIdList parse(File file) throws XmlException, IOException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNotesMasterIdList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNotesMasterIdList parse(URL uRL) throws XmlException, IOException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNotesMasterIdList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNotesMasterIdList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNotesMasterIdList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNotesMasterIdList parse(Reader reader) throws XmlException, IOException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNotesMasterIdList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNotesMasterIdList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNotesMasterIdList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNotesMasterIdList parse(Node node) throws XmlException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNotesMasterIdList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNotesMasterIdList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNotesMasterIdList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNotesMasterIdList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

