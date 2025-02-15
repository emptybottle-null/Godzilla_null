/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotSelection
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetViewType
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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotSelection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetViewType;
import org.w3c.dom.Node;

public interface CTSheetView
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSheetView.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsheetview0f43type");

    public CTPane getPane();

    public boolean isSetPane();

    public void setPane(CTPane var1);

    public CTPane addNewPane();

    public void unsetPane();

    public List<CTSelection> getSelectionList();

    @Deprecated
    public CTSelection[] getSelectionArray();

    public CTSelection getSelectionArray(int var1);

    public int sizeOfSelectionArray();

    public void setSelectionArray(CTSelection[] var1);

    public void setSelectionArray(int var1, CTSelection var2);

    public CTSelection insertNewSelection(int var1);

    public CTSelection addNewSelection();

    public void removeSelection(int var1);

    public List<CTPivotSelection> getPivotSelectionList();

    @Deprecated
    public CTPivotSelection[] getPivotSelectionArray();

    public CTPivotSelection getPivotSelectionArray(int var1);

    public int sizeOfPivotSelectionArray();

    public void setPivotSelectionArray(CTPivotSelection[] var1);

    public void setPivotSelectionArray(int var1, CTPivotSelection var2);

    public CTPivotSelection insertNewPivotSelection(int var1);

    public CTPivotSelection addNewPivotSelection();

    public void removePivotSelection(int var1);

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public boolean getWindowProtection();

    public XmlBoolean xgetWindowProtection();

    public boolean isSetWindowProtection();

    public void setWindowProtection(boolean var1);

    public void xsetWindowProtection(XmlBoolean var1);

    public void unsetWindowProtection();

    public boolean getShowFormulas();

    public XmlBoolean xgetShowFormulas();

    public boolean isSetShowFormulas();

    public void setShowFormulas(boolean var1);

    public void xsetShowFormulas(XmlBoolean var1);

    public void unsetShowFormulas();

    public boolean getShowGridLines();

    public XmlBoolean xgetShowGridLines();

    public boolean isSetShowGridLines();

    public void setShowGridLines(boolean var1);

    public void xsetShowGridLines(XmlBoolean var1);

    public void unsetShowGridLines();

    public boolean getShowRowColHeaders();

    public XmlBoolean xgetShowRowColHeaders();

    public boolean isSetShowRowColHeaders();

    public void setShowRowColHeaders(boolean var1);

    public void xsetShowRowColHeaders(XmlBoolean var1);

    public void unsetShowRowColHeaders();

    public boolean getShowZeros();

    public XmlBoolean xgetShowZeros();

    public boolean isSetShowZeros();

    public void setShowZeros(boolean var1);

    public void xsetShowZeros(XmlBoolean var1);

    public void unsetShowZeros();

    public boolean getRightToLeft();

    public XmlBoolean xgetRightToLeft();

    public boolean isSetRightToLeft();

    public void setRightToLeft(boolean var1);

    public void xsetRightToLeft(XmlBoolean var1);

    public void unsetRightToLeft();

    public boolean getTabSelected();

    public XmlBoolean xgetTabSelected();

    public boolean isSetTabSelected();

    public void setTabSelected(boolean var1);

    public void xsetTabSelected(XmlBoolean var1);

    public void unsetTabSelected();

    public boolean getShowRuler();

    public XmlBoolean xgetShowRuler();

    public boolean isSetShowRuler();

    public void setShowRuler(boolean var1);

    public void xsetShowRuler(XmlBoolean var1);

    public void unsetShowRuler();

    public boolean getShowOutlineSymbols();

    public XmlBoolean xgetShowOutlineSymbols();

    public boolean isSetShowOutlineSymbols();

    public void setShowOutlineSymbols(boolean var1);

    public void xsetShowOutlineSymbols(XmlBoolean var1);

    public void unsetShowOutlineSymbols();

    public boolean getDefaultGridColor();

    public XmlBoolean xgetDefaultGridColor();

    public boolean isSetDefaultGridColor();

    public void setDefaultGridColor(boolean var1);

    public void xsetDefaultGridColor(XmlBoolean var1);

    public void unsetDefaultGridColor();

    public boolean getShowWhiteSpace();

    public XmlBoolean xgetShowWhiteSpace();

    public boolean isSetShowWhiteSpace();

    public void setShowWhiteSpace(boolean var1);

    public void xsetShowWhiteSpace(XmlBoolean var1);

    public void unsetShowWhiteSpace();

    public STSheetViewType.Enum getView();

    public STSheetViewType xgetView();

    public boolean isSetView();

    public void setView(STSheetViewType.Enum var1);

    public void xsetView(STSheetViewType var1);

    public void unsetView();

    public String getTopLeftCell();

    public STCellRef xgetTopLeftCell();

    public boolean isSetTopLeftCell();

    public void setTopLeftCell(String var1);

    public void xsetTopLeftCell(STCellRef var1);

    public void unsetTopLeftCell();

    public long getColorId();

    public XmlUnsignedInt xgetColorId();

    public boolean isSetColorId();

    public void setColorId(long var1);

    public void xsetColorId(XmlUnsignedInt var1);

    public void unsetColorId();

    public long getZoomScale();

    public XmlUnsignedInt xgetZoomScale();

    public boolean isSetZoomScale();

    public void setZoomScale(long var1);

    public void xsetZoomScale(XmlUnsignedInt var1);

    public void unsetZoomScale();

    public long getZoomScaleNormal();

    public XmlUnsignedInt xgetZoomScaleNormal();

    public boolean isSetZoomScaleNormal();

    public void setZoomScaleNormal(long var1);

    public void xsetZoomScaleNormal(XmlUnsignedInt var1);

    public void unsetZoomScaleNormal();

    public long getZoomScaleSheetLayoutView();

    public XmlUnsignedInt xgetZoomScaleSheetLayoutView();

    public boolean isSetZoomScaleSheetLayoutView();

    public void setZoomScaleSheetLayoutView(long var1);

    public void xsetZoomScaleSheetLayoutView(XmlUnsignedInt var1);

    public void unsetZoomScaleSheetLayoutView();

    public long getZoomScalePageLayoutView();

    public XmlUnsignedInt xgetZoomScalePageLayoutView();

    public boolean isSetZoomScalePageLayoutView();

    public void setZoomScalePageLayoutView(long var1);

    public void xsetZoomScalePageLayoutView(XmlUnsignedInt var1);

    public void unsetZoomScalePageLayoutView();

    public long getWorkbookViewId();

    public XmlUnsignedInt xgetWorkbookViewId();

    public void setWorkbookViewId(long var1);

    public void xsetWorkbookViewId(XmlUnsignedInt var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSheetView.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSheetView newInstance() {
            return (CTSheetView)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSheetView newInstance(XmlOptions xmlOptions) {
            return (CTSheetView)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSheetView parse(String string) throws XmlException {
            return (CTSheetView)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSheetView parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetView)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSheetView parse(File file) throws XmlException, IOException {
            return (CTSheetView)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSheetView parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetView)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSheetView parse(URL uRL) throws XmlException, IOException {
            return (CTSheetView)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSheetView parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetView)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSheetView parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSheetView)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSheetView parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetView)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSheetView parse(Reader reader) throws XmlException, IOException {
            return (CTSheetView)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSheetView parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSheetView)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSheetView parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSheetView)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSheetView parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetView)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSheetView parse(Node node) throws XmlException {
            return (CTSheetView)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSheetView parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSheetView)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSheetView parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSheetView)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSheetView parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSheetView)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

