/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.helpers.BaseRowColShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.LocaleUtil;

public abstract class RowShifter
extends BaseRowColShifter {
    protected final Sheet sheet;

    public RowShifter(Sheet sh) {
        this.sheet = sh;
    }

    @Override
    public List<CellRangeAddress> shiftMergedRegions(int startRow, int endRow, int n) {
        ArrayList<CellRangeAddress> shiftedRegions = new ArrayList<CellRangeAddress>();
        HashSet<Integer> removedIndices = new HashSet<Integer>();
        int size = this.sheet.getNumMergedRegions();
        for (int i = 0; i < size; ++i) {
            boolean inEnd;
            CellRangeAddress merged = this.sheet.getMergedRegion(i);
            if (this.removalNeeded(merged, startRow, endRow, n)) {
                removedIndices.add(i);
                continue;
            }
            boolean inStart = merged.getFirstRow() >= startRow || merged.getLastRow() >= startRow;
            boolean bl = inEnd = merged.getFirstRow() <= endRow || merged.getLastRow() <= endRow;
            if (!inStart || !inEnd || merged.containsRow(startRow - 1) || merged.containsRow(endRow + 1)) continue;
            merged.setFirstRow(merged.getFirstRow() + n);
            merged.setLastRow(merged.getLastRow() + n);
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

    private boolean removalNeeded(CellRangeAddress merged, int startRow, int endRow, int n) {
        CellRangeAddress overwrite;
        int movedRows = endRow - startRow + 1;
        if (n > 0) {
            int firstRow = Math.max(endRow + 1, endRow + n - movedRows);
            int lastRow = endRow + n;
            overwrite = new CellRangeAddress(firstRow, lastRow, 0, 0);
        } else {
            int firstRow = startRow + n;
            int lastRow = Math.min(startRow - 1, startRow + n + movedRows);
            overwrite = new CellRangeAddress(firstRow, lastRow, 0, 0);
        }
        return merged.intersects(overwrite);
    }

    public static void validateShiftParameters(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        if (step < 0) {
            throw new IllegalArgumentException("Shifting step may not be negative, but had " + step);
        }
        if (firstShiftColumnIndex > lastShiftColumnIndex) {
            throw new IllegalArgumentException(String.format(LocaleUtil.getUserLocale(), "Incorrect shifting range : %d-%d", firstShiftColumnIndex, lastShiftColumnIndex));
        }
    }

    public static void validateShiftLeftParameters(int firstShiftColumnIndex, int lastShiftColumnIndex, int step) {
        RowShifter.validateShiftParameters(firstShiftColumnIndex, lastShiftColumnIndex, step);
        if (firstShiftColumnIndex - step < 0) {
            throw new IllegalStateException("Column index less than zero: " + (firstShiftColumnIndex + step));
        }
    }
}

