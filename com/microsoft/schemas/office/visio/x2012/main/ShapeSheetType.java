/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.DataType
 *  com.microsoft.schemas.office.visio.x2012.main.ForeignDataType
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.DataType;
import com.microsoft.schemas.office.visio.x2012.main.ForeignDataType;
import com.microsoft.schemas.office.visio.x2012.main.ShapesType;
import com.microsoft.schemas.office.visio.x2012.main.SheetType;
import com.microsoft.schemas.office.visio.x2012.main.TextType;
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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ShapeSheetType
extends SheetType {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ShapeSheetType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("shapesheettype59bbtype");

    public TextType getText();

    public boolean isSetText();

    public void setText(TextType var1);

    public TextType addNewText();

    public void unsetText();

    public DataType getData1();

    public boolean isSetData1();

    public void setData1(DataType var1);

    public DataType addNewData1();

    public void unsetData1();

    public DataType getData2();

    public boolean isSetData2();

    public void setData2(DataType var1);

    public DataType addNewData2();

    public void unsetData2();

    public DataType getData3();

    public boolean isSetData3();

    public void setData3(DataType var1);

    public DataType addNewData3();

    public void unsetData3();

    public ForeignDataType getForeignData();

    public boolean isSetForeignData();

    public void setForeignData(ForeignDataType var1);

    public ForeignDataType addNewForeignData();

    public void unsetForeignData();

    public ShapesType getShapes();

    public boolean isSetShapes();

    public void setShapes(ShapesType var1);

    public ShapesType addNewShapes();

    public void unsetShapes();

    public long getID();

    public XmlUnsignedInt xgetID();

    public void setID(long var1);

    public void xsetID(XmlUnsignedInt var1);

    public long getOriginalID();

    public XmlUnsignedInt xgetOriginalID();

    public boolean isSetOriginalID();

    public void setOriginalID(long var1);

    public void xsetOriginalID(XmlUnsignedInt var1);

    public void unsetOriginalID();

    public boolean getDel();

    public XmlBoolean xgetDel();

    public boolean isSetDel();

    public void setDel(boolean var1);

    public void xsetDel(XmlBoolean var1);

    public void unsetDel();

    public long getMasterShape();

    public XmlUnsignedInt xgetMasterShape();

    public boolean isSetMasterShape();

    public void setMasterShape(long var1);

    public void xsetMasterShape(XmlUnsignedInt var1);

    public void unsetMasterShape();

    public String getUniqueID();

    public XmlString xgetUniqueID();

    public boolean isSetUniqueID();

    public void setUniqueID(String var1);

    public void xsetUniqueID(XmlString var1);

    public void unsetUniqueID();

    public String getName();

    public XmlString xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlString var1);

    public void unsetName();

    public String getNameU();

    public XmlString xgetNameU();

    public boolean isSetNameU();

    public void setNameU(String var1);

    public void xsetNameU(XmlString var1);

    public void unsetNameU();

    public boolean getIsCustomName();

    public XmlBoolean xgetIsCustomName();

    public boolean isSetIsCustomName();

    public void setIsCustomName(boolean var1);

    public void xsetIsCustomName(XmlBoolean var1);

    public void unsetIsCustomName();

    public boolean getIsCustomNameU();

    public XmlBoolean xgetIsCustomNameU();

    public boolean isSetIsCustomNameU();

    public void setIsCustomNameU(boolean var1);

    public void xsetIsCustomNameU(XmlBoolean var1);

    public void unsetIsCustomNameU();

    public long getMaster();

    public XmlUnsignedInt xgetMaster();

    public boolean isSetMaster();

    public void setMaster(long var1);

    public void xsetMaster(XmlUnsignedInt var1);

    public void unsetMaster();

    public String getType();

    public XmlToken xgetType();

    public boolean isSetType();

    public void setType(String var1);

    public void xsetType(XmlToken var1);

    public void unsetType();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(ShapeSheetType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static ShapeSheetType newInstance() {
            return (ShapeSheetType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static ShapeSheetType newInstance(XmlOptions xmlOptions) {
            return (ShapeSheetType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static ShapeSheetType parse(String string) throws XmlException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static ShapeSheetType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static ShapeSheetType parse(File file) throws XmlException, IOException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static ShapeSheetType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static ShapeSheetType parse(URL uRL) throws XmlException, IOException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static ShapeSheetType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static ShapeSheetType parse(InputStream inputStream) throws XmlException, IOException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static ShapeSheetType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static ShapeSheetType parse(Reader reader) throws XmlException, IOException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static ShapeSheetType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static ShapeSheetType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static ShapeSheetType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static ShapeSheetType parse(Node node) throws XmlException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static ShapeSheetType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static ShapeSheetType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static ShapeSheetType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (ShapeSheetType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

