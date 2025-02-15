/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.SharedFormula;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.model.CalculationChain;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.BaseXSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType;

public final class XSSFCell
extends CellBase {
    private static final String FALSE_AS_STRING = "0";
    private static final String TRUE_AS_STRING = "1";
    private static final String FALSE = "FALSE";
    private static final String TRUE = "TRUE";
    private CTCell _cell;
    private final XSSFRow _row;
    private int _cellNum;
    private SharedStringsTable _sharedStringSource;
    private StylesTable _stylesSource;

    protected XSSFCell(XSSFRow row, CTCell cell) {
        this._cell = cell;
        this._row = row;
        if (cell.getR() != null) {
            this._cellNum = new CellReference(cell.getR()).getCol();
        } else {
            short prevNum = row.getLastCellNum();
            if (prevNum != -1) {
                this._cellNum = row.getCell(prevNum - 1, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK).getColumnIndex() + 1;
            }
        }
        this._sharedStringSource = row.getSheet().getWorkbook().getSharedStringSource();
        this._stylesSource = row.getSheet().getWorkbook().getStylesSource();
    }

    @Override
    protected SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    @Internal
    public void copyCellFrom(Cell srcCell, CellCopyPolicy policy) {
        Hyperlink srcHyperlink;
        if (policy.isCopyCellValue()) {
            if (srcCell != null) {
                CellType copyCellType = srcCell.getCellType();
                if (copyCellType == CellType.FORMULA && !policy.isCopyCellFormula()) {
                    copyCellType = srcCell.getCachedFormulaResultType();
                }
                switch (copyCellType) {
                    case NUMERIC: {
                        if (DateUtil.isCellDateFormatted(srcCell)) {
                            this.setCellValue(srcCell.getDateCellValue());
                            break;
                        }
                        this.setCellValue(srcCell.getNumericCellValue());
                        break;
                    }
                    case STRING: {
                        this.setCellValue(srcCell.getStringCellValue());
                        break;
                    }
                    case FORMULA: {
                        this.setCellFormula(srcCell.getCellFormula());
                        break;
                    }
                    case BLANK: {
                        this.setBlank();
                        break;
                    }
                    case BOOLEAN: {
                        this.setCellValue(srcCell.getBooleanCellValue());
                        break;
                    }
                    case ERROR: {
                        this.setCellErrorValue(srcCell.getErrorCellValue());
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Invalid cell type " + (Object)((Object)srcCell.getCellType()));
                    }
                }
            } else {
                this.setBlank();
            }
        }
        if (policy.isCopyCellStyle()) {
            this.setCellStyle(srcCell == null ? null : srcCell.getCellStyle());
        }
        Hyperlink hyperlink = srcHyperlink = srcCell == null ? null : srcCell.getHyperlink();
        if (policy.isMergeHyperlink()) {
            if (srcHyperlink != null) {
                this.setHyperlink(new XSSFHyperlink(srcHyperlink));
            }
        } else if (policy.isCopyHyperlink()) {
            this.setHyperlink(srcHyperlink == null ? null : new XSSFHyperlink(srcHyperlink));
        }
    }

    protected SharedStringsTable getSharedStringSource() {
        return this._sharedStringSource;
    }

    protected StylesTable getStylesSource() {
        return this._stylesSource;
    }

    @Override
    public XSSFSheet getSheet() {
        return this.getRow().getSheet();
    }

    @Override
    public XSSFRow getRow() {
        return this._row;
    }

    @Override
    public boolean getBooleanCellValue() {
        CellType cellType = this.getCellType();
        switch (cellType) {
            case BLANK: {
                return false;
            }
            case BOOLEAN: {
                return this._cell.isSetV() && TRUE_AS_STRING.equals(this._cell.getV());
            }
            case FORMULA: {
                return this._cell.isSetV() && TRUE_AS_STRING.equals(this._cell.getV());
            }
        }
        throw XSSFCell.typeMismatch(CellType.BOOLEAN, cellType, false);
    }

    @Override
    public void setCellValue(boolean value) {
        this._cell.setT(STCellType.B);
        this._cell.setV(value ? TRUE_AS_STRING : FALSE_AS_STRING);
    }

    @Override
    public double getNumericCellValue() {
        CellType valueType = this.isFormulaCell() ? this.getCachedFormulaResultType() : this.getCellType();
        switch (valueType) {
            case BLANK: {
                return 0.0;
            }
            case NUMERIC: {
                if (this._cell.isSetV()) {
                    String v = this._cell.getV();
                    if (v.isEmpty()) {
                        return 0.0;
                    }
                    try {
                        return Double.parseDouble(v);
                    } catch (NumberFormatException e) {
                        throw XSSFCell.typeMismatch(CellType.NUMERIC, CellType.STRING, false);
                    }
                }
                return 0.0;
            }
            case FORMULA: {
                throw new AssertionError();
            }
        }
        throw XSSFCell.typeMismatch(CellType.NUMERIC, valueType, false);
    }

    @Override
    public void setCellValueImpl(double value) {
        this._cell.setT(STCellType.N);
        this._cell.setV(String.valueOf(value));
    }

    @Override
    public String getStringCellValue() {
        return this.getRichStringCellValue().getString();
    }

    @Override
    public XSSFRichTextString getRichStringCellValue() {
        XSSFRichTextString rt;
        CellType cellType = this.getCellType();
        switch (cellType) {
            case BLANK: {
                rt = new XSSFRichTextString("");
                break;
            }
            case STRING: {
                if (this._cell.getT() == STCellType.INLINE_STR) {
                    if (this._cell.isSetIs()) {
                        rt = new XSSFRichTextString(this._cell.getIs());
                        break;
                    }
                    if (this._cell.isSetV()) {
                        rt = new XSSFRichTextString(this._cell.getV());
                        break;
                    }
                    rt = new XSSFRichTextString("");
                    break;
                }
                if (this._cell.getT() == STCellType.STR) {
                    rt = new XSSFRichTextString(this._cell.isSetV() ? this._cell.getV() : "");
                    break;
                }
                if (this._cell.isSetV()) {
                    int idx = Integer.parseInt(this._cell.getV());
                    rt = new XSSFRichTextString(this._sharedStringSource.getEntryAt(idx));
                    break;
                }
                rt = new XSSFRichTextString("");
                break;
            }
            case FORMULA: {
                XSSFCell.checkFormulaCachedValueType(CellType.STRING, this.getBaseCellType(false));
                rt = new XSSFRichTextString(this._cell.isSetV() ? this._cell.getV() : "");
                break;
            }
            default: {
                throw XSSFCell.typeMismatch(CellType.STRING, cellType, false);
            }
        }
        rt.setStylesTableReference(this._stylesSource);
        return rt;
    }

    private static void checkFormulaCachedValueType(CellType expectedTypeCode, CellType cachedValueType) {
        if (cachedValueType != expectedTypeCode) {
            throw XSSFCell.typeMismatch(expectedTypeCode, cachedValueType, true);
        }
    }

    @Override
    protected void setCellValueImpl(String value) {
        this.setCellValueImpl(new XSSFRichTextString(value));
    }

    @Override
    protected void setCellValueImpl(RichTextString str) {
        CellType cellType = this.getCellType();
        if (cellType == CellType.FORMULA) {
            this._cell.setV(str.getString());
            this._cell.setT(STCellType.STR);
        } else if (this._cell.getT() == STCellType.INLINE_STR) {
            this._cell.setV(str.getString());
        } else {
            this._cell.setT(STCellType.S);
            XSSFRichTextString rt = (XSSFRichTextString)str;
            rt.setStylesTableReference(this._stylesSource);
            int sRef = this._sharedStringSource.addSharedStringItem(rt);
            this._cell.setV(Integer.toString(sRef));
        }
    }

    @Override
    public String getCellFormula() {
        return this.getCellFormula(null);
    }

    protected String getCellFormula(BaseXSSFEvaluationWorkbook fpb) {
        CellType cellType = this.getCellType();
        if (cellType != CellType.FORMULA) {
            throw XSSFCell.typeMismatch(CellType.FORMULA, cellType, false);
        }
        CTCellFormula f = this._cell.getF();
        if (this.isPartOfArrayFormulaGroup() && (f == null || f.getStringValue().isEmpty())) {
            XSSFCell cell = this.getSheet().getFirstCellInArrayFormula(this);
            return cell.getCellFormula(fpb);
        }
        if (f == null) {
            return null;
        }
        if (f.getT() == STCellFormulaType.SHARED) {
            return this.convertSharedFormula(Math.toIntExact(f.getSi()), fpb == null ? XSSFEvaluationWorkbook.create(this.getSheet().getWorkbook()) : fpb);
        }
        return f.getStringValue();
    }

    private String convertSharedFormula(int si, BaseXSSFEvaluationWorkbook fpb) {
        XSSFSheet sheet = this.getSheet();
        CTCellFormula f = sheet.getSharedFormula(si);
        if (f == null) {
            throw new IllegalStateException("Master cell of a shared formula with sid=" + si + " was not found");
        }
        String sharedFormula = f.getStringValue();
        String sharedFormulaRange = f.getRef();
        CellRangeAddress ref = CellRangeAddress.valueOf(sharedFormulaRange);
        int sheetIndex = sheet.getWorkbook().getSheetIndex(sheet);
        SharedFormula sf = new SharedFormula(SpreadsheetVersion.EXCEL2007);
        Ptg[] ptgs = FormulaParser.parse(sharedFormula, fpb, FormulaType.CELL, sheetIndex, this.getRowIndex());
        Ptg[] fmla = sf.convertSharedFormulas(ptgs, this.getRowIndex() - ref.getFirstRow(), this.getColumnIndex() - ref.getFirstColumn());
        return FormulaRenderer.toFormulaString(fpb, fmla);
    }

    @Override
    protected void setCellFormulaImpl(String formula) {
        assert (formula != null);
        this.setFormula(formula, FormulaType.CELL);
    }

    void setCellArrayFormula(String formula, CellRangeAddress range) {
        this.setFormula(formula, FormulaType.ARRAY);
        CTCellFormula cellFormula = this._cell.getF();
        cellFormula.setT(STCellFormulaType.ARRAY);
        cellFormula.setRef(range.formatAsString());
    }

    private void setFormula(String formula, FormulaType formulaType) {
        CTCellFormula f;
        XSSFWorkbook wb = this._row.getSheet().getWorkbook();
        if (formulaType == FormulaType.ARRAY && formula == null) {
            wb.onDeleteFormula(this);
            if (this._cell.isSetF()) {
                this._row.getSheet().onDeleteFormula(this, null);
                this._cell.unsetF();
            }
            return;
        }
        if (wb.getCellFormulaValidation()) {
            XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create(wb);
            FormulaParser.parse(formula, fpb, formulaType, wb.getSheetIndex(this.getSheet()), this.getRowIndex());
        }
        if (this._cell.isSetF()) {
            f = this._cell.getF();
            f.setStringValue(formula);
            if (f.getT() == STCellFormulaType.SHARED) {
                this.getRow().getSheet().onReadCell(this);
            }
        } else {
            f = CTCellFormula.Factory.newInstance();
            f.setStringValue(formula);
            this._cell.setF(f);
        }
    }

    @Override
    protected void removeFormulaImpl() {
        this._row.getSheet().getWorkbook().onDeleteFormula(this);
        if (this._cell.isSetF()) {
            this._row.getSheet().onDeleteFormula(this, null);
            this._cell.unsetF();
        }
    }

    @Override
    public int getColumnIndex() {
        return this._cellNum;
    }

    @Override
    public int getRowIndex() {
        return this._row.getRowNum();
    }

    public String getReference() {
        String ref = this._cell.getR();
        if (ref == null) {
            return this.getAddress().formatAsString();
        }
        return ref;
    }

    @Override
    public XSSFCellStyle getCellStyle() {
        XSSFCellStyle style = null;
        if (this._stylesSource.getNumCellStyles() > 0) {
            long idx = this._cell.isSetS() ? this._cell.getS() : 0L;
            style = this._stylesSource.getStyleAt(Math.toIntExact(idx));
        }
        return style;
    }

    @Override
    public void setCellStyle(CellStyle style) {
        if (style == null) {
            if (this._cell.isSetS()) {
                this._cell.unsetS();
            }
        } else {
            XSSFCellStyle xStyle = (XSSFCellStyle)style;
            xStyle.verifyBelongsToStylesSource(this._stylesSource);
            long idx = this._stylesSource.putStyle(xStyle);
            this._cell.setS(idx);
        }
    }

    private boolean isFormulaCell() {
        return this._cell.isSetF() && this._cell.getF().getT() != STCellFormulaType.DATA_TABLE || this.getSheet().isCellInArrayFormulaContext(this);
    }

    @Override
    public CellType getCellType() {
        if (this.isFormulaCell()) {
            return CellType.FORMULA;
        }
        return this.getBaseCellType(true);
    }

    @Override
    public CellType getCachedFormulaResultType() {
        if (!this.isFormulaCell()) {
            throw new IllegalStateException("Only formula cells have cached results");
        }
        return this.getBaseCellType(false);
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public CellType getCachedFormulaResultTypeEnum() {
        return this.getCachedFormulaResultType();
    }

    private CellType getBaseCellType(boolean blankCells) {
        switch (this._cell.getT().intValue()) {
            case 1: {
                return CellType.BOOLEAN;
            }
            case 2: {
                if (!this._cell.isSetV() && blankCells) {
                    return CellType.BLANK;
                }
                return CellType.NUMERIC;
            }
            case 3: {
                return CellType.ERROR;
            }
            case 4: 
            case 5: 
            case 6: {
                return CellType.STRING;
            }
        }
        throw new IllegalStateException("Illegal cell type: " + this._cell.getT());
    }

    @Override
    public Date getDateCellValue() {
        if (this.getCellType() == CellType.BLANK) {
            return null;
        }
        double value = this.getNumericCellValue();
        boolean date1904 = this.getSheet().getWorkbook().isDate1904();
        return DateUtil.getJavaDate(value, date1904);
    }

    @Override
    public LocalDateTime getLocalDateTimeCellValue() {
        if (this.getCellType() == CellType.BLANK) {
            return null;
        }
        double value = this.getNumericCellValue();
        boolean date1904 = this.getSheet().getWorkbook().isDate1904();
        return DateUtil.getLocalDateTime(value, date1904);
    }

    @Override
    protected void setCellValueImpl(Date value) {
        boolean date1904 = this.getSheet().getWorkbook().isDate1904();
        this.setCellValue(DateUtil.getExcelDate(value, date1904));
    }

    @Override
    protected void setCellValueImpl(LocalDateTime value) {
        boolean date1904 = this.getSheet().getWorkbook().isDate1904();
        this.setCellValue(DateUtil.getExcelDate(value, date1904));
    }

    @Override
    protected void setCellValueImpl(Calendar value) {
        boolean date1904 = this.getSheet().getWorkbook().isDate1904();
        this.setCellValue(DateUtil.getExcelDate(value, date1904));
    }

    public String getErrorCellString() throws IllegalStateException {
        CellType cellType = this.getBaseCellType(true);
        if (cellType != CellType.ERROR) {
            throw XSSFCell.typeMismatch(CellType.ERROR, cellType, false);
        }
        return this._cell.getV();
    }

    @Override
    public byte getErrorCellValue() throws IllegalStateException {
        String code = this.getErrorCellString();
        if (code == null) {
            return 0;
        }
        try {
            return FormulaError.forString(code).getCode();
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Unexpected error code", e);
        }
    }

    @Override
    public void setCellErrorValue(byte errorCode) {
        FormulaError error = FormulaError.forInt(errorCode);
        this.setCellErrorValue(error);
    }

    public void setCellErrorValue(FormulaError error) {
        this._cell.setT(STCellType.E);
        this._cell.setV(error.getString());
    }

    @Override
    public void setAsActiveCell() {
        this.getSheet().setActiveCell(this.getAddress());
    }

    private void setBlankPrivate() {
        CTCell blank = CTCell.Factory.newInstance();
        blank.setR(this._cell.getR());
        if (this._cell.isSetS()) {
            blank.setS(this._cell.getS());
        }
        this._cell.set(blank);
    }

    protected void setCellNum(int num) {
        XSSFCell.checkBounds(num);
        this._cellNum = num;
        String ref = new CellReference(this.getRowIndex(), this.getColumnIndex()).formatAsString();
        this._cell.setR(ref);
    }

    @Override
    protected void setCellTypeImpl(CellType cellType) {
        this.setCellType(cellType, null);
    }

    protected void setCellType(CellType cellType, BaseXSSFEvaluationWorkbook evalWb) {
        CellType prevType = this.getCellType();
        if (prevType == CellType.FORMULA && cellType != CellType.FORMULA) {
            if (this._cell.isSetF()) {
                this._row.getSheet().onDeleteFormula(this, evalWb);
            }
            this.getSheet().getWorkbook().onDeleteFormula(this);
        }
        switch (cellType) {
            case NUMERIC: {
                this._cell.setT(STCellType.N);
                break;
            }
            case STRING: {
                if (prevType != CellType.STRING) {
                    String str = this.convertCellValueToString();
                    XSSFRichTextString rt = new XSSFRichTextString(str);
                    rt.setStylesTableReference(this._stylesSource);
                    int sRef = this._sharedStringSource.addSharedStringItem(rt);
                    this._cell.setV(Integer.toString(sRef));
                }
                this._cell.setT(STCellType.S);
                break;
            }
            case FORMULA: {
                if (this._cell.isSetF()) break;
                CTCellFormula f = CTCellFormula.Factory.newInstance();
                f.setStringValue(FALSE_AS_STRING);
                this._cell.setF(f);
                if (!this._cell.isSetT()) break;
                this._cell.unsetT();
                break;
            }
            case BLANK: {
                this.setBlankPrivate();
                break;
            }
            case BOOLEAN: {
                String newVal = this.convertCellValueToBoolean() ? TRUE_AS_STRING : FALSE_AS_STRING;
                this._cell.setT(STCellType.B);
                this._cell.setV(newVal);
                break;
            }
            case ERROR: {
                this._cell.setT(STCellType.E);
                break;
            }
            default: {
                throw new IllegalArgumentException("Illegal cell type: " + (Object)((Object)cellType));
            }
        }
        if (cellType != CellType.FORMULA && this._cell.isSetF()) {
            this._cell.unsetF();
        }
    }

    public String toString() {
        switch (this.getCellType()) {
            case NUMERIC: {
                if (DateUtil.isCellDateFormatted(this)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", LocaleUtil.getUserLocale());
                    sdf.setTimeZone(LocaleUtil.getUserTimeZone());
                    return sdf.format(this.getDateCellValue());
                }
                return Double.toString(this.getNumericCellValue());
            }
            case STRING: {
                return this.getRichStringCellValue().toString();
            }
            case FORMULA: {
                return this.getCellFormula();
            }
            case BLANK: {
                return "";
            }
            case BOOLEAN: {
                return this.getBooleanCellValue() ? TRUE : FALSE;
            }
            case ERROR: {
                return ErrorEval.getText(this.getErrorCellValue());
            }
        }
        return "Unknown Cell Type: " + (Object)((Object)this.getCellType());
    }

    public String getRawValue() {
        return this._cell.getV();
    }

    private static RuntimeException typeMismatch(CellType expectedType, CellType actualType, boolean isFormulaCell) {
        String msg = "Cannot get a " + (Object)((Object)expectedType) + " value from a " + (Object)((Object)actualType) + " " + (isFormulaCell ? "formula " : "") + "cell";
        return new IllegalStateException(msg);
    }

    private static void checkBounds(int cellIndex) {
        SpreadsheetVersion v = SpreadsheetVersion.EXCEL2007;
        int maxcol = SpreadsheetVersion.EXCEL2007.getLastColumnIndex();
        if (cellIndex < 0 || cellIndex > maxcol) {
            throw new IllegalArgumentException("Invalid column index (" + cellIndex + ").  Allowable column range for " + v.name() + " is (0.." + maxcol + ") or ('A'..'" + v.getLastColumnName() + "')");
        }
    }

    @Override
    public XSSFComment getCellComment() {
        return this.getSheet().getCellComment(new CellAddress(this));
    }

    @Override
    public void setCellComment(Comment comment) {
        if (comment == null) {
            this.removeCellComment();
            return;
        }
        comment.setAddress(this.getRowIndex(), this.getColumnIndex());
    }

    @Override
    public void removeCellComment() {
        XSSFComment comment = this.getCellComment();
        if (comment != null) {
            CellAddress ref = new CellAddress(this.getReference());
            XSSFSheet sh = this.getSheet();
            sh.getCommentsTable(false).removeComment(ref);
            sh.getVMLDrawing(false).removeCommentShape(this.getRowIndex(), this.getColumnIndex());
        }
    }

    @Override
    public XSSFHyperlink getHyperlink() {
        return this.getSheet().getHyperlink(this._row.getRowNum(), this._cellNum);
    }

    @Override
    public void setHyperlink(Hyperlink hyperlink) {
        if (hyperlink == null) {
            this.removeHyperlink();
            return;
        }
        XSSFHyperlink link = (XSSFHyperlink)hyperlink;
        link.setCellReference(new CellReference(this._row.getRowNum(), this._cellNum).formatAsString());
        this.getSheet().addHyperlink(link);
    }

    @Override
    public void removeHyperlink() {
        this.getSheet().removeHyperlink(this._row.getRowNum(), this._cellNum);
    }

    @Internal
    public CTCell getCTCell() {
        return this._cell;
    }

    @Internal
    public void setCTCell(CTCell cell) {
        this._cell = cell;
    }

    private boolean convertCellValueToBoolean() {
        CellType cellType = this.getCellType();
        if (cellType == CellType.FORMULA) {
            cellType = this.getBaseCellType(false);
        }
        switch (cellType) {
            case BOOLEAN: {
                return TRUE_AS_STRING.equals(this._cell.getV());
            }
            case STRING: {
                int sstIndex = Integer.parseInt(this._cell.getV());
                XSSFRichTextString rt = new XSSFRichTextString(this._sharedStringSource.getEntryAt(sstIndex));
                String text = rt.getString();
                return Boolean.parseBoolean(text);
            }
            case NUMERIC: {
                return Double.parseDouble(this._cell.getV()) != 0.0;
            }
            case BLANK: 
            case ERROR: {
                return false;
            }
        }
        throw new RuntimeException("Unexpected cell type (" + (Object)((Object)cellType) + ")");
    }

    private String convertCellValueToString() {
        CellType cellType = this.getCellType();
        switch (cellType) {
            case BLANK: {
                return "";
            }
            case BOOLEAN: {
                return TRUE_AS_STRING.equals(this._cell.getV()) ? TRUE : FALSE;
            }
            case STRING: {
                int sstIndex = Integer.parseInt(this._cell.getV());
                XSSFRichTextString rt = new XSSFRichTextString(this._sharedStringSource.getEntryAt(sstIndex));
                return rt.getString();
            }
            case NUMERIC: 
            case ERROR: {
                return this._cell.getV();
            }
            case FORMULA: {
                break;
            }
            default: {
                throw new IllegalStateException("Unexpected cell type (" + (Object)((Object)cellType) + ")");
            }
        }
        cellType = this.getBaseCellType(false);
        String textValue = this._cell.getV();
        switch (cellType) {
            case BOOLEAN: {
                if (TRUE_AS_STRING.equals(textValue)) {
                    return TRUE;
                }
                if (FALSE_AS_STRING.equals(textValue)) {
                    return FALSE;
                }
                throw new IllegalStateException("Unexpected boolean cached formula value '" + textValue + "'.");
            }
            case NUMERIC: 
            case STRING: 
            case ERROR: {
                return textValue;
            }
        }
        throw new IllegalStateException("Unexpected formula result type (" + (Object)((Object)cellType) + ")");
    }

    @Override
    public CellRangeAddress getArrayFormulaRange() {
        XSSFCell cell = this.getSheet().getFirstCellInArrayFormula(this);
        if (cell == null) {
            throw new IllegalStateException("Cell " + new CellReference(this).formatAsString() + " is not part of an array formula.");
        }
        String formulaRef = cell._cell.getF().getRef();
        return CellRangeAddress.valueOf(formulaRef);
    }

    @Override
    public boolean isPartOfArrayFormulaGroup() {
        return this.getSheet().isCellInArrayFormulaContext(this);
    }

    public void updateCellReferencesForShifting(String msg) {
        if (this.isPartOfArrayFormulaGroup()) {
            this.tryToDeleteArrayFormula(msg);
        }
        CalculationChain calcChain = this.getSheet().getWorkbook().getCalculationChain();
        int sheetId = Math.toIntExact(this.getSheet().sheet.getSheetId());
        if (calcChain != null) {
            calcChain.removeItem(sheetId, this.getReference());
        }
        CTCell ctCell = this.getCTCell();
        String r = new CellReference(this.getRowIndex(), this.getColumnIndex()).formatAsString();
        ctCell.setR(r);
    }
}

