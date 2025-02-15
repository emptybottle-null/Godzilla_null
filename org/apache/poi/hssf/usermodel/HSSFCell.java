/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordBase;
import org.apache.poi.hssf.record.aggregates.FormulaRecordAggregate;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.ptg.ExpPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.Removal;

public class HSSFCell
extends CellBase {
    private static final String FILE_FORMAT_NAME = "BIFF8";
    public static final int LAST_COLUMN_NUMBER = SpreadsheetVersion.EXCEL97.getLastColumnIndex();
    private static final String LAST_COLUMN_NAME = SpreadsheetVersion.EXCEL97.getLastColumnName();
    public static final short ENCODING_UNCHANGED = -1;
    public static final short ENCODING_COMPRESSED_UNICODE = 0;
    public static final short ENCODING_UTF_16 = 1;
    private final HSSFWorkbook _book;
    private final HSSFSheet _sheet;
    private CellType _cellType;
    private HSSFRichTextString _stringValue;
    private CellValueRecordInterface _record;
    private HSSFComment _comment;

    protected HSSFCell(HSSFWorkbook book, HSSFSheet sheet, int row, short col) {
        HSSFCell.checkBounds(col);
        this._stringValue = null;
        this._book = book;
        this._sheet = sheet;
        short xfindex = sheet.getSheet().getXFIndexForColAt(col);
        this.setCellType(CellType.BLANK, false, row, col, xfindex);
    }

    @Override
    protected SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL97;
    }

    @Override
    public HSSFSheet getSheet() {
        return this._sheet;
    }

    @Override
    public HSSFRow getRow() {
        int rowIndex = this.getRowIndex();
        return this._sheet.getRow(rowIndex);
    }

    protected HSSFCell(HSSFWorkbook book, HSSFSheet sheet, int row, short col, CellType type) {
        HSSFCell.checkBounds(col);
        this._cellType = CellType._NONE;
        this._stringValue = null;
        this._book = book;
        this._sheet = sheet;
        short xfindex = sheet.getSheet().getXFIndexForColAt(col);
        this.setCellType(type, false, row, col, xfindex);
    }

    protected HSSFCell(HSSFWorkbook book, HSSFSheet sheet, CellValueRecordInterface cval) {
        this._record = cval;
        this._cellType = HSSFCell.determineType(cval);
        this._stringValue = null;
        this._book = book;
        this._sheet = sheet;
        switch (this._cellType) {
            case STRING: {
                this._stringValue = new HSSFRichTextString(book.getWorkbook(), (LabelSSTRecord)cval);
                break;
            }
            case BLANK: {
                break;
            }
            case FORMULA: {
                this._stringValue = new HSSFRichTextString(((FormulaRecordAggregate)cval).getStringValue());
                break;
            }
        }
    }

    private static CellType determineType(CellValueRecordInterface cval) {
        if (cval instanceof FormulaRecordAggregate) {
            return CellType.FORMULA;
        }
        Record record = (Record)((Object)cval);
        switch (record.getSid()) {
            case 515: {
                return CellType.NUMERIC;
            }
            case 513: {
                return CellType.BLANK;
            }
            case 253: {
                return CellType.STRING;
            }
            case 517: {
                BoolErrRecord boolErrRecord = (BoolErrRecord)record;
                return boolErrRecord.isBoolean() ? CellType.BOOLEAN : CellType.ERROR;
            }
        }
        throw new RuntimeException("Bad cell value rec (" + cval.getClass().getName() + ")");
    }

    protected InternalWorkbook getBoundWorkbook() {
        return this._book.getWorkbook();
    }

    @Override
    public int getRowIndex() {
        return this._record.getRow();
    }

    protected void updateCellNum(short num) {
        this._record.setColumn(num);
    }

    @Override
    public int getColumnIndex() {
        return this._record.getColumn() & 0xFFFF;
    }

    @Override
    protected void setCellTypeImpl(CellType cellType) {
        this.notifyFormulaChanging();
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        this.setCellType(cellType, true, row, col, styleIndex);
    }

    private void setCellType(CellType cellType, boolean setValue, int row, short col, short styleIndex) {
        switch (cellType) {
            case FORMULA: {
                FormulaRecordAggregate frec;
                if (cellType != this._cellType) {
                    frec = this._sheet.getSheet().getRowsAggregate().createFormula(row, col);
                } else {
                    frec = (FormulaRecordAggregate)this._record;
                    frec.setRow(row);
                    frec.setColumn(col);
                }
                if (this.getCellType() == CellType.BLANK) {
                    frec.getFormulaRecord().setValue(0.0);
                }
                frec.setXFIndex(styleIndex);
                this._record = frec;
                break;
            }
            case NUMERIC: {
                NumberRecord nrec = cellType != this._cellType ? new NumberRecord() : (NumberRecord)this._record;
                nrec.setColumn(col);
                if (setValue) {
                    nrec.setValue(this.getNumericCellValue());
                }
                nrec.setXFIndex(styleIndex);
                nrec.setRow(row);
                this._record = nrec;
                break;
            }
            case STRING: {
                LabelSSTRecord lrec;
                if (cellType == this._cellType) {
                    lrec = (LabelSSTRecord)this._record;
                } else {
                    lrec = new LabelSSTRecord();
                    lrec.setColumn(col);
                    lrec.setRow(row);
                    lrec.setXFIndex(styleIndex);
                }
                if (setValue) {
                    String str = this.convertCellValueToString();
                    if (str == null) {
                        this.setCellType(CellType.BLANK, false, row, col, styleIndex);
                        return;
                    }
                    int sstIndex = this._book.getWorkbook().addSSTString(new UnicodeString(str));
                    lrec.setSSTIndex(sstIndex);
                    UnicodeString us = this._book.getWorkbook().getSSTString(sstIndex);
                    this._stringValue = new HSSFRichTextString();
                    this._stringValue.setUnicodeString(us);
                }
                this._record = lrec;
                break;
            }
            case BLANK: {
                BlankRecord brec = cellType != this._cellType ? new BlankRecord() : (BlankRecord)this._record;
                brec.setColumn(col);
                brec.setXFIndex(styleIndex);
                brec.setRow(row);
                this._record = brec;
                break;
            }
            case BOOLEAN: {
                BoolErrRecord boolRec = cellType != this._cellType ? new BoolErrRecord() : (BoolErrRecord)this._record;
                boolRec.setColumn(col);
                if (setValue) {
                    boolRec.setValue(this.convertCellValueToBoolean());
                }
                boolRec.setXFIndex(styleIndex);
                boolRec.setRow(row);
                this._record = boolRec;
                break;
            }
            case ERROR: {
                BoolErrRecord errRec = cellType != this._cellType ? new BoolErrRecord() : (BoolErrRecord)this._record;
                errRec.setColumn(col);
                if (setValue) {
                    errRec.setValue(FormulaError.VALUE.getCode());
                }
                errRec.setXFIndex(styleIndex);
                errRec.setRow(row);
                this._record = errRec;
                break;
            }
            default: {
                throw new IllegalStateException("Invalid cell type: " + (Object)((Object)cellType));
            }
        }
        if (cellType != this._cellType && this._cellType != CellType._NONE) {
            this._sheet.getSheet().replaceValueRecord(this._record);
        }
        this._cellType = cellType;
    }

    @Override
    public CellType getCellType() {
        return this._cellType;
    }

    @Override
    protected void setCellValueImpl(double value) {
        switch (this._cellType) {
            default: {
                this.setCellType(CellType.NUMERIC, false, this._record.getRow(), this._record.getColumn(), this._record.getXFIndex());
            }
            case NUMERIC: {
                ((NumberRecord)this._record).setValue(value);
                break;
            }
            case FORMULA: {
                ((FormulaRecordAggregate)this._record).setCachedDoubleResult(value);
            }
        }
    }

    @Override
    protected void setCellValueImpl(Date value) {
        this.setCellValue(DateUtil.getExcelDate(value, this._book.getWorkbook().isUsing1904DateWindowing()));
    }

    @Override
    protected void setCellValueImpl(LocalDateTime value) {
        this.setCellValue(DateUtil.getExcelDate(value, this._book.getWorkbook().isUsing1904DateWindowing()));
    }

    @Override
    protected void setCellValueImpl(Calendar value) {
        this.setCellValue(DateUtil.getExcelDate(value, this._book.getWorkbook().isUsing1904DateWindowing()));
    }

    @Override
    protected void setCellValueImpl(String value) {
        this.setCellValueImpl(new HSSFRichTextString(value));
    }

    @Override
    protected void setCellValueImpl(RichTextString value) {
        if (this._cellType == CellType.FORMULA) {
            FormulaRecordAggregate fr = (FormulaRecordAggregate)this._record;
            fr.setCachedStringResult(value.getString());
            this._stringValue = new HSSFRichTextString(value.getString());
            return;
        }
        if (this._cellType != CellType.STRING) {
            int row = this._record.getRow();
            short col = this._record.getColumn();
            short styleIndex = this._record.getXFIndex();
            this.setCellType(CellType.STRING, false, row, col, styleIndex);
        }
        HSSFRichTextString hvalue = (HSSFRichTextString)value;
        UnicodeString str = hvalue.getUnicodeString();
        int index = this._book.getWorkbook().addSSTString(str);
        ((LabelSSTRecord)this._record).setSSTIndex(index);
        this._stringValue = hvalue;
        this._stringValue.setWorkbookReferences(this._book.getWorkbook(), (LabelSSTRecord)this._record);
        this._stringValue.setUnicodeString(this._book.getWorkbook().getSSTString(index));
    }

    @Override
    protected void setCellFormulaImpl(String formula) {
        assert (formula != null);
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        CellValue savedValue = this.readValue();
        int sheetIndex = this._book.getSheetIndex(this._sheet);
        Ptg[] ptgs = HSSFFormulaParser.parse(formula, this._book, FormulaType.CELL, sheetIndex);
        this.setCellType(CellType.FORMULA, false, row, col, styleIndex);
        FormulaRecordAggregate agg = (FormulaRecordAggregate)this._record;
        FormulaRecord frec = agg.getFormulaRecord();
        frec.setOptions((short)2);
        if (agg.getXFIndex() == 0) {
            agg.setXFIndex((short)15);
        }
        agg.setParsedExpression(ptgs);
        this.restoreValue(savedValue);
    }

    private CellValue readValue() {
        CellType valueType = this.getCellType() == CellType.FORMULA ? this.getCachedFormulaResultType() : this.getCellType();
        switch (valueType) {
            case NUMERIC: {
                return new CellValue(this.getNumericCellValue());
            }
            case STRING: {
                return new CellValue(this.getStringCellValue());
            }
            case BOOLEAN: {
                return CellValue.valueOf(this.getBooleanCellValue());
            }
            case ERROR: {
                return CellValue.getError(this.getErrorCellValue());
            }
        }
        throw new IllegalStateException();
    }

    private void restoreValue(CellValue value) {
        switch (value.getCellType()) {
            case NUMERIC: {
                this.setCellValue(value.getNumberValue());
                break;
            }
            case STRING: {
                this.setCellValue(value.getStringValue());
                break;
            }
            case BOOLEAN: {
                this.setCellValue(value.getBooleanValue());
                break;
            }
            case ERROR: {
                this.setCellErrorValue(FormulaError.forInt(value.getErrorValue()));
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }

    @Override
    protected void removeFormulaImpl() {
        assert (this.getCellType() == CellType.FORMULA);
        this.notifyFormulaChanging();
        switch (this.getCachedFormulaResultType()) {
            case NUMERIC: {
                double numericValue = ((FormulaRecordAggregate)this._record).getFormulaRecord().getValue();
                this._record = new NumberRecord();
                ((NumberRecord)this._record).setValue(numericValue);
                this._cellType = CellType.NUMERIC;
                break;
            }
            case STRING: {
                this._record = new NumberRecord();
                ((NumberRecord)this._record).setValue(0.0);
                this._cellType = CellType.STRING;
                break;
            }
            case BOOLEAN: {
                boolean booleanValue = ((FormulaRecordAggregate)this._record).getFormulaRecord().getCachedBooleanValue();
                this._record = new BoolErrRecord();
                ((BoolErrRecord)this._record).setValue(booleanValue);
                this._cellType = CellType.BOOLEAN;
                break;
            }
            case ERROR: {
                byte errorValue = (byte)((FormulaRecordAggregate)this._record).getFormulaRecord().getCachedErrorValue();
                this._record = new BoolErrRecord();
                ((BoolErrRecord)this._record).setValue(errorValue);
                this._cellType = CellType.ERROR;
                break;
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    private void notifyFormulaChanging() {
        if (this._record instanceof FormulaRecordAggregate) {
            ((FormulaRecordAggregate)this._record).notifyFormulaChanging();
        }
    }

    @Override
    public String getCellFormula() {
        if (!(this._record instanceof FormulaRecordAggregate)) {
            throw HSSFCell.typeMismatch(CellType.FORMULA, this._cellType, true);
        }
        return HSSFFormulaParser.toFormulaString(this._book, ((FormulaRecordAggregate)this._record).getFormulaTokens());
    }

    private static RuntimeException typeMismatch(CellType expectedTypeCode, CellType actualTypeCode, boolean isFormulaCell) {
        String msg = "Cannot get a " + (Object)((Object)expectedTypeCode) + " value from a " + (Object)((Object)actualTypeCode) + " " + (isFormulaCell ? "formula " : "") + "cell";
        return new IllegalStateException(msg);
    }

    private static void checkFormulaCachedValueType(CellType expectedTypeCode, FormulaRecord fr) {
        CellType cachedValueType = CellType.forInt(fr.getCachedResultType());
        if (cachedValueType != expectedTypeCode) {
            throw HSSFCell.typeMismatch(expectedTypeCode, cachedValueType, true);
        }
    }

    @Override
    public double getNumericCellValue() {
        switch (this._cellType) {
            case BLANK: {
                return 0.0;
            }
            case NUMERIC: {
                return ((NumberRecord)this._record).getValue();
            }
            default: {
                throw HSSFCell.typeMismatch(CellType.NUMERIC, this._cellType, false);
            }
            case FORMULA: 
        }
        FormulaRecord fr = ((FormulaRecordAggregate)this._record).getFormulaRecord();
        HSSFCell.checkFormulaCachedValueType(CellType.NUMERIC, fr);
        return fr.getValue();
    }

    @Override
    public Date getDateCellValue() {
        if (this._cellType == CellType.BLANK) {
            return null;
        }
        double value = this.getNumericCellValue();
        if (this._book.getWorkbook().isUsing1904DateWindowing()) {
            return DateUtil.getJavaDate(value, true);
        }
        return DateUtil.getJavaDate(value, false);
    }

    @Override
    public LocalDateTime getLocalDateTimeCellValue() {
        if (this._cellType == CellType.BLANK) {
            return null;
        }
        double value = this.getNumericCellValue();
        if (this._book.getWorkbook().isUsing1904DateWindowing()) {
            return DateUtil.getLocalDateTime(value, true);
        }
        return DateUtil.getLocalDateTime(value, false);
    }

    @Override
    public String getStringCellValue() {
        HSSFRichTextString str = this.getRichStringCellValue();
        return str.getString();
    }

    @Override
    public HSSFRichTextString getRichStringCellValue() {
        switch (this._cellType) {
            case BLANK: {
                return new HSSFRichTextString("");
            }
            case STRING: {
                return this._stringValue;
            }
            default: {
                throw HSSFCell.typeMismatch(CellType.STRING, this._cellType, false);
            }
            case FORMULA: 
        }
        FormulaRecordAggregate fra = (FormulaRecordAggregate)this._record;
        HSSFCell.checkFormulaCachedValueType(CellType.STRING, fra.getFormulaRecord());
        String strVal = fra.getStringValue();
        return new HSSFRichTextString(strVal == null ? "" : strVal);
    }

    @Override
    public void setCellValue(boolean value) {
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        switch (this._cellType) {
            default: {
                this.setCellType(CellType.BOOLEAN, false, row, col, styleIndex);
            }
            case BOOLEAN: {
                ((BoolErrRecord)this._record).setValue(value);
                break;
            }
            case FORMULA: {
                ((FormulaRecordAggregate)this._record).setCachedBooleanResult(value);
            }
        }
    }

    @Override
    public void setCellErrorValue(byte errorCode) {
        FormulaError error = FormulaError.forInt(errorCode);
        this.setCellErrorValue(error);
    }

    public void setCellErrorValue(FormulaError error) {
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        switch (this._cellType) {
            default: {
                this.setCellType(CellType.ERROR, false, row, col, styleIndex);
            }
            case ERROR: {
                ((BoolErrRecord)this._record).setValue(error);
                break;
            }
            case FORMULA: {
                ((FormulaRecordAggregate)this._record).setCachedErrorResult(error.getCode());
            }
        }
    }

    private boolean convertCellValueToBoolean() {
        switch (this._cellType) {
            case BOOLEAN: {
                return ((BoolErrRecord)this._record).getBooleanValue();
            }
            case STRING: {
                int sstIndex = ((LabelSSTRecord)this._record).getSSTIndex();
                String text = this._book.getWorkbook().getSSTString(sstIndex).getString();
                return Boolean.valueOf(text);
            }
            case NUMERIC: {
                return ((NumberRecord)this._record).getValue() != 0.0;
            }
            case FORMULA: {
                FormulaRecord fr = ((FormulaRecordAggregate)this._record).getFormulaRecord();
                HSSFCell.checkFormulaCachedValueType(CellType.BOOLEAN, fr);
                return fr.getCachedBooleanValue();
            }
            case BLANK: 
            case ERROR: {
                return false;
            }
        }
        throw new RuntimeException("Unexpected cell type (" + (Object)((Object)this._cellType) + ")");
    }

    private String convertCellValueToString() {
        switch (this._cellType) {
            case BLANK: {
                return "";
            }
            case BOOLEAN: {
                return ((BoolErrRecord)this._record).getBooleanValue() ? "TRUE" : "FALSE";
            }
            case STRING: {
                int sstIndex = ((LabelSSTRecord)this._record).getSSTIndex();
                return this._book.getWorkbook().getSSTString(sstIndex).getString();
            }
            case NUMERIC: {
                return NumberToTextConverter.toText(((NumberRecord)this._record).getValue());
            }
            case ERROR: {
                return FormulaError.forInt(((BoolErrRecord)this._record).getErrorValue()).getString();
            }
            case FORMULA: {
                break;
            }
            default: {
                throw new IllegalStateException("Unexpected cell type (" + (Object)((Object)this._cellType) + ")");
            }
        }
        FormulaRecordAggregate fra = (FormulaRecordAggregate)this._record;
        FormulaRecord fr = fra.getFormulaRecord();
        switch (CellType.forInt(fr.getCachedResultType())) {
            case BOOLEAN: {
                return fr.getCachedBooleanValue() ? "TRUE" : "FALSE";
            }
            case STRING: {
                return fra.getStringValue();
            }
            case NUMERIC: {
                return NumberToTextConverter.toText(fr.getValue());
            }
            case ERROR: {
                return FormulaError.forInt(fr.getCachedErrorValue()).getString();
            }
        }
        throw new IllegalStateException("Unexpected formula result type (" + (Object)((Object)this._cellType) + ")");
    }

    @Override
    public boolean getBooleanCellValue() {
        switch (this._cellType) {
            case BLANK: {
                return false;
            }
            case BOOLEAN: {
                return ((BoolErrRecord)this._record).getBooleanValue();
            }
            case FORMULA: {
                break;
            }
            default: {
                throw HSSFCell.typeMismatch(CellType.BOOLEAN, this._cellType, false);
            }
        }
        FormulaRecord fr = ((FormulaRecordAggregate)this._record).getFormulaRecord();
        HSSFCell.checkFormulaCachedValueType(CellType.BOOLEAN, fr);
        return fr.getCachedBooleanValue();
    }

    @Override
    public byte getErrorCellValue() {
        switch (this._cellType) {
            case ERROR: {
                return ((BoolErrRecord)this._record).getErrorValue();
            }
            case FORMULA: {
                FormulaRecord fr = ((FormulaRecordAggregate)this._record).getFormulaRecord();
                HSSFCell.checkFormulaCachedValueType(CellType.ERROR, fr);
                return (byte)fr.getCachedErrorValue();
            }
        }
        throw HSSFCell.typeMismatch(CellType.ERROR, this._cellType, false);
    }

    @Override
    public void setCellStyle(CellStyle style) {
        this.setCellStyle((HSSFCellStyle)style);
    }

    public void setCellStyle(HSSFCellStyle style) {
        if (style == null) {
            this._record.setXFIndex((short)15);
            return;
        }
        style.verifyBelongsToWorkbook(this._book);
        short styleIndex = style.getUserStyleName() != null ? this.applyUserCellStyle(style) : style.getIndex();
        this._record.setXFIndex(styleIndex);
    }

    @Override
    public HSSFCellStyle getCellStyle() {
        short styleIndex = this._record.getXFIndex();
        ExtendedFormatRecord xf = this._book.getWorkbook().getExFormatAt(styleIndex);
        return new HSSFCellStyle(styleIndex, xf, this._book);
    }

    protected CellValueRecordInterface getCellValueRecord() {
        return this._record;
    }

    private static void checkBounds(int cellIndex) {
        if (cellIndex < 0 || cellIndex > LAST_COLUMN_NUMBER) {
            throw new IllegalArgumentException("Invalid column index (" + cellIndex + ").  Allowable column range for " + FILE_FORMAT_NAME + " is (0.." + LAST_COLUMN_NUMBER + ") or ('A'..'" + LAST_COLUMN_NAME + "')");
        }
    }

    @Override
    public void setAsActiveCell() {
        int row = this._record.getRow();
        short col = this._record.getColumn();
        this._sheet.getSheet().setActiveCellRow(row);
        this._sheet.getSheet().setActiveCellCol(col);
    }

    public String toString() {
        switch (this.getCellTypeEnum()) {
            case BLANK: {
                return "";
            }
            case BOOLEAN: {
                return this.getBooleanCellValue() ? "TRUE" : "FALSE";
            }
            case ERROR: {
                return ErrorEval.getText(((BoolErrRecord)this._record).getErrorValue());
            }
            case FORMULA: {
                return this.getCellFormula();
            }
            case NUMERIC: {
                if (DateUtil.isCellDateFormatted(this)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", LocaleUtil.getUserLocale());
                    sdf.setTimeZone(LocaleUtil.getUserTimeZone());
                    return sdf.format(this.getDateCellValue());
                }
                return String.valueOf(this.getNumericCellValue());
            }
            case STRING: {
                return this.getStringCellValue();
            }
        }
        return "Unknown Cell Type: " + (Object)((Object)this.getCellType());
    }

    @Override
    public void setCellComment(Comment comment) {
        if (comment == null) {
            this.removeCellComment();
            return;
        }
        comment.setRow(this._record.getRow());
        comment.setColumn(this._record.getColumn());
        this._comment = (HSSFComment)comment;
    }

    @Override
    public HSSFComment getCellComment() {
        if (this._comment == null) {
            this._comment = this._sheet.findCellComment(this._record.getRow(), this._record.getColumn());
        }
        return this._comment;
    }

    @Override
    public void removeCellComment() {
        HSSFComment comment = this._sheet.findCellComment(this._record.getRow(), this._record.getColumn());
        this._comment = null;
        if (null == comment) {
            return;
        }
        this._sheet.getDrawingPatriarch().removeShape(comment);
    }

    @Override
    public HSSFHyperlink getHyperlink() {
        return this._sheet.getHyperlink(this._record.getRow(), this._record.getColumn());
    }

    @Override
    public void setHyperlink(Hyperlink hyperlink) {
        if (hyperlink == null) {
            this.removeHyperlink();
            return;
        }
        HSSFHyperlink link = (HSSFHyperlink)hyperlink;
        link.setFirstRow(this._record.getRow());
        link.setLastRow(this._record.getRow());
        link.setFirstColumn(this._record.getColumn());
        link.setLastColumn(this._record.getColumn());
        switch (link.getTypeEnum()) {
            case EMAIL: 
            case URL: {
                link.setLabel("url");
                break;
            }
            case FILE: {
                link.setLabel("file");
                break;
            }
            case DOCUMENT: {
                link.setLabel("place");
                break;
            }
        }
        List<RecordBase> records = this._sheet.getSheet().getRecords();
        int eofLoc = records.size() - 1;
        records.add(eofLoc, link.record);
    }

    @Override
    public void removeHyperlink() {
        Iterator<RecordBase> it = this._sheet.getSheet().getRecords().iterator();
        while (it.hasNext()) {
            HyperlinkRecord link;
            RecordBase rec = it.next();
            if (!(rec instanceof HyperlinkRecord) || (link = (HyperlinkRecord)rec).getFirstColumn() != this._record.getColumn() || link.getFirstRow() != this._record.getRow()) continue;
            it.remove();
            return;
        }
    }

    @Override
    public CellType getCachedFormulaResultType() {
        if (this._cellType != CellType.FORMULA) {
            throw new IllegalStateException("Only formula cells have cached results");
        }
        int code = ((FormulaRecordAggregate)this._record).getFormulaRecord().getCachedResultType();
        return CellType.forInt(code);
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public CellType getCachedFormulaResultTypeEnum() {
        return this.getCachedFormulaResultType();
    }

    void setCellArrayFormula(CellRangeAddress range) {
        int row = this._record.getRow();
        short col = this._record.getColumn();
        short styleIndex = this._record.getXFIndex();
        this.setCellType(CellType.FORMULA, false, row, col, styleIndex);
        Ptg[] ptgsForCell = new Ptg[]{new ExpPtg(range.getFirstRow(), range.getFirstColumn())};
        FormulaRecordAggregate agg = (FormulaRecordAggregate)this._record;
        agg.setParsedExpression(ptgsForCell);
    }

    @Override
    public CellRangeAddress getArrayFormulaRange() {
        if (this._cellType != CellType.FORMULA) {
            String ref = new CellReference(this).formatAsString();
            throw new IllegalStateException("Cell " + ref + " is not part of an array formula.");
        }
        return ((FormulaRecordAggregate)this._record).getArrayFormulaRange();
    }

    @Override
    public boolean isPartOfArrayFormulaGroup() {
        return this._cellType == CellType.FORMULA && ((FormulaRecordAggregate)this._record).isPartOfArrayFormula();
    }

    private short applyUserCellStyle(HSSFCellStyle style) {
        int styleIndex;
        if (style.getUserStyleName() == null) {
            throw new IllegalArgumentException("Expected user-defined style");
        }
        InternalWorkbook iwb = this._book.getWorkbook();
        int userXf = -1;
        int numfmt = iwb.getNumExFormats();
        for (int i = 0; i < numfmt; i = (int)((short)(i + 1))) {
            ExtendedFormatRecord xf = iwb.getExFormatAt(i);
            if (xf.getXFType() != 0 || xf.getParentIndex() != style.getIndex()) continue;
            userXf = i;
            break;
        }
        if (userXf == -1) {
            ExtendedFormatRecord xfr = iwb.createCellXF();
            xfr.cloneStyleFrom(iwb.getExFormatAt(style.getIndex()));
            xfr.setIndentionOptions((short)0);
            xfr.setXFType((short)0);
            xfr.setParentIndex(style.getIndex());
            styleIndex = (short)numfmt;
        } else {
            styleIndex = userXf;
        }
        return (short)styleIndex;
    }
}

