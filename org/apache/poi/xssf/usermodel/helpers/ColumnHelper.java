/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.util.CTColComparator;
import org.apache.poi.xssf.util.NumericRanges;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public class ColumnHelper {
    private CTWorksheet worksheet;

    public ColumnHelper(CTWorksheet worksheet) {
        this.worksheet = worksheet;
        this.cleanColumns();
    }

    public void cleanColumns() {
        TreeSet<CTCol> trackedCols = new TreeSet<CTCol>(CTColComparator.BY_MIN_MAX);
        CTCols newCols = CTCols.Factory.newInstance();
        CTCols[] colsArray = this.worksheet.getColsArray();
        for (int i = 0; i < colsArray.length; ++i) {
            CTCols cols = colsArray[i];
            for (CTCol col : cols.getColList()) {
                this.addCleanColIntoCols(newCols, col, trackedCols);
            }
        }
        for (int y = i - 1; y >= 0; --y) {
            this.worksheet.removeCols(y);
        }
        newCols.setColArray(trackedCols.toArray(new CTCol[0]));
        this.worksheet.addNewCols();
        this.worksheet.setColsArray(0, newCols);
    }

    public CTCols addCleanColIntoCols(CTCols cols, CTCol newCol) {
        TreeSet<CTCol> trackedCols = new TreeSet<CTCol>(CTColComparator.BY_MIN_MAX);
        trackedCols.addAll(cols.getColList());
        this.addCleanColIntoCols(cols, newCol, trackedCols);
        cols.setColArray(trackedCols.toArray(new CTCol[0]));
        return cols;
    }

    private void addCleanColIntoCols(CTCols cols, CTCol newCol, TreeSet<CTCol> trackedCols) {
        List<CTCol> overlapping = this.getOverlappingCols(newCol, trackedCols);
        if (overlapping.isEmpty()) {
            trackedCols.add(this.cloneCol(cols, newCol));
            return;
        }
        trackedCols.removeAll(overlapping);
        for (CTCol existing : overlapping) {
            CTCol afterCol;
            long[] overlap = this.getOverlap(newCol, existing);
            CTCol overlapCol = this.cloneCol(cols, existing, overlap);
            this.setColumnAttributes(newCol, overlapCol);
            trackedCols.add(overlapCol);
            CTCol beforeCol = existing.getMin() < newCol.getMin() ? existing : newCol;
            long[] before = new long[]{Math.min(existing.getMin(), newCol.getMin()), overlap[0] - 1L};
            if (before[0] <= before[1]) {
                trackedCols.add(this.cloneCol(cols, beforeCol, before));
            }
            CTCol cTCol = afterCol = existing.getMax() > newCol.getMax() ? existing : newCol;
            long[] after = new long[]{overlap[1] + 1L, Math.max(existing.getMax(), newCol.getMax())};
            if (after[0] > after[1]) continue;
            trackedCols.add(this.cloneCol(cols, afterCol, after));
        }
    }

    private CTCol cloneCol(CTCols cols, CTCol col, long[] newRange) {
        CTCol cloneCol = this.cloneCol(cols, col);
        cloneCol.setMin(newRange[0]);
        cloneCol.setMax(newRange[1]);
        return cloneCol;
    }

    private long[] getOverlap(CTCol col1, CTCol col2) {
        return this.getOverlappingRange(col1, col2);
    }

    private List<CTCol> getOverlappingCols(CTCol newCol, TreeSet<CTCol> trackedCols) {
        CTCol existing;
        CTCol lower = trackedCols.lower(newCol);
        TreeSet<CTCol> potentiallyOverlapping = lower == null ? trackedCols : trackedCols.tailSet(lower, this.overlaps(lower, newCol));
        ArrayList<CTCol> overlapping = new ArrayList<CTCol>();
        Iterator iterator = potentiallyOverlapping.iterator();
        while (iterator.hasNext() && this.overlaps(newCol, existing = (CTCol)iterator.next())) {
            overlapping.add(existing);
        }
        return overlapping;
    }

    private boolean overlaps(CTCol col1, CTCol col2) {
        return NumericRanges.getOverlappingType(this.toRange(col1), this.toRange(col2)) != -1;
    }

    private long[] getOverlappingRange(CTCol col1, CTCol col2) {
        return NumericRanges.getOverlappingRange(this.toRange(col1), this.toRange(col2));
    }

    private long[] toRange(CTCol col) {
        return new long[]{col.getMin(), col.getMax()};
    }

    public static void sortColumns(CTCols newCols) {
        CTCol[] colArray = newCols.getColArray();
        Arrays.sort(colArray, CTColComparator.BY_MIN_MAX);
        newCols.setColArray(colArray);
    }

    public CTCol cloneCol(CTCols cols, CTCol col) {
        CTCol newCol = cols.addNewCol();
        newCol.setMin(col.getMin());
        newCol.setMax(col.getMax());
        this.setColumnAttributes(col, newCol);
        return newCol;
    }

    public CTCol getColumn(long index, boolean splitColumns) {
        return this.getColumn1Based(index + 1L, splitColumns);
    }

    public CTCol getColumn1Based(long index1, boolean splitColumns) {
        CTCol[] colArray;
        CTCols cols = this.worksheet.getColsArray(0);
        for (CTCol col : colArray = cols.getColArray()) {
            long colMin = col.getMin();
            long colMax = col.getMax();
            if (colMin > index1 || colMax < index1) continue;
            if (splitColumns) {
                if (colMin < index1) {
                    this.insertCol(cols, colMin, index1 - 1L, new CTCol[]{col});
                }
                if (colMax > index1) {
                    this.insertCol(cols, index1 + 1L, colMax, new CTCol[]{col});
                }
                col.setMin(index1);
                col.setMax(index1);
            }
            return col;
        }
        return null;
    }

    private CTCol insertCol(CTCols cols, long min, long max, CTCol[] colsWithAttributes) {
        return this.insertCol(cols, min, max, colsWithAttributes, false, null);
    }

    private CTCol insertCol(CTCols cols, long min, long max, CTCol[] colsWithAttributes, boolean ignoreExistsCheck, CTCol overrideColumn) {
        if (ignoreExistsCheck || !this.columnExists(cols, min, max)) {
            CTCol newCol = cols.insertNewCol(0);
            newCol.setMin(min);
            newCol.setMax(max);
            for (CTCol col : colsWithAttributes) {
                this.setColumnAttributes(col, newCol);
            }
            if (overrideColumn != null) {
                this.setColumnAttributes(overrideColumn, newCol);
            }
            return newCol;
        }
        return null;
    }

    public boolean columnExists(CTCols cols, long index) {
        return this.columnExists1Based(cols, index + 1L);
    }

    private boolean columnExists1Based(CTCols cols, long index1) {
        for (CTCol col : cols.getColArray()) {
            if (col.getMin() != index1) continue;
            return true;
        }
        return false;
    }

    public void setColumnAttributes(CTCol fromCol, CTCol toCol) {
        if (fromCol.isSetBestFit()) {
            toCol.setBestFit(fromCol.getBestFit());
        }
        if (fromCol.isSetCustomWidth()) {
            toCol.setCustomWidth(fromCol.getCustomWidth());
        }
        if (fromCol.isSetHidden()) {
            toCol.setHidden(fromCol.getHidden());
        }
        if (fromCol.isSetStyle()) {
            toCol.setStyle(fromCol.getStyle());
        }
        if (fromCol.isSetWidth()) {
            toCol.setWidth(fromCol.getWidth());
        }
        if (fromCol.isSetCollapsed()) {
            toCol.setCollapsed(fromCol.getCollapsed());
        }
        if (fromCol.isSetPhonetic()) {
            toCol.setPhonetic(fromCol.getPhonetic());
        }
        if (fromCol.isSetOutlineLevel()) {
            toCol.setOutlineLevel(fromCol.getOutlineLevel());
        }
        toCol.setCollapsed(fromCol.isSetCollapsed());
    }

    public void setColBestFit(long index, boolean bestFit) {
        CTCol col = this.getOrCreateColumn1Based(index + 1L, false);
        col.setBestFit(bestFit);
    }

    public void setCustomWidth(long index, boolean bestFit) {
        CTCol col = this.getOrCreateColumn1Based(index + 1L, true);
        col.setCustomWidth(bestFit);
    }

    public void setColWidth(long index, double width) {
        CTCol col = this.getOrCreateColumn1Based(index + 1L, true);
        col.setWidth(width);
    }

    public void setColHidden(long index, boolean hidden) {
        CTCol col = this.getOrCreateColumn1Based(index + 1L, true);
        col.setHidden(hidden);
    }

    protected CTCol getOrCreateColumn1Based(long index1, boolean splitColumns) {
        CTCol col = this.getColumn1Based(index1, splitColumns);
        if (col == null) {
            col = this.worksheet.getColsArray(0).addNewCol();
            col.setMin(index1);
            col.setMax(index1);
        }
        return col;
    }

    public void setColDefaultStyle(long index, CellStyle style) {
        this.setColDefaultStyle(index, style.getIndex());
    }

    public void setColDefaultStyle(long index, int styleId) {
        CTCol col = this.getOrCreateColumn1Based(index + 1L, true);
        col.setStyle(styleId);
    }

    public int getColDefaultStyle(long index) {
        if (this.getColumn(index, false) != null) {
            return (int)this.getColumn(index, false).getStyle();
        }
        return -1;
    }

    private boolean columnExists(CTCols cols, long min, long max) {
        for (CTCol col : cols.getColList()) {
            if (col.getMin() != min || col.getMax() != max) continue;
            return true;
        }
        return false;
    }

    public int getIndexOfColumn(CTCols cols, CTCol searchCol) {
        if (cols == null || searchCol == null) {
            return -1;
        }
        int i = 0;
        for (CTCol col : cols.getColList()) {
            if (col.getMin() == searchCol.getMin() && col.getMax() == searchCol.getMax()) {
                return i;
            }
            ++i;
        }
        return -1;
    }
}

