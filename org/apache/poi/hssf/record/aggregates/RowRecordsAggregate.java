/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.DBCellRecord;
import org.apache.poi.hssf.record.DimensionsRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.IndexRecord;
import org.apache.poi.hssf.record.MulBlankRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.UnknownRecord;
import org.apache.poi.hssf.record.aggregates.FormulaRecordAggregate;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.hssf.record.aggregates.SharedValueManager;
import org.apache.poi.hssf.record.aggregates.ValueRecordsAggregate;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.FormulaShifter;

public final class RowRecordsAggregate
extends RecordAggregate {
    private int _firstrow = -1;
    private int _lastrow = -1;
    private final Map<Integer, RowRecord> _rowRecords;
    private final ValueRecordsAggregate _valuesAgg;
    private final List<Record> _unknownRecords;
    private final SharedValueManager _sharedValueManager;
    private RowRecord[] _rowRecordValues;

    public RowRecordsAggregate() {
        this(SharedValueManager.createEmpty());
    }

    private RowRecordsAggregate(SharedValueManager svm) {
        if (svm == null) {
            throw new IllegalArgumentException("SharedValueManager must be provided.");
        }
        this._rowRecords = new TreeMap<Integer, RowRecord>();
        this._valuesAgg = new ValueRecordsAggregate();
        this._unknownRecords = new ArrayList<Record>();
        this._sharedValueManager = svm;
    }

    public RowRecordsAggregate(RecordStream rs, SharedValueManager svm) {
        this(svm);
        block5: while (rs.hasNext()) {
            Record rec = rs.getNext();
            switch (rec.getSid()) {
                case 520: {
                    this.insertRow((RowRecord)rec);
                    continue block5;
                }
                case 81: {
                    this.addUnknownRecord(rec);
                    continue block5;
                }
                case 215: {
                    continue block5;
                }
            }
            if (rec instanceof UnknownRecord) {
                this.addUnknownRecord(rec);
                while (rs.peekNextSid() == 60) {
                    this.addUnknownRecord(rs.getNext());
                }
                continue;
            }
            if (rec instanceof MulBlankRecord) {
                this._valuesAgg.addMultipleBlanks((MulBlankRecord)rec);
                continue;
            }
            if (!(rec instanceof CellValueRecordInterface)) {
                throw new RuntimeException("Unexpected record type (" + rec.getClass().getName() + ")");
            }
            this._valuesAgg.construct((CellValueRecordInterface)((Object)rec), rs, svm);
        }
    }

    private void addUnknownRecord(Record rec) {
        this._unknownRecords.add(rec);
    }

    public void insertRow(RowRecord row) {
        this._rowRecords.put(row.getRowNumber(), row);
        this._rowRecordValues = null;
        if (row.getRowNumber() < this._firstrow || this._firstrow == -1) {
            this._firstrow = row.getRowNumber();
        }
        if (row.getRowNumber() > this._lastrow || this._lastrow == -1) {
            this._lastrow = row.getRowNumber();
        }
    }

    public void removeRow(RowRecord row) {
        int rowIndex = row.getRowNumber();
        this._valuesAgg.removeAllCellsValuesForRow(rowIndex);
        Integer key = rowIndex;
        RowRecord rr = this._rowRecords.remove(key);
        if (rr == null) {
            throw new RuntimeException("Invalid row index (" + key + ")");
        }
        if (row != rr) {
            this._rowRecords.put(key, rr);
            throw new RuntimeException("Attempt to remove row that does not belong to this sheet");
        }
        this._rowRecordValues = null;
    }

    public RowRecord getRow(int rowIndex) {
        int maxrow = SpreadsheetVersion.EXCEL97.getLastRowIndex();
        if (rowIndex < 0 || rowIndex > maxrow) {
            throw new IllegalArgumentException("The row number must be between 0 and " + maxrow + ", but had: " + rowIndex);
        }
        return this._rowRecords.get(rowIndex);
    }

    public int getPhysicalNumberOfRows() {
        return this._rowRecords.size();
    }

    public int getFirstRowNum() {
        return this._firstrow;
    }

    public int getLastRowNum() {
        return this._lastrow;
    }

    public int getRowBlockCount() {
        int size = this._rowRecords.size() / 32;
        if (this._rowRecords.size() % 32 != 0) {
            ++size;
        }
        return size;
    }

    private int getRowBlockSize(int block) {
        return 20 * this.getRowCountForBlock(block);
    }

    public int getRowCountForBlock(int block) {
        int startIndex = block * 32;
        int endIndex = startIndex + 32 - 1;
        if (endIndex >= this._rowRecords.size()) {
            endIndex = this._rowRecords.size() - 1;
        }
        return endIndex - startIndex + 1;
    }

    private int getStartRowNumberForBlock(int block) {
        int startIndex = block * 32;
        if (this._rowRecordValues == null) {
            this._rowRecordValues = this._rowRecords.values().toArray(new RowRecord[0]);
        }
        try {
            return this._rowRecordValues[startIndex].getRowNumber();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Did not find start row for block " + block);
        }
    }

    private int getEndRowNumberForBlock(int block) {
        int endIndex = (block + 1) * 32 - 1;
        if (endIndex >= this._rowRecords.size()) {
            endIndex = this._rowRecords.size() - 1;
        }
        if (this._rowRecordValues == null) {
            this._rowRecordValues = this._rowRecords.values().toArray(new RowRecord[0]);
        }
        try {
            return this._rowRecordValues[endIndex].getRowNumber();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Did not find end row for block " + block);
        }
    }

    private int visitRowRecordsForBlock(int blockIndex, RecordAggregate.RecordVisitor rv) {
        int i;
        int startIndex = blockIndex * 32;
        int endIndex = startIndex + 32;
        Iterator<RowRecord> rowIterator = this._rowRecords.values().iterator();
        for (i = 0; i < startIndex; ++i) {
            rowIterator.next();
        }
        int result = 0;
        while (rowIterator.hasNext() && i++ < endIndex) {
            Record rec = rowIterator.next();
            result += rec.getRecordSize();
            rv.visitRecord(rec);
        }
        return result;
    }

    @Override
    public void visitContainedRecords(RecordAggregate.RecordVisitor rv) {
        RecordAggregate.PositionTrackingVisitor stv = new RecordAggregate.PositionTrackingVisitor(rv, 0);
        int blockCount = this.getRowBlockCount();
        for (int blockIndex = 0; blockIndex < blockCount; ++blockIndex) {
            int pos = 0;
            int rowBlockSize = this.visitRowRecordsForBlock(blockIndex, rv);
            pos += rowBlockSize;
            int startRowNumber = this.getStartRowNumberForBlock(blockIndex);
            int endRowNumber = this.getEndRowNumberForBlock(blockIndex);
            ArrayList<Short> cellOffsets = new ArrayList<Short>();
            int cellRefOffset = rowBlockSize - 20;
            for (int row = startRowNumber; row <= endRowNumber; ++row) {
                if (!this._valuesAgg.rowHasCells(row)) continue;
                stv.setPosition(0);
                this._valuesAgg.visitCellsForRow(row, stv);
                int rowCellSize = stv.getPosition();
                pos += rowCellSize;
                cellOffsets.add((short)cellRefOffset);
                cellRefOffset = rowCellSize;
            }
            rv.visitRecord(new DBCellRecord(pos, RowRecordsAggregate.shortListToArray(cellOffsets)));
        }
        this._unknownRecords.forEach(rv::visitRecord);
    }

    private static short[] shortListToArray(List<Short> list) {
        short[] arr = new short[list.size()];
        int idx = 0;
        for (Short s : list) {
            arr[idx++] = s;
        }
        return arr;
    }

    public Iterator<RowRecord> getIterator() {
        return this._rowRecords.values().iterator();
    }

    public int findStartOfRowOutlineGroup(int row) {
        int currentRow;
        RowRecord rowRecord = this.getRow(row);
        short level = rowRecord.getOutlineLevel();
        for (currentRow = row; currentRow >= 0 && this.getRow(currentRow) != null; --currentRow) {
            rowRecord = this.getRow(currentRow);
            if (rowRecord.getOutlineLevel() >= level) continue;
            return currentRow + 1;
        }
        return currentRow + 1;
    }

    public int findEndOfRowOutlineGroup(int row) {
        int currentRow;
        short level = this.getRow(row).getOutlineLevel();
        for (currentRow = row; currentRow < this.getLastRowNum() && this.getRow(currentRow) != null && this.getRow(currentRow).getOutlineLevel() >= level; ++currentRow) {
        }
        return currentRow - 1;
    }

    private int writeHidden(RowRecord pRowRecord, int row) {
        int rowIx = row;
        RowRecord rowRecord = pRowRecord;
        short level = rowRecord.getOutlineLevel();
        while (rowRecord != null && this.getRow(rowIx).getOutlineLevel() >= level) {
            rowRecord.setZeroHeight(true);
            rowRecord = this.getRow(++rowIx);
        }
        return rowIx;
    }

    public void collapseRow(int rowNumber) {
        int startRow = this.findStartOfRowOutlineGroup(rowNumber);
        RowRecord rowRecord = this.getRow(startRow);
        int nextRowIx = this.writeHidden(rowRecord, startRow);
        RowRecord row = this.getRow(nextRowIx);
        if (row == null) {
            row = RowRecordsAggregate.createRow(nextRowIx);
            this.insertRow(row);
        }
        row.setColapsed(true);
    }

    public static RowRecord createRow(int rowNumber) {
        return new RowRecord(rowNumber);
    }

    public boolean isRowGroupCollapsed(int row) {
        int collapseRow = this.findEndOfRowOutlineGroup(row) + 1;
        return this.getRow(collapseRow) != null && this.getRow(collapseRow).getColapsed();
    }

    public void expandRow(int rowNumber) {
        if (rowNumber == -1) {
            return;
        }
        if (!this.isRowGroupCollapsed(rowNumber)) {
            return;
        }
        int startIdx = this.findStartOfRowOutlineGroup(rowNumber);
        RowRecord row = this.getRow(startIdx);
        int endIdx = this.findEndOfRowOutlineGroup(rowNumber);
        if (!this.isRowGroupHiddenByParent(rowNumber)) {
            for (int i = startIdx; i <= endIdx; ++i) {
                RowRecord otherRow = this.getRow(i);
                if (row.getOutlineLevel() != otherRow.getOutlineLevel() && this.isRowGroupCollapsed(i)) continue;
                otherRow.setZeroHeight(false);
            }
        }
        this.getRow(endIdx + 1).setColapsed(false);
    }

    public boolean isRowGroupHiddenByParent(int row) {
        boolean startHidden;
        short startLevel;
        boolean endHidden;
        short endLevel;
        int endOfOutlineGroupIdx = this.findEndOfRowOutlineGroup(row);
        if (this.getRow(endOfOutlineGroupIdx + 1) == null) {
            endLevel = 0;
            endHidden = false;
        } else {
            endLevel = this.getRow(endOfOutlineGroupIdx + 1).getOutlineLevel();
            endHidden = this.getRow(endOfOutlineGroupIdx + 1).getZeroHeight();
        }
        int startOfOutlineGroupIdx = this.findStartOfRowOutlineGroup(row);
        if (startOfOutlineGroupIdx - 1 < 0 || this.getRow(startOfOutlineGroupIdx - 1) == null) {
            startLevel = 0;
            startHidden = false;
        } else {
            startLevel = this.getRow(startOfOutlineGroupIdx - 1).getOutlineLevel();
            startHidden = this.getRow(startOfOutlineGroupIdx - 1).getZeroHeight();
        }
        if (endLevel > startLevel) {
            return endHidden;
        }
        return startHidden;
    }

    public Iterator<CellValueRecordInterface> getCellValueIterator() {
        return this._valuesAgg.iterator();
    }

    public IndexRecord createIndexRecord(int indexRecordOffset, int sizeOfInitialSheetRecords) {
        IndexRecord result = new IndexRecord();
        result.setFirstRow(this._firstrow);
        result.setLastRowAdd1(this._lastrow + 1);
        int blockCount = this.getRowBlockCount();
        int indexRecSize = IndexRecord.getRecordSizeForBlockCount(blockCount);
        int currentOffset = indexRecordOffset + indexRecSize + sizeOfInitialSheetRecords;
        for (int block = 0; block < blockCount; ++block) {
            currentOffset += this.getRowBlockSize(block);
            result.addDbcell(currentOffset += this._valuesAgg.getRowCellBlockSize(this.getStartRowNumberForBlock(block), this.getEndRowNumberForBlock(block)));
            currentOffset += 8 + this.getRowCountForBlock(block) * 2;
        }
        return result;
    }

    public void insertCell(CellValueRecordInterface cvRec) {
        this._valuesAgg.insertCell(cvRec);
    }

    public void removeCell(CellValueRecordInterface cvRec) {
        if (cvRec instanceof FormulaRecordAggregate) {
            ((FormulaRecordAggregate)cvRec).notifyFormulaChanging();
        }
        this._valuesAgg.removeCell(cvRec);
    }

    public FormulaRecordAggregate createFormula(int row, int col) {
        FormulaRecord fr = new FormulaRecord();
        fr.setRow(row);
        fr.setColumn((short)col);
        return new FormulaRecordAggregate(fr, null, this._sharedValueManager);
    }

    public void updateFormulasAfterRowShift(FormulaShifter formulaShifter, int currentExternSheetIndex) {
        this._valuesAgg.updateFormulasAfterRowShift(formulaShifter, currentExternSheetIndex);
    }

    public DimensionsRecord createDimensions() {
        DimensionsRecord result = new DimensionsRecord();
        result.setFirstRow(this._firstrow);
        result.setLastRow(this._lastrow);
        result.setFirstCol((short)this._valuesAgg.getFirstCellNum());
        result.setLastCol((short)this._valuesAgg.getLastCellNum());
        return result;
    }
}

