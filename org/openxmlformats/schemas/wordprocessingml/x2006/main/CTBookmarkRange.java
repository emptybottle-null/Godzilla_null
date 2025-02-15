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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.w3c.dom.Node;

public interface CTBookmarkRange
extends CTMarkupRange {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBookmarkRange.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbookmarkranged88btype");

    public BigInteger getColFirst();

    public STDecimalNumber xgetColFirst();

    public boolean isSetColFirst();

    public void setColFirst(BigInteger var1);

    public void xsetColFirst(STDecimalNumber var1);

    public void unsetColFirst();

    public BigInteger getColLast();

    public STDecimalNumber xgetColLast();

    public boolean isSetColLast();

    public void setColLast(BigInteger var1);

    public void xsetColLast(STDecimalNumber var1);

    public void unsetColLast();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBookmarkRange.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBookmarkRange newInstance() {
            return (CTBookmarkRange)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBookmarkRange newInstance(XmlOptions xmlOptions) {
            return (CTBookmarkRange)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBookmarkRange parse(String string) throws XmlException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBookmarkRange parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBookmarkRange parse(File file) throws XmlException, IOException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBookmarkRange parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBookmarkRange parse(URL uRL) throws XmlException, IOException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBookmarkRange parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBookmarkRange parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBookmarkRange parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBookmarkRange parse(Reader reader) throws XmlException, IOException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBookmarkRange parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBookmarkRange parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBookmarkRange parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBookmarkRange parse(Node node) throws XmlException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBookmarkRange parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBookmarkRange parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBookmarkRange parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBookmarkRange)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

