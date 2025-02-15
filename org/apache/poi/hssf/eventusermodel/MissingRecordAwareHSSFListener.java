/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventusermodel;

import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingRowDummyRecord;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.CellValueRecordInterface;
import org.apache.poi.hssf.record.MulBlankRecord;
import org.apache.poi.hssf.record.MulRKRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactory;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.hssf.record.StringRecord;

public final class MissingRecordAwareHSSFListener
implements HSSFListener {
    private HSSFListener childListener;
    private int lastRowRow;
    private int lastCellRow;
    private int lastCellColumn;

    public MissingRecordAwareHSSFListener(HSSFListener listener) {
        this.resetCounts();
        this.childListener = listener;
    }

    @Override
    public void processRecord(Record record) {
        int thisColumn;
        int thisRow;
        StandardRecord[] expandedRecords = null;
        if (record instanceof CellValueRecordInterface) {
            CellValueRecordInterface valueRec = (CellValueRecordInterface)((Object)record);
            thisRow = valueRec.getRow();
            thisColumn = valueRec.getColumn();
        } else {
            if (record instanceof StringRecord) {
                this.childListener.processRecord(record);
                return;
            }
            thisRow = -1;
            thisColumn = -1;
            switch (record.getSid()) {
                case 2057: {
                    BOFRecord bof = (BOFRecord)record;
                    if (bof.getType() != 5 && bof.getType() != 16) break;
                    this.resetCounts();
                    break;
                }
                case 520: {
                    RowRecord rowrec = (RowRecord)record;
                    if (this.lastRowRow + 1 < rowrec.getRowNumber()) {
                        for (int i = this.lastRowRow + 1; i < rowrec.getRowNumber(); ++i) {
                            MissingRowDummyRecord dr = new MissingRowDummyRecord(i);
                            this.childListener.processRecord(dr);
                        }
                    }
                    this.lastRowRow = rowrec.getRowNumber();
                    this.lastCellColumn = -1;
                    break;
                }
                case 1212: {
                    this.childListener.processRecord(record);
                    return;
                }
                case 190: {
                    MulBlankRecord mbr = (MulBlankRecord)record;
                    expandedRecords = RecordFactory.convertBlankRecords(mbr);
                    break;
                }
                case 189: {
                    MulRKRecord mrk = (MulRKRecord)record;
                    expandedRecords = RecordFactory.convertRKRecords(mrk);
                    break;
                }
                case 28: {
                    NoteRecord nrec = (NoteRecord)record;
                    thisRow = nrec.getRow();
                    thisColumn = nrec.getColumn();
                    break;
                }
            }
        }
        if (expandedRecords != null && expandedRecords.length > 0) {
            thisRow = expandedRecords[0].getRow();
            thisColumn = expandedRecords[0].getColumn();
        }
        if (thisRow != this.lastCellRow && thisRow > 0) {
            if (this.lastCellRow == -1) {
                this.lastCellRow = 0;
            }
            for (int i = this.lastCellRow; i < thisRow; ++i) {
                int cols = -1;
                if (i == this.lastCellRow) {
                    cols = this.lastCellColumn;
                }
                this.childListener.processRecord(new LastCellOfRowDummyRecord(i, cols));
            }
        }
        if (this.lastCellRow != -1 && this.lastCellColumn != -1 && thisRow == -1) {
            this.childListener.processRecord(new LastCellOfRowDummyRecord(this.lastCellRow, this.lastCellColumn));
            this.lastCellRow = -1;
            this.lastCellColumn = -1;
        }
        if (thisRow != this.lastCellRow) {
            this.lastCellColumn = -1;
        }
        if (this.lastCellColumn != thisColumn - 1) {
            for (int i = this.lastCellColumn + 1; i < thisColumn; ++i) {
                this.childListener.processRecord(new MissingCellDummyRecord(thisRow, i));
            }
        }
        if (expandedRecords != null && expandedRecords.length > 0) {
            thisColumn = expandedRecords[expandedRecords.length - 1].getColumn();
        }
        if (thisColumn != -1) {
            this.lastCellColumn = thisColumn;
            this.lastCellRow = thisRow;
        }
        if (expandedRecords != null && expandedRecords.length > 0) {
            for (StandardRecord r : expandedRecords) {
                this.childListener.processRecord(r);
            }
        } else {
            this.childListener.processRecord(record);
        }
    }

    private void resetCounts() {
        this.lastRowRow = -1;
        this.lastCellRow = -1;
        this.lastCellColumn = -1;
    }
}

