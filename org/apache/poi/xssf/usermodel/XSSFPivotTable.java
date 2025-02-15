/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.XSSFPivotCache;
import org.apache.poi.xssf.usermodel.XSSFPivotCacheDefinition;
import org.apache.poi.xssf.usermodel.XSSFPivotCacheRecords;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCacheSource;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLocation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotCacheDefinition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotField;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableDefinition;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRowFields;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataConsolidateFunction;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STItemType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;

public class XSSFPivotTable
extends POIXMLDocumentPart {
    protected static final short CREATED_VERSION = 3;
    protected static final short MIN_REFRESHABLE_VERSION = 3;
    protected static final short UPDATED_VERSION = 3;
    private CTPivotTableDefinition pivotTableDefinition;
    private XSSFPivotCacheDefinition pivotCacheDefinition;
    private XSSFPivotCache pivotCache;
    private XSSFPivotCacheRecords pivotCacheRecords;
    private Sheet parentSheet;
    private Sheet dataSheet;

    protected XSSFPivotTable() {
        this.pivotTableDefinition = CTPivotTableDefinition.Factory.newInstance();
        this.pivotCache = new XSSFPivotCache();
        this.pivotCacheDefinition = new XSSFPivotCacheDefinition();
        this.pivotCacheRecords = new XSSFPivotCacheRecords();
    }

    protected XSSFPivotTable(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            XmlOptions options = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            options.setLoadReplaceDocumentElement(null);
            this.pivotTableDefinition = CTPivotTableDefinition.Factory.parse(is, options);
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public void setPivotCache(XSSFPivotCache pivotCache) {
        this.pivotCache = pivotCache;
    }

    public XSSFPivotCache getPivotCache() {
        return this.pivotCache;
    }

    public Sheet getParentSheet() {
        return this.parentSheet;
    }

    public void setParentSheet(XSSFSheet parentSheet) {
        this.parentSheet = parentSheet;
    }

    @Internal
    public CTPivotTableDefinition getCTPivotTableDefinition() {
        return this.pivotTableDefinition;
    }

    @Internal
    public void setCTPivotTableDefinition(CTPivotTableDefinition pivotTableDefinition) {
        this.pivotTableDefinition = pivotTableDefinition;
    }

    public XSSFPivotCacheDefinition getPivotCacheDefinition() {
        return this.pivotCacheDefinition;
    }

    public void setPivotCacheDefinition(XSSFPivotCacheDefinition pivotCacheDefinition) {
        this.pivotCacheDefinition = pivotCacheDefinition;
    }

    public XSSFPivotCacheRecords getPivotCacheRecords() {
        return this.pivotCacheRecords;
    }

    public void setPivotCacheRecords(XSSFPivotCacheRecords pivotCacheRecords) {
        this.pivotCacheRecords = pivotCacheRecords;
    }

    public Sheet getDataSheet() {
        return this.dataSheet;
    }

    private void setDataSheet(Sheet dataSheet) {
        this.dataSheet = dataSheet;
    }

    @Override
    protected void commit() throws IOException {
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTPivotTableDefinition.type.getName().getNamespaceURI(), "pivotTableDefinition"));
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.pivotTableDefinition.save(out, xmlOptions);
        out.close();
    }

    protected void setDefaultPivotTableDefinition() {
        this.pivotTableDefinition.setMultipleFieldFilters(false);
        this.pivotTableDefinition.setIndent(0L);
        this.pivotTableDefinition.setCreatedVersion((short)3);
        this.pivotTableDefinition.setMinRefreshableVersion((short)3);
        this.pivotTableDefinition.setUpdatedVersion((short)3);
        this.pivotTableDefinition.setItemPrintTitles(true);
        this.pivotTableDefinition.setUseAutoFormatting(true);
        this.pivotTableDefinition.setApplyNumberFormats(false);
        this.pivotTableDefinition.setApplyWidthHeightFormats(true);
        this.pivotTableDefinition.setApplyAlignmentFormats(false);
        this.pivotTableDefinition.setApplyPatternFormats(false);
        this.pivotTableDefinition.setApplyFontFormats(false);
        this.pivotTableDefinition.setApplyBorderFormats(false);
        this.pivotTableDefinition.setCacheId(this.pivotCache.getCTPivotCache().getCacheId());
        this.pivotTableDefinition.setName("PivotTable" + this.pivotTableDefinition.getCacheId());
        this.pivotTableDefinition.setDataCaption("Values");
        CTPivotTableStyle style = this.pivotTableDefinition.addNewPivotTableStyleInfo();
        style.setName("PivotStyleLight16");
        style.setShowLastColumn(true);
        style.setShowColStripes(false);
        style.setShowRowStripes(false);
        style.setShowColHeaders(true);
        style.setShowRowHeaders(true);
    }

    protected AreaReference getPivotArea() {
        Workbook wb = this.getDataSheet().getWorkbook();
        return this.getPivotCacheDefinition().getPivotArea(wb);
    }

    private void checkColumnIndex(int columnIndex) throws IndexOutOfBoundsException {
        AreaReference pivotArea = this.getPivotArea();
        int size = pivotArea.getLastCell().getCol() - pivotArea.getFirstCell().getCol() + 1;
        if (columnIndex < 0 || columnIndex >= size) {
            throw new IndexOutOfBoundsException("Column Index: " + columnIndex + ", Size: " + size);
        }
    }

    public void addRowLabel(int columnIndex) {
        this.checkColumnIndex(columnIndex);
        AreaReference pivotArea = this.getPivotArea();
        int lastRowIndex = pivotArea.getLastCell().getRow() - pivotArea.getFirstCell().getRow();
        CTPivotFields pivotFields = this.pivotTableDefinition.getPivotFields();
        CTPivotField pivotField = CTPivotField.Factory.newInstance();
        CTItems items = pivotField.addNewItems();
        pivotField.setAxis(STAxis.AXIS_ROW);
        pivotField.setShowAll(false);
        for (int i = 0; i <= lastRowIndex; ++i) {
            items.addNewItem().setT(STItemType.DEFAULT);
        }
        items.setCount(items.sizeOfItemArray());
        pivotFields.setPivotFieldArray(columnIndex, pivotField);
        CTRowFields rowFields = this.pivotTableDefinition.getRowFields() != null ? this.pivotTableDefinition.getRowFields() : this.pivotTableDefinition.addNewRowFields();
        rowFields.addNewField().setX(columnIndex);
        rowFields.setCount(rowFields.sizeOfFieldArray());
    }

    public List<Integer> getRowLabelColumns() {
        if (this.pivotTableDefinition.getRowFields() != null) {
            ArrayList<Integer> columnIndexes = new ArrayList<Integer>();
            for (CTField f : this.pivotTableDefinition.getRowFields().getFieldArray()) {
                columnIndexes.add(f.getX());
            }
            return columnIndexes;
        }
        return Collections.emptyList();
    }

    public void addColLabel(int columnIndex, String valueFormat) {
        this.checkColumnIndex(columnIndex);
        AreaReference pivotArea = this.getPivotArea();
        int lastRowIndex = pivotArea.getLastCell().getRow() - pivotArea.getFirstCell().getRow();
        CTPivotFields pivotFields = this.pivotTableDefinition.getPivotFields();
        CTPivotField pivotField = CTPivotField.Factory.newInstance();
        CTItems items = pivotField.addNewItems();
        pivotField.setAxis(STAxis.AXIS_COL);
        pivotField.setShowAll(false);
        if (valueFormat != null && !valueFormat.trim().isEmpty()) {
            DataFormat df = this.parentSheet.getWorkbook().createDataFormat();
            pivotField.setNumFmtId(df.getFormat(valueFormat));
        }
        for (int i = 0; i <= lastRowIndex; ++i) {
            items.addNewItem().setT(STItemType.DEFAULT);
        }
        items.setCount(items.sizeOfItemArray());
        pivotFields.setPivotFieldArray(columnIndex, pivotField);
        CTColFields colFields = this.pivotTableDefinition.getColFields() != null ? this.pivotTableDefinition.getColFields() : this.pivotTableDefinition.addNewColFields();
        colFields.addNewField().setX(columnIndex);
        colFields.setCount(colFields.sizeOfFieldArray());
    }

    public void addColLabel(int columnIndex) {
        this.addColLabel(columnIndex, null);
    }

    public List<Integer> getColLabelColumns() {
        if (this.pivotTableDefinition.getColFields() != null) {
            ArrayList<Integer> columnIndexes = new ArrayList<Integer>();
            for (CTField f : this.pivotTableDefinition.getColFields().getFieldArray()) {
                columnIndexes.add(f.getX());
            }
            return columnIndexes;
        }
        return Collections.emptyList();
    }

    public void addColumnLabel(DataConsolidateFunction function, int columnIndex, String valueFieldName, String valueFormat) {
        this.checkColumnIndex(columnIndex);
        this.addDataColumn(columnIndex, true);
        this.addDataField(function, columnIndex, valueFieldName, valueFormat);
        if (this.pivotTableDefinition.getDataFields().getCount() == 2L) {
            CTColFields colFields = this.pivotTableDefinition.getColFields() != null ? this.pivotTableDefinition.getColFields() : this.pivotTableDefinition.addNewColFields();
            colFields.addNewField().setX(-2);
            colFields.setCount(colFields.sizeOfFieldArray());
        }
    }

    public void addColumnLabel(DataConsolidateFunction function, int columnIndex, String valueFieldName) {
        this.addColumnLabel(function, columnIndex, valueFieldName, null);
    }

    public void addColumnLabel(DataConsolidateFunction function, int columnIndex) {
        this.addColumnLabel(function, columnIndex, function.getName(), null);
    }

    private void addDataField(DataConsolidateFunction function, int columnIndex, String valueFieldName, String valueFormat) {
        this.checkColumnIndex(columnIndex);
        CTDataFields dataFields = this.pivotTableDefinition.getDataFields() != null ? this.pivotTableDefinition.getDataFields() : this.pivotTableDefinition.addNewDataFields();
        CTDataField dataField = dataFields.addNewDataField();
        dataField.setSubtotal(STDataConsolidateFunction.Enum.forInt(function.getValue()));
        dataField.setName(valueFieldName);
        dataField.setFld(columnIndex);
        if (valueFormat != null && !valueFormat.trim().isEmpty()) {
            DataFormat df = this.parentSheet.getWorkbook().createDataFormat();
            dataField.setNumFmtId(df.getFormat(valueFormat));
        }
        dataFields.setCount(dataFields.sizeOfDataFieldArray());
    }

    public void addDataColumn(int columnIndex, boolean isDataField) {
        this.checkColumnIndex(columnIndex);
        CTPivotFields pivotFields = this.pivotTableDefinition.getPivotFields();
        CTPivotField pivotField = CTPivotField.Factory.newInstance();
        pivotField.setDataField(isDataField);
        pivotField.setShowAll(false);
        pivotFields.setPivotFieldArray(columnIndex, pivotField);
    }

    public void addReportFilter(int columnIndex) {
        CTPageFields pageFields;
        this.checkColumnIndex(columnIndex);
        AreaReference pivotArea = this.getPivotArea();
        int lastRowIndex = pivotArea.getLastCell().getRow() - pivotArea.getFirstCell().getRow();
        CTLocation location = this.pivotTableDefinition.getLocation();
        AreaReference destination = new AreaReference(location.getRef(), SpreadsheetVersion.EXCEL2007);
        if (destination.getFirstCell().getRow() < 2) {
            AreaReference newDestination = new AreaReference(new CellReference(2, destination.getFirstCell().getCol()), new CellReference(3, destination.getFirstCell().getCol() + 1), SpreadsheetVersion.EXCEL2007);
            location.setRef(newDestination.formatAsString());
        }
        CTPivotFields pivotFields = this.pivotTableDefinition.getPivotFields();
        CTPivotField pivotField = CTPivotField.Factory.newInstance();
        CTItems items = pivotField.addNewItems();
        pivotField.setAxis(STAxis.AXIS_PAGE);
        pivotField.setShowAll(false);
        for (int i = 0; i <= lastRowIndex; ++i) {
            items.addNewItem().setT(STItemType.DEFAULT);
        }
        items.setCount(items.sizeOfItemArray());
        pivotFields.setPivotFieldArray(columnIndex, pivotField);
        if (this.pivotTableDefinition.getPageFields() != null) {
            pageFields = this.pivotTableDefinition.getPageFields();
            this.pivotTableDefinition.setMultipleFieldFilters(true);
        } else {
            pageFields = this.pivotTableDefinition.addNewPageFields();
        }
        CTPageField pageField = pageFields.addNewPageField();
        pageField.setHier(-1);
        pageField.setFld(columnIndex);
        pageFields.setCount(pageFields.sizeOfPageFieldArray());
        this.pivotTableDefinition.getLocation().setColPageCount(pageFields.getCount());
    }

    protected void createSourceReferences(CellReference position, Sheet sourceSheet, PivotTableReferenceConfigurator refConfig) {
        CTLocation location;
        AreaReference destination = new AreaReference(position, new CellReference(position.getRow() + 1, position.getCol() + 1), SpreadsheetVersion.EXCEL2007);
        if (this.pivotTableDefinition.getLocation() == null) {
            location = this.pivotTableDefinition.addNewLocation();
            location.setFirstDataCol(1L);
            location.setFirstDataRow(1L);
            location.setFirstHeaderRow(1L);
        } else {
            location = this.pivotTableDefinition.getLocation();
        }
        location.setRef(destination.formatAsString());
        this.pivotTableDefinition.setLocation(location);
        CTPivotCacheDefinition cacheDef = this.getPivotCacheDefinition().getCTPivotCacheDefinition();
        CTCacheSource cacheSource = cacheDef.addNewCacheSource();
        cacheSource.setType(STSourceType.WORKSHEET);
        CTWorksheetSource worksheetSource = cacheSource.addNewWorksheetSource();
        worksheetSource.setSheet(sourceSheet.getSheetName());
        this.setDataSheet(sourceSheet);
        refConfig.configureReference(worksheetSource);
        if (worksheetSource.getName() == null && worksheetSource.getRef() == null) {
            throw new IllegalArgumentException("Pivot table source area reference or name must be specified.");
        }
    }

    protected void createDefaultDataColumns() {
        CTPivotFields pivotFields = this.pivotTableDefinition.getPivotFields() != null ? this.pivotTableDefinition.getPivotFields() : this.pivotTableDefinition.addNewPivotFields();
        AreaReference sourceArea = this.getPivotArea();
        int firstColumn = sourceArea.getFirstCell().getCol();
        short lastColumn = sourceArea.getLastCell().getCol();
        for (int i = firstColumn; i <= lastColumn; ++i) {
            CTPivotField pivotField = pivotFields.addNewPivotField();
            pivotField.setDataField(false);
            pivotField.setShowAll(false);
        }
        pivotFields.setCount(pivotFields.sizeOfPivotFieldArray());
    }

    protected static interface PivotTableReferenceConfigurator {
        public void configureReference(CTWorksheetSource var1);
    }
}

