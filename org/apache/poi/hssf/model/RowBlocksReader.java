/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.model.RecordOrderer;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.ArrayRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.MergeCellsRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SharedFormulaRecord;
import org.apache.poi.hssf.record.TableRecord;
import org.apache.poi.hssf.record.aggregates.SharedValueManager;
import org.apache.poi.ss.util.CellReference;

public final class RowBlocksReader {
    private final List<Record> _plainRecords;
    private final SharedValueManager _sfm;
    private final MergeCellsRecord[] _mergedCellsRecords;

    public RowBlocksReader(RecordStream rs) {
        ArrayList<Record> plainRecords = new ArrayList<Record>();
        ArrayList shFrmRecords = new ArrayList();
        ArrayList<CellReference> firstCellRefs = new ArrayList<CellReference>();
        ArrayList arrayRecords = new ArrayList();
        ArrayList tableRecords = new ArrayList();
        ArrayList<Record> mergeCellRecords = new ArrayList<Record>();
        Record prevRec = null;
        while (!RecordOrderer.isEndOfRowBlock(rs.peekNextSid())) {
            ArrayList<Record> dest;
            if (!rs.hasNext()) {
                throw new RuntimeException("Failed to find end of row/cell records");
            }
            Record rec = rs.getNext();
            switch (rec.getSid()) {
                case 229: {
                    dest = mergeCellRecords;
                    break;
                }
                case 1212: {
                    dest = shFrmRecords;
                    if (!(prevRec instanceof FormulaRecord)) {
                        throw new RuntimeException("Shared formula record should follow a FormulaRecord");
                    }
                    FormulaRecord fr = (FormulaRecord)prevRec;
                    firstCellRefs.add(new CellReference(fr.getRow(), fr.getColumn()));
                    break;
                }
                case 545: {
                    dest = arrayRecords;
                    break;
                }
                case 566: {
                    dest = tableRecords;
                    break;
                }
                default: {
                    dest = plainRecords;
                }
            }
            dest.add(rec);
            prevRec = rec;
        }
        SharedFormulaRecord[] sharedFormulaRecs = new SharedFormulaRecord[shFrmRecords.size()];
        CellReference[] firstCells = new CellReference[firstCellRefs.size()];
        ArrayRecord[] arrayRecs = new ArrayRecord[arrayRecords.size()];
        TableRecord[] tableRecs = new TableRecord[tableRecords.size()];
        shFrmRecords.toArray(sharedFormulaRecs);
        firstCellRefs.toArray(firstCells);
        arrayRecords.toArray(arrayRecs);
        tableRecords.toArray(tableRecs);
        this._plainRecords = plainRecords;
        this._sfm = SharedValueManager.create(sharedFormulaRecs, firstCells, arrayRecs, tableRecs);
        this._mergedCellsRecords = new MergeCellsRecord[mergeCellRecords.size()];
        mergeCellRecords.toArray(this._mergedCellsRecords);
    }

    public MergeCellsRecord[] getLooseMergedCells() {
        return this._mergedCellsRecords;
    }

    public SharedValueManager getSharedFormulaManager() {
        return this._sfm;
    }

    public RecordStream getPlainRecordStream() {
        return new RecordStream(this._plainRecords, 0);
    }
}

