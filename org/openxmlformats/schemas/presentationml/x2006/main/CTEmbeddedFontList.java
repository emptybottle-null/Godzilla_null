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
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;
import org.w3c.dom.Node;

public interface CTEmbeddedFontList
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTEmbeddedFontList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctembeddedfontlist240etype");

    public List<CTEmbeddedFontListEntry> getEmbeddedFontList();

    @Deprecated
    public CTEmbeddedFontListEntry[] getEmbeddedFontArray();

    public CTEmbeddedFontListEntry getEmbeddedFontArray(int var1);

    public int sizeOfEmbeddedFontArray();

    public void setEmbeddedFontArray(CTEmbeddedFontListEntry[] var1);

    public void setEmbeddedFontArray(int var1, CTEmbeddedFontListEntry var2);

    public CTEmbeddedFontListEntry insertNewEmbeddedFont(int var1);

    public CTEmbeddedFontListEntry addNewEmbeddedFont();

    public void removeEmbeddedFont(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTEmbeddedFontList.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTEmbeddedFontList newInstance() {
            return (CTEmbeddedFontList)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTEmbeddedFontList newInstance(XmlOptions xmlOptions) {
            return (CTEmbeddedFontList)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTEmbeddedFontList parse(String string) throws XmlException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTEmbeddedFontList parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTEmbeddedFontList parse(File file) throws XmlException, IOException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTEmbeddedFontList parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTEmbeddedFontList parse(URL uRL) throws XmlException, IOException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTEmbeddedFontList parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTEmbeddedFontList parse(InputStream inputStream) throws XmlException, IOException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTEmbeddedFontList parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTEmbeddedFontList parse(Reader reader) throws XmlException, IOException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTEmbeddedFontList parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTEmbeddedFontList parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTEmbeddedFontList parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTEmbeddedFontList parse(Node node) throws XmlException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTEmbeddedFontList parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTEmbeddedFontList parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTEmbeddedFontList parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTEmbeddedFontList)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

