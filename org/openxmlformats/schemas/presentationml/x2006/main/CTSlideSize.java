/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.STSlideSizeType
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
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideSizeCoordinate;
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideSizeType;
import org.w3c.dom.Node;

public interface CTSlideSize
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSlideSize.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctslidesizeb0fdtype");

    public int getCx();

    public STSlideSizeCoordinate xgetCx();

    public void setCx(int var1);

    public void xsetCx(STSlideSizeCoordinate var1);

    public int getCy();

    public STSlideSizeCoordinate xgetCy();

    public void setCy(int var1);

    public void xsetCy(STSlideSizeCoordinate var1);

    public STSlideSizeType.Enum getType();

    public STSlideSizeType xgetType();

    public boolean isSetType();

    public void setType(STSlideSizeType.Enum var1);

    public void xsetType(STSlideSizeType var1);

    public void unsetType();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSlideSize.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSlideSize newInstance() {
            return (CTSlideSize)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSlideSize newInstance(XmlOptions xmlOptions) {
            return (CTSlideSize)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSlideSize parse(String string) throws XmlException {
            return (CTSlideSize)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSlideSize parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideSize)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSlideSize parse(File file) throws XmlException, IOException {
            return (CTSlideSize)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSlideSize parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideSize)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSlideSize parse(URL uRL) throws XmlException, IOException {
            return (CTSlideSize)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSlideSize parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideSize)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSlideSize parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSlideSize)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSlideSize parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideSize)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSlideSize parse(Reader reader) throws XmlException, IOException {
            return (CTSlideSize)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSlideSize parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSlideSize)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSlideSize parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSlideSize)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSlideSize parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideSize)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSlideSize parse(Node node) throws XmlException {
            return (CTSlideSize)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSlideSize parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSlideSize)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSlideSize parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSlideSize)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSlideSize parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSlideSize)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

