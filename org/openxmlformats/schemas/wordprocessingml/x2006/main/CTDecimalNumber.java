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
import org.w3c.dom.Node;

public interface CTDecimalNumber
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDecimalNumber.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdecimalnumbera518type");

    public BigInteger getVal();

    public STDecimalNumber xgetVal();

    public void setVal(BigInteger var1);

    public void xsetVal(STDecimalNumber var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDecimalNumber.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDecimalNumber newInstance() {
            return (CTDecimalNumber)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDecimalNumber newInstance(XmlOptions xmlOptions) {
            return (CTDecimalNumber)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDecimalNumber parse(String string) throws XmlException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDecimalNumber parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDecimalNumber parse(File file) throws XmlException, IOException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDecimalNumber parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDecimalNumber parse(URL uRL) throws XmlException, IOException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDecimalNumber parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDecimalNumber parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDecimalNumber parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDecimalNumber parse(Reader reader) throws XmlException, IOException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDecimalNumber parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDecimalNumber parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDecimalNumber parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDecimalNumber parse(Node node) throws XmlException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDecimalNumber parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDecimalNumber parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDecimalNumber parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDecimalNumber)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

