/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.STShortHexNumber
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShortHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.w3c.dom.Node;

public interface CTSym
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSym.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsym0dabtype");

    public String getFont();

    public STString xgetFont();

    public boolean isSetFont();

    public void setFont(String var1);

    public void xsetFont(STString var1);

    public void unsetFont();

    public byte[] getChar();

    public STShortHexNumber xgetChar();

    public boolean isSetChar();

    public void setChar(byte[] var1);

    public void xsetChar(STShortHexNumber var1);

    public void unsetChar();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSym.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSym newInstance() {
            return (CTSym)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSym newInstance(XmlOptions xmlOptions) {
            return (CTSym)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSym parse(String string) throws XmlException {
            return (CTSym)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSym parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSym)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSym parse(File file) throws XmlException, IOException {
            return (CTSym)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSym parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSym)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSym parse(URL uRL) throws XmlException, IOException {
            return (CTSym)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSym parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSym)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSym parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSym)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSym parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSym)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSym parse(Reader reader) throws XmlException, IOException {
            return (CTSym)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSym parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSym)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSym parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSym)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSym parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSym)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSym parse(Node node) throws XmlException {
            return (CTSym)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSym parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSym)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSym parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSym)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSym parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSym)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

