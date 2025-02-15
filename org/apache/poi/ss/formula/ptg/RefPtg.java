/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.ptg;

import org.apache.poi.ss.formula.ptg.Ref2DPtgBase;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LittleEndianInput;

public final class RefPtg
extends Ref2DPtgBase {
    public static final byte sid = 36;

    public RefPtg(String cellref) {
        super(new CellReference(cellref));
    }

    public RefPtg(RefPtg other) {
        super(other);
    }

    public RefPtg(int row, int column, boolean isRowRelative, boolean isColumnRelative) {
        super(row, column, isRowRelative, isColumnRelative);
    }

    public RefPtg(LittleEndianInput in) {
        super(in);
    }

    public RefPtg(CellReference cr) {
        super(cr);
    }

    @Override
    protected byte getSid() {
        return 36;
    }

    @Override
    public RefPtg copy() {
        return new RefPtg(this);
    }
}

