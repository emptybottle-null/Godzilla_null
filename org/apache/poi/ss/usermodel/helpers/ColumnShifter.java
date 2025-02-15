/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.helpers.BaseRowColShifter;
import org.apache.poi.ss.util.CellRangeAddress;

public abstract class ColumnShifter
extends BaseRowColShifter {
    protected final Sheet sheet;

    public ColumnShifter(Sheet sh) {
        this.sheet = sh;
    }

    @Override
    public List<CellRangeAddress> shiftMergedRegions(int startColumn, int endColumn, int n) {
        ArrayList<CellRangeAddress> shiftedRegions = new ArrayList<CellRangeAddress>();
        HashSet<Integer> removedIndices = new HashSet<Integer>();
        int size = this.sheet.getNumMergedRegions();
        for (int i = 0; i < size; ++i) {
            boolean inEnd;
            CellRangeAddress merged = this.sheet.getMergedRegion(i);
            if (this.removalNeeded(merged, startColumn, endColumn, n)) {
                removedIndices.add(i);
                continue;
            }
            boolean inStart = merged.getFirstColumn() >= startColumn || merged.getLastColumn() >= startColumn;
            boolean bl = inEnd = merged.getFirstColumn() <= endColumn || merged.getLastColumn() <= endColumn;
            if (!inStart || !inEnd || merged.containsColumn(startColumn - 1) || merged.containsColumn(endColumn + 1)) continue;
            merged.setFirstColumn(merged.getFirstColumn() + n);
            merged.setLastColumn(merged.getLastColumn() + n);
            shiftedRegions.add(merged);
            removedIndices.add(i);
        }
        if (!removedIndices.isEmpty()) {
            this.sheet.removeMergedRegions(removedIndices);
        }
        for (CellRangeAddress region : shiftedRegions) {
            this.sheet.addMergedRegion(region);
        }
        return shiftedRegions;
    }

    private boolean removalNeeded(CellRangeAddress merged, int startColumn, int endColumn, int n) {
        CellRangeAddress overwrite;
        int movedColumns = endColumn - startColumn + 1;
        if (n > 0) {
            int firstCol = Math.max(endColumn + 1, endColumn + n - movedColumns);
            int lastCol = endColumn + n;
            overwrite = new CellRangeAddress(0, 0, firstCol, lastCol);
        } else {
            int firstCol = startColumn + n;
            int lastCol = Math.min(startColumn - 1, startColumn + n + movedColumns);
            overwrite = new CellRangeAddress(0, 0, firstCol, lastCol);
        }
        return merged.intersects(overwrite);
    }

    public void shiftColumns(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        block3: {
            block2: {
                if (step <= 0) break block2;
                for (Row row : this.sheet) {
                    if (row == null) continue;
                    row.shiftCellsRight(firstShiftColumnIndex, lastShiftColumnIndex, step);
                }
                break block3;
            }
            if (step >= 0) break block3;
            for (Row row : this.sheet) {
                if (row == null) continue;
                row.shiftCellsLeft(firstShiftColumnIndex, lastShiftColumnIndex, -step);
            }
        }
    }
}

