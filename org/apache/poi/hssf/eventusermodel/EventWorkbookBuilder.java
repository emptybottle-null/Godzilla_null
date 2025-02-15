/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventusermodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.ExternSheetRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.SupBookRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class EventWorkbookBuilder {
    public static InternalWorkbook createStubWorkbook(ExternSheetRecord[] externs, BoundSheetRecord[] bounds, SSTRecord sst) {
        ArrayList<Record> wbRecords = new ArrayList<Record>();
        if (bounds != null) {
            Collections.addAll(wbRecords, bounds);
        }
        if (sst != null) {
            wbRecords.add(sst);
        }
        if (externs != null) {
            wbRecords.add(SupBookRecord.createInternalReferences((short)externs.length));
            Collections.addAll(wbRecords, externs);
        }
        wbRecords.add(EOFRecord.instance);
        return InternalWorkbook.createWorkbook(wbRecords);
    }

    public static InternalWorkbook createStubWorkbook(ExternSheetRecord[] externs, BoundSheetRecord[] bounds) {
        return EventWorkbookBuilder.createStubWorkbook(externs, bounds, null);
    }

    public static class SheetRecordCollectingListener
    implements HSSFListener {
        private final HSSFListener childListener;
        private final List<BoundSheetRecord> boundSheetRecords = new ArrayList<BoundSheetRecord>();
        private final List<ExternSheetRecord> externSheetRecords = new ArrayList<ExternSheetRecord>();
        private SSTRecord sstRecord;

        public SheetRecordCollectingListener(HSSFListener childListener) {
            this.childListener = childListener;
        }

        public BoundSheetRecord[] getBoundSheetRecords() {
            return this.boundSheetRecords.toArray(new BoundSheetRecord[0]);
        }

        public ExternSheetRecord[] getExternSheetRecords() {
            return this.externSheetRecords.toArray(new ExternSheetRecord[0]);
        }

        public SSTRecord getSSTRecord() {
            return this.sstRecord;
        }

        public HSSFWorkbook getStubHSSFWorkbook() {
            HSSFWorkbook wb = HSSFWorkbook.create(this.getStubWorkbook());
            for (BoundSheetRecord bsr : this.boundSheetRecords) {
                wb.createSheet(bsr.getSheetname());
            }
            return wb;
        }

        public InternalWorkbook getStubWorkbook() {
            return EventWorkbookBuilder.createStubWorkbook(this.getExternSheetRecords(), this.getBoundSheetRecords(), this.getSSTRecord());
        }

        @Override
        public void processRecord(Record record) {
            this.processRecordInternally(record);
            this.childListener.processRecord(record);
        }

        public void processRecordInternally(Record record) {
            if (record instanceof BoundSheetRecord) {
                this.boundSheetRecords.add((BoundSheetRecord)record);
            } else if (record instanceof ExternSheetRecord) {
                this.externSheetRecords.add((ExternSheetRecord)record);
            } else if (record instanceof SSTRecord) {
                this.sstRecord = (SSTRecord)record;
            }
        }
    }
}

