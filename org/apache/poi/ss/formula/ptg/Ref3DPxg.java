/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.SheetIdentifier;
import org.apache.poi.ss.formula.SheetRangeAndWorkbookIndexFormatter;
import org.apache.poi.ss.formula.SheetRangeIdentifier;
import org.apache.poi.ss.formula.ptg.Pxg3D;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianOutput;

public final class Ref3DPxg
extends RefPtgBase
implements Pxg3D {
    private int externalWorkbookNumber = -1;
    private String firstSheetName;
    private String lastSheetName;

    public Ref3DPxg(Ref3DPxg other) {
        super(other);
        this.externalWorkbookNumber = other.externalWorkbookNumber;
        this.firstSheetName = other.firstSheetName;
        this.lastSheetName = other.lastSheetName;
    }

    public Ref3DPxg(int externalWorkbookNumber, SheetIdentifier sheetName, String cellref) {
        this(externalWorkbookNumber, sheetName, new CellReference(cellref));
    }

    public Ref3DPxg(int externalWorkbookNumber, SheetIdentifier sheetName, CellReference c) {
        super(c);
        this.externalWorkbookNumber = externalWorkbookNumber;
        this.firstSheetName = sheetName.getSheetIdentifier().getName();
        this.lastSheetName = sheetName instanceof SheetRangeIdentifier ? ((SheetRangeIdentifier)sheetName).getLastSheetIdentifier().getName() : null;
    }

    public Ref3DPxg(SheetIdentifier sheetName, String cellref) {
        this(sheetName, new CellReference(cellref));
    }

    public Ref3DPxg(SheetIdentifier sheetName, CellReference c) {
        this(-1, sheetName, c);
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
        sb.append("sheet=").append(this.firstSheetName);
        if (this.lastSheetName != null) {
            sb.append(" : ");
            sb.append("sheet=").append(this.lastSheetName);
        }
        sb.append(" ! ");
        sb.append(this.formatReferenceAsString());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int getExternalWorkbookNumber() {
        return this.externalWorkbookNumber;
    }

    @Override
    public String getSheetName() {
        return this.firstSheetName;
    }

    @Override
    public String getLastSheetName() {
        return this.lastSheetName;
    }

    @Override
    public void setSheetName(String sheetName) {
        this.firstSheetName = sheetName;
    }

    @Override
    public void setLastSheetName(String sheetName) {
        this.lastSheetName = sheetName;
    }

    public String format2DRefAsString() {
        return this.formatReferenceAsString();
    }

    @Override
    public String toFormulaString() {
        StringBuilder sb = new StringBuilder(64);
        SheetRangeAndWorkbookIndexFormatter.format(sb, this.externalWorkbookNumber, this.firstSheetName, this.lastSheetName);
        sb.append('!');
        sb.append(this.formatReferenceAsString());
        return sb.toString();
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
    public Ref3DPxg copy() {
        return new Ref3DPxg(this);
    }
}

