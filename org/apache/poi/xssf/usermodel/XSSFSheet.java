/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.PartAlreadyExistsException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipCollection;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellRange;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IgnoredErrorType;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Table;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.PaneInformation;
import org.apache.poi.ss.util.SSCellRange;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.usermodel.BaseXSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFAutoFilter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFEvenFooter;
import org.apache.poi.xssf.usermodel.XSSFEvenHeader;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFFirstFooter;
import org.apache.poi.xssf.usermodel.XSSFFirstHeader;
import org.apache.poi.xssf.usermodel.XSSFHeaderFooterProperties;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFOddFooter;
import org.apache.poi.xssf.usermodel.XSSFOddHeader;
import org.apache.poi.xssf.usermodel.XSSFPivotCache;
import org.apache.poi.xssf.usermodel.XSSFPivotCacheDefinition;
import org.apache.poi.xssf.usermodel.XSSFPivotCacheRecords;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheetConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFVMLDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.helpers.ColumnHelper;
import org.apache.poi.xssf.usermodel.helpers.XSSFColumnShifter;
import org.apache.poi.xssf.usermodel.helpers.XSSFIgnoredErrorHelper;
import org.apache.poi.xssf.usermodel.helpers.XSSFPasswordHelper;
import org.apache.poi.xssf.usermodel.helpers.XSSFRowShifter;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidations;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredError;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredErrors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOutlinePr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetUpPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetView;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTablePart;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableParts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheetSource;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalcMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument;

