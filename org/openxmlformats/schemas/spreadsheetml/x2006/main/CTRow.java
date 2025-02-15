/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellSpans
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellSpans;
import org.w3c.dom.Node;

public interface CTRow
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRow.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctrowdd39type");

    public List<CTCell> getCList();

    @Deprecated
    public CTCell[] getCArray();

    public CTCell getCArray(int var1);

    public int sizeOfCArray();

    public void setCArray(CTCell[] var1);

    public void setCArray(int var1, CTCell var2);

    public CTCell insertNewC(int var1);

    public CTCell addNewC();

    public void removeC(int var1);

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public long getR();

    public XmlUnsignedInt xgetR();

    public boolean isSetR();

    public void setR(long var1);

    public void xsetR(XmlUnsignedInt var1);

    public void unsetR();

    public List getSpans();

    public STCellSpans xgetSpans();

    public boolean isSetSpans();

    public void setSpans(List var1);

    public void xsetSpans(STCellSpans var1);

    public void unsetSpans();

    public long getS();

    public XmlUnsignedInt xgetS();

    public boolean isSetS();

    public void setS(long var1);

    public void xsetS(XmlUnsignedInt var1);

    public void unsetS();

    public boolean getCustomFormat();

    public XmlBoolean xgetCustomFormat();

    public boolean isSetCustomFormat();

    public void setCustomFormat(boolean var1);

    public void xsetCustomFormat(XmlBoolean var1);

    public void unsetCustomFormat();

    public double getHt();

    public XmlDouble xgetHt();

    public boolean isSetHt();

    public void setHt(double var1);

    public void xsetHt(XmlDouble var1);

    public void unsetHt();

    public boolean getHidden();

    public XmlBoolean xgetHidden();

    public boolean isSetHidden();

    public void setHidden(boolean var1);

    public void xsetHidden(XmlBoolean var1);

    public void unsetHidden();

    public boolean getCustomHeight();

    public XmlBoolean xgetCustomHeight();

    public boolean isSetCustomHeight();

    public void setCustomHeight(boolean var1);

    public void xsetCustomHeight(XmlBoolean var1);

    public void unsetCustomHeight();

    public short getOutlineLevel();

    public XmlUnsignedByte xgetOutlineLevel();

    public boolean isSetOutlineLevel();

    public void setOutlineLevel(short var1);

    public void xsetOutlineLevel(XmlUnsignedByte var1);

    public void unsetOutlineLevel();

    public boolean getCollapsed();

    public XmlBoolean xgetCollapsed();

    public boolean isSetCollapsed();

    public void setCollapsed(boolean var1);

    public void xsetCollapsed(XmlBoolean var1);

    public void unsetCollapsed();

    public boolean getThickTop();

    public XmlBoolean xgetThickTop();

    public boolean isSetThickTop();

    public void setThickTop(boolean var1);

    public void xsetThickTop(XmlBoolean var1);

    public void unsetThickTop();

    public boolean getThickBot();

    public XmlBoolean xgetThickBot();

    public boolean isSetThickBot();

    public void setThickBot(boolean var1);

    public void xsetThickBot(XmlBoolean var1);

    public void unsetThickBot();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRow.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRow newInstance() {
            return (CTRow)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRow newInstance(XmlOptions xmlOptions) {
            return (CTRow)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRow parse(String string) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRow parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRow parse(File file) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRow parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRow parse(URL uRL) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRow parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRow parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRow parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRow parse(Reader reader) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRow parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRow)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRow parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRow parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRow parse(Node node) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRow parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRow)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRow parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRow)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRow parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRow)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

