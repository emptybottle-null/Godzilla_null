/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STItemType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTItem
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTItem.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctitemc69ctype");

    public String getN();

    public STXstring xgetN();

    public boolean isSetN();

    public void setN(String var1);

    public void xsetN(STXstring var1);

    public void unsetN();

    public STItemType.Enum getT();

    public STItemType xgetT();

    public boolean isSetT();

    public void setT(STItemType.Enum var1);

    public void xsetT(STItemType var1);

    public void unsetT();

    public boolean getH();

    public XmlBoolean xgetH();

    public boolean isSetH();

    public void setH(boolean var1);

    public void xsetH(XmlBoolean var1);

    public void unsetH();

    public boolean getS();

    public XmlBoolean xgetS();

    public boolean isSetS();

    public void setS(boolean var1);

    public void xsetS(XmlBoolean var1);

    public void unsetS();

    public boolean getSd();

    public XmlBoolean xgetSd();

    public boolean isSetSd();

    public void setSd(boolean var1);

    public void xsetSd(XmlBoolean var1);

    public void unsetSd();

    public boolean getF();

    public XmlBoolean xgetF();

    public boolean isSetF();

    public void setF(boolean var1);

    public void xsetF(XmlBoolean var1);

    public void unsetF();

    public boolean getM();

    public XmlBoolean xgetM();

    public boolean isSetM();

    public void setM(boolean var1);

    public void xsetM(XmlBoolean var1);

    public void unsetM();

    public boolean getC();

    public XmlBoolean xgetC();

    public boolean isSetC();

    public void setC(boolean var1);

    public void xsetC(XmlBoolean var1);

    public void unsetC();

    public long getX();

    public XmlUnsignedInt xgetX();

    public boolean isSetX();

    public void setX(long var1);

    public void xsetX(XmlUnsignedInt var1);

    public void unsetX();

    public boolean getD();

    public XmlBoolean xgetD();

    public boolean isSetD();

    public void setD(boolean var1);

    public void xsetD(XmlBoolean var1);

    public void unsetD();

    public boolean getE();

    public XmlBoolean xgetE();

    public boolean isSetE();

    public void setE(boolean var1);

    public void xsetE(XmlBoolean var1);

    public void unsetE();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTItem.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTItem newInstance() {
            return (CTItem)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTItem newInstance(XmlOptions xmlOptions) {
            return (CTItem)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTItem parse(String string) throws XmlException {
            return (CTItem)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTItem parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTItem)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTItem parse(File file) throws XmlException, IOException {
            return (CTItem)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTItem parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTItem)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTItem parse(URL uRL) throws XmlException, IOException {
            return (CTItem)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTItem parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTItem)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTItem parse(InputStream inputStream) throws XmlException, IOException {
            return (CTItem)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTItem parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTItem)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTItem parse(Reader reader) throws XmlException, IOException {
            return (CTItem)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTItem parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTItem)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTItem parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTItem)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTItem parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTItem)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTItem parse(Node node) throws XmlException {
            return (CTItem)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTItem parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTItem)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTItem parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTItem)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTItem parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTItem)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

