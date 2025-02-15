/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.ColumnInfoRecord;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.util.Removal;

public final class ColumnInfoRecordsAggregate
extends RecordAggregate
implements Duplicatable {
    private final List<ColumnInfoRecord> records = new ArrayList<ColumnInfoRecord>();

    public ColumnInfoRecordsAggregate() {
    }

    public ColumnInfoRecordsAggregate(ColumnInfoRecordsAggregate other) {
        other.records.stream().map(ColumnInfoRecord::copy).forEach(this.records::add);
    }

    public ColumnInfoRecordsAggregate(RecordStream rs) {
        this();
        boolean isInOrder = true;
        ColumnInfoRecord cirPrev = null;
        while (rs.peekNextClass() == ColumnInfoRecord.class) {
            ColumnInfoRecord cir = (ColumnInfoRecord)rs.getNext();
            this.records.add(cir);
            if (cirPrev != null && ColumnInfoRecordsAggregate.compareColInfos(cirPrev, cir) > 0) {
                isInOrder = false;
            }
            cirPrev = cir;
        }
        if (this.records.size() < 1) {
            throw new RuntimeException("No column info records found");
        }
        if (!isInOrder) {
            this.records.sort(ColumnInfoRecordsAggregate::compareColInfos);
        }
    }

    @Deprecated
    @Removal(version="5.0.0")
    public ColumnInfoRecordsAggregate clone() {
        return this.copy();
    }

    @Override
    public ColumnInfoRecordsAggregate copy() {
        return new ColumnInfoRecordsAggregate(this);
    }

    public void insertColumn(ColumnInfoRecord col) {
        this.records.add(col);
        this.records.sort(ColumnInfoRecordsAggregate::compareColInfos);
    }

    private void insertColumn(int idx, ColumnInfoRecord col) {
        this.records.add(idx, col);
    }

    int getNumColumns() {
        return this.records.size();
    }

    @Override
    public void visitContainedRecords(RecordAggregate.RecordVisitor rv) {
        int nItems = this.records.size();
        if (nItems < 1) {
            return;
        }
        ColumnInfoRecord cirPrev = null;
        for (ColumnInfoRecord cir : this.records) {
            rv.visitRecord(cir);
            if (cirPrev != null && ColumnInfoRecordsAggregate.compareColInfos(cirPrev, cir) > 0) {
                throw new RuntimeException("Column info records are out of order");
            }
            cirPrev = cir;
        }
    }

    private int findStartOfColumnOutlineGroup(int pIdx) {
        ColumnInfoRecord prevColumnInfo;
        int idx;
        ColumnInfoRecord columnInfo = this.records.get(pIdx);
        int level = columnInfo.getOutlineLevel();
        for (idx = pIdx; idx != 0 && (prevColumnInfo = this.records.get(idx - 1)).isAdjacentBefore(columnInfo) && prevColumnInfo.getOutlineLevel() >= level; --idx) {
            columnInfo = prevColumnInfo;
        }
        return idx;
    }

    private int findEndOfColumnOutlineGroup(int colInfoIndex) {
        ColumnInfoRecord nextColumnInfo;
        int idx;
        ColumnInfoRecord columnInfo = this.records.get(colInfoIndex);
        int level = columnInfo.getOutlineLevel();
        for (idx = colInfoIndex; idx < this.records.size() - 1 && columnInfo.isAdjacentBefore(nextColumnInfo = this.records.get(idx + 1)) && nextColumnInfo.getOutlineLevel() >= level; ++idx) {
            columnInfo = nextColumnInfo;
        }
        return idx;
    }

    private ColumnInfoRecord getColInfo(int idx) {
        return this.records.get(idx);
    }

    private boolean isColumnGroupCollapsed(int idx) {
        int endOfOutlineGroupIdx = this.findEndOfColumnOutlineGroup(idx);
        int nextColInfoIx = endOfOutlineGroupIdx + 1;
        if (nextColInfoIx >= this.records.size()) {
            return false;
        }
        ColumnInfoRecord nextColInfo = this.getColInfo(nextColInfoIx);
        if (!this.getColInfo(endOfOutlineGroupIdx).isAdjacentBefore(nextColInfo)) {
            return false;
        }
        return nextColInfo.getCollapsed();
    }

    private boolean isColumnGroupHiddenByParent(int idx) {
        ColumnInfoRecord prevInfo;
        int endLevel = 0;
        boolean endHidden = false;
        int endOfOutlineGroupIdx = this.findEndOfColumnOutlineGroup(idx);
        if (endOfOutlineGroupIdx < this.records.size()) {
            ColumnInfoRecord nextInfo = this.getColInfo(endOfOutlineGroupIdx + 1);
            if (this.getColInfo(endOfOutlineGroupIdx).isAdjacentBefore(nextInfo)) {
                endLevel = nextInfo.getOutlineLevel();
                endHidden = nextInfo.getHidden();
            }
        }
        int startLevel = 0;
        boolean startHidden = false;
        int startOfOutlineGroupIdx = this.findStartOfColumnOutlineGroup(idx);
        if (startOfOutlineGroupIdx > 0 && (prevInfo = this.getColInfo(startOfOutlineGroupIdx - 1)).isAdjacentBefore(this.getColInfo(startOfOutlineGroupIdx))) {
            startLevel = prevInfo.getOutlineLevel();
            startHidden = prevInfo.getHidden();
        }
        if (endLevel > startLevel) {
            return endHidden;
        }
        return startHidden;
    }

    public void collapseColumn(int columnIndex) {
        int colInfoIx = this.findColInfoIdx(columnIndex, 0);
        if (colInfoIx == -1) {
            return;
        }
        int groupStartColInfoIx = this.findStartOfColumnOutlineGroup(colInfoIx);
        ColumnInfoRecord columnInfo = this.getColInfo(groupStartColInfoIx);
        int lastColIx = this.setGroupHidden(groupStartColInfoIx, columnInfo.getOutlineLevel(), true);
        this.setColumn(lastColIx + 1, null, null, null, null, Boolean.TRUE);
    }

    private int setGroupHidden(int pIdx, int level, boolean hidden) {
        int idx;
        ColumnInfoRecord columnInfo = this.getColInfo(idx);
        for (idx = pIdx; idx < this.records.size(); ++idx) {
            columnInfo.setHidden(hidden);
            if (idx + 1 >= this.records.size()) continue;
            ColumnInfoRecord nextColumnInfo = this.getColInfo(idx + 1);
            if (!columnInfo.isAdjacentBefore(nextColumnInfo) || nextColumnInfo.getOutlineLevel() < level) break;
            columnInfo = nextColumnInfo;
        }
        return columnInfo.getLastColumn();
    }

    public void expandColumn(int columnIndex) {
        int idx = this.findColInfoIdx(columnIndex, 0);
        if (idx == -1) {
            return;
        }
        if (!this.isColumnGroupCollapsed(idx)) {
            return;
        }
        int startIdx = this.findStartOfColumnOutlineGroup(idx);
        int endIdx = this.findEndOfColumnOutlineGroup(idx);
        ColumnInfoRecord columnInfo = this.getColInfo(endIdx);
        if (!this.isColumnGroupHiddenByParent(idx)) {
            int outlineLevel = columnInfo.getOutlineLevel();
            for (int i = startIdx; i <= endIdx; ++i) {
                ColumnInfoRecord ci = this.getColInfo(i);
                if (outlineLevel != ci.getOutlineLevel()) continue;
                ci.setHidden(false);
            }
        }
        this.setColumn(columnInfo.getLastColumn() + 1, null, null, null, null, Boolean.FALSE);
    }

    private static ColumnInfoRecord copyColInfo(ColumnInfoRecord ci) {
        return ci.copy();
    }

    public void setColumn(int targetColumnIx, Short xfIndex, Integer width, Integer level, Boolean hidden, Boolean collapsed) {
        boolean columnChanged;
        int k;
        ColumnInfoRecord ci = null;
        for (k = 0; k < this.records.size(); ++k) {
            ColumnInfoRecord tci = this.records.get(k);
            if (tci.containsColumn(targetColumnIx)) {
                ci = tci;
                break;
            }
            if (tci.getFirstColumn() > targetColumnIx) break;
        }
        if (ci == null) {
            ColumnInfoRecord nci = new ColumnInfoRecord();
            nci.setFirstColumn(targetColumnIx);
            nci.setLastColumn(targetColumnIx);
            ColumnInfoRecordsAggregate.setColumnInfoFields(nci, xfIndex, width, level, hidden, collapsed);
            this.insertColumn(k, nci);
            this.attemptMergeColInfoRecords(k);
            return;
        }
        boolean styleChanged = xfIndex != null && ci.getXFIndex() != xfIndex.shortValue();
        boolean widthChanged = width != null && ci.getColumnWidth() != width.shortValue();
        boolean levelChanged = level != null && ci.getOutlineLevel() != level.intValue();
        boolean hiddenChanged = hidden != null && ci.getHidden() != hidden.booleanValue();
        boolean collapsedChanged = collapsed != null && ci.getCollapsed() != collapsed.booleanValue();
        boolean bl = columnChanged = styleChanged || widthChanged || levelChanged || hiddenChanged || collapsedChanged;
        if (!columnChanged) {
            return;
        }
        if (ci.getFirstColumn() == targetColumnIx && ci.getLastColumn() == targetColumnIx) {
            ColumnInfoRecordsAggregate.setColumnInfoFields(ci, xfIndex, width, level, hidden, collapsed);
            this.attemptMergeColInfoRecords(k);
            return;
        }
        if (ci.getFirstColumn() == targetColumnIx || ci.getLastColumn() == targetColumnIx) {
            if (ci.getFirstColumn() == targetColumnIx) {
                ci.setFirstColumn(targetColumnIx + 1);
            } else {
                ci.setLastColumn(targetColumnIx - 1);
                ++k;
            }
            ColumnInfoRecord nci = ColumnInfoRecordsAggregate.copyColInfo(ci);
            nci.setFirstColumn(targetColumnIx);
            nci.setLastColumn(targetColumnIx);
            ColumnInfoRecordsAggregate.setColumnInfoFields(nci, xfIndex, width, level, hidden, collapsed);
            this.insertColumn(k, nci);
            this.attemptMergeColInfoRecords(k);
        } else {
            ColumnInfoRecord ciMid = ColumnInfoRecordsAggregate.copyColInfo(ci);
            ColumnInfoRecord ciEnd = ColumnInfoRecordsAggregate.copyColInfo(ci);
            int lastcolumn = ci.getLastColumn();
            ci.setLastColumn(targetColumnIx - 1);
            ciMid.setFirstColumn(targetColumnIx);
            ciMid.setLastColumn(targetColumnIx);
            ColumnInfoRecordsAggregate.setColumnInfoFields(ciMid, xfIndex, width, level, hidden, collapsed);
            this.insertColumn(++k, ciMid);
            ciEnd.setFirstColumn(targetColumnIx + 1);
            ciEnd.setLastColumn(lastcolumn);
            this.insertColumn(++k, ciEnd);
        }
    }

    private static void setColumnInfoFields(ColumnInfoRecord ci, Short xfStyle, Integer width, Integer level, Boolean hidden, Boolean collapsed) {
        if (xfStyle != null) {
            ci.setXFIndex(xfStyle.shortValue());
        }
        if (width != null) {
            ci.setColumnWidth(width);
        }
        if (level != null) {
            ci.setOutlineLevel(level.shortValue());
        }
        if (hidden != null) {
            ci.setHidden(hidden);
        }
        if (collapsed != null) {
            ci.setCollapsed(collapsed);
        }
    }

    private int findColInfoIdx(int columnIx, int fromColInfoIdx) {
        if (columnIx < 0) {
            throw new IllegalArgumentException("column parameter out of range: " + columnIx);
        }
        if (fromColInfoIdx < 0) {
            throw new IllegalArgumentException("fromIdx parameter out of range: " + fromColInfoIdx);
        }
        for (int k = fromColInfoIdx; k < this.records.size(); ++k) {
            ColumnInfoRecord ci = this.getColInfo(k);
            if (ci.containsColumn(columnIx)) {
                return k;
            }
            if (ci.getFirstColumn() > columnIx) break;
        }
        return -1;
    }

    private void attemptMergeColInfoRecords(int colInfoIx) {
        int nRecords = this.records.size();
        if (colInfoIx < 0 || colInfoIx >= nRecords) {
            throw new IllegalArgumentException("colInfoIx " + colInfoIx + " is out of range (0.." + (nRecords - 1) + ")");
        }
        ColumnInfoRecord currentCol = this.getColInfo(colInfoIx);
        int nextIx = colInfoIx + 1;
        if (nextIx < nRecords && ColumnInfoRecordsAggregate.mergeColInfoRecords(currentCol, this.getColInfo(nextIx))) {
            this.records.remove(nextIx);
        }
        if (colInfoIx > 0 && ColumnInfoRecordsAggregate.mergeColInfoRecords(this.getColInfo(colInfoIx - 1), currentCol)) {
            this.records.remove(colInfoIx);
        }
    }

    private static boolean mergeColInfoRecords(ColumnInfoRecord ciA, ColumnInfoRecord ciB) {
        if (ciA.isAdjacentBefore(ciB) && ciA.formatMatches(ciB)) {
            ciA.setLastColumn(ciB.getLastColumn());
            return true;
        }
        return false;
    }

    public void groupColumnRange(int fromColumnIx, int toColumnIx, boolean indent) {
        int colInfoSearchStartIdx = 0;
        for (int i = fromColumnIx; i <= toColumnIx; ++i) {
            int level = 1;
            int colInfoIdx = this.findColInfoIdx(i, colInfoSearchStartIdx);
            if (colInfoIdx != -1) {
                level = this.getColInfo(colInfoIdx).getOutlineLevel();
                level = indent ? ++level : --level;
                level = Math.max(0, level);
                level = Math.min(7, level);
                colInfoSearchStartIdx = Math.max(0, colInfoIdx - 1);
            }
            this.setColumn(i, null, null, level, null, null);
        }
    }

    public ColumnInfoRecord findColumnInfo(int columnIndex) {
        int nInfos = this.records.size();
        for (int i = 0; i < nInfos; ++i) {
            ColumnInfoRecord ci = this.getColInfo(i);
            if (!ci.containsColumn(columnIndex)) continue;
            return ci;
        }
        return null;
    }

    public int getMaxOutlineLevel() {
        int result = 0;
        int count = this.records.size();
        for (int i = 0; i < count; ++i) {
            ColumnInfoRecord columnInfoRecord = this.getColInfo(i);
            result = Math.max(columnInfoRecord.getOutlineLevel(), result);
        }
        return result;
    }

    public int getOutlineLevel(int columnIndex) {
        ColumnInfoRecord ci = this.findColumnInfo(columnIndex);
        if (ci != null) {
            return ci.getOutlineLevel();
        }
        return 0;
    }

    public int getMinColumnIndex() {
        if (this.records.isEmpty()) {
            return 0;
        }
        int minIndex = Integer.MAX_VALUE;
        int nInfos = this.records.size();
        for (int i = 0; i < nInfos; ++i) {
            ColumnInfoRecord ci = this.getColInfo(i);
            minIndex = Math.min(minIndex, ci.getFirstColumn());
        }
        return minIndex;
    }

    public int getMaxColumnIndex() {
        if (this.records.isEmpty()) {
            return 0;
        }
        int maxIndex = 0;
        int nInfos = this.records.size();
        for (int i = 0; i < nInfos; ++i) {
            ColumnInfoRecord ci = this.getColInfo(i);
            maxIndex = Math.max(maxIndex, ci.getLastColumn());
        }
        return maxIndex;
    }

    private static int compareColInfos(ColumnInfoRecord a, ColumnInfoRecord b) {
        return a.getFirstColumn() - b.getFirstColumn();
    }
}

