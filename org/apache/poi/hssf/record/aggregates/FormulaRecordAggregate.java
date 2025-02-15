/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.aggregates;

import org.apache.poi.hssf.record.ArrayRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.SharedFormulaRecord;
import org.apache.poi.hssf.record.SharedValueRecordBase;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.hssf.record.aggregates.SharedValueManager;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.ExpPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.RecordFormatException;

public final class FormulaRecordAggregate
extends RecordAggregate
implements CellValueRecordInterface {
    private final FormulaRecord _formulaRecord;
    private SharedValueManager _sharedValueManager;
    private StringRecord _stringRecord;
    private SharedFormulaRecord _sharedFormulaRecord;

    public FormulaRecordAggregate(FormulaRecord formulaRec, StringRecord stringRec, SharedValueManager svm) {
        if (svm == null) {
            throw new IllegalArgumentException("sfm must not be null");
        }
        if (formulaRec.hasCachedResultString()) {
            if (stringRec == null) {
                throw new RecordFormatException("Formula record flag is set but String record was not found");
            }
            this._stringRecord = stringRec;
        } else {
            this._stringRecord = null;
        }
        this._formulaRecord = formulaRec;
        this._sharedValueManager = svm;
        if (formulaRec.isSharedFormula()) {
            CellReference firstCell = formulaRec.getFormula().getExpReference();
            if (firstCell == null) {
                FormulaRecordAggregate.handleMissingSharedFormulaRecord(formulaRec);
            } else {
                this._sharedFormulaRecord = svm.linkSharedFormulaRecord(firstCell, this);
            }
        }
    }

    private static void handleMissingSharedFormulaRecord(FormulaRecord formula) {
        Ptg firstToken = formula.getParsedExpression()[0];
        if (firstToken instanceof ExpPtg) {
            throw new RecordFormatException("SharedFormulaRecord not found for FormulaRecord with (isSharedFormula=true)");
        }
        formula.setSharedFormula(false);
    }

    public FormulaRecord getFormulaRecord() {
        return this._formulaRecord;
    }

    public StringRecord getStringRecord() {
        return this._stringRecord;
    }

    @Override
    public short getXFIndex() {
        return this._formulaRecord.getXFIndex();
    }

    @Override
    public void setXFIndex(short xf) {
        this._formulaRecord.setXFIndex(xf);
    }

    @Override
    public void setColumn(short col) {
        this._formulaRecord.setColumn(col);
    }

    @Override
    public void setRow(int row) {
        this._formulaRecord.setRow(row);
    }

    @Override
    public short getColumn() {
        return this._formulaRecord.getColumn();
    }

    @Override
    public int getRow() {
        return this._formulaRecord.getRow();
    }

    public String toString() {
        return this._formulaRecord.toString();
    }

    @Override
    public void visitContainedRecords(RecordAggregate.RecordVisitor rv) {
        rv.visitRecord(this._formulaRecord);
        SharedValueRecordBase sharedFormulaRecord = this._sharedValueManager.getRecordForFirstCell(this);
        if (sharedFormulaRecord != null) {
            rv.visitRecord(sharedFormulaRecord);
        }
        if (this._formulaRecord.hasCachedResultString() && this._stringRecord != null) {
            rv.visitRecord(this._stringRecord);
        }
    }

    public String getStringValue() {
        if (this._stringRecord == null) {
            return null;
        }
        return this._stringRecord.getString();
    }

    public void setCachedStringResult(String value) {
        if (this._stringRecord == null) {
            this._stringRecord = new StringRecord();
        }
        this._stringRecord.setString(value);
        if (value.length() < 1) {
            this._formulaRecord.setCachedResultTypeEmptyString();
        } else {
            this._formulaRecord.setCachedResultTypeString();
        }
    }

    public void setCachedBooleanResult(boolean value) {
        this._stringRecord = null;
        this._formulaRecord.setCachedResultBoolean(value);
    }

    public void setCachedErrorResult(int errorCode) {
        this._stringRecord = null;
        this._formulaRecord.setCachedResultErrorCode(errorCode);
    }

    public void setCachedErrorResult(FormulaError error) {
        this.setCachedErrorResult(error.getCode());
    }

    public void setCachedDoubleResult(double value) {
        this._stringRecord = null;
        this._formulaRecord.setValue(value);
    }

    public Ptg[] getFormulaTokens() {
        if (this._sharedFormulaRecord != null) {
            return this._sharedFormulaRecord.getFormulaTokens(this._formulaRecord);
        }
        CellReference expRef = this._formulaRecord.getFormula().getExpReference();
        if (expRef != null) {
            ArrayRecord arec = this._sharedValueManager.getArrayRecord(expRef.getRow(), expRef.getCol());
            return arec.getFormulaTokens();
        }
        return this._formulaRecord.getParsedExpression();
    }

    public void setParsedExpression(Ptg[] ptgs) {
        this.notifyFormulaChanging();
        this._formulaRecord.setParsedExpression(ptgs);
    }

    public void unlinkSharedFormula() {
        SharedFormulaRecord sfr = this._sharedFormulaRecord;
        if (sfr == null) {
            throw new IllegalStateException("Formula not linked to shared formula");
        }
        Ptg[] ptgs = sfr.getFormulaTokens(this._formulaRecord);
        this._formulaRecord.setParsedExpression(ptgs);
        this._formulaRecord.setSharedFormula(false);
        this._sharedFormulaRecord = null;
    }

    public void notifyFormulaChanging() {
        if (this._sharedFormulaRecord != null) {
            this._sharedValueManager.unlink(this._sharedFormulaRecord);
        }
    }

    public boolean isPartOfArrayFormula() {
        if (this._sharedFormulaRecord != null) {
            return false;
        }
        CellReference expRef = this._formulaRecord.getFormula().getExpReference();
        ArrayRecord arec = expRef == null ? null : this._sharedValueManager.getArrayRecord(expRef.getRow(), expRef.getCol());
        return arec != null;
    }

    public CellRangeAddress getArrayFormulaRange() {
        if (this._sharedFormulaRecord != null) {
            throw new IllegalStateException("not an array formula cell.");
        }
        CellReference expRef = this._formulaRecord.getFormula().getExpReference();
        if (expRef == null) {
            throw new IllegalStateException("not an array formula cell.");
        }
        ArrayRecord arec = this._sharedValueManager.getArrayRecord(expRef.getRow(), expRef.getCol());
        if (arec == null) {
            throw new IllegalStateException("ArrayRecord was not found for the locator " + expRef.formatAsString());
        }
        CellRangeAddress8Bit a = arec.getRange();
        return new CellRangeAddress(a.getFirstRow(), a.getLastRow(), a.getFirstColumn(), a.getLastColumn());
    }

    public void setArrayFormula(CellRangeAddress r, Ptg[] ptgs) {
        ArrayRecord arr = new ArrayRecord(Formula.create(ptgs), new CellRangeAddress8Bit(r.getFirstRow(), r.getLastRow(), r.getFirstColumn(), r.getLastColumn()));
        this._sharedValueManager.addArrayRecord(arr);
    }

    public CellRangeAddress removeArrayFormula(int rowIndex, int columnIndex) {
        CellRangeAddress8Bit a = this._sharedValueManager.removeArrayFormula(rowIndex, columnIndex);
        this._formulaRecord.setParsedExpression(null);
        return new CellRangeAddress(a.getFirstRow(), a.getLastRow(), a.getFirstColumn(), a.getLastColumn());
    }
}

