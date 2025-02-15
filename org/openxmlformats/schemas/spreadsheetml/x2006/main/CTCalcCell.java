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
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.w3c.dom.Node;

public interface CTCalcCell
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCalcCell.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcalccellb960type");

    public String getR();

    public STCellRef xgetR();

    public void setR(String var1);

    public void xsetR(STCellRef var1);

    public int getI();

    public XmlInt xgetI();

    public boolean isSetI();

    public void setI(int var1);

    public void xsetI(XmlInt var1);

    public void unsetI();

    public boolean getS();

    public XmlBoolean xgetS();

    public boolean isSetS();

    public void setS(boolean var1);

    public void xsetS(XmlBoolean var1);

    public void unsetS();

    public boolean getL();

    public XmlBoolean xgetL();

    public boolean isSetL();

    public void setL(boolean var1);

    public void xsetL(XmlBoolean var1);

    public void unsetL();

    public boolean getT();

    public XmlBoolean xgetT();

    public boolean isSetT();

    public void setT(boolean var1);

    public void xsetT(XmlBoolean var1);

    public void unsetT();

    public boolean getA();

    public XmlBoolean xgetA();

    public boolean isSetA();

    public void setA(boolean var1);

    public void xsetA(XmlBoolean var1);

    public void unsetA();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCalcCell.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCalcCell newInstance() {
            return (CTCalcCell)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCalcCell newInstance(XmlOptions xmlOptions) {
            return (CTCalcCell)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCalcCell parse(String string) throws XmlException {
            return (CTCalcCell)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCalcCell parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCalcCell)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCalcCell parse(File file) throws XmlException, IOException {
            return (CTCalcCell)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCalcCell parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcCell)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCalcCell parse(URL uRL) throws XmlException, IOException {
            return (CTCalcCell)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCalcCell parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcCell)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCalcCell parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCalcCell)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCalcCell parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcCell)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCalcCell parse(Reader reader) throws XmlException, IOException {
            return (CTCalcCell)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCalcCell parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCalcCell)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCalcCell parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCalcCell)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCalcCell parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCalcCell)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCalcCell parse(Node node) throws XmlException {
            return (CTCalcCell)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCalcCell parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCalcCell)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCalcCell parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCalcCell)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCalcCell parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCalcCell)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

