/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.TriggerType
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.TriggerType;
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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface RowType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(RowType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("rowtype03d1type");

    public List<CellType> getCellList();

    @Deprecated
    public CellType[] getCellArray();

    public CellType getCellArray(int var1);

    public int sizeOfCellArray();

    public void setCellArray(CellType[] var1);

    public void setCellArray(int var1, CellType var2);

    public CellType insertNewCell(int var1);

    public CellType addNewCell();

    public void removeCell(int var1);

    public List<TriggerType> getTriggerList();

    @Deprecated
    public TriggerType[] getTriggerArray();

    public TriggerType getTriggerArray(int var1);

    public int sizeOfTriggerArray();

    public void setTriggerArray(TriggerType[] var1);

    public void setTriggerArray(int var1, TriggerType var2);

    public TriggerType insertNewTrigger(int var1);

    public TriggerType addNewTrigger();

    public void removeTrigger(int var1);

    public String getN();

    public XmlString xgetN();

    public boolean isSetN();

    public void setN(String var1);

    public void xsetN(XmlString var1);

    public void unsetN();

    public String getLocalName();

    public XmlString xgetLocalName();

    public boolean isSetLocalName();

    public void setLocalName(String var1);

    public void xsetLocalName(XmlString var1);

    public void unsetLocalName();

    public long getIX();

    public XmlUnsignedInt xgetIX();

    public boolean isSetIX();

    public void setIX(long var1);

    public void xsetIX(XmlUnsignedInt var1);

    public void unsetIX();

    public String getT();

    public XmlString xgetT();

    public boolean isSetT();

    public void setT(String var1);

    public void xsetT(XmlString var1);

    public void unsetT();

    public boolean getDel();

    public XmlBoolean xgetDel();

    public boolean isSetDel();

    public void setDel(boolean var1);

    public void xsetDel(XmlBoolean var1);

    public void unsetDel();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(RowType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static RowType newInstance() {
            return (RowType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static RowType newInstance(XmlOptions xmlOptions) {
            return (RowType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static RowType parse(String string) throws XmlException {
            return (RowType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static RowType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (RowType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static RowType parse(File file) throws XmlException, IOException {
            return (RowType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static RowType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RowType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static RowType parse(URL uRL) throws XmlException, IOException {
            return (RowType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static RowType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RowType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static RowType parse(InputStream inputStream) throws XmlException, IOException {
            return (RowType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static RowType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RowType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static RowType parse(Reader reader) throws XmlException, IOException {
            return (RowType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static RowType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RowType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static RowType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (RowType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static RowType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (RowType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static RowType parse(Node node) throws XmlException {
            return (RowType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static RowType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (RowType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static RowType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (RowType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static RowType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (RowType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

