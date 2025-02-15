/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;
import org.w3c.dom.Node;

public interface CTBaseStyles
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBaseStyles.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbasestyles122etype");

    public CTColorScheme getClrScheme();

    public void setClrScheme(CTColorScheme var1);

    public CTColorScheme addNewClrScheme();

    public CTFontScheme getFontScheme();

    public void setFontScheme(CTFontScheme var1);

    public CTFontScheme addNewFontScheme();

    public CTStyleMatrix getFmtScheme();

    public void setFmtScheme(CTStyleMatrix var1);

    public CTStyleMatrix addNewFmtScheme();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBaseStyles.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBaseStyles newInstance() {
            return (CTBaseStyles)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBaseStyles newInstance(XmlOptions xmlOptions) {
            return (CTBaseStyles)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBaseStyles parse(String string) throws XmlException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBaseStyles parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBaseStyles parse(File file) throws XmlException, IOException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBaseStyles parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBaseStyles parse(URL uRL) throws XmlException, IOException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBaseStyles parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBaseStyles parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBaseStyles parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBaseStyles parse(Reader reader) throws XmlException, IOException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBaseStyles parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBaseStyles parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBaseStyles parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBaseStyles parse(Node node) throws XmlException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBaseStyles parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBaseStyles parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBaseStyles parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBaseStyles)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

