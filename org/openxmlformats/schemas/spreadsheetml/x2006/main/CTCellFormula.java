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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;
import org.w3c.dom.Node;

public interface CTCellFormula
extends STFormula {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCellFormula.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcellformula3583type");

    public STCellFormulaType.Enum getT();

    public STCellFormulaType xgetT();

    public boolean isSetT();

    public void setT(STCellFormulaType.Enum var1);

    public void xsetT(STCellFormulaType var1);

    public void unsetT();

    public boolean getAca();

    public XmlBoolean xgetAca();

    public boolean isSetAca();

    public void setAca(boolean var1);

    public void xsetAca(XmlBoolean var1);

    public void unsetAca();

    public String getRef();

    public STRef xgetRef();

    public boolean isSetRef();

    public void setRef(String var1);

    public void xsetRef(STRef var1);

    public void unsetRef();

    public boolean getDt2D();

    public XmlBoolean xgetDt2D();

    public boolean isSetDt2D();

    public void setDt2D(boolean var1);

    public void xsetDt2D(XmlBoolean var1);

    public void unsetDt2D();

    public boolean getDtr();

    public XmlBoolean xgetDtr();

    public boolean isSetDtr();

    public void setDtr(boolean var1);

    public void xsetDtr(XmlBoolean var1);

    public void unsetDtr();

    public boolean getDel1();

    public XmlBoolean xgetDel1();

    public boolean isSetDel1();

    public void setDel1(boolean var1);

    public void xsetDel1(XmlBoolean var1);

    public void unsetDel1();

    public boolean getDel2();

    public XmlBoolean xgetDel2();

    public boolean isSetDel2();

    public void setDel2(boolean var1);

    public void xsetDel2(XmlBoolean var1);

    public void unsetDel2();

    public String getR1();

    public STCellRef xgetR1();

    public boolean isSetR1();

    public void setR1(String var1);

    public void xsetR1(STCellRef var1);

    public void unsetR1();

    public String getR2();

    public STCellRef xgetR2();

    public boolean isSetR2();

    public void setR2(String var1);

    public void xsetR2(STCellRef var1);

    public void unsetR2();

    public boolean getCa();

    public XmlBoolean xgetCa();

    public boolean isSetCa();

    public void setCa(boolean var1);

    public void xsetCa(XmlBoolean var1);

    public void unsetCa();

    public long getSi();

    public XmlUnsignedInt xgetSi();

    public boolean isSetSi();

    public void setSi(long var1);

    public void xsetSi(XmlUnsignedInt var1);

    public void unsetSi();

    public boolean getBx();

    public XmlBoolean xgetBx();

    public boolean isSetBx();

    public void setBx(boolean var1);

    public void xsetBx(XmlBoolean var1);

    public void unsetBx();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCellFormula.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCellFormula newInstance() {
            return (CTCellFormula)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCellFormula newInstance(XmlOptions xmlOptions) {
            return (CTCellFormula)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCellFormula parse(String string) throws XmlException {
            return (CTCellFormula)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCellFormula parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCellFormula)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCellFormula parse(File file) throws XmlException, IOException {
            return (CTCellFormula)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCellFormula parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellFormula)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCellFormula parse(URL uRL) throws XmlException, IOException {
            return (CTCellFormula)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCellFormula parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellFormula)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCellFormula parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCellFormula)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCellFormula parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellFormula)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCellFormula parse(Reader reader) throws XmlException, IOException {
            return (CTCellFormula)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCellFormula parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellFormula)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCellFormula parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCellFormula)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCellFormula parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCellFormula)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCellFormula parse(Node node) throws XmlException {
            return (CTCellFormula)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCellFormula parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCellFormula)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCellFormula parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCellFormula)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCellFormula parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCellFormula)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

