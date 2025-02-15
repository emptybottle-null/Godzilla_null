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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure;
import org.w3c.dom.Node;

public interface CTInd
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTInd.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctind4b93type");

    public BigInteger getLeft();

    public STSignedTwipsMeasure xgetLeft();

    public boolean isSetLeft();

    public void setLeft(BigInteger var1);

    public void xsetLeft(STSignedTwipsMeasure var1);

    public void unsetLeft();

    public BigInteger getLeftChars();

    public STDecimalNumber xgetLeftChars();

    public boolean isSetLeftChars();

    public void setLeftChars(BigInteger var1);

    public void xsetLeftChars(STDecimalNumber var1);

    public void unsetLeftChars();

    public BigInteger getRight();

    public STSignedTwipsMeasure xgetRight();

    public boolean isSetRight();

    public void setRight(BigInteger var1);

    public void xsetRight(STSignedTwipsMeasure var1);

    public void unsetRight();

    public BigInteger getRightChars();

    public STDecimalNumber xgetRightChars();

    public boolean isSetRightChars();

    public void setRightChars(BigInteger var1);

    public void xsetRightChars(STDecimalNumber var1);

    public void unsetRightChars();

    public BigInteger getHanging();

    public STTwipsMeasure xgetHanging();

    public boolean isSetHanging();

    public void setHanging(BigInteger var1);

    public void xsetHanging(STTwipsMeasure var1);

    public void unsetHanging();

    public BigInteger getHangingChars();

    public STDecimalNumber xgetHangingChars();

    public boolean isSetHangingChars();

    public void setHangingChars(BigInteger var1);

    public void xsetHangingChars(STDecimalNumber var1);

    public void unsetHangingChars();

    public BigInteger getFirstLine();

    public STTwipsMeasure xgetFirstLine();

    public boolean isSetFirstLine();

    public void setFirstLine(BigInteger var1);

    public void xsetFirstLine(STTwipsMeasure var1);

    public void unsetFirstLine();

    public BigInteger getFirstLineChars();

    public STDecimalNumber xgetFirstLineChars();

    public boolean isSetFirstLineChars();

    public void setFirstLineChars(BigInteger var1);

    public void xsetFirstLineChars(STDecimalNumber var1);

    public void unsetFirstLineChars();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTInd.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTInd newInstance() {
            return (CTInd)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTInd newInstance(XmlOptions xmlOptions) {
            return (CTInd)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTInd parse(String string) throws XmlException {
            return (CTInd)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTInd parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTInd)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTInd parse(File file) throws XmlException, IOException {
            return (CTInd)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTInd parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTInd)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTInd parse(URL uRL) throws XmlException, IOException {
            return (CTInd)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTInd parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTInd)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTInd parse(InputStream inputStream) throws XmlException, IOException {
            return (CTInd)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTInd parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTInd)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTInd parse(Reader reader) throws XmlException, IOException {
            return (CTInd)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTInd parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTInd)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTInd parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTInd)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTInd parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTInd)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTInd parse(Node node) throws XmlException {
            return (CTInd)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTInd parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTInd)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTInd parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTInd)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTInd parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTInd)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

