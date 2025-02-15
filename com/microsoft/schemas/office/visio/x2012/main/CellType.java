/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.RefByType
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.RefByType;
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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CellType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CellType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("celltyped857type");

    public List<RefByType> getRefByList();

    @Deprecated
    public RefByType[] getRefByArray();

    public RefByType getRefByArray(int var1);

    public int sizeOfRefByArray();

    public void setRefByArray(RefByType[] var1);

    public void setRefByArray(int var1, RefByType var2);

    public RefByType insertNewRefBy(int var1);

    public RefByType addNewRefBy();

    public void removeRefBy(int var1);

    public String getN();

    public XmlString xgetN();

    public void setN(String var1);

    public void xsetN(XmlString var1);

    public String getU();

    public XmlString xgetU();

    public boolean isSetU();

    public void setU(String var1);

    public void xsetU(XmlString var1);

    public void unsetU();

    public String getE();

    public XmlString xgetE();

    public boolean isSetE();

    public void setE(String var1);

    public void xsetE(XmlString var1);

    public void unsetE();

    public String getF();

    public XmlString xgetF();

    public boolean isSetF();

    public void setF(String var1);

    public void xsetF(XmlString var1);

    public void unsetF();

    public String getV();

    public XmlString xgetV();

    public boolean isSetV();

    public void setV(String var1);

    public void xsetV(XmlString var1);

    public void unsetV();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CellType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CellType newInstance() {
            return (CellType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CellType newInstance(XmlOptions xmlOptions) {
            return (CellType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CellType parse(String string) throws XmlException {
            return (CellType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CellType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CellType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CellType parse(File file) throws XmlException, IOException {
            return (CellType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CellType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CellType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CellType parse(URL uRL) throws XmlException, IOException {
            return (CellType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CellType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CellType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CellType parse(InputStream inputStream) throws XmlException, IOException {
            return (CellType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CellType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CellType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CellType parse(Reader reader) throws XmlException, IOException {
            return (CellType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CellType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CellType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CellType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CellType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CellType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CellType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CellType parse(Node node) throws XmlException {
            return (CellType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CellType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CellType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CellType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CellType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CellType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CellType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