public class XSSFSheet
extends POIXMLDocumentPart
implements Sheet {
    private static final POILogger logger = POILogFactory.getLogger(XSSFSheet.class);
    private static final double DEFAULT_ROW_HEIGHT = 15.0;
    private static final double DEFAULT_MARGIN_HEADER = 0.3;
    private static final double DEFAULT_MARGIN_FOOTER = 0.3;
    private static final double DEFAULT_MARGIN_TOP = 0.75;
    private static final double DEFAULT_MARGIN_BOTTOM = 0.75;
    private static final double DEFAULT_MARGIN_LEFT = 0.7;
    private static final double DEFAULT_MARGIN_RIGHT = 0.7;
    public static final int TWIPS_PER_POINT = 20;
    protected CTSheet sheet;
    protected CTWorksheet worksheet;
    private final SortedMap<Integer, XSSFRow> _rows = new TreeMap<Integer, XSSFRow>();
    private List<XSSFHyperlink> hyperlinks;
    private ColumnHelper columnHelper;
    private CommentsTable sheetComments;
    private Map<Integer, CTCellFormula> sharedFormulas;
    private SortedMap<String, XSSFTable> tables;
    private List<CellRangeAddress> arrayFormulas;
    private XSSFDataValidationHelper dataValidationHelper = new XSSFDataValidationHelper(this);

    protected XSSFSheet() {
        this.onDocumentCreate();
    }

    protected XSSFSheet(PackagePart part) {
        super(part);
    }

    @Override
    public XSSFWorkbook getWorkbook() {
        return (XSSFWorkbook)this.getParent();
    }

    @Override
    protected void onDocumentRead() {
        try {
            this.read(this.getPackagePart().getInputStream());
        } catch (IOException e) {
            throw new POIXMLException(e);
        }
    }

    protected void read(InputStream is) throws IOException {
        try {
            this.worksheet = WorksheetDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS).getWorksheet();
        } catch (XmlException e) {
            throw new POIXMLException(e);
        }
        this.initRows(this.worksheet);
        this.columnHelper = new ColumnHelper(this.worksheet);
        for (POIXMLDocumentPart.RelationPart rp : this.getRelationParts()) {
            Object p = rp.getDocumentPart();
            if (p instanceof CommentsTable) {
                this.sheetComments = (CommentsTable)p;
            }
            if (p instanceof XSSFTable) {
                this.tables.put(rp.getRelationship().getId(), (XSSFTable)p);
            }
            if (!(p instanceof XSSFPivotTable)) continue;
            this.getWorkbook().getPivotTables().add((XSSFPivotTable)p);
        }
        this.initHyperlinks();
    }

    @Override
    protected void onDocumentCreate() {
        this.worksheet = XSSFSheet.newSheet();
        this.initRows(this.worksheet);
        this.columnHelper = new ColumnHelper(this.worksheet);
        this.hyperlinks = new ArrayList<XSSFHyperlink>();
    }

    private void initRows(CTWorksheet worksheetParam) {
        this._rows.clear();
        this.tables = new TreeMap<String, XSSFTable>();
        this.sharedFormulas = new HashMap<Integer, CTCellFormula>();
        this.arrayFormulas = new ArrayList<CellRangeAddress>();
        for (CTRow row : worksheetParam.getSheetData().getRowArray()) {
            XSSFRow r = new XSSFRow(row, this);
            Integer rownumI = r.getRowNum();
            this._rows.put(rownumI, r);
        }
    }

    private void initHyperlinks() {
        this.hyperlinks = new ArrayList<XSSFHyperlink>();
        if (!this.worksheet.isSetHyperlinks()) {
            return;
        }
        try {
            PackageRelationshipCollection hyperRels = this.getPackagePart().getRelationshipsByType(XSSFRelation.SHEET_HYPERLINKS.getRelation());
            for (CTHyperlink hyperlink : this.worksheet.getHyperlinks().getHyperlinkArray()) {
                PackageRelationship hyperRel = null;
                if (hyperlink.getId() != null) {
                    hyperRel = hyperRels.getRelationshipByID(hyperlink.getId());
                }
                this.hyperlinks.add(new XSSFHyperlink(hyperlink, hyperRel));
            }
        } catch (InvalidFormatException e) {
            throw new POIXMLException(e);
        }
    }

    private static CTWorksheet newSheet() {
        CTWorksheet worksheet = CTWorksheet.Factory.newInstance();
        CTSheetFormatPr ctFormat = worksheet.addNewSheetFormatPr();
        ctFormat.setDefaultRowHeight(15.0);
        CTSheetView ctView = worksheet.addNewSheetViews().addNewSheetView();
        ctView.setWorkbookViewId(0L);
        worksheet.addNewDimension().setRef("A1");
        worksheet.addNewSheetData();
        CTPageMargins ctMargins = worksheet.addNewPageMargins();
        ctMargins.setBottom(0.75);
        ctMargins.setFooter(0.3);
        ctMargins.setHeader(0.3);
        ctMargins.setLeft(0.7);
        ctMargins.setRight(0.7);
        ctMargins.setTop(0.75);
        return worksheet;
    }

    @Internal
    public CTWorksheet getCTWorksheet() {
        return this.worksheet;
    }

    public ColumnHelper getColumnHelper() {
        return this.columnHelper;
    }

    @Override
    public String getSheetName() {
        return this.sheet.getName();
    }

    @Override
    public int addMergedRegion(CellRangeAddress region) {
        return this.addMergedRegion(region, true);
    }

    @Override
    public int addMergedRegionUnsafe(CellRangeAddress region) {
        return this.addMergedRegion(region, false);
    }

    private int addMergedRegion(CellRangeAddress region, boolean validate) {
        if (region.getNumberOfCells() < 2) {
            throw new IllegalArgumentException("Merged region " + region.formatAsString() + " must contain 2 or more cells");
        }
        region.validate(SpreadsheetVersion.EXCEL2007);
        if (validate) {
            this.validateArrayFormulas(region);
            this.validateMergedRegions(region);
        }
        CTMergeCells ctMergeCells = this.worksheet.isSetMergeCells() ? this.worksheet.getMergeCells() : this.worksheet.addNewMergeCells();
        CTMergeCell ctMergeCell = ctMergeCells.addNewMergeCell();
        ctMergeCell.setRef(region.formatAsString());
        int numMergeRegions = ctMergeCells.sizeOfMergeCellArray();
        ctMergeCells.setCount(numMergeRegions);
        return numMergeRegions - 1;
    }

    private void validateArrayFormulas(CellRangeAddress region) {
        int firstRow = region.getFirstRow();
        int firstColumn = region.getFirstColumn();
        int lastRow = region.getLastRow();
        int lastColumn = region.getLastColumn();
        for (int rowIn = firstRow; rowIn <= lastRow; ++rowIn) {
            XSSFRow row = this.getRow(rowIn);
            if (row == null) continue;
            for (int colIn = firstColumn; colIn <= lastColumn; ++colIn) {
                CellRangeAddress arrayRange;
                XSSFCell cell = row.getCell(colIn);
                if (cell == null || !cell.isPartOfArrayFormulaGroup() || (arrayRange = cell.getArrayFormulaRange()).getNumberOfCells() <= 1 || !region.intersects(arrayRange)) continue;
                String msg = "The range " + region.formatAsString() + " intersects with a multi-cell array formula. You cannot merge cells of an array.";
                throw new IllegalStateException(msg);
            }
        }
    }

    private void checkForMergedRegionsIntersectingArrayFormulas() {
        for (CellRangeAddress region : this.getMergedRegions()) {
            this.validateArrayFormulas(region);
        }
    }

    private void validateMergedRegions(CellRangeAddress candidateRegion) {
        for (CellRangeAddress existingRegion : this.getMergedRegions()) {
            if (!existingRegion.intersects(candidateRegion)) continue;
            throw new IllegalStateException("Cannot add merged region " + candidateRegion.formatAsString() + " to sheet because it overlaps with an existing merged region (" + existingRegion.formatAsString() + ").");
        }
    }

    private void checkForIntersectingMergedRegions() {
        List<CellRangeAddress> regions = this.getMergedRegions();
        int size = regions.size();
        for (int i = 0; i < size; ++i) {
            CellRangeAddress region = regions.get(i);
            for (CellRangeAddress other : regions.subList(i + 1, regions.size())) {
                if (!region.intersects(other)) continue;
                String msg = "The range " + region.formatAsString() + " intersects with another merged region " + other.formatAsString() + " in this sheet";
                throw new IllegalStateException(msg);
            }
        }
    }

    @Override
    public void validateMergedRegions() {
        this.checkForMergedRegionsIntersectingArrayFormulas();
        this.checkForIntersectingMergedRegions();
    }

    @Override
    public void autoSizeColumn(int column) {
        this.autoSizeColumn(column, false);
    }

    @Override
    public void autoSizeColumn(int column, boolean useMergedCells) {
        double width = SheetUtil.getColumnWidth(this, column, useMergedCells);
        if (width != -1.0) {
            int maxColumnWidth = 65280;
            if ((width *= 256.0) > (double)maxColumnWidth) {
                width = maxColumnWidth;
            }
            this.setColumnWidth(column, Math.toIntExact(Math.round(width)));
            this.columnHelper.setColBestFit(column, true);
        }
    }

    public XSSFDrawing getDrawingPatriarch() {
        CTDrawing ctDrawing = this.getCTDrawing();
        if (ctDrawing != null) {
            for (POIXMLDocumentPart.RelationPart rp : this.getRelationParts()) {
                Object p = rp.getDocumentPart();
                if (!(p instanceof XSSFDrawing)) continue;
                XSSFDrawing dr = (XSSFDrawing)p;
                String drId = rp.getRelationship().getId();
                if (!drId.equals(ctDrawing.getId())) break;
                return dr;
            }
            logger.log(7, "Can't find drawing with id=" + ctDrawing.getId() + " in the list of the sheet's relationships");
        }
        return null;
    }

    public XSSFDrawing createDrawingPatriarch() {
        CTDrawing ctDrawing = this.getCTDrawing();
        if (ctDrawing != null) {
            return this.getDrawingPatriarch();
        }
        int drawingNumber = this.getPackagePart().getPackage().getPartsByContentType(XSSFRelation.DRAWINGS.getContentType()).size() + 1;
        drawingNumber = this.getNextPartNumber(XSSFRelation.DRAWINGS, drawingNumber);
        POIXMLDocumentPart.RelationPart rp = this.createRelationship(XSSFRelation.DRAWINGS, XSSFFactory.getInstance(), drawingNumber, false);
        XSSFDrawing drawing = (XSSFDrawing)rp.getDocumentPart();
        String relId = rp.getRelationship().getId();
        ctDrawing = this.worksheet.addNewDrawing();
        ctDrawing.setId(relId);
        return drawing;
    }

    protected XSSFVMLDrawing getVMLDrawing(boolean autoCreate) {
        XSSFVMLDrawing drawing = null;
        CTLegacyDrawing ctDrawing = this.getCTLegacyDrawing();
        if (ctDrawing == null) {
            if (autoCreate) {
                int drawingNumber = this.getPackagePart().getPackage().getPartsByContentType(XSSFRelation.VML_DRAWINGS.getContentType()).size() + 1;
                POIXMLDocumentPart.RelationPart rp = this.createRelationship(XSSFRelation.VML_DRAWINGS, XSSFFactory.getInstance(), drawingNumber, false);
                drawing = (XSSFVMLDrawing)rp.getDocumentPart();
                String relId = rp.getRelationship().getId();
                ctDrawing = this.worksheet.addNewLegacyDrawing();
                ctDrawing.setId(relId);
            }
        } else {
            String id = ctDrawing.getId();
            for (POIXMLDocumentPart.RelationPart rp : this.getRelationParts()) {
                Object p = rp.getDocumentPart();
                if (!(p instanceof XSSFVMLDrawing)) continue;
                XSSFVMLDrawing dr = (XSSFVMLDrawing)p;
                String drId = rp.getRelationship().getId();
                if (!drId.equals(id)) continue;
                drawing = dr;
                break;
            }
            if (drawing == null) {
                logger.log(7, "Can't find VML drawing with id=" + id + " in the list of the sheet's relationships");
            }
        }
        return drawing;
    }

    protected CTDrawing getCTDrawing() {
        return this.worksheet.getDrawing();
    }

    protected CTLegacyDrawing getCTLegacyDrawing() {
        return this.worksheet.getLegacyDrawing();
    }

    @Override
    public void createFreezePane(int colSplit, int rowSplit) {
        this.createFreezePane(colSplit, rowSplit, colSplit, rowSplit);
    }

    @Override
    public void createFreezePane(int colSplit, int rowSplit, int leftmostColumn, int topRow) {
        CTPane pane;
        boolean removeSplit = colSplit == 0 && rowSplit == 0;
        CTSheetView ctView = this.getDefaultSheetView(!removeSplit);
        if (ctView != null) {
            ctView.setSelectionArray(null);
        }
        if (removeSplit) {
            if (ctView != null && ctView.isSetPane()) {
                ctView.unsetPane();
            }
            return;
        }
        assert (ctView != null);
        CTPane cTPane = pane = ctView.isSetPane() ? ctView.getPane() : ctView.addNewPane();
        assert (pane != null);
        if (colSplit > 0) {
            pane.setXSplit(colSplit);
        } else if (pane.isSetXSplit()) {
            pane.unsetXSplit();
        }
        if (rowSplit > 0) {
            pane.setYSplit(rowSplit);
        } else if (pane.isSetYSplit()) {
            pane.unsetYSplit();
        }
        STPane.Enum activePane = STPane.BOTTOM_RIGHT;
        int pRow = topRow;
        int pCol = leftmostColumn;
        if (rowSplit == 0) {
            pRow = 0;
            activePane = STPane.TOP_RIGHT;
        } else if (colSplit == 0) {
            pCol = 0;
            activePane = STPane.BOTTOM_LEFT;
        }
        pane.setState(STPaneState.FROZEN);
        pane.setTopLeftCell(new CellReference(pRow, pCol).formatAsString());
        pane.setActivePane(activePane);
        ctView.addNewSelection().setPane(activePane);
    }

    @Override
    public XSSFRow createRow(int rownum) {
        CTRow ctRow;
        Integer rownumI = rownum;
        XSSFRow prev = (XSSFRow)this._rows.get(rownumI);
        if (prev != null) {
            while (prev.getFirstCellNum() != -1) {
                prev.removeCell(prev.getCell(prev.getFirstCellNum()));
            }
            ctRow = prev.getCTRow();
            ctRow.set(CTRow.Factory.newInstance());
        } else if (this._rows.isEmpty() || rownum > this._rows.lastKey()) {
            ctRow = this.worksheet.getSheetData().addNewRow();
        } else {
            int idx = this._rows.headMap(rownumI).size();
            ctRow = this.worksheet.getSheetData().insertNewRow(idx);
        }
        XSSFRow r = new XSSFRow(ctRow, this);
        r.setRowNum(rownum);
        this._rows.put(rownumI, r);
        return r;
    }

    @Override
    public void createSplitPane(int xSplitPos, int ySplitPos, int leftmostColumn, int topRow, int activePane) {
        this.createFreezePane(xSplitPos, ySplitPos, leftmostColumn, topRow);
        if (xSplitPos > 0 || ySplitPos > 0) {
            CTPane pane = this.getPane(true);
            pane.setState(STPaneState.SPLIT);
            pane.setActivePane(STPane.Enum.forInt(activePane));
        }
    }

    @Override
    public XSSFComment getCellComment(CellAddress address) {
        int column;
        if (this.sheetComments == null) {
            return null;
        }
        int row = address.getRow();
        CellAddress ref = new CellAddress(row, column = address.getColumn());
        CTComment ctComment = this.sheetComments.getCTComment(ref);
        if (ctComment == null) {
            return null;
        }
        XSSFVMLDrawing vml = this.getVMLDrawing(false);
        return new XSSFComment(this.sheetComments, ctComment, vml == null ? null : vml.findCommentShape(row, column));
    }

    public Map<CellAddress, XSSFComment> getCellComments() {
        if (this.sheetComments == null) {
            return Collections.emptyMap();
        }
        HashMap<CellAddress, XSSFComment> map = new HashMap<CellAddress, XSSFComment>();
        Iterator<CellAddress> iter = this.sheetComments.getCellAddresses();
        while (iter.hasNext()) {
            CellAddress address = iter.next();
            map.put(address, this.getCellComment(address));
        }
        return map;
    }

    @Override
    public XSSFHyperlink getHyperlink(int row, int column) {
        return this.getHyperlink(new CellAddress(row, column));
    }

    @Override
    public XSSFHyperlink getHyperlink(CellAddress addr) {
        String ref = addr.formatAsString();
        for (XSSFHyperlink hyperlink : this.hyperlinks) {
            if (!hyperlink.getCellRef().equals(ref)) continue;
            return hyperlink;
        }
        return null;
    }

    public List<XSSFHyperlink> getHyperlinkList() {
        return Collections.unmodifiableList(this.hyperlinks);
    }

    private int[] getBreaks(CTPageBreak ctPageBreak) {
        CTBreak[] brkArray = ctPageBreak.getBrkArray();
        int[] breaks = new int[brkArray.length];
        for (int i = 0; i < brkArray.length; ++i) {
            breaks[i] = Math.toIntExact(brkArray[i].getId() - 1L);
        }
        return breaks;
    }

    private void removeBreak(int index, CTPageBreak ctPageBreak) {
        int index1 = index + 1;
        CTBreak[] brkArray = ctPageBreak.getBrkArray();
        for (int i = 0; i < brkArray.length; ++i) {
            if (brkArray[i].getId() != (long)index1) continue;
            ctPageBreak.removeBrk(i);
        }
    }

    @Override
    public int[] getColumnBreaks() {
        return this.worksheet.isSetColBreaks() ? this.getBreaks(this.worksheet.getColBreaks()) : new int[]{};
    }

    @Override
    public int getColumnWidth(int columnIndex) {
        CTCol col = this.columnHelper.getColumn(columnIndex, false);
        double width = col == null || !col.isSetWidth() ? (double)this.getDefaultColumnWidth() : col.getWidth();
        return Math.toIntExact(Math.round(width * 256.0));
    }

    @Override
    public float getColumnWidthInPixels(int columnIndex) {
        float widthIn256 = this.getColumnWidth(columnIndex);
        return (float)((double)widthIn256 / 256.0 * (double)7.0017f);
    }

    @Override
    public int getDefaultColumnWidth() {
        CTSheetFormatPr pr = this.worksheet.getSheetFormatPr();
        return pr == null ? 8 : Math.toIntExact(pr.getBaseColWidth());
    }

    @Override
    public short getDefaultRowHeight() {
        return (short)(this.getDefaultRowHeightInPoints() * 20.0f);
    }

    @Override
    public float getDefaultRowHeightInPoints() {
        CTSheetFormatPr pr = this.worksheet.getSheetFormatPr();
        return (float)(pr == null ? 0.0 : pr.getDefaultRowHeight());
    }

    private CTSheetFormatPr getSheetTypeSheetFormatPr() {
        return this.worksheet.isSetSheetFormatPr() ? this.worksheet.getSheetFormatPr() : this.worksheet.addNewSheetFormatPr();
    }

    @Override
    public CellStyle getColumnStyle(int column) {
        int idx = this.columnHelper.getColDefaultStyle(column);
        return this.getWorkbook().getCellStyleAt((short)(idx == -1 ? 0 : idx));
    }

    @Override
    public void setRightToLeft(boolean value) {
        CTSheetView dsv = this.getDefaultSheetView(true);
        assert (dsv != null);
        dsv.setRightToLeft(value);
    }

    @Override
    public boolean isRightToLeft() {
        CTSheetView dsv = this.getDefaultSheetView(false);
        return dsv != null && dsv.getRightToLeft();
    }

    @Override
    public boolean getDisplayGuts() {
        CTSheetPr sheetPr = this.getSheetTypeSheetPr();
        CTOutlinePr outlinePr = sheetPr.getOutlinePr() == null ? CTOutlinePr.Factory.newInstance() : sheetPr.getOutlinePr();
        return outlinePr.getShowOutlineSymbols();
    }

    @Override
    public void setDisplayGuts(boolean value) {
        CTSheetPr sheetPr = this.getSheetTypeSheetPr();
        CTOutlinePr outlinePr = sheetPr.getOutlinePr() == null ? sheetPr.addNewOutlinePr() : sheetPr.getOutlinePr();
        outlinePr.setShowOutlineSymbols(value);
    }

    @Override
    public boolean isDisplayZeros() {
        CTSheetView dsv = this.getDefaultSheetView(false);
        return dsv == null || dsv.getShowZeros();
    }

    @Override
    public void setDisplayZeros(boolean value) {
        CTSheetView view = this.getDefaultSheetView(true);
        assert (view != null);
        view.setShowZeros(value);
    }

    @Override
    public int getFirstRowNum() {
        return this._rows.isEmpty() ? -1 : this._rows.firstKey();
    }

    @Override
    public boolean getFitToPage() {
        CTSheetPr sheetPr = this.getSheetTypeSheetPr();
        CTPageSetUpPr psSetup = sheetPr == null || !sheetPr.isSetPageSetUpPr() ? CTPageSetUpPr.Factory.newInstance() : sheetPr.getPageSetUpPr();
        return psSetup.getFitToPage();
    }

    private CTSheetPr getSheetTypeSheetPr() {
        if (this.worksheet.getSheetPr() == null) {
            this.worksheet.setSheetPr(CTSheetPr.Factory.newInstance());
        }
        return this.worksheet.getSheetPr();
    }

    private CTHeaderFooter getSheetTypeHeaderFooter() {
        if (this.worksheet.getHeaderFooter() == null) {
            this.worksheet.setHeaderFooter(CTHeaderFooter.Factory.newInstance());
        }
        return this.worksheet.getHeaderFooter();
    }

    @Override
    public Footer getFooter() {
        return this.getOddFooter();
    }

    @Override
    public Header getHeader() {
        return this.getOddHeader();
    }

    public Footer getOddFooter() {
        return new XSSFOddFooter(this.getSheetTypeHeaderFooter());
    }

    public Footer getEvenFooter() {
        return new XSSFEvenFooter(this.getSheetTypeHeaderFooter());
    }

    public Footer getFirstFooter() {
        return new XSSFFirstFooter(this.getSheetTypeHeaderFooter());
    }

    public Header getOddHeader() {
        return new XSSFOddHeader(this.getSheetTypeHeaderFooter());
    }

    public Header getEvenHeader() {
        return new XSSFEvenHeader(this.getSheetTypeHeaderFooter());
    }

    public Header getFirstHeader() {
        return new XSSFFirstHeader(this.getSheetTypeHeaderFooter());
    }

    @Override
    public boolean getHorizontallyCenter() {
        CTPrintOptions opts = this.worksheet.getPrintOptions();
        return opts != null && opts.getHorizontalCentered();
    }

    @Override
    public int getLastRowNum() {
        return this._rows.isEmpty() ? -1 : this._rows.lastKey();
    }

    @Override
    public short getLeftCol() {
        String cellRef = this.worksheet.getSheetViews().getSheetViewArray(0).getTopLeftCell();
        if (cellRef == null) {
            return 0;
        }
        CellReference cellReference = new CellReference(cellRef);
        return cellReference.getCol();
    }

    @Override
    public double getMargin(short margin) {
        if (!this.worksheet.isSetPageMargins()) {
            return 0.0;
        }
        CTPageMargins pageMargins = this.worksheet.getPageMargins();
        switch (margin) {
            case 0: {
                return pageMargins.getLeft();
            }
            case 1: {
                return pageMargins.getRight();
            }
            case 2: {
                return pageMargins.getTop();
            }
            case 3: {
                return pageMargins.getBottom();
            }
            case 4: {
                return pageMargins.getHeader();
            }
            case 5: {
                return pageMargins.getFooter();
            }
        }
        throw new IllegalArgumentException("Unknown margin constant:  " + margin);
    }

    @Override
    public void setMargin(short margin, double size) {
        CTPageMargins pageMargins = this.worksheet.isSetPageMargins() ? this.worksheet.getPageMargins() : this.worksheet.addNewPageMargins();
        switch (margin) {
            case 0: {
                pageMargins.setLeft(size);
                break;
            }
            case 1: {
                pageMargins.setRight(size);
                break;
            }
            case 2: {
                pageMargins.setTop(size);
                break;
            }
            case 3: {
                pageMargins.setBottom(size);
                break;
            }
            case 4: {
                pageMargins.setHeader(size);
                break;
            }
            case 5: {
                pageMargins.setFooter(size);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown margin constant:  " + margin);
            }
        }
    }

    @Override
    public CellRangeAddress getMergedRegion(int index) {
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        if (ctMergeCells == null) {
            throw new IllegalStateException("This worksheet does not contain merged regions");
        }
        CTMergeCell ctMergeCell = ctMergeCells.getMergeCellArray(index);
        String ref = ctMergeCell.getRef();
        return CellRangeAddress.valueOf(ref);
    }

    @Override
    public List<CellRangeAddress> getMergedRegions() {
        ArrayList<CellRangeAddress> addresses = new ArrayList<CellRangeAddress>();
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        if (ctMergeCells == null) {
            return addresses;
        }
        for (CTMergeCell ctMergeCell : ctMergeCells.getMergeCellArray()) {
            String ref = ctMergeCell.getRef();
            addresses.add(CellRangeAddress.valueOf(ref));
        }
        return addresses;
    }

    @Override
    public int getNumMergedRegions() {
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        return ctMergeCells == null ? 0 : ctMergeCells.sizeOfMergeCellArray();
    }

    public int getNumHyperlinks() {
        return this.hyperlinks.size();
    }

    @Override
    public PaneInformation getPaneInformation() {
        CTPane pane = this.getPane(false);
        if (pane == null) {
            return null;
        }
        short row = 0;
        short col = 0;
        if (pane.isSetTopLeftCell()) {
            CellReference cellRef = new CellReference(pane.getTopLeftCell());
            row = (short)cellRef.getRow();
            col = cellRef.getCol();
        }
        short x = (short)pane.getXSplit();
        short y = (short)pane.getYSplit();
        byte active = (byte)(pane.getActivePane().intValue() - 1);
        boolean frozen = pane.getState() == STPaneState.FROZEN;
        return new PaneInformation(x, y, row, col, active, frozen);
    }

    @Override
    public int getPhysicalNumberOfRows() {
        return this._rows.size();
    }

    @Override
    public XSSFPrintSetup getPrintSetup() {
        return new XSSFPrintSetup(this.worksheet);
    }

    @Override
    public boolean getProtect() {
        return this.isSheetLocked();
    }

    @Override
    public void protectSheet(String password) {
        if (password != null) {
            CTSheetProtection sheetProtection = this.safeGetProtectionField();
            this.setSheetPassword(password, null);
            sheetProtection.setSheet(true);
            sheetProtection.setScenarios(true);
            sheetProtection.setObjects(true);
        } else {
            this.worksheet.unsetSheetProtection();
        }
    }

    public void setSheetPassword(String password, HashAlgorithm hashAlgo) {
        if (password == null && !this.isSheetProtectionEnabled()) {
            return;
        }
        XSSFPasswordHelper.setPassword(this.safeGetProtectionField(), password, hashAlgo, null);
    }

    public boolean validateSheetPassword(String password) {
        if (!this.isSheetProtectionEnabled()) {
            return password == null;
        }
        return XSSFPasswordHelper.validatePassword(this.safeGetProtectionField(), password, null);
    }

    @Override
    public XSSFRow getRow(int rownum) {
        Integer rownumI = rownum;
        return (XSSFRow)this._rows.get(rownumI);
    }

    private List<XSSFRow> getRows(int startRowNum, int endRowNum, boolean createRowIfMissing) {
        if (startRowNum > endRowNum) {
            throw new IllegalArgumentException("getRows: startRowNum must be less than or equal to endRowNum");
        }
        ArrayList<XSSFRow> rows = new ArrayList<XSSFRow>();
        if (createRowIfMissing) {
            for (int i = startRowNum; i <= endRowNum; ++i) {
                XSSFRow row = this.getRow(i);
                if (row == null) {
                    row = this.createRow(i);
                }
                rows.add(row);
            }
        } else {
            Integer startI = startRowNum;
            Integer endI = endRowNum + 1;
            Collection<XSSFRow> inclusive = this._rows.subMap(startI, endI).values();
            rows.addAll(inclusive);
        }
        return rows;
    }

    @Override
    public int[] getRowBreaks() {
        return this.worksheet.isSetRowBreaks() ? this.getBreaks(this.worksheet.getRowBreaks()) : new int[]{};
    }

    @Override
    public boolean getRowSumsBelow() {
        CTSheetPr sheetPr = this.worksheet.getSheetPr();
        CTOutlinePr outlinePr = sheetPr != null && sheetPr.isSetOutlinePr() ? sheetPr.getOutlinePr() : null;
        return outlinePr == null || outlinePr.getSummaryBelow();
    }

    @Override
    public void setRowSumsBelow(boolean value) {
        this.ensureOutlinePr().setSummaryBelow(value);
    }

    @Override
    public boolean getRowSumsRight() {
        CTSheetPr sheetPr = this.worksheet.getSheetPr();
        CTOutlinePr outlinePr = sheetPr != null && sheetPr.isSetOutlinePr() ? sheetPr.getOutlinePr() : CTOutlinePr.Factory.newInstance();
        return outlinePr.getSummaryRight();
    }

    @Override
    public void setRowSumsRight(boolean value) {
        this.ensureOutlinePr().setSummaryRight(value);
    }

    private CTOutlinePr ensureOutlinePr() {
        CTSheetPr sheetPr = this.worksheet.isSetSheetPr() ? this.worksheet.getSheetPr() : this.worksheet.addNewSheetPr();
        return sheetPr.isSetOutlinePr() ? sheetPr.getOutlinePr() : sheetPr.addNewOutlinePr();
    }

    @Override
    public boolean getScenarioProtect() {
        return this.worksheet.isSetSheetProtection() && this.worksheet.getSheetProtection().getScenarios();
    }

    @Override
    public short getTopRow() {
        String cellRef;
        CTSheetView dsv = this.getDefaultSheetView(false);
        String string = cellRef = dsv == null ? null : dsv.getTopLeftCell();
        if (cellRef == null) {
            return 0;
        }
        return (short)new CellReference(cellRef).getRow();
    }

    @Override
    public boolean getVerticallyCenter() {
        CTPrintOptions opts = this.worksheet.getPrintOptions();
        return opts != null && opts.getVerticalCentered();
    }

    @Override
    public void groupColumn(int fromColumn, int toColumn) {
        this.groupColumn1Based(fromColumn + 1, toColumn + 1);
    }

    private void groupColumn1Based(int fromColumn, int toColumn) {
        CTCols ctCols = this.worksheet.getColsArray(0);
        CTCol ctCol = CTCol.Factory.newInstance();
        CTCol fixCol_before = this.columnHelper.getColumn1Based(toColumn, false);
        if (fixCol_before != null) {
            fixCol_before = (CTCol)fixCol_before.copy();
        }
        ctCol.setMin(fromColumn);
        ctCol.setMax(toColumn);
        this.columnHelper.addCleanColIntoCols(ctCols, ctCol);
        CTCol fixCol_after = this.columnHelper.getColumn1Based(toColumn, false);
        if (fixCol_before != null && fixCol_after != null) {
            this.columnHelper.setColumnAttributes(fixCol_before, fixCol_after);
        }
        for (int index = fromColumn; index <= toColumn; ++index) {
            CTCol col = this.columnHelper.getColumn1Based(index, false);
            short outlineLevel = col.getOutlineLevel();
            col.setOutlineLevel((short)(outlineLevel + 1));
            index = Math.toIntExact(col.getMax());
        }
        this.worksheet.setColsArray(0, ctCols);
        this.setSheetFormatPrOutlineLevelCol();
    }

    private void setColWidthAttribute(CTCols ctCols) {
        for (CTCol col : ctCols.getColArray()) {
            if (col.isSetWidth()) continue;
            col.setWidth(this.getDefaultColumnWidth());
            col.setCustomWidth(false);
        }
    }

    @Override
    public void groupRow(int fromRow, int toRow) {
        for (int i = fromRow; i <= toRow; ++i) {
            XSSFRow xrow = this.getRow(i);
            if (xrow == null) {
                xrow = this.createRow(i);
            }
            CTRow ctrow = xrow.getCTRow();
            short outlineLevel = ctrow.getOutlineLevel();
            ctrow.setOutlineLevel((short)(outlineLevel + 1));
        }
        this.setSheetFormatPrOutlineLevelRow();
    }

    private short getMaxOutlineLevelRows() {
        int outlineLevel = 0;
        for (XSSFRow xrow : this._rows.values()) {
            outlineLevel = Math.max(outlineLevel, xrow.getCTRow().getOutlineLevel());
        }
        return (short)outlineLevel;
    }

    private short getMaxOutlineLevelCols() {
        CTCols ctCols = this.worksheet.getColsArray(0);
        int outlineLevel = 0;
        for (CTCol col : ctCols.getColArray()) {
            outlineLevel = Math.max(outlineLevel, col.getOutlineLevel());
        }
        return (short)outlineLevel;
    }

    @Override
    public boolean isColumnBroken(int column) {
        for (int colBreak : this.getColumnBreaks()) {
            if (colBreak != column) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean isColumnHidden(int columnIndex) {
        CTCol col = this.columnHelper.getColumn(columnIndex, false);
        return col != null && col.getHidden();
    }

    @Override
    public boolean isDisplayFormulas() {
        CTSheetView dsv = this.getDefaultSheetView(false);
        return dsv != null && dsv.getShowFormulas();
    }

    @Override
    public boolean isDisplayGridlines() {
        CTSheetView dsv = this.getDefaultSheetView(false);
        return dsv == null || dsv.getShowGridLines();
    }

    @Override
    public void setDisplayGridlines(boolean show) {
        CTSheetView dsv = this.getDefaultSheetView(true);
        assert (dsv != null);
        dsv.setShowGridLines(show);
    }

    @Override
    public boolean isDisplayRowColHeadings() {
        CTSheetView dsv = this.getDefaultSheetView(false);
        return dsv == null || dsv.getShowRowColHeaders();
    }

    @Override
    public void setDisplayRowColHeadings(boolean show) {
        CTSheetView dsv = this.getDefaultSheetView(true);
        assert (dsv != null);
        dsv.setShowRowColHeaders(show);
    }

    @Override
    public boolean isPrintGridlines() {
        CTPrintOptions opts = this.worksheet.getPrintOptions();
        return opts != null && opts.getGridLines();
    }

    @Override
    public void setPrintGridlines(boolean value) {
        CTPrintOptions opts = this.worksheet.isSetPrintOptions() ? this.worksheet.getPrintOptions() : this.worksheet.addNewPrintOptions();
        opts.setGridLines(value);
    }

    @Override
    public boolean isPrintRowAndColumnHeadings() {
        CTPrintOptions opts = this.worksheet.getPrintOptions();
        return opts != null && opts.getHeadings();
    }

    @Override
    public void setPrintRowAndColumnHeadings(boolean value) {
        CTPrintOptions opts = this.worksheet.isSetPrintOptions() ? this.worksheet.getPrintOptions() : this.worksheet.addNewPrintOptions();
        opts.setHeadings(value);
    }

    @Override
    public boolean isRowBroken(int row) {
        for (int rowBreak : this.getRowBreaks()) {
            if (rowBreak != row) continue;
            return true;
        }
        return false;
    }

    private void setBreak(int id, CTPageBreak ctPgBreak, int lastIndex) {
        CTBreak brk = ctPgBreak.addNewBrk();
        brk.setId(id + 1);
        brk.setMan(true);
        brk.setMax(lastIndex);
        int nPageBreaks = ctPgBreak.sizeOfBrkArray();
        ctPgBreak.setCount(nPageBreaks);
        ctPgBreak.setManualBreakCount(nPageBreaks);
    }

    @Override
    public void setRowBreak(int row) {
        if (!this.isRowBroken(row)) {
            CTPageBreak pgBreak = this.worksheet.isSetRowBreaks() ? this.worksheet.getRowBreaks() : this.worksheet.addNewRowBreaks();
            this.setBreak(row, pgBreak, SpreadsheetVersion.EXCEL2007.getLastColumnIndex());
        }
    }

    @Override
    public void removeColumnBreak(int column) {
        if (this.worksheet.isSetColBreaks()) {
            this.removeBreak(column, this.worksheet.getColBreaks());
        }
    }

    @Override
    public void removeMergedRegion(int index) {
        if (!this.worksheet.isSetMergeCells()) {
            return;
        }
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        int size = ctMergeCells.sizeOfMergeCellArray();
        assert (0 <= index && index < size);
        if (size > 1) {
            ctMergeCells.removeMergeCell(index);
        } else {
            this.worksheet.unsetMergeCells();
        }
    }

    @Override
    public void removeMergedRegions(Collection<Integer> indices) {
        if (!this.worksheet.isSetMergeCells()) {
            return;
        }
        CTMergeCells ctMergeCells = this.worksheet.getMergeCells();
        ArrayList<CTMergeCell> newMergeCells = new ArrayList<CTMergeCell>(ctMergeCells.sizeOfMergeCellArray());
        int idx = 0;
        for (CTMergeCell mc : ctMergeCells.getMergeCellArray()) {
            if (indices.contains(idx++)) continue;
            newMergeCells.add(mc);
        }
        if (newMergeCells.isEmpty()) {
            this.worksheet.unsetMergeCells();
        } else {
            CTMergeCell[] newMergeCellsArray = new CTMergeCell[newMergeCells.size()];
            ctMergeCells.setMergeCellArray(newMergeCells.toArray(newMergeCellsArray));
        }
    }

    @Override
    public void removeRow(Row row) {
        if (row.getSheet() != this) {
            throw new IllegalArgumentException("Specified row does not belong to this sheet");
        }
        ArrayList<XSSFCell> cellsToDelete = new ArrayList<XSSFCell>();
        for (Cell cell : row) {
            cellsToDelete.add((XSSFCell)cell);
        }
        for (XSSFCell xSSFCell : cellsToDelete) {
            row.removeCell(xSSFCell);
        }
        int rowNum = row.getRowNum();
        Integer n = rowNum;
        int idx = this._rows.headMap(n).size();
        this._rows.remove(n);
        this.worksheet.getSheetData().removeRow(idx);
        if (this.sheetComments != null) {
            for (CellAddress ref : this.getCellComments().keySet()) {
                if (ref.getRow() != rowNum) continue;
                this.sheetComments.removeComment(ref);
            }
        }
    }

    @Override
    public void removeRowBreak(int row) {
        if (this.worksheet.isSetRowBreaks()) {
            this.removeBreak(row, this.worksheet.getRowBreaks());
        }
    }

    @Override
    public void setForceFormulaRecalculation(boolean value) {
        CTCalcPr calcPr = this.getWorkbook().getCTWorkbook().getCalcPr();
        if (this.worksheet.isSetSheetCalcPr()) {
            CTSheetCalcPr calc = this.worksheet.getSheetCalcPr();
            calc.setFullCalcOnLoad(value);
        } else if (value) {
            CTSheetCalcPr calc = this.worksheet.addNewSheetCalcPr();
            calc.setFullCalcOnLoad(value);
        }
        if (value && calcPr != null && calcPr.getCalcMode() == STCalcMode.MANUAL) {
            calcPr.setCalcMode(STCalcMode.AUTO);
        }
    }

    @Override
    public boolean getForceFormulaRecalculation() {
        if (this.worksheet.isSetSheetCalcPr()) {
            CTSheetCalcPr calc = this.worksheet.getSheetCalcPr();
            return calc.getFullCalcOnLoad();
        }
        return false;
    }

    @Override
    public Iterator<Row> rowIterator() {
        return this._rows.values().iterator();
    }

    @Override
    public Iterator<Row> iterator() {
        return this.rowIterator();
    }

    @Override
    public boolean getAutobreaks() {
        CTSheetPr sheetPr = this.getSheetTypeSheetPr();
        CTPageSetUpPr psSetup = sheetPr == null || !sheetPr.isSetPageSetUpPr() ? CTPageSetUpPr.Factory.newInstance() : sheetPr.getPageSetUpPr();
        return psSetup.getAutoPageBreaks();
    }

    @Override
    public void setAutobreaks(boolean value) {
        CTSheetPr sheetPr = this.getSheetTypeSheetPr();
        CTPageSetUpPr psSetup = sheetPr.isSetPageSetUpPr() ? sheetPr.getPageSetUpPr() : sheetPr.addNewPageSetUpPr();
        psSetup.setAutoPageBreaks(value);
    }

    @Override
    public void setColumnBreak(int column) {
        if (!this.isColumnBroken(column)) {
            CTPageBreak pgBreak = this.worksheet.isSetColBreaks() ? this.worksheet.getColBreaks() : this.worksheet.addNewColBreaks();
            this.setBreak(column, pgBreak, SpreadsheetVersion.EXCEL2007.getLastRowIndex());
        }
    }

    @Override
    public void setColumnGroupCollapsed(int columnNumber, boolean collapsed) {
        if (collapsed) {
            this.collapseColumn(columnNumber);
        } else {
            this.expandColumn(columnNumber);
        }
    }

    private void collapseColumn(int columnNumber) {
        CTCol col;
        CTCols cols = this.worksheet.getColsArray(0);
        int colInfoIx = this.columnHelper.getIndexOfColumn(cols, col = this.columnHelper.getColumn(columnNumber, false));
        if (colInfoIx == -1) {
            return;
        }
        int groupStartColInfoIx = this.findStartOfColumnOutlineGroup(colInfoIx);
        CTCol columnInfo = cols.getColArray(groupStartColInfoIx);
        int lastColMax = this.setGroupHidden(groupStartColInfoIx, columnInfo.getOutlineLevel(), true);
        this.setColumn(lastColMax + 1, 0, null, null, Boolean.TRUE);
    }

    private void setColumn(int targetColumnIx, Integer style, Integer level, Boolean hidden, Boolean collapsed) {
        boolean columnChanged;
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol ci = null;
        for (CTCol tci : cols.getColArray()) {
            long tciMin = tci.getMin();
            long tciMax = tci.getMax();
            if (tciMin >= (long)targetColumnIx && tciMax <= (long)targetColumnIx) {
                ci = tci;
                break;
            }
            if (tciMin > (long)targetColumnIx) break;
        }
        if (ci == null) {
            CTCol nci = CTCol.Factory.newInstance();
            nci.setMin(targetColumnIx);
            nci.setMax(targetColumnIx);
            this.unsetCollapsed(collapsed, nci);
            this.columnHelper.addCleanColIntoCols(cols, nci);
            return;
        }
        boolean styleChanged = style != null && ci.getStyle() != (long)style.intValue();
        boolean levelChanged = level != null && ci.getOutlineLevel() != level.intValue();
        boolean hiddenChanged = hidden != null && ci.getHidden() != hidden.booleanValue();
        boolean collapsedChanged = collapsed != null && ci.getCollapsed() != collapsed.booleanValue();
        boolean bl = columnChanged = levelChanged || hiddenChanged || collapsedChanged || styleChanged;
        if (!columnChanged) {
            return;
        }
        long ciMin = ci.getMin();
        long ciMax = ci.getMax();
        if (ciMin == (long)targetColumnIx && ciMax == (long)targetColumnIx) {
            this.unsetCollapsed(collapsed, ci);
            return;
        }
        if (ciMin == (long)targetColumnIx || ciMax == (long)targetColumnIx) {
            if (ciMin == (long)targetColumnIx) {
                ci.setMin(targetColumnIx + 1);
            } else {
                ci.setMax(targetColumnIx - 1);
            }
            CTCol nci = this.columnHelper.cloneCol(cols, ci);
            nci.setMin(targetColumnIx);
            this.unsetCollapsed(collapsed, nci);
            this.columnHelper.addCleanColIntoCols(cols, nci);
        } else {
            CTCol ciMid = this.columnHelper.cloneCol(cols, ci);
            CTCol ciEnd = this.columnHelper.cloneCol(cols, ci);
            int lastcolumn = Math.toIntExact(ciMax);
            ci.setMax(targetColumnIx - 1);
            ciMid.setMin(targetColumnIx);
            ciMid.setMax(targetColumnIx);
            this.unsetCollapsed(collapsed, ciMid);
            this.columnHelper.addCleanColIntoCols(cols, ciMid);
            ciEnd.setMin(targetColumnIx + 1);
            ciEnd.setMax(lastcolumn);
            this.columnHelper.addCleanColIntoCols(cols, ciEnd);
        }
    }

    private void unsetCollapsed(Boolean collapsed, CTCol ci) {
        if (collapsed != null && collapsed.booleanValue()) {
            ci.setCollapsed(true);
        } else {
            ci.unsetCollapsed();
        }
    }

    private int setGroupHidden(int pIdx, int level, boolean hidden) {
        int idx;
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol[] colArray = cols.getColArray();
        CTCol columnInfo = colArray[idx];
        for (idx = pIdx; idx < colArray.length; ++idx) {
            columnInfo.setHidden(hidden);
            if (idx + 1 >= colArray.length) continue;
            CTCol nextColumnInfo = colArray[idx + 1];
            if (!this.isAdjacentBefore(columnInfo, nextColumnInfo) || nextColumnInfo.getOutlineLevel() < level) break;
            columnInfo = nextColumnInfo;
        }
        return Math.toIntExact(columnInfo.getMax());
    }

    private boolean isAdjacentBefore(CTCol col, CTCol otherCol) {
        return col.getMax() == otherCol.getMin() - 1L;
    }

    private int findStartOfColumnOutlineGroup(int pIdx) {
        CTCol prevColumnInfo;
        int idx;
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol[] colArray = cols.getColArray();
        CTCol columnInfo = colArray[pIdx];
        short level = columnInfo.getOutlineLevel();
        for (idx = pIdx; idx != 0 && this.isAdjacentBefore(prevColumnInfo = colArray[idx - 1], columnInfo) && prevColumnInfo.getOutlineLevel() >= level; --idx) {
            columnInfo = prevColumnInfo;
        }
        return idx;
    }

    private int findEndOfColumnOutlineGroup(int colInfoIndex) {
        CTCol nextColumnInfo;
        int idx;
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol[] colArray = cols.getColArray();
        CTCol columnInfo = colArray[colInfoIndex];
        short level = columnInfo.getOutlineLevel();
        int lastIdx = colArray.length - 1;
        for (idx = colInfoIndex; idx < lastIdx && this.isAdjacentBefore(columnInfo, nextColumnInfo = colArray[idx + 1]) && nextColumnInfo.getOutlineLevel() >= level; ++idx) {
            columnInfo = nextColumnInfo;
        }
        return idx;
    }

    private void expandColumn(int columnIndex) {
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol col = this.columnHelper.getColumn(columnIndex, false);
        int colInfoIx = this.columnHelper.getIndexOfColumn(cols, col);
        int idx = this.findColInfoIdx(Math.toIntExact(col.getMax()), colInfoIx);
        if (idx == -1) {
            return;
        }
        if (!this.isColumnGroupCollapsed(idx)) {
            return;
        }
        int startIdx = this.findStartOfColumnOutlineGroup(idx);
        int endIdx = this.findEndOfColumnOutlineGroup(idx);
        CTCol[] colArray = cols.getColArray();
        CTCol columnInfo = colArray[endIdx];
        if (!this.isColumnGroupHiddenByParent(idx)) {
            short outlineLevel = columnInfo.getOutlineLevel();
            boolean nestedGroup = false;
            for (int i = startIdx; i <= endIdx; ++i) {
                CTCol ci = colArray[i];
                if (outlineLevel == ci.getOutlineLevel()) {
                    ci.unsetHidden();
                    if (!nestedGroup) continue;
                    nestedGroup = false;
                    ci.setCollapsed(true);
                    continue;
                }
                nestedGroup = true;
            }
        }
        this.setColumn(Math.toIntExact(columnInfo.getMax() + 1L), null, null, Boolean.FALSE, Boolean.FALSE);
    }

    private boolean isColumnGroupHiddenByParent(int idx) {
        CTCol prevInfo;
        CTCol nextInfo;
        CTCol[] colArray;
        CTCols cols = this.worksheet.getColsArray(0);
        short endLevel = 0;
        boolean endHidden = false;
        int endOfOutlineGroupIdx = this.findEndOfColumnOutlineGroup(idx);
        if (endOfOutlineGroupIdx < (colArray = cols.getColArray()).length - 1 && this.isAdjacentBefore(colArray[endOfOutlineGroupIdx], nextInfo = colArray[endOfOutlineGroupIdx + 1])) {
            endLevel = nextInfo.getOutlineLevel();
            endHidden = nextInfo.getHidden();
        }
        short startLevel = 0;
        boolean startHidden = false;
        int startOfOutlineGroupIdx = this.findStartOfColumnOutlineGroup(idx);
        if (startOfOutlineGroupIdx > 0 && this.isAdjacentBefore(prevInfo = colArray[startOfOutlineGroupIdx - 1], colArray[startOfOutlineGroupIdx])) {
            startLevel = prevInfo.getOutlineLevel();
            startHidden = prevInfo.getHidden();
        }
        if (endLevel > startLevel) {
            return endHidden;
        }
        return startHidden;
    }

    private int findColInfoIdx(int columnValue, int fromColInfoIdx) {
        CTCols cols = this.worksheet.getColsArray(0);
        if (columnValue < 0) {
            throw new IllegalArgumentException("column parameter out of range: " + columnValue);
        }
        if (fromColInfoIdx < 0) {
            throw new IllegalArgumentException("fromIdx parameter out of range: " + fromColInfoIdx);
        }
        CTCol[] colArray = cols.getColArray();
        for (int k = fromColInfoIdx; k < colArray.length; ++k) {
            CTCol ci = colArray[k];
            if (this.containsColumn(ci, columnValue)) {
                return k;
            }
            if (ci.getMin() > (long)fromColInfoIdx) break;
        }
        return -1;
    }

    private boolean containsColumn(CTCol col, int columnIndex) {
        return col.getMin() <= (long)columnIndex && (long)columnIndex <= col.getMax();
    }

    private boolean isColumnGroupCollapsed(int idx) {
        CTCols cols = this.worksheet.getColsArray(0);
        CTCol[] colArray = cols.getColArray();
        int endOfOutlineGroupIdx = this.findEndOfColumnOutlineGroup(idx);
        int nextColInfoIx = endOfOutlineGroupIdx + 1;
        if (nextColInfoIx >= colArray.length) {
            return false;
        }
        CTCol col = colArray[endOfOutlineGroupIdx];
        CTCol nextColInfo = colArray[nextColInfoIx];
        if (!this.isAdjacentBefore(col, nextColInfo)) {
            return false;
        }
        return nextColInfo.getCollapsed();
    }

    @Override
    public void setColumnHidden(int columnIndex, boolean hidden) {
        this.columnHelper.setColHidden(columnIndex, hidden);
    }

    @Override
    public void setColumnWidth(int columnIndex, int width) {
        if (width > 65280) {
            throw new IllegalArgumentException("The maximum column width for an individual cell is 255 characters.");
        }
        this.columnHelper.setColWidth(columnIndex, (double)width / 256.0);
        this.columnHelper.setCustomWidth(columnIndex, true);
    }

    @Override
    public void setDefaultColumnStyle(int column, CellStyle style) {
        this.columnHelper.setColDefaultStyle((long)column, style);
    }

    @Override
    public void setDefaultColumnWidth(int width) {
        this.getSheetTypeSheetFormatPr().setBaseColWidth(width);
    }

    @Override
    public void setDefaultRowHeight(short height) {
        this.setDefaultRowHeightInPoints((float)height / 20.0f);
    }

    @Override
    public void setDefaultRowHeightInPoints(float height) {
        CTSheetFormatPr pr = this.getSheetTypeSheetFormatPr();
        pr.setDefaultRowHeight(height);
        pr.setCustomHeight(true);
    }

    @Override
    public void setDisplayFormulas(boolean show) {
        CTSheetView dsv = this.getDefaultSheetView(true);
        assert (dsv != null);
        dsv.setShowFormulas(show);
    }

    @Override
    public void setFitToPage(boolean b) {
        this.getSheetTypePageSetUpPr().setFitToPage(b);
    }

    @Override
    public void setHorizontallyCenter(boolean value) {
        CTPrintOptions opts = this.worksheet.isSetPrintOptions() ? this.worksheet.getPrintOptions() : this.worksheet.addNewPrintOptions();
        opts.setHorizontalCentered(value);
    }

    @Override
    public void setVerticallyCenter(boolean value) {
        CTPrintOptions opts = this.worksheet.isSetPrintOptions() ? this.worksheet.getPrintOptions() : this.worksheet.addNewPrintOptions();
        opts.setVerticalCentered(value);
    }

    @Override
    public void setRowGroupCollapsed(int rowIndex, boolean collapse) {
        if (collapse) {
            this.collapseRow(rowIndex);
        } else {
            this.expandRow(rowIndex);
        }
    }

    private void collapseRow(int rowIndex) {
        XSSFRow row = this.getRow(rowIndex);
        if (row != null) {
            int startRow = this.findStartOfRowOutlineGroup(rowIndex);
            int lastRow = this.writeHidden(row, startRow, true);
            if (this.getRow(lastRow) != null) {
                this.getRow(lastRow).getCTRow().setCollapsed(true);
            } else {
                XSSFRow newRow = this.createRow(lastRow);
                newRow.getCTRow().setCollapsed(true);
            }
        }
    }

    private int findStartOfRowOutlineGroup(int rowIndex) {
        short level = this.getRow(rowIndex).getCTRow().getOutlineLevel();
        int currentRow = rowIndex;
        while (this.getRow(currentRow) != null) {
            if (this.getRow(currentRow).getCTRow().getOutlineLevel() < level) {
                return currentRow + 1;
            }
            --currentRow;
        }
        return currentRow;
    }

    private int writeHidden(XSSFRow xRow, int rowIndex, boolean hidden) {
        short level = xRow.getCTRow().getOutlineLevel();
        Iterator<Row> it = this.rowIterator();
        while (it.hasNext()) {
            xRow = (XSSFRow)it.next();
            if (xRow.getRowNum() < rowIndex || xRow.getCTRow().getOutlineLevel() < level) continue;
            xRow.getCTRow().setHidden(hidden);
            ++rowIndex;
        }
        return rowIndex;
    }

    private void expandRow(int rowNumber) {
        CTRow ctRow;
        if (rowNumber == -1) {
            return;
        }
        XSSFRow row = this.getRow(rowNumber);
        if (!row.getCTRow().isSetHidden()) {
            return;
        }
        int startIdx = this.findStartOfRowOutlineGroup(rowNumber);
        int endIdx = this.findEndOfRowOutlineGroup(rowNumber);
        short level = row.getCTRow().getOutlineLevel();
        if (!this.isRowGroupHiddenByParent(rowNumber)) {
            for (int i = startIdx; i < endIdx; ++i) {
                if (level == this.getRow(i).getCTRow().getOutlineLevel()) {
                    this.getRow(i).getCTRow().unsetHidden();
                    continue;
                }
                if (this.isRowGroupCollapsed(i)) continue;
                this.getRow(i).getCTRow().unsetHidden();
            }
        }
        if ((ctRow = this.getRow(endIdx).getCTRow()).getCollapsed()) {
            ctRow.unsetCollapsed();
        }
    }

    public int findEndOfRowOutlineGroup(int row) {
        int currentRow;
        short level = this.getRow(row).getCTRow().getOutlineLevel();
        int lastRowNum = this.getLastRowNum();
        for (currentRow = row; currentRow < lastRowNum && this.getRow(currentRow) != null && this.getRow(currentRow).getCTRow().getOutlineLevel() >= level; ++currentRow) {
        }
        return currentRow;
    }

    private boolean isRowGroupHiddenByParent(int row) {
        boolean startHidden;
        short startLevel;
        boolean endHidden;
        short endLevel;
        int endOfOutlineGroupIdx = this.findEndOfRowOutlineGroup(row);
        if (this.getRow(endOfOutlineGroupIdx) == null) {
            endLevel = 0;
            endHidden = false;
        } else {
            endLevel = this.getRow(endOfOutlineGroupIdx).getCTRow().getOutlineLevel();
            endHidden = this.getRow(endOfOutlineGroupIdx).getCTRow().getHidden();
        }
        int startOfOutlineGroupIdx = this.findStartOfRowOutlineGroup(row);
        if (startOfOutlineGroupIdx < 0 || this.getRow(startOfOutlineGroupIdx) == null) {
            startLevel = 0;
            startHidden = false;
        } else {
            startLevel = this.getRow(startOfOutlineGroupIdx).getCTRow().getOutlineLevel();
            startHidden = this.getRow(startOfOutlineGroupIdx).getCTRow().getHidden();
        }
        if (endLevel > startLevel) {
            return endHidden;
        }
        return startHidden;
    }

    private boolean isRowGroupCollapsed(int row) {
        int collapseRow = this.findEndOfRowOutlineGroup(row) + 1;
        if (this.getRow(collapseRow) == null) {
            return false;
        }
        return this.getRow(collapseRow).getCTRow().getCollapsed();
    }

    @Override
    public void setZoom(int scale) {
        if (scale < 10 || scale > 400) {
            throw new IllegalArgumentException("Valid scale values range from 10 to 400");
        }
        CTSheetView dsv = this.getDefaultSheetView(true);
        assert (dsv != null);
        dsv.setZoomScale(scale);
    }

    public void copyRows(List<? extends Row> srcRows, int destStartRow, CellCopyPolicy policy) {
        if (srcRows == null || srcRows.size() == 0) {
            throw new IllegalArgumentException("No rows to copy");
        }
        Row srcStartRow = srcRows.get(0);
        Row srcEndRow = srcRows.get(srcRows.size() - 1);
        if (srcStartRow == null) {
            throw new IllegalArgumentException("copyRows: First row cannot be null");
        }
        int srcStartRowNum = srcStartRow.getRowNum();
        int srcEndRowNum = srcEndRow.getRowNum();
        int size = srcRows.size();
        for (int index = 1; index < size; ++index) {
            Row curRow = srcRows.get(index);
            if (curRow == null) {
                throw new IllegalArgumentException("srcRows may not contain null rows. Found null row at index " + index + ".");
            }
            if (srcStartRow.getSheet().getWorkbook() != curRow.getSheet().getWorkbook()) {
                throw new IllegalArgumentException("All rows in srcRows must belong to the same sheet in the same workbook. Expected all rows from same workbook (" + srcStartRow.getSheet().getWorkbook() + "). Got srcRows[" + index + "] from different workbook (" + curRow.getSheet().getWorkbook() + ").");
            }
            if (srcStartRow.getSheet() == curRow.getSheet()) continue;
            throw new IllegalArgumentException("All rows in srcRows must belong to the same sheet. Expected all rows from " + srcStartRow.getSheet().getSheetName() + ". Got srcRows[" + index + "] from " + curRow.getSheet().getSheetName());
        }
        CellCopyPolicy options = new CellCopyPolicy(policy);
        options.setCopyMergedRegions(false);
        int r = destStartRow;
        for (Row row : srcRows) {
            int destRowNum;
            if (policy.isCondenseRows()) {
                destRowNum = r++;
            } else {
                int shift = row.getRowNum() - srcStartRowNum;
                destRowNum = destStartRow + shift;
            }
            XSSFRow destRow = this.createRow(destRowNum);
            destRow.copyRowFrom(row, options);
        }
        if (policy.isCopyMergedRegions()) {
            int shift = destStartRow - srcStartRowNum;
            for (CellRangeAddress srcRegion : srcStartRow.getSheet().getMergedRegions()) {
                if (srcStartRowNum > srcRegion.getFirstRow() || srcRegion.getLastRow() > srcEndRowNum) continue;
                CellRangeAddress destRegion = srcRegion.copy();
                destRegion.setFirstRow(destRegion.getFirstRow() + shift);
                destRegion.setLastRow(destRegion.getLastRow() + shift);
                this.addMergedRegion(destRegion);
            }
        }
    }

    public void copyRows(int srcStartRow, int srcEndRow, int destStartRow, CellCopyPolicy cellCopyPolicy) {
        List<XSSFRow> srcRows = this.getRows(srcStartRow, srcEndRow, false);
        this.copyRows(srcRows, destStartRow, cellCopyPolicy);
    }

    @Override
    public void shiftRows(int startRow, int endRow, int n) {
        this.shiftRows(startRow, endRow, n, false, false);
    }

    @Override
    public void shiftRows(int startRow, int endRow, int n, boolean copyRowHeight, boolean resetOriginalRowHeight) {
        XSSFVMLDrawing vml = this.getVMLDrawing(false);
        int sheetIndex = this.getWorkbook().getSheetIndex(this);
        String sheetName = this.getWorkbook().getSheetName(sheetIndex);
        FormulaShifter formulaShifter = FormulaShifter.createForRowShift(sheetIndex, sheetName, startRow, endRow, n, SpreadsheetVersion.EXCEL2007);
        this.removeOverwritten(vml, startRow, endRow, n);
        this.shiftCommentsAndRows(vml, startRow, endRow, n);
        XSSFRowShifter rowShifter = new XSSFRowShifter(this);
        rowShifter.shiftMergedRegions(startRow, endRow, n);
        rowShifter.updateNamedRanges(formulaShifter);
        rowShifter.updateFormulas(formulaShifter);
        rowShifter.updateConditionalFormatting(formulaShifter);
        rowShifter.updateHyperlinks(formulaShifter);
        this.rebuildRows();
    }

    @Override
    public void shiftColumns(int startColumn, int endColumn, int n) {
        XSSFVMLDrawing vml = this.getVMLDrawing(false);
        this.shiftCommentsForColumns(vml, startColumn, endColumn, n);
        FormulaShifter formulaShifter = FormulaShifter.createForColumnShift(this.getWorkbook().getSheetIndex(this), this.getSheetName(), startColumn, endColumn, n, SpreadsheetVersion.EXCEL2007);
        XSSFColumnShifter columnShifter = new XSSFColumnShifter(this);
        columnShifter.shiftColumns(startColumn, endColumn, n);
        columnShifter.shiftMergedRegions(startColumn, startColumn, n);
        columnShifter.updateFormulas(formulaShifter);
        columnShifter.updateConditionalFormatting(formulaShifter);
        columnShifter.updateHyperlinks(formulaShifter);
        columnShifter.updateNamedRanges(formulaShifter);
        this.rebuildRows();
    }

    private void rebuildRows() {
        ArrayList<XSSFRow> rowList = new ArrayList<XSSFRow>(this._rows.values());
        this._rows.clear();
        for (XSSFRow r : rowList) {
            Integer rownumI = new Integer(r.getRowNum());
            this._rows.put(rownumI, r);
        }
    }

    private void removeOverwritten(XSSFVMLDrawing vml, int startRow, int endRow, int n) {
        Iterator<Row> it = this.rowIterator();
        while (it.hasNext()) {
            XSSFRow row = (XSSFRow)it.next();
            int rownum = row.getRowNum();
            if (!XSSFSheet.shouldRemoveRow(startRow, endRow, n, rownum)) continue;
            Integer rownumI = row.getRowNum();
            int idx = this._rows.headMap(rownumI).size();
            this.worksheet.getSheetData().removeRow(idx);
            it.remove();
            if (this.sheetComments != null) {
                CTCommentList lst = this.sheetComments.getCTComments().getCommentList();
                for (CTComment comment : lst.getCommentArray()) {
                    String strRef = comment.getRef();
                    CellAddress ref = new CellAddress(strRef);
                    if (ref.getRow() != rownum) continue;
                    this.sheetComments.removeComment(ref);
                    vml.removeCommentShape(ref.getRow(), ref.getColumn());
                }
            }
            if (this.hyperlinks == null) continue;
            for (XSSFHyperlink link : new ArrayList<XSSFHyperlink>(this.hyperlinks)) {
                CellReference ref = new CellReference(link.getCellRef());
                if (ref.getRow() != rownum) continue;
                this.hyperlinks.remove(link);
            }
        }
    }

    private void shiftCommentsAndRows(XSSFVMLDrawing vml, int startRow, int endRow, int n) {
        TreeMap<XSSFComment, Integer> commentsToShift = new TreeMap<XSSFComment, Integer>((o1, o2) -> {
            int row2;
            int row1 = o1.getRow();
            if (row1 == (row2 = o2.getRow())) {
                return o1.hashCode() - o2.hashCode();
            }
            if (n > 0) {
                return row1 < row2 ? 1 : -1;
            }
            return row1 > row2 ? 1 : -1;
        });
        Iterator<Row> it = this.rowIterator();
        while (it.hasNext()) {
            int newrownum;
            XSSFRow row = (XSSFRow)it.next();
            int rownum = row.getRowNum();
            if (this.sheetComments != null && (newrownum = this.shiftedRowNum(startRow, endRow, n, rownum)) != rownum) {
                CTCommentList lst = this.sheetComments.getCTComments().getCommentList();
                for (CTComment comment : lst.getCommentArray()) {
                    String oldRef = comment.getRef();
                    CellReference ref = new CellReference(oldRef);
                    if (ref.getRow() != rownum) continue;
                    XSSFComment xssfComment = new XSSFComment(this.sheetComments, comment, vml == null ? null : vml.findCommentShape(rownum, ref.getCol()));
                    commentsToShift.put(xssfComment, newrownum);
                }
            }
            if (rownum < startRow || rownum > endRow) continue;
            row.shift(n);
        }
        for (Map.Entry entry : commentsToShift.entrySet()) {
            ((XSSFComment)entry.getKey()).setRow((Integer)entry.getValue());
        }
        this.rebuildRows();
    }

    private int shiftedRowNum(int startRow, int endRow, int n, int rownum) {
        if (rownum < startRow && (n > 0 || startRow - rownum > n)) {
            return rownum;
        }
        if (rownum > endRow && (n < 0 || rownum - endRow > n)) {
            return rownum;
        }
        if (rownum < startRow) {
            return rownum + (endRow - startRow);
        }
        if (rownum > endRow) {
            return rownum - (endRow - startRow);
        }
        return rownum + n;
    }

    private void shiftCommentsForColumns(XSSFVMLDrawing vml, int startColumnIndex, int endColumnIndex, int n) {
        TreeMap<XSSFComment, Integer> commentsToShift = new TreeMap<XSSFComment, Integer>((o1, o2) -> {
            int column2;
            int column1 = o1.getColumn();
            if (column1 == (column2 = o2.getColumn())) {
                return o1.hashCode() - o2.hashCode();
            }
            if (n > 0) {
                return column1 < column2 ? 1 : -1;
            }
            return column1 > column2 ? 1 : -1;
        });
        if (this.sheetComments != null) {
            CTCommentList lst = this.sheetComments.getCTComments().getCommentList();
            for (CTComment comment : lst.getCommentArray()) {
                String oldRef = comment.getRef();
                CellReference ref = new CellReference(oldRef);
                short columnIndex = ref.getCol();
                int newColumnIndex = this.shiftedRowNum(startColumnIndex, endColumnIndex, n, columnIndex);
                if (newColumnIndex == columnIndex) continue;
                XSSFComment xssfComment = new XSSFComment(this.sheetComments, comment, vml == null ? null : vml.findCommentShape(ref.getRow(), columnIndex));
                commentsToShift.put(xssfComment, newColumnIndex);
            }
        }
        for (Map.Entry entry : commentsToShift.entrySet()) {
            ((XSSFComment)entry.getKey()).setColumn((Integer)entry.getValue());
        }
        this.rebuildRows();
    }

    @Override
    public void showInPane(int topRow, int leftCol) {
        CellReference cellReference = new CellReference(topRow, leftCol);
        String cellRef = cellReference.formatAsString();
        CTPane pane = this.getPane(true);
        assert (pane != null);
        pane.setTopLeftCell(cellRef);
    }

    @Override
    public void ungroupColumn(int fromColumn, int toColumn) {
        CTCols cols = this.worksheet.getColsArray(0);
        for (int index = fromColumn; index <= toColumn; ++index) {
            CTCol col = this.columnHelper.getColumn(index, false);
            if (col == null) continue;
            short outlineLevel = col.getOutlineLevel();
            col.setOutlineLevel((short)(outlineLevel - 1));
            index = Math.toIntExact(col.getMax());
            if (col.getOutlineLevel() > 0) continue;
            int colIndex = this.columnHelper.getIndexOfColumn(cols, col);
            this.worksheet.getColsArray(0).removeCol(colIndex);
        }
        this.worksheet.setColsArray(0, cols);
        this.setSheetFormatPrOutlineLevelCol();
    }

    @Override
    public void ungroupRow(int fromRow, int toRow) {
        for (int i = fromRow; i <= toRow; ++i) {
            XSSFRow xrow = this.getRow(i);
            if (xrow == null) continue;
            CTRow ctRow = xrow.getCTRow();
            short outlineLevel = ctRow.getOutlineLevel();
            ctRow.setOutlineLevel((short)(outlineLevel - 1));
            if (outlineLevel != 1 || xrow.getFirstCellNum() != -1) continue;
            this.removeRow(xrow);
        }
        this.setSheetFormatPrOutlineLevelRow();
    }

    private void setSheetFormatPrOutlineLevelRow() {
        short maxLevelRow = this.getMaxOutlineLevelRows();
        this.getSheetTypeSheetFormatPr().setOutlineLevelRow(maxLevelRow);
    }

    private void setSheetFormatPrOutlineLevelCol() {
        short maxLevelCol = this.getMaxOutlineLevelCols();
        this.getSheetTypeSheetFormatPr().setOutlineLevelCol(maxLevelCol);
    }

    protected CTSheetViews getSheetTypeSheetViews(boolean create) {
        CTSheetViews views;
        CTSheetViews cTSheetViews = views = this.worksheet.isSetSheetViews() || !create ? this.worksheet.getSheetViews() : this.worksheet.addNewSheetViews();
        assert (views != null || !create);
        if (views == null) {
            return null;
        }
        if (views.sizeOfSheetViewArray() == 0 && create) {
            views.addNewSheetView();
        }
        return views;
    }

    @Override
    public boolean isSelected() {
        CTSheetView dsv = this.getDefaultSheetView(false);
        return dsv != null && dsv.getTabSelected();
    }

    @Override
    public void setSelected(boolean value) {
        CTSheetViews views = this.getSheetTypeSheetViews(true);
        assert (views != null);
        for (CTSheetView view : views.getSheetViewArray()) {
            view.setTabSelected(value);
        }
    }

    @Internal
    public void addHyperlink(XSSFHyperlink hyperlink) {
        this.hyperlinks.add(hyperlink);
    }

    @Internal
    public void removeHyperlink(int row, int column) {
        String ref = new CellReference(row, column).formatAsString();
        Iterator<XSSFHyperlink> it = this.hyperlinks.iterator();
        while (it.hasNext()) {
            XSSFHyperlink hyperlink = it.next();
            if (!hyperlink.getCellRef().equals(ref)) continue;
            it.remove();
            return;
        }
    }

    @Override
    public CellAddress getActiveCell() {
        CTSelection sts = this.getSheetTypeSelection(false);
        String address = sts != null ? sts.getActiveCell() : null;
        return address != null ? new CellAddress(address) : null;
    }

    @Override
    public void setActiveCell(CellAddress address) {
        CTSelection ctsel = this.getSheetTypeSelection(true);
        assert (ctsel != null);
        String ref = address.formatAsString();
        ctsel.setActiveCell(ref);
        ctsel.setSqref(Collections.singletonList(ref));
    }

    public boolean hasComments() {
        return this.sheetComments != null && this.sheetComments.getNumberOfComments() > 0;
    }

    protected int getNumberOfComments() {
        return this.sheetComments == null ? 0 : this.sheetComments.getNumberOfComments();
    }

    private CTSelection getSheetTypeSelection(boolean create) {
        CTSheetView dsv = this.getDefaultSheetView(create);
        assert (dsv != null || !create);
        if (dsv == null) {
            return null;
        }
        int sz = dsv.sizeOfSelectionArray();
        if (sz == 0) {
            return create ? dsv.addNewSelection() : null;
        }
        return dsv.getSelectionArray(sz - 1);
    }

    private CTSheetView getDefaultSheetView(boolean create) {
        CTSheetViews views = this.getSheetTypeSheetViews(create);
        assert (views != null || !create);
        if (views == null) {
            return null;
        }
        int sz = views.sizeOfSheetViewArray();
        assert (sz > 0 || !create);
        return sz == 0 ? null : views.getSheetViewArray(sz - 1);
    }

    protected CommentsTable getCommentsTable(boolean create) {
        if (this.sheetComments == null && create) {
            try {
                this.sheetComments = (CommentsTable)this.createRelationship(XSSFRelation.SHEET_COMMENTS, XSSFFactory.getInstance(), Math.toIntExact(this.sheet.getSheetId()));
            } catch (PartAlreadyExistsException e) {
                this.sheetComments = (CommentsTable)this.createRelationship(XSSFRelation.SHEET_COMMENTS, XSSFFactory.getInstance(), -1);
            }
        }
        return this.sheetComments;
    }

    private CTPageSetUpPr getSheetTypePageSetUpPr() {
        CTSheetPr sheetPr = this.getSheetTypeSheetPr();
        return sheetPr.isSetPageSetUpPr() ? sheetPr.getPageSetUpPr() : sheetPr.addNewPageSetUpPr();
    }

    private static boolean shouldRemoveRow(int startRow, int endRow, int n, int rownum) {
        if (rownum >= startRow + n && rownum <= endRow + n) {
            if (n > 0 && rownum > endRow) {
                return true;
            }
            return n < 0 && rownum < startRow;
        }
        return false;
    }

    private CTPane getPane(boolean create) {
        CTSheetView dsv = this.getDefaultSheetView(create);
        assert (dsv != null || !create);
        if (dsv == null) {
            return null;
        }
        return dsv.isSetPane() || !create ? dsv.getPane() : dsv.addNewPane();
    }

    @Internal
    public CTCellFormula getSharedFormula(int sid) {
        return this.sharedFormulas.get(sid);
    }

    void onReadCell(XSSFCell cell) {
        CTCell ct = cell.getCTCell();
        CTCellFormula f = ct.getF();
        if (f != null && f.getT() == STCellFormulaType.SHARED && f.isSetRef() && f.getStringValue() != null) {
            CTCellFormula sf = (CTCellFormula)f.copy();
            CellRangeAddress sfRef = CellRangeAddress.valueOf(sf.getRef());
            CellReference cellRef = new CellReference(cell);
            if (cellRef.getCol() > sfRef.getFirstColumn() || cellRef.getRow() > sfRef.getFirstRow()) {
                String effectiveRef = new CellRangeAddress(Math.max(cellRef.getRow(), sfRef.getFirstRow()), sfRef.getLastRow(), Math.max(cellRef.getCol(), sfRef.getFirstColumn()), sfRef.getLastColumn()).formatAsString();
                sf.setRef(effectiveRef);
            }
            this.sharedFormulas.put(Math.toIntExact(f.getSi()), sf);
        }
        if (f != null && f.getT() == STCellFormulaType.ARRAY && f.getRef() != null) {
            this.arrayFormulas.add(CellRangeAddress.valueOf(f.getRef()));
        }
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.write(out);
        out.close();
    }

    protected void write(OutputStream out) throws IOException {
        int i;
        boolean setToNull = false;
        if (this.worksheet.sizeOfColsArray() == 1) {
            CTCols col = this.worksheet.getColsArray(0);
            if (col.sizeOfColArray() == 0) {
                setToNull = true;
                this.worksheet.setColsArray(null);
            } else {
                this.setColWidthAttribute(col);
            }
        }
        if (this.hyperlinks.size() > 0) {
            if (this.worksheet.getHyperlinks() == null) {
                this.worksheet.addNewHyperlinks();
            }
            CTHyperlink[] ctHls = new CTHyperlink[this.hyperlinks.size()];
            for (i = 0; i < ctHls.length; ++i) {
                XSSFHyperlink hyperlink = this.hyperlinks.get(i);
                hyperlink.generateRelationIfNeeded(this.getPackagePart());
                ctHls[i] = hyperlink.getCTHyperlink();
            }
            this.worksheet.getHyperlinks().setHyperlinkArray(ctHls);
        } else if (this.worksheet.getHyperlinks() != null) {
            int count = this.worksheet.getHyperlinks().sizeOfHyperlinkArray();
            for (i = count - 1; i >= 0; --i) {
                this.worksheet.getHyperlinks().removeHyperlink(i);
            }
            this.worksheet.unsetHyperlinks();
        }
        int minCell = Integer.MAX_VALUE;
        int maxCell = Integer.MIN_VALUE;
        for (Map.Entry<Integer, XSSFRow> entry : this._rows.entrySet()) {
            XSSFRow row = entry.getValue();
            row.onDocumentWrite();
            if (row.getFirstCellNum() != -1) {
                minCell = Math.min(minCell, row.getFirstCellNum());
            }
            if (row.getLastCellNum() == -1) continue;
            maxCell = Math.max(maxCell, row.getLastCellNum() - 1);
        }
        if (minCell != Integer.MAX_VALUE) {
            String ref = new CellRangeAddress(this.getFirstRowNum(), this.getLastRowNum(), minCell, maxCell).formatAsString();
            if (this.worksheet.isSetDimension()) {
                this.worksheet.getDimension().setRef(ref);
            } else {
                this.worksheet.addNewDimension().setRef(ref);
            }
        }
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTWorksheet.type.getName().getNamespaceURI(), "worksheet"));
        this.worksheet.save(out, xmlOptions);
        if (setToNull) {
            this.worksheet.addNewCols();
        }
    }

    public boolean isAutoFilterLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getAutoFilter();
    }

    public boolean isDeleteColumnsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getDeleteColumns();
    }

    public boolean isDeleteRowsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getDeleteRows();
    }

    public boolean isFormatCellsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getFormatCells();
    }

    public boolean isFormatColumnsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getFormatColumns();
    }

    public boolean isFormatRowsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getFormatRows();
    }

    public boolean isInsertColumnsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getInsertColumns();
    }

    public boolean isInsertHyperlinksLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getInsertHyperlinks();
    }

    public boolean isInsertRowsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getInsertRows();
    }

    public boolean isPivotTablesLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getPivotTables();
    }

    public boolean isSortLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getSort();
    }

    public boolean isObjectsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getObjects();
    }

    public boolean isScenariosLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getScenarios();
    }

    public boolean isSelectLockedCellsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getSelectLockedCells();
    }

    public boolean isSelectUnlockedCellsLocked() {
        return this.isSheetLocked() && this.safeGetProtectionField().getSelectUnlockedCells();
    }

    public boolean isSheetLocked() {
        return this.worksheet.isSetSheetProtection() && this.safeGetProtectionField().getSheet();
    }

    public void enableLocking() {
        this.safeGetProtectionField().setSheet(true);
    }

    public void disableLocking() {
        this.safeGetProtectionField().setSheet(false);
    }

    public void lockAutoFilter(boolean enabled) {
        this.safeGetProtectionField().setAutoFilter(enabled);
    }

    public void lockDeleteColumns(boolean enabled) {
        this.safeGetProtectionField().setDeleteColumns(enabled);
    }

    public void lockDeleteRows(boolean enabled) {
        this.safeGetProtectionField().setDeleteRows(enabled);
    }

    public void lockFormatCells(boolean enabled) {
        this.safeGetProtectionField().setFormatCells(enabled);
    }

    public void lockFormatColumns(boolean enabled) {
        this.safeGetProtectionField().setFormatColumns(enabled);
    }

    public void lockFormatRows(boolean enabled) {
        this.safeGetProtectionField().setFormatRows(enabled);
    }

    public void lockInsertColumns(boolean enabled) {
        this.safeGetProtectionField().setInsertColumns(enabled);
    }

    public void lockInsertHyperlinks(boolean enabled) {
        this.safeGetProtectionField().setInsertHyperlinks(enabled);
    }

    public void lockInsertRows(boolean enabled) {
        this.safeGetProtectionField().setInsertRows(enabled);
    }

    public void lockPivotTables(boolean enabled) {
        this.safeGetProtectionField().setPivotTables(enabled);
    }

    public void lockSort(boolean enabled) {
        this.safeGetProtectionField().setSort(enabled);
    }

    public void lockObjects(boolean enabled) {
        this.safeGetProtectionField().setObjects(enabled);
    }

    public void lockScenarios(boolean enabled) {
        this.safeGetProtectionField().setScenarios(enabled);
    }

    public void lockSelectLockedCells(boolean enabled) {
        this.safeGetProtectionField().setSelectLockedCells(enabled);
    }

    public void lockSelectUnlockedCells(boolean enabled) {
        this.safeGetProtectionField().setSelectUnlockedCells(enabled);
    }

    private CTSheetProtection safeGetProtectionField() {
        if (!this.isSheetProtectionEnabled()) {
            return this.worksheet.addNewSheetProtection();
        }
        return this.worksheet.getSheetProtection();
    }

    boolean isSheetProtectionEnabled() {
        return this.worksheet.isSetSheetProtection();
    }

    boolean isCellInArrayFormulaContext(XSSFCell cell) {
        for (CellRangeAddress range : this.arrayFormulas) {
            if (!range.isInRange(cell.getRowIndex(), cell.getColumnIndex())) continue;
            return true;
        }
        return false;
    }

    XSSFCell getFirstCellInArrayFormula(XSSFCell cell) {
        for (CellRangeAddress range : this.arrayFormulas) {
            if (!range.isInRange(cell.getRowIndex(), cell.getColumnIndex())) continue;
            return this.getRow(range.getFirstRow()).getCell(range.getFirstColumn());
        }
        return null;
    }

    private CellRange<XSSFCell> getCellRange(CellRangeAddress range) {
        int firstRow = range.getFirstRow();
        int firstColumn = range.getFirstColumn();
        int lastRow = range.getLastRow();
        int lastColumn = range.getLastColumn();
        int height = lastRow - firstRow + 1;
        int width = lastColumn - firstColumn + 1;
        ArrayList<XSSFCell> temp = new ArrayList<XSSFCell>(height * width);
        for (int rowIn = firstRow; rowIn <= lastRow; ++rowIn) {
            for (int colIn = firstColumn; colIn <= lastColumn; ++colIn) {
                XSSFCell cell;
                XSSFRow row = this.getRow(rowIn);
                if (row == null) {
                    row = this.createRow(rowIn);
                }
                if ((cell = row.getCell(colIn)) == null) {
                    cell = row.createCell(colIn);
                }
                temp.add(cell);
            }
        }
        return SSCellRange.create(firstRow, firstColumn, height, width, temp, XSSFCell.class);
    }

    public CellRange<XSSFCell> setArrayFormula(String formula, CellRangeAddress range) {
        CellRange<XSSFCell> cr = this.getCellRange(range);
        XSSFCell mainArrayFormulaCell = cr.getTopLeftCell();
        mainArrayFormulaCell.setCellArrayFormula(formula, range);
        this.arrayFormulas.add(range);
        return cr;
    }

    public CellRange<XSSFCell> removeArrayFormula(Cell cell) {
        if (cell.getSheet() != this) {
            throw new IllegalArgumentException("Specified cell does not belong to this sheet.");
        }
        for (CellRangeAddress range : this.arrayFormulas) {
            if (!range.isInRange(cell)) continue;
            this.arrayFormulas.remove(range);
            CellRange<XSSFCell> cr = this.getCellRange(range);
            for (XSSFCell c : cr) {
                c.setBlank();
            }
            return cr;
        }
        String ref = new CellReference(cell).formatAsString();
        throw new IllegalArgumentException("Cell " + ref + " is not part of an array formula.");
    }

    @Override
    public DataValidationHelper getDataValidationHelper() {
        return this.dataValidationHelper;
    }

    public List<XSSFDataValidation> getDataValidations() {
        ArrayList<XSSFDataValidation> xssfValidations = new ArrayList<XSSFDataValidation>();
        CTDataValidations dataValidations = this.worksheet.getDataValidations();
        if (dataValidations != null && dataValidations.getCount() > 0L) {
            for (CTDataValidation ctDataValidation : dataValidations.getDataValidationArray()) {
                CellRangeAddressList addressList = new CellRangeAddressList();
                List sqref = ctDataValidation.getSqref();
                for (String stRef : sqref) {
                    String[] regions;
                    for (String region : regions = stRef.split(" ")) {
                        String[] parts = region.split(":");
                        CellReference begin = new CellReference(parts[0]);
                        CellReference end = parts.length > 1 ? new CellReference(parts[1]) : begin;
                        CellRangeAddress cellRangeAddress = new CellRangeAddress(begin.getRow(), end.getRow(), begin.getCol(), end.getCol());
                        addressList.addCellRangeAddress(cellRangeAddress);
                    }
                }
                XSSFDataValidation xssfDataValidation = new XSSFDataValidation(addressList, ctDataValidation);
                xssfValidations.add(xssfDataValidation);
            }
        }
        return xssfValidations;
    }

    @Override
    public void addValidationData(DataValidation dataValidation) {
        XSSFDataValidation xssfDataValidation = (XSSFDataValidation)dataValidation;
        CTDataValidations dataValidations = this.worksheet.getDataValidations();
        if (dataValidations == null) {
            dataValidations = this.worksheet.addNewDataValidations();
        }
        int currentCount = dataValidations.sizeOfDataValidationArray();
        CTDataValidation newval = dataValidations.addNewDataValidation();
        newval.set(xssfDataValidation.getCtDdataValidation());
        dataValidations.setCount(currentCount + 1);
    }

    @Override
    public XSSFAutoFilter setAutoFilter(CellRangeAddress range) {
        CTAutoFilter af = this.worksheet.getAutoFilter();
        if (af == null) {
            af = this.worksheet.addNewAutoFilter();
        }
        CellRangeAddress norm = new CellRangeAddress(range.getFirstRow(), range.getLastRow(), range.getFirstColumn(), range.getLastColumn());
        String ref = norm.formatAsString();
        af.setRef(ref);
        XSSFWorkbook wb = this.getWorkbook();
        int sheetIndex = this.getWorkbook().getSheetIndex(this);
        XSSFName name = wb.getBuiltInName("_xlnm._FilterDatabase", sheetIndex);
        if (name == null) {
            name = wb.createBuiltInName("_xlnm._FilterDatabase", sheetIndex);
        }
        name.getCTName().setHidden(true);
        CellReference r1 = new CellReference(this.getSheetName(), range.getFirstRow(), range.getFirstColumn(), true, true);
        CellReference r2 = new CellReference(null, range.getLastRow(), range.getLastColumn(), true, true);
        String fmla = r1.formatAsString() + ":" + r2.formatAsString();
        name.setRefersToFormula(fmla);
        return new XSSFAutoFilter(this);
    }

    @Deprecated
    @Removal(version="4.2.0")
    public XSSFTable createTable() {
        return this.createTable(null);
    }

    public XSSFTable createTable(AreaReference tableArea) {
        if (!this.worksheet.isSetTableParts()) {
            this.worksheet.addNewTableParts();
        }
        CTTableParts tblParts = this.worksheet.getTableParts();
        CTTablePart tbl = tblParts.addNewTablePart();
        int tableNumber = this.getPackagePart().getPackage().getPartsByContentType(XSSFRelation.TABLE.getContentType()).size() + 1;
        boolean loop = true;
        while (loop) {
            loop = false;
            for (PackagePart packagePart : this.getPackagePart().getPackage().getPartsByContentType(XSSFRelation.TABLE.getContentType())) {
                String fileName = XSSFRelation.TABLE.getFileName(tableNumber);
                if (!fileName.equals(packagePart.getPartName().getName())) continue;
                ++tableNumber;
                loop = true;
            }
        }
        POIXMLDocumentPart.RelationPart rp = this.createRelationship(XSSFRelation.TABLE, XSSFFactory.getInstance(), tableNumber, false);
        XSSFTable table = (XSSFTable)rp.getDocumentPart();
        tbl.setId(rp.getRelationship().getId());
        table.getCTTable().setId(tableNumber);
        this.tables.put(tbl.getId(), table);
        if (tableArea != null) {
            table.setArea(tableArea);
        }
        while (tableNumber < Integer.MAX_VALUE) {
            String displayName = "Table" + tableNumber;
            if (this.getWorkbook().getTable(displayName) == null && this.getWorkbook().getName(displayName) == null) {
                table.setDisplayName(displayName);
                table.setName(displayName);
                break;
            }
            ++tableNumber;
        }
        return table;
    }

    public List<XSSFTable> getTables() {
        return new ArrayList<XSSFTable>(this.tables.values());
    }

    public void removeTable(XSSFTable t) {
        long id = t.getCTTable().getId();
        Map.Entry<String, XSSFTable> toDelete = null;
        for (Map.Entry<String, XSSFTable> entry : this.tables.entrySet()) {
            if (entry.getValue().getCTTable().getId() != id) continue;
            toDelete = entry;
        }
        if (toDelete != null) {
            this.removeRelation(this.getRelationById((String)toDelete.getKey()), true);
            this.tables.remove(toDelete.getKey());
            toDelete.getValue().onTableDelete();
        }
    }

    @Override
    public XSSFSheetConditionalFormatting getSheetConditionalFormatting() {
        return new XSSFSheetConditionalFormatting(this);
    }

    public XSSFColor getTabColor() {
        CTSheetPr pr = this.worksheet.getSheetPr();
        if (pr == null) {
            pr = this.worksheet.addNewSheetPr();
        }
        if (!pr.isSetTabColor()) {
            return null;
        }
        return XSSFColor.from(pr.getTabColor(), this.getWorkbook().getStylesSource().getIndexedColors());
    }

    public void setTabColor(XSSFColor color) {
        CTSheetPr pr = this.worksheet.getSheetPr();
        if (pr == null) {
            pr = this.worksheet.addNewSheetPr();
        }
        pr.setTabColor(color.getCTColor());
    }

    @Override
    public CellRangeAddress getRepeatingRows() {
        return this.getRepeatingRowsOrColums(true);
    }

    @Override
    public CellRangeAddress getRepeatingColumns() {
        return this.getRepeatingRowsOrColums(false);
    }

    @Override
    public void setRepeatingRows(CellRangeAddress rowRangeRef) {
        CellRangeAddress columnRangeRef = this.getRepeatingColumns();
        this.setRepeatingRowsAndColumns(rowRangeRef, columnRangeRef);
    }

    @Override
    public void setRepeatingColumns(CellRangeAddress columnRangeRef) {
        CellRangeAddress rowRangeRef = this.getRepeatingRows();
        this.setRepeatingRowsAndColumns(rowRangeRef, columnRangeRef);
    }

    private void setRepeatingRowsAndColumns(CellRangeAddress rowDef, CellRangeAddress colDef) {
        int col1 = -1;
        int col2 = -1;
        int row1 = -1;
        int row2 = -1;
        if (rowDef != null) {
            row1 = rowDef.getFirstRow();
            row2 = rowDef.getLastRow();
            if (row1 == -1 && row2 != -1 || row1 < -1 || row2 < -1 || row1 > row2) {
                throw new IllegalArgumentException("Invalid row range specification");
            }
        }
        if (colDef != null) {
            col1 = colDef.getFirstColumn();
            col2 = colDef.getLastColumn();
            if (col1 == -1 && col2 != -1 || col1 < -1 || col2 < -1 || col1 > col2) {
                throw new IllegalArgumentException("Invalid column range specification");
            }
        }
        int sheetIndex = this.getWorkbook().getSheetIndex(this);
        boolean removeAll = rowDef == null && colDef == null;
        XSSFName name = this.getWorkbook().getBuiltInName("_xlnm.Print_Titles", sheetIndex);
        if (removeAll) {
            if (name != null) {
                this.getWorkbook().removeName(name);
            }
            return;
        }
        if (name == null) {
            name = this.getWorkbook().createBuiltInName("_xlnm.Print_Titles", sheetIndex);
        }
        String reference = XSSFSheet.getReferenceBuiltInRecord(name.getSheetName(), col1, col2, row1, row2);
        name.setRefersToFormula(reference);
        if (!this.worksheet.isSetPageSetup() || !this.worksheet.isSetPageMargins()) {
            this.getPrintSetup().setValidSettings(false);
        }
    }

    private static String getReferenceBuiltInRecord(String sheetName, int startC, int endC, int startR, int endR) {
        CellReference colRef = new CellReference(sheetName, 0, startC, true, true);
        CellReference colRef2 = new CellReference(sheetName, 0, endC, true, true);
        CellReference rowRef = new CellReference(sheetName, startR, 0, true, true);
        CellReference rowRef2 = new CellReference(sheetName, endR, 0, true, true);
        String escapedName = SheetNameFormatter.format(sheetName);
        String c = "";
        String r = "";
        if (startC != -1 || endC != -1) {
            String col1 = colRef.getCellRefParts()[2];
            String col2 = colRef2.getCellRefParts()[2];
            c = escapedName + "!$" + col1 + ":$" + col2;
        }
        if (startR != -1 || endR != -1) {
            String row1 = rowRef.getCellRefParts()[1];
            String row2 = rowRef2.getCellRefParts()[1];
            if (!row1.equals("0") && !row2.equals("0")) {
                r = escapedName + "!$" + row1 + ":$" + row2;
            }
        }
        StringBuilder rng = new StringBuilder();
        rng.append(c);
        if (rng.length() > 0 && r.length() > 0) {
            rng.append(',');
        }
        rng.append(r);
        return rng.toString();
    }

    private CellRangeAddress getRepeatingRowsOrColums(boolean rows) {
        int sheetIndex = this.getWorkbook().getSheetIndex(this);
        XSSFName name = this.getWorkbook().getBuiltInName("_xlnm.Print_Titles", sheetIndex);
        if (name == null) {
            return null;
        }
        String refStr = name.getRefersToFormula();
        if (refStr == null) {
            return null;
        }
        String[] parts = refStr.split(",");
        int maxRowIndex = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        int maxColIndex = SpreadsheetVersion.EXCEL2007.getLastColumnIndex();
        for (String part : parts) {
            CellRangeAddress range = CellRangeAddress.valueOf(part);
            if (!(range.getFirstColumn() == 0 && range.getLastColumn() == maxColIndex || range.getFirstColumn() == -1 && range.getLastColumn() == -1 ? rows : (range.getFirstRow() == 0 && range.getLastRow() == maxRowIndex || range.getFirstRow() == -1 && range.getLastRow() == -1) && !rows)) continue;
            return range;
        }
        return null;
    }

    private XSSFPivotTable createPivotTable() {
        XSSFWorkbook wb = this.getWorkbook();
        List<XSSFPivotTable> pivotTables = wb.getPivotTables();
        int tableId = this.getWorkbook().getPivotTables().size() + 1;
        XSSFPivotTable pivotTable = (XSSFPivotTable)this.createRelationship(XSSFRelation.PIVOT_TABLE, XSSFFactory.getInstance(), tableId);
        pivotTable.setParentSheet(this);
        pivotTables.add(pivotTable);
        XSSFWorkbook workbook = this.getWorkbook();
        XSSFPivotCacheDefinition pivotCacheDefinition = (XSSFPivotCacheDefinition)workbook.createRelationship(XSSFRelation.PIVOT_CACHE_DEFINITION, XSSFFactory.getInstance(), tableId);
        String rId = workbook.getRelationId(pivotCacheDefinition);
        PackagePart pivotPackagePart = pivotTable.getPackagePart();
        pivotPackagePart.addRelationship(pivotCacheDefinition.getPackagePart().getPartName(), TargetMode.INTERNAL, XSSFRelation.PIVOT_CACHE_DEFINITION.getRelation());
        pivotTable.setPivotCacheDefinition(pivotCacheDefinition);
        pivotTable.setPivotCache(new XSSFPivotCache(workbook.addPivotCache(rId)));
        XSSFPivotCacheRecords pivotCacheRecords = (XSSFPivotCacheRecords)pivotCacheDefinition.createRelationship(XSSFRelation.PIVOT_CACHE_RECORDS, XSSFFactory.getInstance(), tableId);
        pivotTable.getPivotCacheDefinition().getCTPivotCacheDefinition().setId(pivotCacheDefinition.getRelationId(pivotCacheRecords));
        wb.setPivotTables(pivotTables);
        return pivotTable;
    }

    public XSSFPivotTable createPivotTable(AreaReference source, CellReference position, Sheet sourceSheet) {
        String sourceSheetName = source.getFirstCell().getSheetName();
        if (sourceSheetName != null && !sourceSheetName.equalsIgnoreCase(sourceSheet.getSheetName())) {
            throw new IllegalArgumentException("The area is referenced in another sheet than the defined source sheet " + sourceSheet.getSheetName() + ".");
        }
        return this.createPivotTable(position, sourceSheet, (CTWorksheetSource wsSource) -> {
            String[] firstCell = source.getFirstCell().getCellRefParts();
            String firstRow = firstCell[1];
            String firstCol = firstCell[2];
            String[] lastCell = source.getLastCell().getCellRefParts();
            String lastRow = lastCell[1];
            String lastCol = lastCell[2];
            String ref = firstCol + firstRow + ':' + lastCol + lastRow;
            wsSource.setRef(ref);
        });
    }

    private XSSFPivotTable createPivotTable(CellReference position, Sheet sourceSheet, XSSFPivotTable.PivotTableReferenceConfigurator refConfig) {
        XSSFPivotTable pivotTable = this.createPivotTable();
        pivotTable.setDefaultPivotTableDefinition();
        pivotTable.createSourceReferences(position, sourceSheet, refConfig);
        pivotTable.getPivotCacheDefinition().createCacheFields(sourceSheet);
        pivotTable.createDefaultDataColumns();
        return pivotTable;
    }

    public XSSFPivotTable createPivotTable(AreaReference source, CellReference position) {
        String sourceSheetName = source.getFirstCell().getSheetName();
        if (sourceSheetName != null && !sourceSheetName.equalsIgnoreCase(this.getSheetName())) {
            XSSFSheet sourceSheet = this.getWorkbook().getSheet(sourceSheetName);
            return this.createPivotTable(source, position, (Sheet)sourceSheet);
        }
        return this.createPivotTable(source, position, (Sheet)this);
    }

    public XSSFPivotTable createPivotTable(Name source, CellReference position, Sheet sourceSheet) {
        if (source.getSheetName() != null && !source.getSheetName().equals(sourceSheet.getSheetName())) {
            throw new IllegalArgumentException("The named range references another sheet than the defined source sheet " + sourceSheet.getSheetName() + ".");
        }
        return this.createPivotTable(position, sourceSheet, (CTWorksheetSource wsSource) -> wsSource.setName(source.getNameName()));
    }

    public XSSFPivotTable createPivotTable(Name source, CellReference position) {
        return this.createPivotTable(source, position, (Sheet)this.getWorkbook().getSheet(source.getSheetName()));
    }

    public XSSFPivotTable createPivotTable(Table source, CellReference position) {
        return this.createPivotTable(position, this.getWorkbook().getSheet(source.getSheetName()), (CTWorksheetSource wsSource) -> wsSource.setName(source.getName()));
    }

    public List<XSSFPivotTable> getPivotTables() {
        ArrayList<XSSFPivotTable> tables = new ArrayList<XSSFPivotTable>();
        for (XSSFPivotTable table : this.getWorkbook().getPivotTables()) {
            if (table.getParent() != this) continue;
            tables.add(table);
        }
        return tables;
    }

    @Override
    public int getColumnOutlineLevel(int columnIndex) {
        CTCol col = this.columnHelper.getColumn(columnIndex, false);
        if (col == null) {
            return 0;
        }
        return col.getOutlineLevel();
    }

    public void addIgnoredErrors(CellReference cell, IgnoredErrorType ... ignoredErrorTypes) {
        this.addIgnoredErrors(cell.formatAsString(false), ignoredErrorTypes);
    }

    public void addIgnoredErrors(CellRangeAddress region, IgnoredErrorType ... ignoredErrorTypes) {
        region.validate(SpreadsheetVersion.EXCEL2007);
        this.addIgnoredErrors(region.formatAsString(), ignoredErrorTypes);
    }

    public Map<IgnoredErrorType, Set<CellRangeAddress>> getIgnoredErrors() {
        LinkedHashMap<IgnoredErrorType, Set<CellRangeAddress>> result = new LinkedHashMap<IgnoredErrorType, Set<CellRangeAddress>>();
        if (this.worksheet.isSetIgnoredErrors()) {
            for (CTIgnoredError err : this.worksheet.getIgnoredErrors().getIgnoredErrorList()) {
                for (IgnoredErrorType errType : XSSFIgnoredErrorHelper.getErrorTypes(err)) {
                    if (!result.containsKey((Object)errType)) {
                        result.put(errType, new LinkedHashSet());
                    }
                    for (Object ref : err.getSqref()) {
                        ((Set)result.get((Object)errType)).add(CellRangeAddress.valueOf(ref.toString()));
                    }
                }
            }
        }
        return result;
    }

    private void addIgnoredErrors(String ref, IgnoredErrorType ... ignoredErrorTypes) {
        CTIgnoredErrors ctIgnoredErrors = this.worksheet.isSetIgnoredErrors() ? this.worksheet.getIgnoredErrors() : this.worksheet.addNewIgnoredErrors();
        CTIgnoredError ctIgnoredError = ctIgnoredErrors.addNewIgnoredError();
        XSSFIgnoredErrorHelper.addIgnoredErrors(ctIgnoredError, ref, ignoredErrorTypes);
    }

    protected void onSheetDelete() {
        for (POIXMLDocumentPart.RelationPart part : this.getRelationParts()) {
            if (part.getDocumentPart() instanceof XSSFTable) {
                this.removeTable((XSSFTable)part.getDocumentPart());
                continue;
            }
            this.removeRelation((POIXMLDocumentPart)part.getDocumentPart(), true);
        }
    }

    protected void onDeleteFormula(XSSFCell cell, BaseXSSFEvaluationWorkbook evalWb) {
        CellRangeAddress ref;
        CTCellFormula f = cell.getCTCell().getF();
        if (f != null && f.getT() == STCellFormulaType.SHARED && f.isSetRef() && f.getStringValue() != null && (ref = CellRangeAddress.valueOf(f.getRef())).getNumberOfCells() > 1) {
            block0: for (int i = cell.getRowIndex(); i <= ref.getLastRow(); ++i) {
                XSSFRow row = this.getRow(i);
                if (row == null) continue;
                for (int j = cell.getColumnIndex(); j <= ref.getLastColumn(); ++j) {
                    XSSFCell nextCell = row.getCell(j);
                    if (nextCell == null || nextCell == cell || nextCell.getCellType() != CellType.FORMULA) continue;
                    CTCellFormula nextF = nextCell.getCTCell().getF();
                    nextF.setStringValue(nextCell.getCellFormula(evalWb));
                    CellRangeAddress nextRef = new CellRangeAddress(nextCell.getRowIndex(), ref.getLastRow(), nextCell.getColumnIndex(), ref.getLastColumn());
                    nextF.setRef(nextRef.formatAsString());
                    this.sharedFormulas.put(Math.toIntExact(nextF.getSi()), nextF);
                    break block0;
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    protected CTOleObject readOleObject(long shapeId) {
        if (!this.getCTWorksheet().isSetOleObjects()) {
            return null;
        }
        String xquery = "declare namespace p='http://schemas.openxmlformats.org/spreadsheetml/2006/main' .//p:oleObject";
        XmlCursor cur = this.getCTWorksheet().getOleObjects().newCursor();
        try {
            cur.selectPath(xquery);
            CTOleObject coo = null;
            while (cur.toNextSelection()) {
                block21: {
                    XMLStreamReader reader;
                    String sId = cur.getAttributeText(new QName(null, "shapeId"));
                    if (sId == null || Long.parseLong(sId) != shapeId) continue;
                    XmlObject xObj = cur.getObject();
                    if (xObj instanceof CTOleObject) {
                        coo = (CTOleObject)xObj;
                    } else {
                        CTOleObjects coos;
                        block20: {
                            reader = cur.newXMLStreamReader();
                            coos = CTOleObjects.Factory.parse(reader);
                            if (coos.sizeOfOleObjectArray() != 0) break block20;
                            try {
                                reader.close();
                            } catch (XMLStreamException e) {
                                logger.log(3, "can't close reader", e);
                            }
                            continue;
                        }
                        coo = coos.getOleObjectArray(0);
                        try {
                            reader.close();
                        } catch (XMLStreamException e) {
                            logger.log(3, "can't close reader", e);
                        }
                    }
                    break block21;
                    catch (XmlException e) {
                        try {
                            logger.log(3, "can't parse CTOleObjects", e);
                        } catch (Throwable throwable) {
                            try {
                                reader.close();
                            } catch (XMLStreamException e2) {
                                logger.log(3, "can't close reader", e2);
                            }
                            throw throwable;
                        }
                        try {
                            reader.close();
                        } catch (XMLStreamException e3) {
                            logger.log(3, "can't close reader", e3);
                        }
                    }
                }
                if (!cur.toChild("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "objectPr")) continue;
                break;
            }
            CTOleObject cTOleObject = coo;
            return cTOleObject;
        } finally {
            cur.dispose();
        }
    }

    public XSSFHeaderFooterProperties getHeaderFooterProperties() {
        return new XSSFHeaderFooterProperties(this.getSheetTypeHeaderFooter());
    }
}

