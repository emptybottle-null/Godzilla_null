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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.w3c.dom.Node;

public interface CTNum
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTNum.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctnume94ctype");

    public CTDecimalNumber getAbstractNumId();

    public void setAbstractNumId(CTDecimalNumber var1);

    public CTDecimalNumber addNewAbstractNumId();

    public List<CTNumLvl> getLvlOverrideList();

    @Deprecated
    public CTNumLvl[] getLvlOverrideArray();

    public CTNumLvl getLvlOverrideArray(int var1);

    public int sizeOfLvlOverrideArray();

    public void setLvlOverrideArray(CTNumLvl[] var1);

    public void setLvlOverrideArray(int var1, CTNumLvl var2);

    public CTNumLvl insertNewLvlOverride(int var1);

    public CTNumLvl addNewLvlOverride();

    public void removeLvlOverride(int var1);

    public BigInteger getNumId();

    public STDecimalNumber xgetNumId();

    public void setNumId(BigInteger var1);

    public void xsetNumId(STDecimalNumber var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTNum.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTNum newInstance() {
            return (CTNum)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTNum newInstance(XmlOptions xmlOptions) {
            return (CTNum)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTNum parse(String string) throws XmlException {
            return (CTNum)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTNum parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTNum)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTNum parse(File file) throws XmlException, IOException {
            return (CTNum)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTNum parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNum)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTNum parse(URL uRL) throws XmlException, IOException {
            return (CTNum)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTNum parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNum)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTNum parse(InputStream inputStream) throws XmlException, IOException {
            return (CTNum)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTNum parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNum)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTNum parse(Reader reader) throws XmlException, IOException {
            return (CTNum)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTNum parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTNum)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTNum parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTNum)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTNum parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTNum)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTNum parse(Node node) throws XmlException {
            return (CTNum)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTNum parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTNum)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTNum parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTNum)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTNum parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTNum)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

