/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellWatches
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControls
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomProperties
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataConsolidate
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTScenarios
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTags
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems
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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellWatches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControls;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomProperties;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataConsolidate;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidations;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlinks;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredErrors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTScenarios;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTags;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableParts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems;
import org.w3c.dom.Node;

public interface CTWorksheet
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTWorksheet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctworksheet530dtype");

    public CTSheetPr getSheetPr();

    public boolean isSetSheetPr();

    public void setSheetPr(CTSheetPr var1);

    public CTSheetPr addNewSheetPr();

    public void unsetSheetPr();

    public CTSheetDimension getDimension();

    public boolean isSetDimension();

    public void setDimension(CTSheetDimension var1);

    public CTSheetDimension addNewDimension();

    public void unsetDimension();

    public CTSheetViews getSheetViews();

    public boolean isSetSheetViews();

    public void setSheetViews(CTSheetViews var1);

    public CTSheetViews addNewSheetViews();

    public void unsetSheetViews();

    public CTSheetFormatPr getSheetFormatPr();

    public boolean isSetSheetFormatPr();

    public void setSheetFormatPr(CTSheetFormatPr var1);

    public CTSheetFormatPr addNewSheetFormatPr();

    public void unsetSheetFormatPr();

    public List<CTCols> getColsList();

    @Deprecated
    public CTCols[] getColsArray();

    public CTCols getColsArray(int var1);

    public int sizeOfColsArray();

    public void setColsArray(CTCols[] var1);

    public void setColsArray(int var1, CTCols var2);

    public CTCols insertNewCols(int var1);

    public CTCols addNewCols();

    public void removeCols(int var1);

    public CTSheetData getSheetData();

    public void setSheetData(CTSheetData var1);

    public CTSheetData addNewSheetData();

    public CTSheetCalcPr getSheetCalcPr();

    public boolean isSetSheetCalcPr();

    public void setSheetCalcPr(CTSheetCalcPr var1);

    public CTSheetCalcPr addNewSheetCalcPr();

    public void unsetSheetCalcPr();

    public CTSheetProtection getSheetProtection();

    public boolean isSetSheetProtection();

    public void setSheetProtection(CTSheetProtection var1);

    public CTSheetProtection addNewSheetProtection();

    public void unsetSheetProtection();

    public CTProtectedRanges getProtectedRanges();

    public boolean isSetProtectedRanges();

    public void setProtectedRanges(CTProtectedRanges var1);

    public CTProtectedRanges addNewProtectedRanges();

    public void unsetProtectedRanges();

    public CTScenarios getScenarios();

    public boolean isSetScenarios();

    public void setScenarios(CTScenarios var1);

    public CTScenarios addNewScenarios();

    public void unsetScenarios();

    public CTAutoFilter getAutoFilter();

    public boolean isSetAutoFilter();

    public void setAutoFilter(CTAutoFilter var1);

    public CTAutoFilter addNewAutoFilter();

    public void unsetAutoFilter();

    public CTSortState getSortState();

    public boolean isSetSortState();

    public void setSortState(CTSortState var1);

    public CTSortState addNewSortState();

    public void unsetSortState();

    public CTDataConsolidate getDataConsolidate();

    public boolean isSetDataConsolidate();

    public void setDataConsolidate(CTDataConsolidate var1);

    public CTDataConsolidate addNewDataConsolidate();

    public void unsetDataConsolidate();

    public CTCustomSheetViews getCustomSheetViews();

    public boolean isSetCustomSheetViews();

    public void setCustomSheetViews(CTCustomSheetViews var1);

    public CTCustomSheetViews addNewCustomSheetViews();

    public void unsetCustomSheetViews();

    public CTMergeCells getMergeCells();

    public boolean isSetMergeCells();

    public void setMergeCells(CTMergeCells var1);

    public CTMergeCells addNewMergeCells();

    public void unsetMergeCells();

    public CTPhoneticPr getPhoneticPr();

    public boolean isSetPhoneticPr();

    public void setPhoneticPr(CTPhoneticPr var1);

    public CTPhoneticPr addNewPhoneticPr();

    public void unsetPhoneticPr();

    public List<CTConditionalFormatting> getConditionalFormattingList();

    @Deprecated
    public CTConditionalFormatting[] getConditionalFormattingArray();

    public CTConditionalFormatting getConditionalFormattingArray(int var1);

    public int sizeOfConditionalFormattingArray();

    public void setConditionalFormattingArray(CTConditionalFormatting[] var1);

    public void setConditionalFormattingArray(int var1, CTConditionalFormatting var2);

    public CTConditionalFormatting insertNewConditionalFormatting(int var1);

    public CTConditionalFormatting addNewConditionalFormatting();

    public void removeConditionalFormatting(int var1);

    public CTDataValidations getDataValidations();

    public boolean isSetDataValidations();

    public void setDataValidations(CTDataValidations var1);

    public CTDataValidations addNewDataValidations();

    public void unsetDataValidations();

    public CTHyperlinks getHyperlinks();

    public boolean isSetHyperlinks();

    public void setHyperlinks(CTHyperlinks var1);

    public CTHyperlinks addNewHyperlinks();

    public void unsetHyperlinks();

    public CTPrintOptions getPrintOptions();

    public boolean isSetPrintOptions();

    public void setPrintOptions(CTPrintOptions var1);

    public CTPrintOptions addNewPrintOptions();

    public void unsetPrintOptions();

    public CTPageMargins getPageMargins();

    public boolean isSetPageMargins();

    public void setPageMargins(CTPageMargins var1);

    public CTPageMargins addNewPageMargins();

    public void unsetPageMargins();

    public CTPageSetup getPageSetup();

    public boolean isSetPageSetup();

    public void setPageSetup(CTPageSetup var1);

    public CTPageSetup addNewPageSetup();

    public void unsetPageSetup();

    public CTHeaderFooter getHeaderFooter();

    public boolean isSetHeaderFooter();

    public void setHeaderFooter(CTHeaderFooter var1);

    public CTHeaderFooter addNewHeaderFooter();

    public void unsetHeaderFooter();

    public CTPageBreak getRowBreaks();

    public boolean isSetRowBreaks();

    public void setRowBreaks(CTPageBreak var1);

    public CTPageBreak addNewRowBreaks();

    public void unsetRowBreaks();

    public CTPageBreak getColBreaks();

    public boolean isSetColBreaks();

    public void setColBreaks(CTPageBreak var1);

    public CTPageBreak addNewColBreaks();

    public void unsetColBreaks();

    public CTCustomProperties getCustomProperties();

    public boolean isSetCustomProperties();

    public void setCustomProperties(CTCustomProperties var1);

    public CTCustomProperties addNewCustomProperties();

    public void unsetCustomProperties();

    public CTCellWatches getCellWatches();

    public boolean isSetCellWatches();

    public void setCellWatches(CTCellWatches var1);

    public CTCellWatches addNewCellWatches();

    public void unsetCellWatches();

    public CTIgnoredErrors getIgnoredErrors();

    public boolean isSetIgnoredErrors();

    public void setIgnoredErrors(CTIgnoredErrors var1);

    public CTIgnoredErrors addNewIgnoredErrors();

    public void unsetIgnoredErrors();

    public CTSmartTags getSmartTags();

    public boolean isSetSmartTags();

    public void setSmartTags(CTSmartTags var1);

    public CTSmartTags addNewSmartTags();

    public void unsetSmartTags();

    public CTDrawing getDrawing();

    public boolean isSetDrawing();

    public void setDrawing(CTDrawing var1);

    public CTDrawing addNewDrawing();

    public void unsetDrawing();

    public CTLegacyDrawing getLegacyDrawing();

    public boolean isSetLegacyDrawing();

    public void setLegacyDrawing(CTLegacyDrawing var1);

    public CTLegacyDrawing addNewLegacyDrawing();

    public void unsetLegacyDrawing();

    public CTLegacyDrawing getLegacyDrawingHF();

    public boolean isSetLegacyDrawingHF();

    public void setLegacyDrawingHF(CTLegacyDrawing var1);

    public CTLegacyDrawing addNewLegacyDrawingHF();

    public void unsetLegacyDrawingHF();

    public CTSheetBackgroundPicture getPicture();

    public boolean isSetPicture();

    public void setPicture(CTSheetBackgroundPicture var1);

    public CTSheetBackgroundPicture addNewPicture();

    public void unsetPicture();

    public CTOleObjects getOleObjects();

    public boolean isSetOleObjects();

    public void setOleObjects(CTOleObjects var1);

    public CTOleObjects addNewOleObjects();

    public void unsetOleObjects();

    public CTControls getControls();

    public boolean isSetControls();

    public void setControls(CTControls var1);

    public CTControls addNewControls();

    public void unsetControls();

    public CTWebPublishItems getWebPublishItems();

    public boolean isSetWebPublishItems();

    public void setWebPublishItems(CTWebPublishItems var1);

    public CTWebPublishItems addNewWebPublishItems();

    public void unsetWebPublishItems();

    public CTTableParts getTableParts();

    public boolean isSetTableParts();

    public void setTableParts(CTTableParts var1);

    public CTTableParts addNewTableParts();

    public void unsetTableParts();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTWorksheet.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTWorksheet newInstance() {
            return (CTWorksheet)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTWorksheet newInstance(XmlOptions xmlOptions) {
            return (CTWorksheet)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTWorksheet parse(String string) throws XmlException {
            return (CTWorksheet)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTWorksheet parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTWorksheet)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTWorksheet parse(File file) throws XmlException, IOException {
            return (CTWorksheet)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTWorksheet parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorksheet)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTWorksheet parse(URL uRL) throws XmlException, IOException {
            return (CTWorksheet)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTWorksheet parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorksheet)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTWorksheet parse(InputStream inputStream) throws XmlException, IOException {
            return (CTWorksheet)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTWorksheet parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorksheet)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTWorksheet parse(Reader reader) throws XmlException, IOException {
            return (CTWorksheet)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTWorksheet parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTWorksheet)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTWorksheet parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTWorksheet)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTWorksheet parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTWorksheet)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTWorksheet parse(Node node) throws XmlException {
            return (CTWorksheet)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTWorksheet parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTWorksheet)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTWorksheet parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTWorksheet)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTWorksheet parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTWorksheet)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

