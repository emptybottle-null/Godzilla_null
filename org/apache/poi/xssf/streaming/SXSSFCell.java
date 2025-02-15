/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.CellBase;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LocaleUtil;
import org.apache.poi.util.NotImplemented;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class SXSSFCell
extends CellBase {
    private final SXSSFRow _row;
    private Value _value;
    private CellStyle _style;
    private Property _firstProperty;

    public SXSSFCell(SXSSFRow row, CellType cellType) {
        this._row = row;
        this._value = new BlankValue();
        this.setType(cellType);
    }

    @Override
    protected SpreadsheetVersion getSpreadsheetVersion() {
        return SpreadsheetVersion.EXCEL2007;
    }

    @Override
    public int getColumnIndex() {
        return this._row.getCellIndex(this);
    }

    @Override
    public int getRowIndex() {
        return this._row.getRowNum();
    }

    @Override
    public SXSSFSheet getSheet() {
        return this._row.getSheet();
    }

    @Override
    public Row getRow() {
        return this._row;
    }

    @Override
    protected void setCellTypeImpl(CellType cellType) {
        this.ensureType(cellType);
    }

    private boolean isFormulaCell() {
        return this._value instanceof FormulaValue;
    }

    @Override
    public CellType getCellType() {
        if (this.isFormulaCell()) {
            return CellType.FORMULA;
        }
        return this._value.getType();
    }

    @Override
    public CellType getCachedFormulaResultType() {
        if (!this.isFormulaCell()) {
            throw new IllegalStateException("Only formula cells have cached results");
        }
        return ((FormulaValue)this._value).getFormulaType();
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public CellType getCachedFormulaResultTypeEnum() {
        return this.getCachedFormulaResultType();
    }

    @Override
    public void setCellValueImpl(double value) {
        this.ensureTypeOrFormulaType(CellType.NUMERIC);
        if (this._value.getType() == CellType.FORMULA) {
            ((NumericFormulaValue)this._value).setPreEvaluatedValue(value);
        } else {
            ((NumericValue)this._value).setValue(value);
        }
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

    @Override
    protected void setCellValueImpl(RichTextString value) {
        this.ensureRichTextStringType();
        if (this._value instanceof RichTextStringFormulaValue) {
            ((RichTextStringFormulaValue)this._value).setPreEvaluatedValue(value);
        } else {
            ((RichTextValue)this._value).setValue(value);
        }
    }

    @Override
    protected void setCellValueImpl(String value) {
        this.ensureTypeOrFormulaType(CellType.STRING);
        if (this._value.getType() == CellType.FORMULA) {
            ((StringFormulaValue)this._value).setPreEvaluatedValue(value);
        } else {
            ((PlainStringValue)this._value).setValue(value);
        }
    }

    @Override
    public void setCellFormulaImpl(String formula) throws FormulaParseException {
        assert (formula != null);
        if (this.getCellType() == CellType.FORMULA) {
            ((FormulaValue)this._value).setValue(formula);
        } else {
            switch (this.getCellType()) {
                case NUMERIC: {
                    this._value = new NumericFormulaValue(formula, this.getNumericCellValue());
                    break;
                }
                case STRING: {
                    if (this._value instanceof PlainStringValue) {
                        this._value = new StringFormulaValue(formula, this.getStringCellValue());
                        break;
                    }
                    assert (this._value instanceof RichTextValue);
                    this._value = new RichTextStringFormulaValue(formula, ((RichTextValue)this._value).getValue());
                    break;
                }
                case BOOLEAN: {
                    this._value = new BooleanFormulaValue(formula, this.getBooleanCellValue());
                    break;
                }
                case ERROR: {
                    this._value = new ErrorFormulaValue(formula, this.getErrorCellValue());
                    break;
                }
                case _NONE: 
                case FORMULA: 
                case BLANK: {
                    throw new AssertionError();
                }
            }
        }
    }

    @Override
    protected void removeFormulaImpl() {
        assert (this.getCellType() == CellType.FORMULA);
        switch (this.getCachedFormulaResultType()) {
            case NUMERIC: {
                double numericValue = ((NumericFormulaValue)this._value).getPreEvaluatedValue();
                this._value = new NumericValue();
                ((NumericValue)this._value).setValue(numericValue);
                break;
            }
            case STRING: {
                String stringValue = ((StringFormulaValue)this._value).getPreEvaluatedValue();
                this._value = new PlainStringValue();
                ((PlainStringValue)this._value).setValue(stringValue);
                break;
            }
            case BOOLEAN: {
                boolean booleanValue = ((BooleanFormulaValue)this._value).getPreEvaluatedValue();
                this._value = new BooleanValue();
                ((BooleanValue)this._value).setValue(booleanValue);
                break;
            }
            case ERROR: {
                byte errorValue = ((ErrorFormulaValue)this._value).getPreEvaluatedValue();
                this._value = new ErrorValue();
                ((ErrorValue)this._value).setValue(errorValue);
                break;
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    @Override
    public String getCellFormula() {
        if (this._value.getType() != CellType.FORMULA) {
            throw SXSSFCell.typeMismatch(CellType.FORMULA, this._value.getType(), false);
        }
        return ((FormulaValue)this._value).getValue();
    }

    @Override
    public double getNumericCellValue() {
        CellType cellType = this.getCellType();
        switch (cellType) {
            case BLANK: {
                return 0.0;
            }
            case FORMULA: {
                FormulaValue fv = (FormulaValue)this._value;
                if (fv.getFormulaType() != CellType.NUMERIC) {
                    throw SXSSFCell.typeMismatch(CellType.NUMERIC, CellType.FORMULA, false);
                }
                return ((NumericFormulaValue)this._value).getPreEvaluatedValue();
            }
            case NUMERIC: {
                return ((NumericValue)this._value).getValue();
            }
        }
        throw SXSSFCell.typeMismatch(CellType.NUMERIC, cellType, false);
    }

    @Override
    public Date getDateCellValue() {
        CellType cellType = this.getCellType();
        if (cellType == CellType.BLANK) {
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
    public RichTextString getRichStringCellValue() {
        CellType cellType = this.getCellType();
        if (this.getCellType() != CellType.STRING) {
            throw SXSSFCell.typeMismatch(CellType.STRING, cellType, false);
        }
        StringValue sval = (StringValue)this._value;
        if (sval.isRichText()) {
            return ((RichTextValue)this._value).getValue();
        }
        String plainText = this.getStringCellValue();
        return this.getSheet().getWorkbook().getCreationHelper().createRichTextString(plainText);
    }

    @Override
    public String getStringCellValue() {
        CellType cellType = this.getCellType();
        switch (cellType) {
            case BLANK: {
                return "";
            }
            case FORMULA: {
                FormulaValue fv = (FormulaValue)this._value;
                if (fv.getFormulaType() != CellType.STRING) {
                    throw SXSSFCell.typeMismatch(CellType.STRING, CellType.FORMULA, false);
                }
                if (this._value instanceof RichTextStringFormulaValue) {
                    return ((RichTextStringFormulaValue)this._value).getPreEvaluatedValue().getString();
                }
                return ((StringFormulaValue)this._value).getPreEvaluatedValue();
            }
            case STRING: {
                if (((StringValue)this._value).isRichText()) {
                    return ((RichTextValue)this._value).getValue().getString();
                }
                return ((PlainStringValue)this._value).getValue();
            }
        }
        throw SXSSFCell.typeMismatch(CellType.STRING, cellType, false);
    }

    @Override
    public void setCellValue(boolean value) {
        this.ensureTypeOrFormulaType(CellType.BOOLEAN);
        if (this._value.getType() == CellType.FORMULA) {
            ((BooleanFormulaValue)this._value).setPreEvaluatedValue(value);
        } else {
            ((BooleanValue)this._value).setValue(value);
        }
    }

    @Override
    public void setCellErrorValue(byte value) {
        this._value = this._value.getType() == CellType.FORMULA ? new ErrorFormulaValue(this.getCellFormula(), value) : new ErrorValue(value);
    }

    @Override
    public boolean getBooleanCellValue() {
        CellType cellType = this.getCellType();
        switch (cellType) {
            case BLANK: {
                return false;
            }
            case FORMULA: {
                FormulaValue fv = (FormulaValue)this._value;
                if (fv.getFormulaType() != CellType.BOOLEAN) {
                    throw SXSSFCell.typeMismatch(CellType.BOOLEAN, CellType.FORMULA, false);
                }
                return ((BooleanFormulaValue)this._value).getPreEvaluatedValue();
            }
            case BOOLEAN: {
                return ((BooleanValue)this._value).getValue();
            }
        }
        throw SXSSFCell.typeMismatch(CellType.BOOLEAN, cellType, false);
    }

    @Override
    public byte getErrorCellValue() {
        CellType cellType = this.getCellType();
        switch (cellType) {
            case BLANK: {
                return 0;
            }
            case FORMULA: {
                FormulaValue fv = (FormulaValue)this._value;
                if (fv.getFormulaType() != CellType.ERROR) {
                    throw SXSSFCell.typeMismatch(CellType.ERROR, CellType.FORMULA, false);
                }
                return ((ErrorFormulaValue)this._value).getPreEvaluatedValue();
            }
            case ERROR: {
                return ((ErrorValue)this._value).getValue();
            }
        }
        throw SXSSFCell.typeMismatch(CellType.ERROR, cellType, false);
    }

    @Override
    public void setCellStyle(CellStyle style) {
        this._style = style;
    }

    @Override
    public CellStyle getCellStyle() {
        if (this._style == null) {
            SXSSFWorkbook wb = (SXSSFWorkbook)this.getRow().getSheet().getWorkbook();
            return wb.getCellStyleAt(0);
        }
        return this._style;
    }

    @Override
    public void setAsActiveCell() {
        this.getSheet().setActiveCell(this.getAddress());
    }

    @Override
    public void setCellComment(Comment comment) {
        this.setProperty(1, comment);
    }

    @Override
    public Comment getCellComment() {
        return (Comment)this.getPropertyValue(1);
    }

    @Override
    public void removeCellComment() {
        this.removeProperty(1);
    }

    @Override
    public Hyperlink getHyperlink() {
        return (Hyperlink)this.getPropertyValue(2);
    }

    @Override
    public void setHyperlink(Hyperlink link) {
        if (link == null) {
            this.removeHyperlink();
            return;
        }
        this.setProperty(2, link);
        XSSFHyperlink xssfobj = (XSSFHyperlink)link;
        CellReference ref = new CellReference(this.getRowIndex(), this.getColumnIndex());
        xssfobj.setCellReference(ref);
        this.getSheet()._sh.addHyperlink(xssfobj);
    }

    @Override
    public void removeHyperlink() {
        this.removeProperty(2);
        this.getSheet()._sh.removeHyperlink(this.getRowIndex(), this.getColumnIndex());
    }

    @Override
    @NotImplemented
    public CellRangeAddress getArrayFormulaRange() {
        return null;
    }

    @Override
    @NotImplemented
    public boolean isPartOfArrayFormulaGroup() {
        return false;
    }

    public String toString() {
        switch (this.getCellType()) {
            case BLANK: {
                return "";
            }
            case BOOLEAN: {
                return this.getBooleanCellValue() ? "TRUE" : "FALSE";
            }
            case ERROR: {
                return ErrorEval.getText(this.getErrorCellValue());
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
                return this.getNumericCellValue() + "";
            }
            case STRING: {
                return this.getRichStringCellValue().toString();
            }
        }
        return "Unknown Cell Type: " + (Object)((Object)this.getCellType());
    }

    void removeProperty(int type) {
        Property current = this._firstProperty;
        Property previous = null;
        while (current != null && current.getType() != type) {
            previous = current;
            current = current._next;
        }
        if (current != null) {
            if (previous != null) {
                previous._next = current._next;
            } else {
                this._firstProperty = current._next;
            }
        }
    }

    void setProperty(int type, Object value) {
        Property current = this._firstProperty;
        Property previous = null;
        while (current != null && current.getType() != type) {
            previous = current;
            current = current._next;
        }
        if (current != null) {
            current.setValue(value);
        } else {
            switch (type) {
                case 1: {
                    current = new CommentProperty(value);
                    break;
                }
                case 2: {
                    current = new HyperlinkProperty(value);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Invalid type: " + type);
                }
            }
            if (previous != null) {
                previous._next = current;
            } else {
                this._firstProperty = current;
            }
        }
    }

    Object getPropertyValue(int type) {
        return this.getPropertyValue(type, null);
    }

    Object getPropertyValue(int type, String defaultValue) {
        Property current = this._firstProperty;
        while (current != null && current.getType() != type) {
            current = current._next;
        }
        return current == null ? defaultValue : current.getValue();
    }

    void ensurePlainStringType() {
        if (this._value.getType() != CellType.STRING || ((StringValue)this._value).isRichText()) {
            this._value = new PlainStringValue();
        }
    }

    void ensureRichTextStringType() {
        if (this._value.getType() == CellType.FORMULA) {
            String formula = ((FormulaValue)this._value).getValue();
            this._value = new RichTextStringFormulaValue(formula, new XSSFRichTextString(""));
        } else if (this._value.getType() != CellType.STRING || !((StringValue)this._value).isRichText()) {
            this._value = new RichTextValue();
        }
    }

    void ensureType(CellType type) {
        if (this._value.getType() != type) {
            this.setType(type);
        }
    }

    void ensureTypeOrFormulaType(CellType type) {
        if (this._value.getType() == type) {
            if (type == CellType.STRING && ((StringValue)this._value).isRichText()) {
                this.setType(CellType.STRING);
            }
            return;
        }
        if (this._value.getType() == CellType.FORMULA) {
            if (((FormulaValue)this._value).getFormulaType() == type) {
                return;
            }
            switch (type) {
                case BOOLEAN: {
                    this._value = new BooleanFormulaValue(this.getCellFormula(), false);
                    break;
                }
                case NUMERIC: {
                    this._value = new NumericFormulaValue(this.getCellFormula(), 0.0);
                    break;
                }
                case STRING: {
                    this._value = new StringFormulaValue(this.getCellFormula(), "");
                    break;
                }
                case ERROR: {
                    this._value = new ErrorFormulaValue(this.getCellFormula(), FormulaError._NO_ERROR.getCode());
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
            return;
        }
        this.setType(type);
    }

    void setType(CellType type) {
        switch (type) {
            case NUMERIC: {
                this._value = new NumericValue();
                break;
            }
            case STRING: {
                PlainStringValue sval = new PlainStringValue();
                if (this._value != null) {
                    String str = this.convertCellValueToString();
                    sval.setValue(str);
                }
                this._value = sval;
                break;
            }
            case FORMULA: {
                if (this.getCellType() != CellType.BLANK) break;
                this._value = new NumericFormulaValue("", 0.0);
                break;
            }
            case BLANK: {
                this._value = new BlankValue();
                break;
            }
            case BOOLEAN: {
                BooleanValue bval = new BooleanValue();
                if (this._value != null) {
                    boolean val = this.convertCellValueToBoolean();
                    bval.setValue(val);
                }
                this._value = bval;
                break;
            }
            case ERROR: {
                this._value = new ErrorValue();
                break;
            }
            default: {
                throw new IllegalArgumentException("Illegal type " + (Object)((Object)type));
            }
        }
    }

    private static RuntimeException typeMismatch(CellType expectedTypeCode, CellType actualTypeCode, boolean isFormulaCell) {
        String msg = "Cannot get a " + (Object)((Object)expectedTypeCode) + " value from a " + (Object)((Object)actualTypeCode) + " " + (isFormulaCell ? "formula " : "") + "cell";
        return new IllegalStateException(msg);
    }

    private boolean convertCellValueToBoolean() {
        CellType cellType = this.getCellType();
        if (cellType == CellType.FORMULA) {
            cellType = this.getCachedFormulaResultType();
        }
        switch (cellType) {
            case BOOLEAN: {
                return this.getBooleanCellValue();
            }
            case STRING: {
                String text = this.getStringCellValue();
                return Boolean.parseBoolean(text);
            }
            case NUMERIC: {
                return this.getNumericCellValue() != 0.0;
            }
            case ERROR: 
            case BLANK: {
                return false;
            }
        }
        throw new RuntimeException("Unexpected cell type (" + (Object)((Object)cellType) + ")");
    }

    private String convertCellValueToString() {
        CellType cellType = this.getCellType();
        return this.convertCellValueToString(cellType);
    }

    private String convertCellValueToString(CellType cellType) {
        switch (cellType) {
            case BLANK: {
                return "";
            }
            case BOOLEAN: {
                return this.getBooleanCellValue() ? "TRUE" : "FALSE";
            }
            case STRING: {
                return this.getStringCellValue();
            }
            case NUMERIC: {
                return Double.toString(this.getNumericCellValue());
            }
            case ERROR: {
                byte errVal = this.getErrorCellValue();
                return FormulaError.forInt(errVal).getString();
            }
            case FORMULA: {
                FormulaValue fv;
                if (this._value != null && (fv = (FormulaValue)this._value).getFormulaType() != CellType.FORMULA) {
                    return this.convertCellValueToString(fv.getFormulaType());
                }
                return "";
            }
        }
        throw new IllegalStateException("Unexpected cell type (" + (Object)((Object)cellType) + ")");
    }

    static class ErrorValue
    implements Value {
        byte _value;

        public ErrorValue() {
            this._value = FormulaError._NO_ERROR.getCode();
        }

        public ErrorValue(byte _value) {
            this._value = _value;
        }

        @Override
        public CellType getType() {
            return CellType.ERROR;
        }

        void setValue(byte value) {
            this._value = value;
        }

        byte getValue() {
            return this._value;
        }
    }

    static class BooleanValue
    implements Value {
        boolean _value;

        public BooleanValue() {
            this._value = false;
        }

        public BooleanValue(boolean _value) {
            this._value = _value;
        }

        @Override
        public CellType getType() {
            return CellType.BOOLEAN;
        }

        void setValue(boolean value) {
            this._value = value;
        }

        boolean getValue() {
            return this._value;
        }
    }

    static class BlankValue
    implements Value {
        BlankValue() {
        }

        @Override
        public CellType getType() {
            return CellType.BLANK;
        }
    }

    static class ErrorFormulaValue
    extends FormulaValue {
        byte _preEvaluatedValue;

        public ErrorFormulaValue(String formula, byte value) {
            super(formula);
            this._preEvaluatedValue = value;
        }

        @Override
        CellType getFormulaType() {
            return CellType.ERROR;
        }

        void setPreEvaluatedValue(byte value) {
            this._preEvaluatedValue = value;
        }

        byte getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static class BooleanFormulaValue
    extends FormulaValue {
        boolean _preEvaluatedValue;

        public BooleanFormulaValue(String formula, boolean value) {
            super(formula);
            this._preEvaluatedValue = value;
        }

        @Override
        CellType getFormulaType() {
            return CellType.BOOLEAN;
        }

        void setPreEvaluatedValue(boolean value) {
            this._preEvaluatedValue = value;
        }

        boolean getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static class RichTextStringFormulaValue
    extends FormulaValue {
        RichTextString _preEvaluatedValue;

        public RichTextStringFormulaValue(String formula, RichTextString value) {
            super(formula);
            this._preEvaluatedValue = value;
        }

        @Override
        CellType getFormulaType() {
            return CellType.STRING;
        }

        void setPreEvaluatedValue(RichTextString value) {
            this._preEvaluatedValue = value;
        }

        RichTextString getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static class StringFormulaValue
    extends FormulaValue {
        String _preEvaluatedValue;

        public StringFormulaValue(String formula, String value) {
            super(formula);
            this._preEvaluatedValue = value;
        }

        @Override
        CellType getFormulaType() {
            return CellType.STRING;
        }

        void setPreEvaluatedValue(String value) {
            this._preEvaluatedValue = value;
        }

        String getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static class NumericFormulaValue
    extends FormulaValue {
        double _preEvaluatedValue;

        public NumericFormulaValue(String formula, double _preEvaluatedValue) {
            super(formula);
            this._preEvaluatedValue = _preEvaluatedValue;
        }

        @Override
        CellType getFormulaType() {
            return CellType.NUMERIC;
        }

        void setPreEvaluatedValue(double value) {
            this._preEvaluatedValue = value;
        }

        double getPreEvaluatedValue() {
            return this._preEvaluatedValue;
        }
    }

    static abstract class FormulaValue
    implements Value {
        String _value;

        public FormulaValue(String _value) {
            this._value = _value;
        }

        @Override
        public CellType getType() {
            return CellType.FORMULA;
        }

        void setValue(String value) {
            this._value = value;
        }

        String getValue() {
            return this._value;
        }

        abstract CellType getFormulaType();
    }

    static class RichTextValue
    extends StringValue {
        RichTextString _value;

        RichTextValue() {
        }

        @Override
        public CellType getType() {
            return CellType.STRING;
        }

        void setValue(RichTextString value) {
            this._value = value;
        }

        RichTextString getValue() {
            return this._value;
        }

        @Override
        boolean isRichText() {
            return true;
        }
    }

    static class PlainStringValue
    extends StringValue {
        String _value;

        PlainStringValue() {
        }

        void setValue(String value) {
            this._value = value;
        }

        String getValue() {
            return this._value;
        }

        @Override
        boolean isRichText() {
            return false;
        }
    }

    static abstract class StringValue
    implements Value {
        StringValue() {
        }

        @Override
        public CellType getType() {
            return CellType.STRING;
        }

        abstract boolean isRichText();
    }

    static class NumericValue
    implements Value {
        double _value;

        public NumericValue() {
            this._value = 0.0;
        }

        public NumericValue(double _value) {
            this._value = _value;
        }

        @Override
        public CellType getType() {
            return CellType.NUMERIC;
        }

        void setValue(double value) {
            this._value = value;
        }

        double getValue() {
            return this._value;
        }
    }

    static interface Value {
        public CellType getType();
    }

    static class HyperlinkProperty
    extends Property {
        public HyperlinkProperty(Object value) {
            super(value);
        }

        @Override
        public int getType() {
            return 2;
        }
    }

    static class CommentProperty
    extends Property {
        public CommentProperty(Object value) {
            super(value);
        }

        @Override
        public int getType() {
            return 1;
        }
    }

    static abstract class Property {
        static final int COMMENT = 1;
        static final int HYPERLINK = 2;
        Object _value;
        Property _next;

        public Property(Object value) {
            this._value = value;
        }

        abstract int getType();

        void setValue(Object value) {
            this._value = value;
        }

        Object getValue() {
            return this._value;
        }
    }
}

