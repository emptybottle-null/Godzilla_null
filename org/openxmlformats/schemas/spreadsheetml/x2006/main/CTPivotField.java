/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoSortScope
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.STFieldSortType
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoSortScope;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFieldSortType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STNumFmtId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTPivotField
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPivotField.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpivotfieldf961type");

    public CTItems getItems();

    public boolean isSetItems();

    public void setItems(CTItems var1);

    public CTItems addNewItems();

    public void unsetItems();

    public CTAutoSortScope getAutoSortScope();

    public boolean isSetAutoSortScope();

    public void setAutoSortScope(CTAutoSortScope var1);

    public CTAutoSortScope addNewAutoSortScope();

    public void unsetAutoSortScope();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getName();

    public STXstring xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(STXstring var1);

    public void unsetName();

    public STAxis.Enum getAxis();

    public STAxis xgetAxis();

    public boolean isSetAxis();

    public void setAxis(STAxis.Enum var1);

    public void xsetAxis(STAxis var1);

    public void unsetAxis();

    public boolean getDataField();

    public XmlBoolean xgetDataField();

    public boolean isSetDataField();

    public void setDataField(boolean var1);

    public void xsetDataField(XmlBoolean var1);

    public void unsetDataField();

    public String getSubtotalCaption();

    public STXstring xgetSubtotalCaption();

    public boolean isSetSubtotalCaption();

    public void setSubtotalCaption(String var1);

    public void xsetSubtotalCaption(STXstring var1);

    public void unsetSubtotalCaption();

    public boolean getShowDropDowns();

    public XmlBoolean xgetShowDropDowns();

    public boolean isSetShowDropDowns();

    public void setShowDropDowns(boolean var1);

    public void xsetShowDropDowns(XmlBoolean var1);

    public void unsetShowDropDowns();

    public boolean getHiddenLevel();

    public XmlBoolean xgetHiddenLevel();

    public boolean isSetHiddenLevel();

    public void setHiddenLevel(boolean var1);

    public void xsetHiddenLevel(XmlBoolean var1);

    public void unsetHiddenLevel();

    public String getUniqueMemberProperty();

    public STXstring xgetUniqueMemberProperty();

    public boolean isSetUniqueMemberProperty();

    public void setUniqueMemberProperty(String var1);

    public void xsetUniqueMemberProperty(STXstring var1);

    public void unsetUniqueMemberProperty();

    public boolean getCompact();

    public XmlBoolean xgetCompact();

    public boolean isSetCompact();

    public void setCompact(boolean var1);

    public void xsetCompact(XmlBoolean var1);

    public void unsetCompact();

    public boolean getAllDrilled();

    public XmlBoolean xgetAllDrilled();

    public boolean isSetAllDrilled();

    public void setAllDrilled(boolean var1);

    public void xsetAllDrilled(XmlBoolean var1);

    public void unsetAllDrilled();

    public long getNumFmtId();

    public STNumFmtId xgetNumFmtId();

    public boolean isSetNumFmtId();

    public void setNumFmtId(long var1);

    public void xsetNumFmtId(STNumFmtId var1);

    public void unsetNumFmtId();

    public boolean getOutline();

    public XmlBoolean xgetOutline();

    public boolean isSetOutline();

    public void setOutline(boolean var1);

    public void xsetOutline(XmlBoolean var1);

    public void unsetOutline();

    public boolean getSubtotalTop();

    public XmlBoolean xgetSubtotalTop();

    public boolean isSetSubtotalTop();

    public void setSubtotalTop(boolean var1);

    public void xsetSubtotalTop(XmlBoolean var1);

    public void unsetSubtotalTop();

    public boolean getDragToRow();

    public XmlBoolean xgetDragToRow();

    public boolean isSetDragToRow();

    public void setDragToRow(boolean var1);

    public void xsetDragToRow(XmlBoolean var1);

    public void unsetDragToRow();

    public boolean getDragToCol();

    public XmlBoolean xgetDragToCol();

    public boolean isSetDragToCol();

    public void setDragToCol(boolean var1);

    public void xsetDragToCol(XmlBoolean var1);

    public void unsetDragToCol();

    public boolean getMultipleItemSelectionAllowed();

    public XmlBoolean xgetMultipleItemSelectionAllowed();

    public boolean isSetMultipleItemSelectionAllowed();

    public void setMultipleItemSelectionAllowed(boolean var1);

    public void xsetMultipleItemSelectionAllowed(XmlBoolean var1);

    public void unsetMultipleItemSelectionAllowed();

    public boolean getDragToPage();

    public XmlBoolean xgetDragToPage();

    public boolean isSetDragToPage();

    public void setDragToPage(boolean var1);

    public void xsetDragToPage(XmlBoolean var1);

    public void unsetDragToPage();

    public boolean getDragToData();

    public XmlBoolean xgetDragToData();

    public boolean isSetDragToData();

    public void setDragToData(boolean var1);

    public void xsetDragToData(XmlBoolean var1);

    public void unsetDragToData();

    public boolean getDragOff();

    public XmlBoolean xgetDragOff();

    public boolean isSetDragOff();

    public void setDragOff(boolean var1);

    public void xsetDragOff(XmlBoolean var1);

    public void unsetDragOff();

    public boolean getShowAll();

    public XmlBoolean xgetShowAll();

    public boolean isSetShowAll();

    public void setShowAll(boolean var1);

    public void xsetShowAll(XmlBoolean var1);

    public void unsetShowAll();

    public boolean getInsertBlankRow();

    public XmlBoolean xgetInsertBlankRow();

    public boolean isSetInsertBlankRow();

    public void setInsertBlankRow(boolean var1);

    public void xsetInsertBlankRow(XmlBoolean var1);

    public void unsetInsertBlankRow();

    public boolean getServerField();

    public XmlBoolean xgetServerField();

    public boolean isSetServerField();

    public void setServerField(boolean var1);

    public void xsetServerField(XmlBoolean var1);

    public void unsetServerField();

    public boolean getInsertPageBreak();

    public XmlBoolean xgetInsertPageBreak();

    public boolean isSetInsertPageBreak();

    public void setInsertPageBreak(boolean var1);

    public void xsetInsertPageBreak(XmlBoolean var1);

    public void unsetInsertPageBreak();

    public boolean getAutoShow();

    public XmlBoolean xgetAutoShow();

    public boolean isSetAutoShow();

    public void setAutoShow(boolean var1);

    public void xsetAutoShow(XmlBoolean var1);

    public void unsetAutoShow();

    public boolean getTopAutoShow();

    public XmlBoolean xgetTopAutoShow();

    public boolean isSetTopAutoShow();

    public void setTopAutoShow(boolean var1);

    public void xsetTopAutoShow(XmlBoolean var1);

    public void unsetTopAutoShow();

    public boolean getHideNewItems();

    public XmlBoolean xgetHideNewItems();

    public boolean isSetHideNewItems();

    public void setHideNewItems(boolean var1);

    public void xsetHideNewItems(XmlBoolean var1);

    public void unsetHideNewItems();

    public boolean getMeasureFilter();

    public XmlBoolean xgetMeasureFilter();

    public boolean isSetMeasureFilter();

    public void setMeasureFilter(boolean var1);

    public void xsetMeasureFilter(XmlBoolean var1);

    public void unsetMeasureFilter();

    public boolean getIncludeNewItemsInFilter();

    public XmlBoolean xgetIncludeNewItemsInFilter();

    public boolean isSetIncludeNewItemsInFilter();

    public void setIncludeNewItemsInFilter(boolean var1);

    public void xsetIncludeNewItemsInFilter(XmlBoolean var1);

    public void unsetIncludeNewItemsInFilter();

    public long getItemPageCount();

    public XmlUnsignedInt xgetItemPageCount();

    public boolean isSetItemPageCount();

    public void setItemPageCount(long var1);

    public void xsetItemPageCount(XmlUnsignedInt var1);

    public void unsetItemPageCount();

    public STFieldSortType.Enum getSortType();

    public STFieldSortType xgetSortType();

    public boolean isSetSortType();

    public void setSortType(STFieldSortType.Enum var1);

    public void xsetSortType(STFieldSortType var1);

    public void unsetSortType();

    public boolean getDataSourceSort();

    public XmlBoolean xgetDataSourceSort();

    public boolean isSetDataSourceSort();

    public void setDataSourceSort(boolean var1);

    public void xsetDataSourceSort(XmlBoolean var1);

    public void unsetDataSourceSort();

    public boolean getNonAutoSortDefault();

    public XmlBoolean xgetNonAutoSortDefault();

    public boolean isSetNonAutoSortDefault();

    public void setNonAutoSortDefault(boolean var1);

    public void xsetNonAutoSortDefault(XmlBoolean var1);

    public void unsetNonAutoSortDefault();

    public long getRankBy();

    public XmlUnsignedInt xgetRankBy();

    public boolean isSetRankBy();

    public void setRankBy(long var1);

    public void xsetRankBy(XmlUnsignedInt var1);

    public void unsetRankBy();

    public boolean getDefaultSubtotal();

    public XmlBoolean xgetDefaultSubtotal();

    public boolean isSetDefaultSubtotal();

    public void setDefaultSubtotal(boolean var1);

    public void xsetDefaultSubtotal(XmlBoolean var1);

    public void unsetDefaultSubtotal();

    public boolean getSumSubtotal();

    public XmlBoolean xgetSumSubtotal();

    public boolean isSetSumSubtotal();

    public void setSumSubtotal(boolean var1);

    public void xsetSumSubtotal(XmlBoolean var1);

    public void unsetSumSubtotal();

    public boolean getCountASubtotal();

    public XmlBoolean xgetCountASubtotal();

    public boolean isSetCountASubtotal();

    public void setCountASubtotal(boolean var1);

    public void xsetCountASubtotal(XmlBoolean var1);

    public void unsetCountASubtotal();

    public boolean getAvgSubtotal();

    public XmlBoolean xgetAvgSubtotal();

    public boolean isSetAvgSubtotal();

    public void setAvgSubtotal(boolean var1);

    public void xsetAvgSubtotal(XmlBoolean var1);

    public void unsetAvgSubtotal();

    public boolean getMaxSubtotal();

    public XmlBoolean xgetMaxSubtotal();

    public boolean isSetMaxSubtotal();

    public void setMaxSubtotal(boolean var1);

    public void xsetMaxSubtotal(XmlBoolean var1);

    public void unsetMaxSubtotal();

    public boolean getMinSubtotal();

    public XmlBoolean xgetMinSubtotal();

    public boolean isSetMinSubtotal();

    public void setMinSubtotal(boolean var1);

    public void xsetMinSubtotal(XmlBoolean var1);

    public void unsetMinSubtotal();

    public boolean getProductSubtotal();

    public XmlBoolean xgetProductSubtotal();

    public boolean isSetProductSubtotal();

    public void setProductSubtotal(boolean var1);

    public void xsetProductSubtotal(XmlBoolean var1);

    public void unsetProductSubtotal();

    public boolean getCountSubtotal();

    public XmlBoolean xgetCountSubtotal();

    public boolean isSetCountSubtotal();

    public void setCountSubtotal(boolean var1);

    public void xsetCountSubtotal(XmlBoolean var1);

    public void unsetCountSubtotal();

    public boolean getStdDevSubtotal();

    public XmlBoolean xgetStdDevSubtotal();

    public boolean isSetStdDevSubtotal();

    public void setStdDevSubtotal(boolean var1);

    public void xsetStdDevSubtotal(XmlBoolean var1);

    public void unsetStdDevSubtotal();

    public boolean getStdDevPSubtotal();

    public XmlBoolean xgetStdDevPSubtotal();

    public boolean isSetStdDevPSubtotal();

    public void setStdDevPSubtotal(boolean var1);

    public void xsetStdDevPSubtotal(XmlBoolean var1);

    public void unsetStdDevPSubtotal();

    public boolean getVarSubtotal();

    public XmlBoolean xgetVarSubtotal();

    public boolean isSetVarSubtotal();

    public void setVarSubtotal(boolean var1);

    public void xsetVarSubtotal(XmlBoolean var1);

    public void unsetVarSubtotal();

    public boolean getVarPSubtotal();

    public XmlBoolean xgetVarPSubtotal();

    public boolean isSetVarPSubtotal();

    public void setVarPSubtotal(boolean var1);

    public void xsetVarPSubtotal(XmlBoolean var1);

    public void unsetVarPSubtotal();

    public boolean getShowPropCell();

    public XmlBoolean xgetShowPropCell();

    public boolean isSetShowPropCell();

    public void setShowPropCell(boolean var1);

    public void xsetShowPropCell(XmlBoolean var1);

    public void unsetShowPropCell();

    public boolean getShowPropTip();

    public XmlBoolean xgetShowPropTip();

    public boolean isSetShowPropTip();

    public void setShowPropTip(boolean var1);

    public void xsetShowPropTip(XmlBoolean var1);

    public void unsetShowPropTip();

    public boolean getShowPropAsCaption();

    public XmlBoolean xgetShowPropAsCaption();

    public boolean isSetShowPropAsCaption();

    public void setShowPropAsCaption(boolean var1);

    public void xsetShowPropAsCaption(XmlBoolean var1);

    public void unsetShowPropAsCaption();

    public boolean getDefaultAttributeDrillState();

    public XmlBoolean xgetDefaultAttributeDrillState();

    public boolean isSetDefaultAttributeDrillState();

    public void setDefaultAttributeDrillState(boolean var1);

    public void xsetDefaultAttributeDrillState(XmlBoolean var1);

    public void unsetDefaultAttributeDrillState();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPivotField.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPivotField newInstance() {
            return (CTPivotField)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPivotField newInstance(XmlOptions xmlOptions) {
            return (CTPivotField)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPivotField parse(String string) throws XmlException {
            return (CTPivotField)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPivotField parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotField)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPivotField parse(File file) throws XmlException, IOException {
            return (CTPivotField)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPivotField parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotField)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPivotField parse(URL uRL) throws XmlException, IOException {
            return (CTPivotField)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPivotField parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotField)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPivotField parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPivotField)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPivotField parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotField)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPivotField parse(Reader reader) throws XmlException, IOException {
            return (CTPivotField)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPivotField parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotField)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPivotField parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPivotField)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPivotField parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotField)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPivotField parse(Node node) throws XmlException {
            return (CTPivotField)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPivotField parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotField)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPivotField parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPivotField)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPivotField parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPivotField)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

