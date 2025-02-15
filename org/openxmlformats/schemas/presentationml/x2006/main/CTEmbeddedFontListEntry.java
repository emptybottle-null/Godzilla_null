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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId;
import org.w3c.dom.Node;

public interface CTEmbeddedFontListEntry
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTEmbeddedFontListEntry.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctembeddedfontlistentry48b4type");

    public CTTextFont getFont();

    public void setFont(CTTextFont var1);

    public CTTextFont addNewFont();

    public CTEmbeddedFontDataId getRegular();

    public boolean isSetRegular();

    public void setRegular(CTEmbeddedFontDataId var1);

    public CTEmbeddedFontDataId addNewRegular();

    public void unsetRegular();

    public CTEmbeddedFontDataId getBold();

    public boolean isSetBold();

    public void setBold(CTEmbeddedFontDataId var1);

    public CTEmbeddedFontDataId addNewBold();

    public void unsetBold();

    public CTEmbeddedFontDataId getItalic();

    public boolean isSetItalic();

    public void setItalic(CTEmbeddedFontDataId var1);

    public CTEmbeddedFontDataId addNewItalic();

    public void unsetItalic();

    public CTEmbeddedFontDataId getBoldItalic();

    public boolean isSetBoldItalic();

    public void setBoldItalic(CTEmbeddedFontDataId var1);

    public CTEmbeddedFontDataId addNewBoldItalic();

    public void unsetBoldItalic();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTEmbeddedFontListEntry.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTEmbeddedFontListEntry newInstance() {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTEmbeddedFontListEntry newInstance(XmlOptions xmlOptions) {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTEmbeddedFontListEntry parse(String string) throws XmlException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTEmbeddedFontListEntry parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTEmbeddedFontListEntry parse(File file) throws XmlException, IOException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTEmbeddedFontListEntry parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTEmbeddedFontListEntry parse(URL uRL) throws XmlException, IOException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTEmbeddedFontListEntry parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTEmbeddedFontListEntry parse(InputStream inputStream) throws XmlException, IOException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTEmbeddedFontListEntry parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTEmbeddedFontListEntry parse(Reader reader) throws XmlException, IOException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTEmbeddedFontListEntry parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTEmbeddedFontListEntry parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTEmbeddedFontListEntry parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTEmbeddedFontListEntry parse(Node node) throws XmlException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTEmbeddedFontListEntry parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTEmbeddedFontListEntry parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTEmbeddedFontListEntry parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTEmbeddedFontListEntry)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

