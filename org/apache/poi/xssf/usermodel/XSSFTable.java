/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.usermodel.TableStyleInfo;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTableColumn;
import org.apache.poi.xssf.usermodel.XSSFTableStyleInfo;
import org.apache.poi.xssf.usermodel.helpers.XSSFXmlColumnPr;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumn;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableColumns;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.TableDocument;

public class XSSFTable
extends POIXMLDocumentPart
implements Table {
    private CTTable ctTable;
    private transient List<XSSFXmlColumnPr> xmlColumnPrs;
    private transient List<XSSFTableColumn> tableColumns;
    private transient HashMap<String, Integer> columnMap;
    private transient CellReference startCellReference;
    private transient CellReference endCellReference;
    private transient String commonXPath;
    private transient String name;
    private transient String styleName;

    public XSSFTable() {
        this.ctTable = CTTable.Factory.newInstance();
    }

    public XSSFTable(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            TableDocument doc = TableDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.ctTable = doc.getTable();
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    public XSSFSheet getXSSFSheet() {
        return (XSSFSheet)this.getParent();
    }

    public void writeTo(OutputStream out) throws IOException {
        this.updateHeaders();
        TableDocument doc = TableDocument.Factory.newInstance();
        doc.setTable(this.ctTable);
        doc.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.writeTo(out);
        out.close();
    }

    @Internal(since="POI 3.15 beta 3")
    public CTTable getCTTable() {
        return this.ctTable;
    }

    public boolean mapsTo(long id) {
        List<XSSFXmlColumnPr> pointers = this.getXmlColumnPrs();
        for (XSSFXmlColumnPr pointer : pointers) {
            if (pointer.getMapId() != id) continue;
            return true;
        }
        return false;
    }

    public String getCommonXpath() {
        if (this.commonXPath == null) {
            Object[] commonTokens = new String[]{};
            block0: for (XSSFTableColumn column : this.getColumns()) {
                if (column.getXmlColumnPr() == null) continue;
                String xpath = column.getXmlColumnPr().getXPath();
                String[] tokens = xpath.split("/");
                if (commonTokens.length == 0) {
                    commonTokens = tokens;
                    continue;
                }
                int maxLength = Math.min(commonTokens.length, tokens.length);
                for (int i = 0; i < maxLength; ++i) {
                    if (((String)commonTokens[i]).equals(tokens[i])) continue;
                    List<Object> subCommonTokens = Arrays.asList(commonTokens).subList(0, i);
                    String[] container = new String[]{};
                    commonTokens = subCommonTokens.toArray(container);
                    continue block0;
                }
            }
            commonTokens[0] = "";
            this.commonXPath = StringUtil.join(commonTokens, "/");
        }
        return this.commonXPath;
    }

    public List<XSSFTableColumn> getColumns() {
        if (this.tableColumns == null) {
            ArrayList<XSSFTableColumn> columns = new ArrayList<XSSFTableColumn>();
            CTTableColumns ctTableColumns = this.ctTable.getTableColumns();
            if (ctTableColumns != null) {
                for (CTTableColumn column : ctTableColumns.getTableColumnList()) {
                    XSSFTableColumn tableColumn = new XSSFTableColumn(this, column);
                    columns.add(tableColumn);
                }
            }
            this.tableColumns = Collections.unmodifiableList(columns);
        }
        return this.tableColumns;
    }

    @Deprecated
    @Removal(version="4.2.0")
    public List<XSSFXmlColumnPr> getXmlColumnPrs() {
        if (this.xmlColumnPrs == null) {
            this.xmlColumnPrs = new ArrayList<XSSFXmlColumnPr>();
            for (XSSFTableColumn column : this.getColumns()) {
                XSSFXmlColumnPr xmlColumnPr = column.getXmlColumnPr();
                if (xmlColumnPr == null) continue;
                this.xmlColumnPrs.add(xmlColumnPr);
            }
        }
        return this.xmlColumnPrs;
    }

    public XSSFTableColumn createColumn(String columnName) {
        return this.createColumn(columnName, this.getColumnCount());
    }

    public XSSFTableColumn createColumn(String columnName, int columnIndex) {
        int columnCount = this.getColumnCount();
        if (columnIndex < 0 || columnIndex > columnCount) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        CTTableColumns columns = this.ctTable.getTableColumns();
        if (columns == null) {
            columns = this.ctTable.addNewTableColumns();
        }
        long nextColumnId = 0L;
        for (XSSFTableColumn tableColumn : this.getColumns()) {
            if (columnName != null && columnName.equalsIgnoreCase(tableColumn.getName())) {
                throw new IllegalArgumentException("Column '" + columnName + "' already exists. Column names must be unique per table.");
            }
            nextColumnId = Math.max(nextColumnId, tableColumn.getId());
        }
        CTTableColumn column = columns.insertNewTableColumn(columnIndex);
        columns.setCount(columns.sizeOfTableColumnArray());
        column.setId(++nextColumnId);
        if (columnName != null) {
            column.setName(columnName);
        } else {
            column.setName("Column " + nextColumnId);
        }
        if (this.ctTable.getRef() != null) {
            int newColumnCount = columnCount + 1;
            CellReference tableStart = this.getStartCellReference();
            CellReference tableEnd = this.getEndCellReference();
            SpreadsheetVersion version = this.getXSSFSheet().getWorkbook().getSpreadsheetVersion();
            CellReference newTableEnd = new CellReference(tableEnd.getRow(), tableStart.getCol() + newColumnCount - 1);
            AreaReference newTableArea = new AreaReference(tableStart, newTableEnd, version);
            this.setCellRef(newTableArea);
        }
        this.updateHeaders();
        return this.getColumns().get(columnIndex);
    }

    public void removeColumn(XSSFTableColumn column) {
        int columnIndex = this.getColumns().indexOf(column);
        if (columnIndex >= 0) {
            this.ctTable.getTableColumns().removeTableColumn(columnIndex);
            this.updateReferences();
            this.updateHeaders();
        }
    }

    public void removeColumn(int columnIndex) {
        if (columnIndex < 0 || columnIndex > this.getColumnCount() - 1) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        if (this.getColumnCount() == 1) {
            throw new IllegalArgumentException("Table must have at least one column");
        }
        CTTableColumns tableColumns = this.ctTable.getTableColumns();
        tableColumns.removeTableColumn(columnIndex);
        tableColumns.setCount(tableColumns.getTableColumnList().size());
        this.updateReferences();
        this.updateHeaders();
    }

    @Override
    public String getName() {
        if (this.name == null && this.ctTable.getName() != null) {
            this.setName(this.ctTable.getName());
        }
        return this.name;
    }

    public void setName(String newName) {
        if (newName == null) {
            this.ctTable.unsetName();
            this.name = null;
            return;
        }
        this.ctTable.setName(newName);
        this.name = newName;
    }

    @Override
    public String getStyleName() {
        if (this.styleName == null && this.ctTable.isSetTableStyleInfo()) {
            this.setStyleName(this.ctTable.getTableStyleInfo().getName());
        }
        return this.styleName;
    }

    public void setStyleName(String newStyleName) {
        if (newStyleName == null) {
            if (this.ctTable.isSetTableStyleInfo()) {
                this.ctTable.getTableStyleInfo().unsetName();
            }
            this.styleName = null;
            return;
        }
        if (!this.ctTable.isSetTableStyleInfo()) {
            this.ctTable.addNewTableStyleInfo();
        }
        this.ctTable.getTableStyleInfo().setName(newStyleName);
        this.styleName = newStyleName;
    }

    public String getDisplayName() {
        return this.ctTable.getDisplayName();
    }

    public void setDisplayName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Display name must not be null or empty");
        }
        this.ctTable.setDisplayName(name);
    }

    @Deprecated
    @Removal(version="4.2.0")
    public long getNumberOfMappedColumns() {
        return this.ctTable.getTableColumns().getCount();
    }

    public AreaReference getCellReferences() {
        return new AreaReference(this.getStartCellReference(), this.getEndCellReference(), SpreadsheetVersion.EXCEL2007);
    }

    public void setCellReferences(AreaReference refs) {
        this.setCellRef(refs);
    }

    @Internal
    protected void setCellRef(AreaReference refs) {
        String ref = refs.formatAsString();
        if (ref.indexOf(33) != -1) {
            ref = ref.substring(ref.indexOf(33) + 1);
        }
        this.ctTable.setRef(ref);
        if (this.ctTable.isSetAutoFilter()) {
            String filterRef;
            int totalsRowCount = this.getTotalsRowCount();
            if (totalsRowCount == 0) {
                filterRef = ref;
            } else {
                CellReference start = new CellReference(refs.getFirstCell().getRow(), refs.getFirstCell().getCol());
                CellReference end = new CellReference(refs.getLastCell().getRow() - totalsRowCount, refs.getLastCell().getCol());
                filterRef = new AreaReference(start, end, SpreadsheetVersion.EXCEL2007).formatAsString();
            }
            this.ctTable.getAutoFilter().setRef(filterRef);
        }
        this.updateReferences();
        this.updateHeaders();
    }

    public void setArea(AreaReference tableArea) {
        int minimumRowCount;
        if (tableArea == null) {
            throw new IllegalArgumentException("AreaReference must not be null");
        }
        String areaSheetName = tableArea.getFirstCell().getSheetName();
        if (areaSheetName != null && !areaSheetName.equals(this.getXSSFSheet().getSheetName())) {
            throw new IllegalArgumentException("The AreaReference must not reference a different sheet");
        }
        int rowCount = tableArea.getLastCell().getRow() - tableArea.getFirstCell().getRow() + 1;
        if (rowCount < (minimumRowCount = 1 + this.getHeaderRowCount() + this.getTotalsRowCount())) {
            throw new IllegalArgumentException("AreaReference needs at least " + minimumRowCount + " rows, to cover at least one data row and all header rows and totals rows");
        }
        String ref = tableArea.formatAsString();
        if (ref.indexOf(33) != -1) {
            ref = ref.substring(ref.indexOf(33) + 1);
        }
        this.ctTable.setRef(ref);
        if (this.ctTable.isSetAutoFilter()) {
            this.ctTable.getAutoFilter().setRef(ref);
        }
        this.updateReferences();
        int columnCount = this.getColumnCount();
        int newColumnCount = tableArea.getLastCell().getCol() - tableArea.getFirstCell().getCol() + 1;
        if (newColumnCount > columnCount) {
            for (int i = columnCount; i < newColumnCount; ++i) {
                this.createColumn(null, i);
            }
        } else if (newColumnCount < columnCount) {
            for (int i = columnCount; i > newColumnCount; --i) {
                this.removeColumn(i - 1);
            }
        }
        this.updateHeaders();
    }

    public AreaReference getArea() {
        String ref = this.ctTable.getRef();
        if (ref != null) {
            SpreadsheetVersion version = this.getXSSFSheet().getWorkbook().getSpreadsheetVersion();
            return new AreaReference(this.ctTable.getRef(), version);
        }
        return null;
    }

    public CellReference getStartCellReference() {
        if (this.startCellReference == null) {
            this.setCellReferences();
        }
        return this.startCellReference;
    }

    public CellReference getEndCellReference() {
        if (this.endCellReference == null) {
            this.setCellReferences();
        }
        return this.endCellReference;
    }

    private void setCellReferences() {
        String ref = this.ctTable.getRef();
        if (ref != null) {
            String[] boundaries = ref.split(":", 2);
            String from = boundaries[0];
            String to = boundaries.length == 2 ? boundaries[1] : boundaries[0];
            this.startCellReference = new CellReference(from);
            this.endCellReference = new CellReference(to);
        }
    }

    public void updateReferences() {
        this.startCellReference = null;
        this.endCellReference = null;
    }

    public int getRowCount() {
        CellReference from = this.getStartCellReference();
        CellReference to = this.getEndCellReference();
        int rowCount = 0;
        if (from != null && to != null) {
            rowCount = to.getRow() - from.getRow() + 1;
        }
        return rowCount;
    }

    public int getDataRowCount() {
        CellReference from = this.getStartCellReference();
        CellReference to = this.getEndCellReference();
        int rowCount = 0;
        if (from != null && to != null) {
            rowCount = to.getRow() - from.getRow() + 1 - this.getHeaderRowCount() - this.getTotalsRowCount();
        }
        return rowCount;
    }

    public void setDataRowCount(int newDataRowCount) {
        CellReference clearAreaEnd;
        CellReference clearAreaStart;
        if (newDataRowCount < 1) {
            throw new IllegalArgumentException("Table must have at least one data row");
        }
        this.updateReferences();
        int dataRowCount = this.getDataRowCount();
        if (dataRowCount == newDataRowCount) {
            return;
        }
        CellReference tableStart = this.getStartCellReference();
        CellReference tableEnd = this.getEndCellReference();
        SpreadsheetVersion version = this.getXSSFSheet().getWorkbook().getSpreadsheetVersion();
        int newTotalRowCount = this.getHeaderRowCount() + newDataRowCount + this.getTotalsRowCount();
        CellReference newTableEnd = new CellReference(tableStart.getRow() + newTotalRowCount - 1, tableEnd.getCol());
        AreaReference newTableArea = new AreaReference(tableStart, newTableEnd, version);
        if (newDataRowCount < dataRowCount) {
            clearAreaStart = new CellReference(newTableArea.getLastCell().getRow() + 1, newTableArea.getFirstCell().getCol());
            clearAreaEnd = tableEnd;
        } else {
            clearAreaStart = new CellReference(tableEnd.getRow() + 1, newTableArea.getFirstCell().getCol());
            clearAreaEnd = newTableEnd;
        }
        AreaReference areaToClear = new AreaReference(clearAreaStart, clearAreaEnd, version);
        for (CellReference cellRef : areaToClear.getAllReferencedCells()) {
            XSSFCell cell;
            XSSFRow row = this.getXSSFSheet().getRow(cellRef.getRow());
            if (row == null || (cell = row.getCell(cellRef.getCol())) == null) continue;
            cell.setBlank();
            cell.setCellStyle(null);
        }
        this.setCellRef(newTableArea);
    }

    public int getColumnCount() {
        CTTableColumns tableColumns = this.ctTable.getTableColumns();
        if (tableColumns == null) {
            return 0;
        }
        return (int)tableColumns.getCount();
    }

    public void updateHeaders() {
        XSSFSheet sheet = (XSSFSheet)this.getParent();
        CellReference ref = this.getStartCellReference();
        if (ref == null) {
            return;
        }
        int headerRow = ref.getRow();
        int firstHeaderColumn = ref.getCol();
        XSSFRow row = sheet.getRow(headerRow);
        DataFormatter formatter = new DataFormatter();
        if (row != null && row.getCTRow().validate()) {
            int cellnum = firstHeaderColumn;
            CTTableColumns ctTableColumns = this.getCTTable().getTableColumns();
            if (ctTableColumns != null) {
                for (CTTableColumn col : ctTableColumns.getTableColumnList()) {
                    XSSFCell cell = row.getCell(cellnum);
                    if (cell != null) {
                        col.setName(formatter.formatCellValue(cell));
                    }
                    ++cellnum;
                }
            }
        }
        this.tableColumns = null;
        this.columnMap = null;
        this.xmlColumnPrs = null;
        this.commonXPath = null;
    }

    private static String caseInsensitive(String s) {
        return s.toUpperCase(Locale.ROOT);
    }

    @Override
    public int findColumnIndex(String columnHeader) {
        Integer idx;
        if (columnHeader == null) {
            return -1;
        }
        if (this.columnMap == null) {
            int count = this.getColumnCount();
            this.columnMap = new HashMap(count * 3 / 2);
            int i = 0;
            for (XSSFTableColumn column : this.getColumns()) {
                String columnName = column.getName();
                this.columnMap.put(XSSFTable.caseInsensitive(columnName), i);
                ++i;
            }
        }
        return (idx = this.columnMap.get(XSSFTable.caseInsensitive(columnHeader.replace("'", "")))) == null ? -1 : idx;
    }

    @Override
    public String getSheetName() {
        return this.getXSSFSheet().getSheetName();
    }

    @Override
    public boolean isHasTotalsRow() {
        return this.ctTable.getTotalsRowShown();
    }

    @Override
    public int getTotalsRowCount() {
        return (int)this.ctTable.getTotalsRowCount();
    }

    @Override
    public int getHeaderRowCount() {
        return (int)this.ctTable.getHeaderRowCount();
    }

    @Override
    public int getStartColIndex() {
        return this.getStartCellReference().getCol();
    }

    @Override
    public int getStartRowIndex() {
        return this.getStartCellReference().getRow();
    }

    @Override
    public int getEndColIndex() {
        return this.getEndCellReference().getCol();
    }

    @Override
    public int getEndRowIndex() {
        return this.getEndCellReference().getRow();
    }

    @Override
    public TableStyleInfo getStyle() {
        if (!this.ctTable.isSetTableStyleInfo()) {
            return null;
        }
        return new XSSFTableStyleInfo(((XSSFSheet)this.getParent()).getWorkbook().getStylesSource(), this.ctTable.getTableStyleInfo());
    }

    @Override
    public boolean contains(CellReference cell) {
        if (cell == null) {
            return false;
        }
        if (!this.getSheetName().equals(cell.getSheetName())) {
            return false;
        }
        return cell.getRow() >= this.getStartRowIndex() && cell.getRow() <= this.getEndRowIndex() && cell.getCol() >= this.getStartColIndex() && cell.getCol() <= this.getEndColIndex();
    }

    protected void onTableDelete() {
        for (POIXMLDocumentPart.RelationPart part : this.getRelationParts()) {
            this.removeRelation((POIXMLDocumentPart)part.getDocumentPart(), true);
        }
    }
}

