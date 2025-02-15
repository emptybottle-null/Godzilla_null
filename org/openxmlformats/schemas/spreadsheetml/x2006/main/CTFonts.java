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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.w3c.dom.Node;

public interface CTFonts
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTFonts.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctfonts6623type");

    public List<CTFont> getFontList();

    @Deprecated
    public CTFont[] getFontArray();

    public CTFont getFontArray(int var1);

    public int sizeOfFontArray();

    public void setFontArray(CTFont[] var1);

    public void setFontArray(int var1, CTFont var2);

    public CTFont insertNewFont(int var1);

    public CTFont addNewFont();

    public void removeFont(int var1);

    public long getCount();

    public XmlUnsignedInt xgetCount();

    public boolean isSetCount();

    public void setCount(long var1);

    public void xsetCount(XmlUnsignedInt var1);

    public void unsetCount();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTFonts.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTFonts newInstance() {
            return (CTFonts)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTFonts newInstance(XmlOptions xmlOptions) {
            return (CTFonts)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTFonts parse(String string) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTFonts parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTFonts parse(File file) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTFonts parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTFonts parse(URL uRL) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTFonts parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTFonts parse(InputStream inputStream) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTFonts parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTFonts parse(Reader reader) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTFonts parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTFonts)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTFonts parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTFonts parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTFonts parse(Node node) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTFonts parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTFonts)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTFonts parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTFonts)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTFonts parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTFonts)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

