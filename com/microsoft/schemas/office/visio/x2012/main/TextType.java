/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.CpType
 *  com.microsoft.schemas.office.visio.x2012.main.FldType
 *  com.microsoft.schemas.office.visio.x2012.main.PpType
 *  com.microsoft.schemas.office.visio.x2012.main.TpType
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.CpType;
import com.microsoft.schemas.office.visio.x2012.main.FldType;
import com.microsoft.schemas.office.visio.x2012.main.PpType;
import com.microsoft.schemas.office.visio.x2012.main.TpType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
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
import org.w3c.dom.Node;

public interface TextType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TextType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("texttyped2ectype");

    public List<CpType> getCpList();

    @Deprecated
    public CpType[] getCpArray();

    public CpType getCpArray(int var1);

    public int sizeOfCpArray();

    public void setCpArray(CpType[] var1);

    public void setCpArray(int var1, CpType var2);

    public CpType insertNewCp(int var1);

    public CpType addNewCp();

    public void removeCp(int var1);

    public List<PpType> getPpList();

    @Deprecated
    public PpType[] getPpArray();

    public PpType getPpArray(int var1);

    public int sizeOfPpArray();

    public void setPpArray(PpType[] var1);

    public void setPpArray(int var1, PpType var2);

    public PpType insertNewPp(int var1);

    public PpType addNewPp();

    public void removePp(int var1);

    public List<TpType> getTpList();

    @Deprecated
    public TpType[] getTpArray();

    public TpType getTpArray(int var1);

    public int sizeOfTpArray();

    public void setTpArray(TpType[] var1);

    public void setTpArray(int var1, TpType var2);

    public TpType insertNewTp(int var1);

    public TpType addNewTp();

    public void removeTp(int var1);

    public List<FldType> getFldList();

    @Deprecated
    public FldType[] getFldArray();

    public FldType getFldArray(int var1);

    public int sizeOfFldArray();

    public void setFldArray(FldType[] var1);

    public void setFldArray(int var1, FldType var2);

    public FldType insertNewFld(int var1);

    public FldType addNewFld();

    public void removeFld(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(TextType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static TextType newInstance() {
            return (TextType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static TextType newInstance(XmlOptions xmlOptions) {
            return (TextType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static TextType parse(String string) throws XmlException {
            return (TextType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static TextType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (TextType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static TextType parse(File file) throws XmlException, IOException {
            return (TextType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static TextType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (TextType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static TextType parse(URL uRL) throws XmlException, IOException {
            return (TextType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static TextType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (TextType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static TextType parse(InputStream inputStream) throws XmlException, IOException {
            return (TextType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static TextType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (TextType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static TextType parse(Reader reader) throws XmlException, IOException {
            return (TextType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static TextType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (TextType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static TextType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (TextType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static TextType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (TextType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static TextType parse(Node node) throws XmlException {
            return (TextType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static TextType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (TextType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static TextType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (TextType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static TextType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (TextType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

