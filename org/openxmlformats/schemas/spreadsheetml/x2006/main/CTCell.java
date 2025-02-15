/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTCell
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCell.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcell842btype");

    public CTCellFormula getF();

    public boolean isSetF();

    public void setF(CTCellFormula var1);

    public CTCellFormula addNewF();

    public void unsetF();

    public String getV();

    public STXstring xgetV();

    public boolean isSetV();

    public void setV(String var1);

    public void xsetV(STXstring var1);

    public void unsetV();

    public CTRst getIs();

    public boolean isSetIs();

    public void setIs(CTRst var1);

    public CTRst addNewIs();

    public void unsetIs();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getR();

    public STCellRef xgetR();

    public boolean isSetR();

    public void setR(String var1);

    public void xsetR(STCellRef var1);

    public void unsetR();

    public long getS();

    public XmlUnsignedInt xgetS();

    public boolean isSetS();

    public void setS(long var1);

    public void xsetS(XmlUnsignedInt var1);

    public void unsetS();

    public STCellType.Enum getT();

    public STCellType xgetT();

    public boolean isSetT();

    public void setT(STCellType.Enum var1);

    public void xsetT(STCellType var1);

    public void unsetT();

    public long getCm();

    public XmlUnsignedInt xgetCm();

    public boolean isSetCm();

    public void setCm(long var1);

    public void xsetCm(XmlUnsignedInt var1);

    public void unsetCm();

    public long getVm();

    public XmlUnsignedInt xgetVm();

    public boolean isSetVm();

    public void setVm(long var1);

    public void xsetVm(XmlUnsignedInt var1);

    public void unsetVm();

    public boolean getPh();

    public XmlBoolean xgetPh();

    public boolean isSetPh();

    public void setPh(boolean var1);

    public void xsetPh(XmlBoolean var1);

    public void unsetPh();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCell.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCell newInstance() {
            return (CTCell)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCell newInstance(XmlOptions xmlOptions) {
            return (CTCell)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCell parse(String string) throws XmlException {
            return (CTCell)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCell parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCell)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCell parse(File file) throws XmlException, IOException {
            return (CTCell)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCell parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCell)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCell parse(URL uRL) throws XmlException, IOException {
            return (CTCell)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCell parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCell)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCell parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCell)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCell parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCell)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCell parse(Reader reader) throws XmlException, IOException {
            return (CTCell)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCell parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCell)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCell parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCell)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCell parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCell)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCell parse(Node node) throws XmlException {
            return (CTCell)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCell parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCell)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCell parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCell)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCell parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCell)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

