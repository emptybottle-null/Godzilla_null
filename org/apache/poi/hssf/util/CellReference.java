/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.util;

public final class CellReference
extends org.apache.poi.ss.util.CellReference {
    public CellReference(String cellRef) {
        super(cellRef);
    }

    public CellReference(int pRow, int pCol) {
        super(pRow, pCol, true, true);
    }

    public CellReference(int pRow, int pCol, boolean pAbsRow, boolean pAbsCol) {
        super(null, pRow, pCol, pAbsRow, pAbsCol);
    }

    public CellReference(String pSheetName, int pRow, int pCol, boolean pAbsRow, boolean pAbsCol) {
        super(pSheetName, pRow, pCol, pAbsRow, pAbsCol);
    }
}

