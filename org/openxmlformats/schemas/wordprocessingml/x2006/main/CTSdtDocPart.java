/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.w3c.dom.Node;

public interface CTSdtDocPart
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSdtDocPart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsdtdocpartcea0type");

    public CTString getDocPartGallery();

    public boolean isSetDocPartGallery();

    public void setDocPartGallery(CTString var1);

    public CTString addNewDocPartGallery();

    public void unsetDocPartGallery();

    public CTString getDocPartCategory();

    public boolean isSetDocPartCategory();

    public void setDocPartCategory(CTString var1);

    public CTString addNewDocPartCategory();

    public void unsetDocPartCategory();

    public CTOnOff getDocPartUnique();

    public boolean isSetDocPartUnique();

    public void setDocPartUnique(CTOnOff var1);

    public CTOnOff addNewDocPartUnique();

    public void unsetDocPartUnique();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSdtDocPart.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSdtDocPart newInstance() {
            return (CTSdtDocPart)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSdtDocPart newInstance(XmlOptions xmlOptions) {
            return (CTSdtDocPart)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSdtDocPart parse(String string) throws XmlException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSdtDocPart parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSdtDocPart parse(File file) throws XmlException, IOException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSdtDocPart parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSdtDocPart parse(URL uRL) throws XmlException, IOException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSdtDocPart parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSdtDocPart parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSdtDocPart parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSdtDocPart parse(Reader reader) throws XmlException, IOException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSdtDocPart parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSdtDocPart parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSdtDocPart parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSdtDocPart parse(Node node) throws XmlException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSdtDocPart parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSdtDocPart parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSdtDocPart parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSdtDocPart)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

