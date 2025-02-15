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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure;
import org.w3c.dom.Node;

public interface CTHeight
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTHeight.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctheighta2e1type");

    public BigInteger getVal();

    public STTwipsMeasure xgetVal();

    public boolean isSetVal();

    public void setVal(BigInteger var1);

    public void xsetVal(STTwipsMeasure var1);

    public void unsetVal();

    public STHeightRule.Enum getHRule();

    public STHeightRule xgetHRule();

    public boolean isSetHRule();

    public void setHRule(STHeightRule.Enum var1);

    public void xsetHRule(STHeightRule var1);

    public void unsetHRule();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTHeight.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTHeight newInstance() {
            return (CTHeight)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTHeight newInstance(XmlOptions xmlOptions) {
            return (CTHeight)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTHeight parse(String string) throws XmlException {
            return (CTHeight)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTHeight parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTHeight)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTHeight parse(File file) throws XmlException, IOException {
            return (CTHeight)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTHeight parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeight)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTHeight parse(URL uRL) throws XmlException, IOException {
            return (CTHeight)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTHeight parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeight)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTHeight parse(InputStream inputStream) throws XmlException, IOException {
            return (CTHeight)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTHeight parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeight)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTHeight parse(Reader reader) throws XmlException, IOException {
            return (CTHeight)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTHeight parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHeight)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTHeight parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTHeight)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTHeight parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTHeight)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTHeight parse(Node node) throws XmlException {
            return (CTHeight)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTHeight parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTHeight)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTHeight parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTHeight)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTHeight parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTHeight)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

