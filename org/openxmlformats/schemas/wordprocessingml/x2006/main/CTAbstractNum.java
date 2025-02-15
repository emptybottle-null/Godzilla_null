/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMultiLevelType
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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLongHexNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMultiLevelType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;
import org.w3c.dom.Node;

public interface CTAbstractNum
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTAbstractNum.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctabstractnum588etype");

    public CTLongHexNumber getNsid();

    public boolean isSetNsid();

    public void setNsid(CTLongHexNumber var1);

    public CTLongHexNumber addNewNsid();

    public void unsetNsid();

    public CTMultiLevelType getMultiLevelType();

    public boolean isSetMultiLevelType();

    public void setMultiLevelType(CTMultiLevelType var1);

    public CTMultiLevelType addNewMultiLevelType();

    public void unsetMultiLevelType();

    public CTLongHexNumber getTmpl();

    public boolean isSetTmpl();

    public void setTmpl(CTLongHexNumber var1);

    public CTLongHexNumber addNewTmpl();

    public void unsetTmpl();

    public CTString getName();

    public boolean isSetName();

    public void setName(CTString var1);

    public CTString addNewName();

    public void unsetName();

    public CTString getStyleLink();

    public boolean isSetStyleLink();

    public void setStyleLink(CTString var1);

    public CTString addNewStyleLink();

    public void unsetStyleLink();

    public CTString getNumStyleLink();

    public boolean isSetNumStyleLink();

    public void setNumStyleLink(CTString var1);

    public CTString addNewNumStyleLink();

    public void unsetNumStyleLink();

    public List<CTLvl> getLvlList();

    @Deprecated
    public CTLvl[] getLvlArray();

    public CTLvl getLvlArray(int var1);

    public int sizeOfLvlArray();

    public void setLvlArray(CTLvl[] var1);

    public void setLvlArray(int var1, CTLvl var2);

    public CTLvl insertNewLvl(int var1);

    public CTLvl addNewLvl();

    public void removeLvl(int var1);

    public BigInteger getAbstractNumId();

    public STDecimalNumber xgetAbstractNumId();

    public void setAbstractNumId(BigInteger var1);

    public void xsetAbstractNumId(STDecimalNumber var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTAbstractNum.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTAbstractNum newInstance() {
            return (CTAbstractNum)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTAbstractNum newInstance(XmlOptions xmlOptions) {
            return (CTAbstractNum)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTAbstractNum parse(String string) throws XmlException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTAbstractNum parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTAbstractNum parse(File file) throws XmlException, IOException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTAbstractNum parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTAbstractNum parse(URL uRL) throws XmlException, IOException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTAbstractNum parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTAbstractNum parse(InputStream inputStream) throws XmlException, IOException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTAbstractNum parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTAbstractNum parse(Reader reader) throws XmlException, IOException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTAbstractNum parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTAbstractNum parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTAbstractNum parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTAbstractNum parse(Node node) throws XmlException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTAbstractNum parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTAbstractNum parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTAbstractNum parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTAbstractNum)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

