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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.w3c.dom.Node;

public interface CTNumLvl
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNumLvl.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnumlvl416ctype");

    public CTDecimalNumber getStartOverride();

    public boolean isSetStartOverride();

    public void setStartOverride(CTDecimalNumber var1);

    public CTDecimalNumber addNewStartOverride();

    public void unsetStartOverride();

    public CTLvl getLvl();

    public boolean isSetLvl();

    public void setLvl(CTLvl var1);

    public CTLvl addNewLvl();

    public void unsetLvl();

    public BigInteger getIlvl();

    public STDecimalNumber xgetIlvl();

    public void setIlvl(BigInteger var1);

    public void xsetIlvl(STDecimalNumber var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNumLvl.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNumLvl newInstance() {
            return (CTNumLvl)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNumLvl newInstance(XmlOptions xmlOptions) {
            return (CTNumLvl)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNumLvl parse(String string) throws XmlException {
            return (CTNumLvl)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNumLvl parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNumLvl)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNumLvl parse(File file) throws XmlException, IOException {
            return (CTNumLvl)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNumLvl parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumLvl)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNumLvl parse(URL uRL) throws XmlException, IOException {
            return (CTNumLvl)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNumLvl parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumLvl)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNumLvl parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNumLvl)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNumLvl parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumLvl)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNumLvl parse(Reader reader) throws XmlException, IOException {
            return (CTNumLvl)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNumLvl parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNumLvl)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNumLvl parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNumLvl)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNumLvl parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNumLvl)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNumLvl parse(Node node) throws XmlException {
            return (CTNumLvl)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNumLvl parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNumLvl)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNumLvl parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNumLvl)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNumLvl parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNumLvl)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

