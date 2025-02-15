/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.util.Internal;

@Internal
class AutoSizeColumnTracker {
    private final int defaultCharWidth;
    private final DataFormatter dataFormatter = new DataFormatter();
    private final Map<Integer, ColumnWidthPair> maxColumnWidths = new HashMap<Integer, ColumnWidthPair>();
    private final Set<Integer> untrackedColumns = new HashSet<Integer>();
    private boolean trackAllColumns;

    public AutoSizeColumnTracker(Sheet sheet) {
        this.defaultCharWidth = SheetUtil.getDefaultCharWidth(sheet.getWorkbook());
    }

    public SortedSet<Integer> getTrackedColumns() {
        TreeSet<Integer> sorted = new TreeSet<Integer>(this.maxColumnWidths.keySet());
        return Collections.unmodifiableSortedSet(sorted);
    }

    public boolean isColumnTracked(int column) {
        return this.trackAllColumns || this.maxColumnWidths.containsKey(column);
    }

    public boolean isAllColumnsTracked() {
        return this.trackAllColumns;
    }

    public void trackAllColumns() {
        this.trackAllColumns = true;
        this.untrackedColumns.clear();
    }

    public void untrackAllColumns() {
        this.trackAllColumns = false;
        this.maxColumnWidths.clear();
        this.untrackedColumns.clear();
    }

    public void trackColumns(Collection<Integer> columns) {
        for (int column : columns) {
            this.trackColumn(column);
        }
    }

    public boolean trackColumn(int column) {
        this.untrackedColumns.remove(column);
        if (!this.maxColumnWidths.containsKey(column)) {
            this.maxColumnWidths.put(column, new ColumnWidthPair());
            return true;
        }
        return false;
    }

    private boolean implicitlyTrackColumn(int column) {
        if (!this.untrackedColumns.contains(column)) {
            this.trackColumn(column);
            return true;
        }
        return false;
    }

    public boolean untrackColumns(Collection<Integer> columns) {
        this.untrackedColumns.addAll(columns);
        return this.maxColumnWidths.keySet().removeAll(columns);
    }

    public boolean untrackColumn(int column) {
        this.untrackedColumns.add(column);
        return this.maxColumnWidths.keySet().remove(column);
    }

    public int getBestFitColumnWidth(int column, boolean useMergedCells) {
        if (!this.maxColumnWidths.containsKey(column)) {
            if (this.trackAllColumns) {
                if (!this.implicitlyTrackColumn(column)) {
                    IllegalStateException reason = new IllegalStateException("Column was explicitly untracked after trackAllColumns() was called.");
                    throw new IllegalStateException("Cannot get best fit column width on explicitly untracked column " + column + ". Either explicitly track the column or track all columns.", reason);
                }
            } else {
                IllegalStateException reason = new IllegalStateException("Column was never explicitly tracked and isAllColumnsTracked() is false (trackAllColumns() was never called or untrackAllColumns() was called after trackAllColumns() was called).");
                throw new IllegalStateException("Cannot get best fit column width on untracked column " + column + ". Either explicitly track the column or track all columns.", reason);
            }
        }
        double width = this.maxColumnWidths.get(column).getMaxColumnWidth(useMergedCells);
        return Math.toIntExact(Math.round(256.0 * width));
    }

    public void updateColumnWidths(Row row) {
        this.implicitlyTrackColumnsInRow(row);
        if (this.maxColumnWidths.size() < row.getPhysicalNumberOfCells()) {
            for (Map.Entry<Integer, ColumnWidthPair> e : this.maxColumnWidths.entrySet()) {
                int column = e.getKey();
                Cell cell = row.getCell(column);
                if (cell == null) continue;
                ColumnWidthPair pair = e.getValue();
                this.updateColumnWidth(cell, pair);
            }
        } else {
            for (Cell cell : row) {
                int column = cell.getColumnIndex();
                if (!this.maxColumnWidths.containsKey(column)) continue;
                ColumnWidthPair pair = this.maxColumnWidths.get(column);
                this.updateColumnWidth(cell, pair);
            }
        }
    }

    private void implicitlyTrackColumnsInRow(Row row) {
        if (this.trackAllColumns) {
            for (Cell cell : row) {
                int column = cell.getColumnIndex();
                this.implicitlyTrackColumn(column);
            }
        }
    }

    private void updateColumnWidth(Cell cell, ColumnWidthPair pair) {
        double unmergedWidth = SheetUtil.getCellWidth(cell, this.defaultCharWidth, this.dataFormatter, false);
        double mergedWidth = SheetUtil.getCellWidth(cell, this.defaultCharWidth, this.dataFormatter, true);
        pair.setMaxColumnWidths(unmergedWidth, mergedWidth);
    }

    private static class ColumnWidthPair {
        private double withSkipMergedCells;
        private double withUseMergedCells;

        public ColumnWidthPair() {
            this(-1.0, -1.0);
        }

        public ColumnWidthPair(double columnWidthSkipMergedCells, double columnWidthUseMergedCells) {
            this.withSkipMergedCells = columnWidthSkipMergedCells;
            this.withUseMergedCells = columnWidthUseMergedCells;
        }

        public double getMaxColumnWidth(boolean useMergedCells) {
            return useMergedCells ? this.withUseMergedCells : this.withSkipMergedCells;
        }

        public void setMaxColumnWidths(double unmergedWidth, double mergedWidth) {
            this.withUseMergedCells = Math.max(this.withUseMergedCells, mergedWidth);
            this.withSkipMergedCells = Math.max(this.withUseMergedCells, unmergedWidth);
        }
    }
}

