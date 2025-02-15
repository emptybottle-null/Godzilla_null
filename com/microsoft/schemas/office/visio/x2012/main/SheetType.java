/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.TriggerType
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface SheetType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SheetType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sheettype25actype");

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

    public List<SectionType> getSectionList();

    @Deprecated
    public SectionType[] getSectionArray();

    public SectionType getSectionArray(int var1);

    public int sizeOfSectionArray();

    public void setSectionArray(SectionType[] var1);

    public void setSectionArray(int var1, SectionType var2);

    public SectionType insertNewSection(int var1);

    public SectionType addNewSection();

    public void removeSection(int var1);

    public long getLineStyle();

    public XmlUnsignedInt xgetLineStyle();

    public boolean isSetLineStyle();

    public void setLineStyle(long var1);

    public void xsetLineStyle(XmlUnsignedInt var1);

    public void unsetLineStyle();

    public long getFillStyle();

    public XmlUnsignedInt xgetFillStyle();

    public boolean isSetFillStyle();

    public void setFillStyle(long var1);

    public void xsetFillStyle(XmlUnsignedInt var1);

    public void unsetFillStyle();

    public long getTextStyle();

    public XmlUnsignedInt xgetTextStyle();

    public boolean isSetTextStyle();

    public void setTextStyle(long var1);

    public void xsetTextStyle(XmlUnsignedInt var1);

    public void unsetTextStyle();

    public static final class Factory {
        @Deprecated
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(SheetType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static SheetType newInstance() {
            return (SheetType)Factory.getTypeLoader().newInstance(type, null);
        }

        @Deprecated
        public static SheetType newInstance(XmlOptions xmlOptions) {
            return (SheetType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static SheetType parse(String string) throws XmlException {
            return (SheetType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static SheetType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (SheetType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static SheetType parse(File file) throws XmlException, IOException {
            return (SheetType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static SheetType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SheetType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static SheetType parse(URL uRL) throws XmlException, IOException {
            return (SheetType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static SheetType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SheetType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static SheetType parse(InputStream inputStream) throws XmlException, IOException {
            return (SheetType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static SheetType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SheetType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static SheetType parse(Reader reader) throws XmlException, IOException {
            return (SheetType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static SheetType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SheetType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static SheetType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (SheetType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static SheetType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (SheetType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static SheetType parse(Node node) throws XmlException {
            return (SheetType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static SheetType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (SheetType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static SheetType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (SheetType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static SheetType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (SheetType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

