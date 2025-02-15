/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartFormats
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColHierarchiesUsage
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColItems
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormats
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFormats
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFilters
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotHierarchies
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowHierarchiesUsage
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowItems
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
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartFormats;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColHierarchiesUsage;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormats;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFormats;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLocation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFilters;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotHierarchies;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowHierarchiesUsage;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;
import org.w3c.dom.Node;

public interface CTPivotTableDefinition
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPivotTableDefinition.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpivottabledefinitionb188type");

    public CTLocation getLocation();

    public void setLocation(CTLocation var1);

    public CTLocation addNewLocation();

    public CTPivotFields getPivotFields();

    public boolean isSetPivotFields();

    public void setPivotFields(CTPivotFields var1);

    public CTPivotFields addNewPivotFields();

    public void unsetPivotFields();

    public CTRowFields getRowFields();

    public boolean isSetRowFields();

    public void setRowFields(CTRowFields var1);

    public CTRowFields addNewRowFields();

    public void unsetRowFields();

    public CTRowItems getRowItems();

    public boolean isSetRowItems();

    public void setRowItems(CTRowItems var1);

    public CTRowItems addNewRowItems();

    public void unsetRowItems();

    public CTColFields getColFields();

    public boolean isSetColFields();

    public void setColFields(CTColFields var1);

    public CTColFields addNewColFields();

    public void unsetColFields();

    public CTColItems getColItems();

    public boolean isSetColItems();

    public void setColItems(CTColItems var1);

    public CTColItems addNewColItems();

    public void unsetColItems();

    public CTPageFields getPageFields();

    public boolean isSetPageFields();

    public void setPageFields(CTPageFields var1);

    public CTPageFields addNewPageFields();

    public void unsetPageFields();

    public CTDataFields getDataFields();

    public boolean isSetDataFields();

    public void setDataFields(CTDataFields var1);

    public CTDataFields addNewDataFields();

    public void unsetDataFields();

    public CTFormats getFormats();

    public boolean isSetFormats();

    public void setFormats(CTFormats var1);

    public CTFormats addNewFormats();

    public void unsetFormats();

    public CTConditionalFormats getConditionalFormats();

    public boolean isSetConditionalFormats();

    public void setConditionalFormats(CTConditionalFormats var1);

    public CTConditionalFormats addNewConditionalFormats();

    public void unsetConditionalFormats();

    public CTChartFormats getChartFormats();

    public boolean isSetChartFormats();

    public void setChartFormats(CTChartFormats var1);

    public CTChartFormats addNewChartFormats();

    public void unsetChartFormats();

    public CTPivotHierarchies getPivotHierarchies();

    public boolean isSetPivotHierarchies();

    public void setPivotHierarchies(CTPivotHierarchies var1);

    public CTPivotHierarchies addNewPivotHierarchies();

    public void unsetPivotHierarchies();

    public CTPivotTableStyle getPivotTableStyleInfo();

    public boolean isSetPivotTableStyleInfo();

    public void setPivotTableStyleInfo(CTPivotTableStyle var1);

    public CTPivotTableStyle addNewPivotTableStyleInfo();

    public void unsetPivotTableStyleInfo();

    public CTPivotFilters getFilters();

    public boolean isSetFilters();

    public void setFilters(CTPivotFilters var1);

    public CTPivotFilters addNewFilters();

    public void unsetFilters();

    public CTRowHierarchiesUsage getRowHierarchiesUsage();

    public boolean isSetRowHierarchiesUsage();

    public void setRowHierarchiesUsage(CTRowHierarchiesUsage var1);

    public CTRowHierarchiesUsage addNewRowHierarchiesUsage();

    public void unsetRowHierarchiesUsage();

    public CTColHierarchiesUsage getColHierarchiesUsage();

    public boolean isSetColHierarchiesUsage();

    public void setColHierarchiesUsage(CTColHierarchiesUsage var1);

    public CTColHierarchiesUsage addNewColHierarchiesUsage();

    public void unsetColHierarchiesUsage();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public String getName();

    public STXstring xgetName();

    public void setName(String var1);

    public void xsetName(STXstring var1);

    public long getCacheId();

    public XmlUnsignedInt xgetCacheId();

    public void setCacheId(long var1);

    public void xsetCacheId(XmlUnsignedInt var1);

    public boolean getDataOnRows();

    public XmlBoolean xgetDataOnRows();

    public boolean isSetDataOnRows();

    public void setDataOnRows(boolean var1);

    public void xsetDataOnRows(XmlBoolean var1);

    public void unsetDataOnRows();

    public long getDataPosition();

    public XmlUnsignedInt xgetDataPosition();

    public boolean isSetDataPosition();

    public void setDataPosition(long var1);

    public void xsetDataPosition(XmlUnsignedInt var1);

    public void unsetDataPosition();

    public long getAutoFormatId();

    public XmlUnsignedInt xgetAutoFormatId();

    public boolean isSetAutoFormatId();

    public void setAutoFormatId(long var1);

    public void xsetAutoFormatId(XmlUnsignedInt var1);

    public void unsetAutoFormatId();

    public boolean getApplyNumberFormats();

    public XmlBoolean xgetApplyNumberFormats();

    public boolean isSetApplyNumberFormats();

    public void setApplyNumberFormats(boolean var1);

    public void xsetApplyNumberFormats(XmlBoolean var1);

    public void unsetApplyNumberFormats();

    public boolean getApplyBorderFormats();

    public XmlBoolean xgetApplyBorderFormats();

    public boolean isSetApplyBorderFormats();

    public void setApplyBorderFormats(boolean var1);

    public void xsetApplyBorderFormats(XmlBoolean var1);

    public void unsetApplyBorderFormats();

    public boolean getApplyFontFormats();

    public XmlBoolean xgetApplyFontFormats();

    public boolean isSetApplyFontFormats();

    public void setApplyFontFormats(boolean var1);

    public void xsetApplyFontFormats(XmlBoolean var1);

    public void unsetApplyFontFormats();

    public boolean getApplyPatternFormats();

    public XmlBoolean xgetApplyPatternFormats();

    public boolean isSetApplyPatternFormats();

    public void setApplyPatternFormats(boolean var1);

    public void xsetApplyPatternFormats(XmlBoolean var1);

    public void unsetApplyPatternFormats();

    public boolean getApplyAlignmentFormats();

    public XmlBoolean xgetApplyAlignmentFormats();

    public boolean isSetApplyAlignmentFormats();

    public void setApplyAlignmentFormats(boolean var1);

    public void xsetApplyAlignmentFormats(XmlBoolean var1);

    public void unsetApplyAlignmentFormats();

    public boolean getApplyWidthHeightFormats();

    public XmlBoolean xgetApplyWidthHeightFormats();

    public boolean isSetApplyWidthHeightFormats();

    public void setApplyWidthHeightFormats(boolean var1);

    public void xsetApplyWidthHeightFormats(XmlBoolean var1);

    public void unsetApplyWidthHeightFormats();

    public String getDataCaption();

    public STXstring xgetDataCaption();

    public void setDataCaption(String var1);

    public void xsetDataCaption(STXstring var1);

    public String getGrandTotalCaption();

    public STXstring xgetGrandTotalCaption();

    public boolean isSetGrandTotalCaption();

    public void setGrandTotalCaption(String var1);

    public void xsetGrandTotalCaption(STXstring var1);

    public void unsetGrandTotalCaption();

    public String getErrorCaption();

    public STXstring xgetErrorCaption();

    public boolean isSetErrorCaption();

    public void setErrorCaption(String var1);

    public void xsetErrorCaption(STXstring var1);

    public void unsetErrorCaption();

    public boolean getShowError();

    public XmlBoolean xgetShowError();

    public boolean isSetShowError();

    public void setShowError(boolean var1);

    public void xsetShowError(XmlBoolean var1);

    public void unsetShowError();

    public String getMissingCaption();

    public STXstring xgetMissingCaption();

    public boolean isSetMissingCaption();

    public void setMissingCaption(String var1);

    public void xsetMissingCaption(STXstring var1);

    public void unsetMissingCaption();

    public boolean getShowMissing();

    public XmlBoolean xgetShowMissing();

    public boolean isSetShowMissing();

    public void setShowMissing(boolean var1);

    public void xsetShowMissing(XmlBoolean var1);

    public void unsetShowMissing();

    public String getPageStyle();

    public STXstring xgetPageStyle();

    public boolean isSetPageStyle();

    public void setPageStyle(String var1);

    public void xsetPageStyle(STXstring var1);

    public void unsetPageStyle();

    public String getPivotTableStyle();

    public STXstring xgetPivotTableStyle();

    public boolean isSetPivotTableStyle();

    public void setPivotTableStyle(String var1);

    public void xsetPivotTableStyle(STXstring var1);

    public void unsetPivotTableStyle();

    public String getVacatedStyle();

    public STXstring xgetVacatedStyle();

    public boolean isSetVacatedStyle();

    public void setVacatedStyle(String var1);

    public void xsetVacatedStyle(STXstring var1);

    public void unsetVacatedStyle();

    public String getTag();

    public STXstring xgetTag();

    public boolean isSetTag();

    public void setTag(String var1);

    public void xsetTag(STXstring var1);

    public void unsetTag();

    public short getUpdatedVersion();

    public XmlUnsignedByte xgetUpdatedVersion();

    public boolean isSetUpdatedVersion();

    public void setUpdatedVersion(short var1);

    public void xsetUpdatedVersion(XmlUnsignedByte var1);

    public void unsetUpdatedVersion();

    public short getMinRefreshableVersion();

    public XmlUnsignedByte xgetMinRefreshableVersion();

    public boolean isSetMinRefreshableVersion();

    public void setMinRefreshableVersion(short var1);

    public void xsetMinRefreshableVersion(XmlUnsignedByte var1);

    public void unsetMinRefreshableVersion();

    public boolean getAsteriskTotals();

    public XmlBoolean xgetAsteriskTotals();

    public boolean isSetAsteriskTotals();

    public void setAsteriskTotals(boolean var1);

    public void xsetAsteriskTotals(XmlBoolean var1);

    public void unsetAsteriskTotals();

    public boolean getShowItems();

    public XmlBoolean xgetShowItems();

    public boolean isSetShowItems();

    public void setShowItems(boolean var1);

    public void xsetShowItems(XmlBoolean var1);

    public void unsetShowItems();

    public boolean getEditData();

    public XmlBoolean xgetEditData();

    public boolean isSetEditData();

    public void setEditData(boolean var1);

    public void xsetEditData(XmlBoolean var1);

    public void unsetEditData();

    public boolean getDisableFieldList();

    public XmlBoolean xgetDisableFieldList();

    public boolean isSetDisableFieldList();

    public void setDisableFieldList(boolean var1);

    public void xsetDisableFieldList(XmlBoolean var1);

    public void unsetDisableFieldList();

    public boolean getShowCalcMbrs();

    public XmlBoolean xgetShowCalcMbrs();

    public boolean isSetShowCalcMbrs();

    public void setShowCalcMbrs(boolean var1);

    public void xsetShowCalcMbrs(XmlBoolean var1);

    public void unsetShowCalcMbrs();

    public boolean getVisualTotals();

    public XmlBoolean xgetVisualTotals();

    public boolean isSetVisualTotals();

    public void setVisualTotals(boolean var1);

    public void xsetVisualTotals(XmlBoolean var1);

    public void unsetVisualTotals();

    public boolean getShowMultipleLabel();

    public XmlBoolean xgetShowMultipleLabel();

    public boolean isSetShowMultipleLabel();

    public void setShowMultipleLabel(boolean var1);

    public void xsetShowMultipleLabel(XmlBoolean var1);

    public void unsetShowMultipleLabel();

    public boolean getShowDataDropDown();

    public XmlBoolean xgetShowDataDropDown();

    public boolean isSetShowDataDropDown();

    public void setShowDataDropDown(boolean var1);

    public void xsetShowDataDropDown(XmlBoolean var1);

    public void unsetShowDataDropDown();

    public boolean getShowDrill();

    public XmlBoolean xgetShowDrill();

    public boolean isSetShowDrill();

    public void setShowDrill(boolean var1);

    public void xsetShowDrill(XmlBoolean var1);

    public void unsetShowDrill();

    public boolean getPrintDrill();

    public XmlBoolean xgetPrintDrill();

    public boolean isSetPrintDrill();

    public void setPrintDrill(boolean var1);

    public void xsetPrintDrill(XmlBoolean var1);

    public void unsetPrintDrill();

    public boolean getShowMemberPropertyTips();

    public XmlBoolean xgetShowMemberPropertyTips();

    public boolean isSetShowMemberPropertyTips();

    public void setShowMemberPropertyTips(boolean var1);

    public void xsetShowMemberPropertyTips(XmlBoolean var1);

    public void unsetShowMemberPropertyTips();

    public boolean getShowDataTips();

    public XmlBoolean xgetShowDataTips();

    public boolean isSetShowDataTips();

    public void setShowDataTips(boolean var1);

    public void xsetShowDataTips(XmlBoolean var1);

    public void unsetShowDataTips();

    public boolean getEnableWizard();

    public XmlBoolean xgetEnableWizard();

    public boolean isSetEnableWizard();

    public void setEnableWizard(boolean var1);

    public void xsetEnableWizard(XmlBoolean var1);

    public void unsetEnableWizard();

    public boolean getEnableDrill();

    public XmlBoolean xgetEnableDrill();

    public boolean isSetEnableDrill();

    public void setEnableDrill(boolean var1);

    public void xsetEnableDrill(XmlBoolean var1);

    public void unsetEnableDrill();

    public boolean getEnableFieldProperties();

    public XmlBoolean xgetEnableFieldProperties();

    public boolean isSetEnableFieldProperties();

    public void setEnableFieldProperties(boolean var1);

    public void xsetEnableFieldProperties(XmlBoolean var1);

    public void unsetEnableFieldProperties();

    public boolean getPreserveFormatting();

    public XmlBoolean xgetPreserveFormatting();

    public boolean isSetPreserveFormatting();

    public void setPreserveFormatting(boolean var1);

    public void xsetPreserveFormatting(XmlBoolean var1);

    public void unsetPreserveFormatting();

    public boolean getUseAutoFormatting();

    public XmlBoolean xgetUseAutoFormatting();

    public boolean isSetUseAutoFormatting();

    public void setUseAutoFormatting(boolean var1);

    public void xsetUseAutoFormatting(XmlBoolean var1);

    public void unsetUseAutoFormatting();

    public long getPageWrap();

    public XmlUnsignedInt xgetPageWrap();

    public boolean isSetPageWrap();

    public void setPageWrap(long var1);

    public void xsetPageWrap(XmlUnsignedInt var1);

    public void unsetPageWrap();

    public boolean getPageOverThenDown();

    public XmlBoolean xgetPageOverThenDown();

    public boolean isSetPageOverThenDown();

    public void setPageOverThenDown(boolean var1);

    public void xsetPageOverThenDown(XmlBoolean var1);

    public void unsetPageOverThenDown();

    public boolean getSubtotalHiddenItems();

    public XmlBoolean xgetSubtotalHiddenItems();

    public boolean isSetSubtotalHiddenItems();

    public void setSubtotalHiddenItems(boolean var1);

    public void xsetSubtotalHiddenItems(XmlBoolean var1);

    public void unsetSubtotalHiddenItems();

    public boolean getRowGrandTotals();

    public XmlBoolean xgetRowGrandTotals();

    public boolean isSetRowGrandTotals();

    public void setRowGrandTotals(boolean var1);

    public void xsetRowGrandTotals(XmlBoolean var1);

    public void unsetRowGrandTotals();

    public boolean getColGrandTotals();

    public XmlBoolean xgetColGrandTotals();

    public boolean isSetColGrandTotals();

    public void setColGrandTotals(boolean var1);

    public void xsetColGrandTotals(XmlBoolean var1);

    public void unsetColGrandTotals();

    public boolean getFieldPrintTitles();

    public XmlBoolean xgetFieldPrintTitles();

    public boolean isSetFieldPrintTitles();

    public void setFieldPrintTitles(boolean var1);

    public void xsetFieldPrintTitles(XmlBoolean var1);

    public void unsetFieldPrintTitles();

    public boolean getItemPrintTitles();

    public XmlBoolean xgetItemPrintTitles();

    public boolean isSetItemPrintTitles();

    public void setItemPrintTitles(boolean var1);

    public void xsetItemPrintTitles(XmlBoolean var1);

    public void unsetItemPrintTitles();

    public boolean getMergeItem();

    public XmlBoolean xgetMergeItem();

    public boolean isSetMergeItem();

    public void setMergeItem(boolean var1);

    public void xsetMergeItem(XmlBoolean var1);

    public void unsetMergeItem();

    public boolean getShowDropZones();

    public XmlBoolean xgetShowDropZones();

    public boolean isSetShowDropZones();

    public void setShowDropZones(boolean var1);

    public void xsetShowDropZones(XmlBoolean var1);

    public void unsetShowDropZones();

    public short getCreatedVersion();

    public XmlUnsignedByte xgetCreatedVersion();

    public boolean isSetCreatedVersion();

    public void setCreatedVersion(short var1);

    public void xsetCreatedVersion(XmlUnsignedByte var1);

    public void unsetCreatedVersion();

    public long getIndent();

    public XmlUnsignedInt xgetIndent();

    public boolean isSetIndent();

    public void setIndent(long var1);

    public void xsetIndent(XmlUnsignedInt var1);

    public void unsetIndent();

    public boolean getShowEmptyRow();

    public XmlBoolean xgetShowEmptyRow();

    public boolean isSetShowEmptyRow();

    public void setShowEmptyRow(boolean var1);

    public void xsetShowEmptyRow(XmlBoolean var1);

    public void unsetShowEmptyRow();

    public boolean getShowEmptyCol();

    public XmlBoolean xgetShowEmptyCol();

    public boolean isSetShowEmptyCol();

    public void setShowEmptyCol(boolean var1);

    public void xsetShowEmptyCol(XmlBoolean var1);

    public void unsetShowEmptyCol();

    public boolean getShowHeaders();

    public XmlBoolean xgetShowHeaders();

    public boolean isSetShowHeaders();

    public void setShowHeaders(boolean var1);

    public void xsetShowHeaders(XmlBoolean var1);

    public void unsetShowHeaders();

    public boolean getCompact();

    public XmlBoolean xgetCompact();

    public boolean isSetCompact();

    public void setCompact(boolean var1);

    public void xsetCompact(XmlBoolean var1);

    public void unsetCompact();

    public boolean getOutline();

    public XmlBoolean xgetOutline();

    public boolean isSetOutline();

    public void setOutline(boolean var1);

    public void xsetOutline(XmlBoolean var1);

    public void unsetOutline();

    public boolean getOutlineData();

    public XmlBoolean xgetOutlineData();

    public boolean isSetOutlineData();

    public void setOutlineData(boolean var1);

    public void xsetOutlineData(XmlBoolean var1);

    public void unsetOutlineData();

    public boolean getCompactData();

    public XmlBoolean xgetCompactData();

    public boolean isSetCompactData();

    public void setCompactData(boolean var1);

    public void xsetCompactData(XmlBoolean var1);

    public void unsetCompactData();

    public boolean getPublished();

    public XmlBoolean xgetPublished();

    public boolean isSetPublished();

    public void setPublished(boolean var1);

    public void xsetPublished(XmlBoolean var1);

    public void unsetPublished();

    public boolean getGridDropZones();

    public XmlBoolean xgetGridDropZones();

    public boolean isSetGridDropZones();

    public void setGridDropZones(boolean var1);

    public void xsetGridDropZones(XmlBoolean var1);

    public void unsetGridDropZones();

    public boolean getImmersive();

    public XmlBoolean xgetImmersive();

    public boolean isSetImmersive();

    public void setImmersive(boolean var1);

    public void xsetImmersive(XmlBoolean var1);

    public void unsetImmersive();

    public boolean getMultipleFieldFilters();

    public XmlBoolean xgetMultipleFieldFilters();

    public boolean isSetMultipleFieldFilters();

    public void setMultipleFieldFilters(boolean var1);

    public void xsetMultipleFieldFilters(XmlBoolean var1);

    public void unsetMultipleFieldFilters();

    public long getChartFormat();

    public XmlUnsignedInt xgetChartFormat();

    public boolean isSetChartFormat();

    public void setChartFormat(long var1);

    public void xsetChartFormat(XmlUnsignedInt var1);

    public void unsetChartFormat();

    public String getRowHeaderCaption();

    public STXstring xgetRowHeaderCaption();

    public boolean isSetRowHeaderCaption();

    public void setRowHeaderCaption(String var1);

    public void xsetRowHeaderCaption(STXstring var1);

    public void unsetRowHeaderCaption();

    public String getColHeaderCaption();

    public STXstring xgetColHeaderCaption();

    public boolean isSetColHeaderCaption();

    public void setColHeaderCaption(String var1);

    public void xsetColHeaderCaption(STXstring var1);

    public void unsetColHeaderCaption();

    public boolean getFieldListSortAscending();

    public XmlBoolean xgetFieldListSortAscending();

    public boolean isSetFieldListSortAscending();

    public void setFieldListSortAscending(boolean var1);

    public void xsetFieldListSortAscending(XmlBoolean var1);

    public void unsetFieldListSortAscending();

    public boolean getMdxSubqueries();

    public XmlBoolean xgetMdxSubqueries();

    public boolean isSetMdxSubqueries();

    public void setMdxSubqueries(boolean var1);

    public void xsetMdxSubqueries(XmlBoolean var1);

    public void unsetMdxSubqueries();

    public boolean getCustomListSort();

    public XmlBoolean xgetCustomListSort();

    public boolean isSetCustomListSort();

    public void setCustomListSort(boolean var1);

    public void xsetCustomListSort(XmlBoolean var1);

    public void unsetCustomListSort();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPivotTableDefinition.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPivotTableDefinition newInstance() {
            return (CTPivotTableDefinition)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPivotTableDefinition newInstance(XmlOptions xmlOptions) {
            return (CTPivotTableDefinition)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPivotTableDefinition parse(String string) throws XmlException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPivotTableDefinition parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPivotTableDefinition parse(File file) throws XmlException, IOException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPivotTableDefinition parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPivotTableDefinition parse(URL uRL) throws XmlException, IOException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPivotTableDefinition parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPivotTableDefinition parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPivotTableDefinition parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPivotTableDefinition parse(Reader reader) throws XmlException, IOException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPivotTableDefinition parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPivotTableDefinition parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPivotTableDefinition parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPivotTableDefinition parse(Node node) throws XmlException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPivotTableDefinition parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPivotTableDefinition parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPivotTableDefinition parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPivotTableDefinition)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

