/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Removal;

public abstract class CellBase
implements Cell {
    @Override
    public final void setCellType(CellType cellType) {
        if (cellType == null || cellType == CellType._NONE) {
            throw new IllegalArgumentException("cellType shall not be null nor _NONE");
        }
        if (cellType == CellType.FORMULA) {
            if (this.getCellType() != CellType.FORMULA) {
                throw new IllegalArgumentException("Calling Cell.setCellType(CellType.FORMULA) is illegal. Use setCellFormula(String) directly.");
            }
            return;
        }
        this.tryToDeleteArrayFormulaIfSet();
        this.setCellTypeImpl(cellType);
    }

    @Override
    public void setBlank() {
        this.setCellType(CellType.BLANK);
    }

    @Override
    public CellAddress getAddress() {
        return new CellAddress(this);
    }

    protected abstract void setCellTypeImpl(CellType var1);

    public final void tryToDeleteArrayFormula(String message) {
        assert (this.isPartOfArrayFormulaGroup());
        CellRangeAddress arrayFormulaRange = this.getArrayFormulaRange();
        if (arrayFormulaRange.getNumberOfCells() > 1) {
            if (message == null) {
                message = "Cell " + new CellReference(this).formatAsString() + " is part of a multi-cell array formula. You cannot change part of an array.";
            }
            throw new IllegalStateException(message);
        }
        this.getRow().getSheet().removeArrayFormula(this);
    }

    @Override
    public final void setCellFormula(String formula) throws FormulaParseException, IllegalStateException {
        this.tryToDeleteArrayFormulaIfSet();
        if (formula == null) {
            this.removeFormula();
            return;
        }
        if (this.getValueType() == CellType.BLANK) {
            this.setCellValue(0.0);
        }
        this.setCellFormulaImpl(formula);
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    public final CellType getCellTypeEnum() {
        return this.getCellType();
    }

    protected abstract void setCellFormulaImpl(String var1);

    protected final CellType getValueType() {
        CellType type = this.getCellType();
        if (type != CellType.FORMULA) {
            return type;
        }
        return this.getCachedFormulaResultType();
    }

    @Override
    public final void removeFormula() {
        if (this.getCellType() == CellType.BLANK) {
            return;
        }
        if (this.isPartOfArrayFormulaGroup()) {
            this.tryToDeleteArrayFormula(null);
            return;
        }
        this.removeFormulaImpl();
    }

    protected abstract void removeFormulaImpl();

    private void tryToDeleteArrayFormulaIfSet() {
        if (this.isPartOfArrayFormulaGroup()) {
            this.tryToDeleteArrayFormula(null);
        }
    }

    @Override
    public void setCellValue(double value) {
        if (Double.isInfinite(value)) {
            this.setCellErrorValue(FormulaError.DIV0.getCode());
        } else if (Double.isNaN(value)) {
            this.setCellErrorValue(FormulaError.NUM.getCode());
        } else {
            this.setCellValueImpl(value);
        }
    }

    protected abstract void setCellValueImpl(double var1);

    @Override
    public void setCellValue(Date value) {
        if (value == null) {
            this.setBlank();
            return;
        }
        this.setCellValueImpl(value);
    }

    @Override
    public void setCellValue(LocalDateTime value) {
        if (value == null) {
            this.setBlank();
            return;
        }
        this.setCellValueImpl(value);
    }

    protected abstract void setCellValueImpl(Date var1);

    protected abstract void setCellValueImpl(LocalDateTime var1);

    @Override
    public void setCellValue(Calendar value) {
        if (value == null) {
            this.setBlank();
            return;
        }
        this.setCellValueImpl(value);
    }

    protected abstract void setCellValueImpl(Calendar var1);

    @Override
    public void setCellValue(String value) {
        if (value == null) {
            this.setBlank();
            return;
        }
        this.checkLength(value);
        this.setCellValueImpl(value);
    }

    protected abstract void setCellValueImpl(String var1);

    private void checkLength(String value) {
        if (value.length() > this.getSpreadsheetVersion().getMaxTextLength()) {
            String message = String.format(Locale.ROOT, "The maximum length of cell contents (text) is %d characters", this.getSpreadsheetVersion().getMaxTextLength());
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void setCellValue(RichTextString value) {
        if (value == null || value.getString() == null) {
            this.setBlank();
            return;
        }
        this.checkLength(value.getString());
        this.setCellValueImpl(value);
    }

    protected abstract void setCellValueImpl(RichTextString var1);

    protected abstract SpreadsheetVersion getSpreadsheetVersion();
}

