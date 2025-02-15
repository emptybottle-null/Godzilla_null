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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnsignedShortHex;
import org.w3c.dom.Node;

public interface CTSheetProtection
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSheetProtection.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsheetprotection22f7type");

    public byte[] getPassword();

    public STUnsignedShortHex xgetPassword();

    public boolean isSetPassword();

    public void setPassword(byte[] var1);

    public void xsetPassword(STUnsignedShortHex var1);

    public void unsetPassword();

    public boolean getSheet();

    public XmlBoolean xgetSheet();

    public boolean isSetSheet();

    public void setSheet(boolean var1);

    public void xsetSheet(XmlBoolean var1);

    public void unsetSheet();

    public boolean getObjects();

    public XmlBoolean xgetObjects();

    public boolean isSetObjects();

    public void setObjects(boolean var1);

    public void xsetObjects(XmlBoolean var1);

    public void unsetObjects();

    public boolean getScenarios();

    public XmlBoolean xgetScenarios();

    public boolean isSetScenarios();

    public void setScenarios(boolean var1);

    public void xsetScenarios(XmlBoolean var1);

    public void unsetScenarios();

    public boolean getFormatCells();

    public XmlBoolean xgetFormatCells();

    public boolean isSetFormatCells();

    public void setFormatCells(boolean var1);

    public void xsetFormatCells(XmlBoolean var1);

    public void unsetFormatCells();

    public boolean getFormatColumns();

    public XmlBoolean xgetFormatColumns();

    public boolean isSetFormatColumns();

    public void setFormatColumns(boolean var1);

    public void xsetFormatColumns(XmlBoolean var1);

    public void unsetFormatColumns();

    public boolean getFormatRows();

    public XmlBoolean xgetFormatRows();

    public boolean isSetFormatRows();

    public void setFormatRows(boolean var1);

    public void xsetFormatRows(XmlBoolean var1);

    public void unsetFormatRows();

    public boolean getInsertColumns();

    public XmlBoolean xgetInsertColumns();

    public boolean isSetInsertColumns();

    public void setInsertColumns(boolean var1);

    public void xsetInsertColumns(XmlBoolean var1);

    public void unsetInsertColumns();

    public boolean getInsertRows();

    public XmlBoolean xgetInsertRows();

    public boolean isSetInsertRows();

    public void setInsertRows(boolean var1);

    public void xsetInsertRows(XmlBoolean var1);

    public void unsetInsertRows();

    public boolean getInsertHyperlinks();

    public XmlBoolean xgetInsertHyperlinks();

    public boolean isSetInsertHyperlinks();

    public void setInsertHyperlinks(boolean var1);

    public void xsetInsertHyperlinks(XmlBoolean var1);

    public void unsetInsertHyperlinks();

    public boolean getDeleteColumns();

    public XmlBoolean xgetDeleteColumns();

    public boolean isSetDeleteColumns();

    public void setDeleteColumns(boolean var1);

    public void xsetDeleteColumns(XmlBoolean var1);

    public void unsetDeleteColumns();

    public boolean getDeleteRows();

    public XmlBoolean xgetDeleteRows();

    public boolean isSetDeleteRows();

    public void setDeleteRows(boolean var1);

    public void xsetDeleteRows(XmlBoolean var1);

    public void unsetDeleteRows();

    public boolean getSelectLockedCells();

    public XmlBoolean xgetSelectLockedCells();

    public boolean isSetSelectLockedCells();

    public void setSelectLockedCells(boolean var1);

    public void xsetSelectLockedCells(XmlBoolean var1);

    public void unsetSelectLockedCells();

    public boolean getSort();

    public XmlBoolean xgetSort();

    public boolean isSetSort();

    public void setSort(boolean var1);

    public void xsetSort(XmlBoolean var1);

    public void unsetSort();

    public boolean getAutoFilter();

    public XmlBoolean xgetAutoFilter();

    public boolean isSetAutoFilter();

    public void setAutoFilter(boolean var1);

    public void xsetAutoFilter(XmlBoolean var1);

    public void unsetAutoFilter();

    public boolean getPivotTables();

    public XmlBoolean xgetPivotTables();

    public boolean isSetPivotTables();

    public void setPivotTables(boolean var1);

    public void xsetPivotTables(XmlBoolean var1);

    public void unsetPivotTables();

    public boolean getSelectUnlockedCells();

    public XmlBoolean xgetSelectUnlockedCells();

    public boolean isSetSelectUnlockedCells();

    public void setSelectUnlockedCells(boolean var1);

    public void xsetSelectUnlockedCells(XmlBoolean var1);

    public void unsetSelectUnlockedCells();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSheetProtection.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSheetProtection newInstance() {
            return (CTSheetProtection)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSheetProtection newInstance(XmlOptions xmlOptions) {
            return (CTSheetProtection)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSheetProtection parse(String string) throws XmlException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSheetProtection parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSheetProtection parse(File file) throws XmlException, IOException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSheetProtection parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSheetProtection parse(URL uRL) throws XmlException, IOException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSheetProtection parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSheetProtection parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSheetProtection parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSheetProtection parse(Reader reader) throws XmlException, IOException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSheetProtection parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSheetProtection parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSheetProtection parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSheetProtection parse(Node node) throws XmlException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSheetProtection parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSheetProtection parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSheetProtection parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSheetProtection)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

