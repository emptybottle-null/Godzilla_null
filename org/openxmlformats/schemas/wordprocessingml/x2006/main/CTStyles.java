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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLatentStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.w3c.dom.Node;

public interface CTStyles
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTStyles.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctstyles8506type");

    public CTDocDefaults getDocDefaults();

    public boolean isSetDocDefaults();

    public void setDocDefaults(CTDocDefaults var1);

    public CTDocDefaults addNewDocDefaults();

    public void unsetDocDefaults();

    public CTLatentStyles getLatentStyles();

    public boolean isSetLatentStyles();

    public void setLatentStyles(CTLatentStyles var1);

    public CTLatentStyles addNewLatentStyles();

    public void unsetLatentStyles();

    public List<CTStyle> getStyleList();

    @Deprecated
    public CTStyle[] getStyleArray();

    public CTStyle getStyleArray(int var1);

    public int sizeOfStyleArray();

    public void setStyleArray(CTStyle[] var1);

    public void setStyleArray(int var1, CTStyle var2);

    public CTStyle insertNewStyle(int var1);

    public CTStyle addNewStyle();

    public void removeStyle(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTStyles.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTStyles newInstance() {
            return (CTStyles)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTStyles newInstance(XmlOptions xmlOptions) {
            return (CTStyles)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTStyles parse(String string) throws XmlException {
            return (CTStyles)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTStyles parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTStyles)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTStyles parse(File file) throws XmlException, IOException {
            return (CTStyles)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTStyles parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyles)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTStyles parse(URL uRL) throws XmlException, IOException {
            return (CTStyles)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTStyles parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyles)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTStyles parse(InputStream inputStream) throws XmlException, IOException {
            return (CTStyles)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTStyles parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyles)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTStyles parse(Reader reader) throws XmlException, IOException {
            return (CTStyles)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTStyles parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStyles)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTStyles parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTStyles)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTStyles parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTStyles)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTStyles parse(Node node) throws XmlException {
            return (CTStyles)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTStyles parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTStyles)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTStyles parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTStyles)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTStyles parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTStyles)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

