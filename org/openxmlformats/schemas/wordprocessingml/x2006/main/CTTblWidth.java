/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.w3c.dom.Node;

public interface CTTblWidth
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTblWidth.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttblwidthec40type");

    public BigInteger getW();

    public STDecimalNumber xgetW();

    public boolean isSetW();

    public void setW(BigInteger var1);

    public void xsetW(STDecimalNumber var1);

    public void unsetW();

    public STTblWidth.Enum getType();

    public STTblWidth xgetType();

    public boolean isSetType();

    public void setType(STTblWidth.Enum var1);

    public void xsetType(STTblWidth var1);

    public void unsetType();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTblWidth.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTblWidth newInstance() {
            return (CTTblWidth)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTblWidth newInstance(XmlOptions xmlOptions) {
            return (CTTblWidth)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTblWidth parse(String string) throws XmlException {
            return (CTTblWidth)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTblWidth parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTblWidth)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTblWidth parse(File file) throws XmlException, IOException {
            return (CTTblWidth)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTblWidth parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblWidth)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTblWidth parse(URL uRL) throws XmlException, IOException {
            return (CTTblWidth)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTblWidth parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblWidth)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTblWidth parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTblWidth)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTblWidth parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblWidth)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTblWidth parse(Reader reader) throws XmlException, IOException {
            return (CTTblWidth)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTblWidth parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTblWidth)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTblWidth parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTblWidth)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTblWidth parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTblWidth)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTblWidth parse(Node node) throws XmlException {
            return (CTTblWidth)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTblWidth parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTblWidth)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTblWidth parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTblWidth)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTblWidth parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTblWidth)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

