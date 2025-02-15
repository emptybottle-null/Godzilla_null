/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.Pxg;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.LittleEndianOutput;

public final class Deleted3DPxg
extends OperandPtg
implements Pxg {
    private int externalWorkbookNumber = -1;
    private String sheetName;

    public Deleted3DPxg(int externalWorkbookNumber, String sheetName) {
        this.externalWorkbookNumber = externalWorkbookNumber;
        this.sheetName = sheetName;
    }

    public Deleted3DPxg(Deleted3DPxg other) {
        super(other);
        this.externalWorkbookNumber = other.externalWorkbookNumber;
        this.sheetName = other.sheetName;
    }

    public Deleted3DPxg(String sheetName) {
        this(-1, sheetName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName());
        sb.append(" [");
        if (this.externalWorkbookNumber >= 0) {
            sb.append(" [");
            sb.append("workbook=").append(this.getExternalWorkbookNumber());
            sb.append("] ");
        }
        sb.append("sheet=").append(this.getSheetName());
        sb.append(" ! ");
        sb.append(FormulaError.REF.getString());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int getExternalWorkbookNumber() {
        return this.externalWorkbookNumber;
    }

    @Override
    public String getSheetName() {
        return this.sheetName;
    }

    @Override
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    @Override
    public String toFormulaString() {
        StringBuilder sb = new StringBuilder(64);
        if (this.externalWorkbookNumber >= 0) {
            sb.append('[');
            sb.append(this.externalWorkbookNumber);
            sb.append(']');
        }
        if (this.sheetName != null) {
            SheetNameFormatter.appendFormat(sb, this.sheetName);
        }
        sb.append('!');
        sb.append(FormulaError.REF.getString());
        return sb.toString();
    }

    @Override
    public byte getDefaultOperandClass() {
        return 32;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public void write(LittleEndianOutput out) {
        throw new IllegalStateException("XSSF-only Ptg, should not be serialised");
    }

    @Override
    public Deleted3DPxg copy() {
        return new Deleted3DPxg(this);
    }
}

