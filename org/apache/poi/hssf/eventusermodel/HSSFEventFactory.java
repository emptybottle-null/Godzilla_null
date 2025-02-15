/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.eventusermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.HSSFUserException;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactoryInputStream;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class HSSFEventFactory {
    public void processWorkbookEvents(HSSFRequest req, POIFSFileSystem fs) throws IOException {
        this.processWorkbookEvents(req, fs.getRoot());
    }

    public void processWorkbookEvents(HSSFRequest req, DirectoryNode dir) throws IOException {
        String name = null;
        Set<String> entryNames = dir.getEntryNames();
        for (String potentialName : InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES) {
            if (!entryNames.contains(potentialName)) continue;
            name = potentialName;
            break;
        }
        if (name == null) {
            name = InternalWorkbook.WORKBOOK_DIR_ENTRY_NAMES[0];
        }
        try (DocumentInputStream in = dir.createDocumentInputStream(name);){
            this.processEvents(req, in);
        }
    }

    public short abortableProcessWorkbookEvents(HSSFRequest req, POIFSFileSystem fs) throws IOException, HSSFUserException {
        return this.abortableProcessWorkbookEvents(req, fs.getRoot());
    }

    public short abortableProcessWorkbookEvents(HSSFRequest req, DirectoryNode dir) throws IOException, HSSFUserException {
        try (DocumentInputStream in = dir.createDocumentInputStream("Workbook");){
            short s = this.abortableProcessEvents(req, in);
            return s;
        }
    }

    public void processEvents(HSSFRequest req, InputStream in) {
        try {
            this.genericProcessEvents(req, in);
        } catch (HSSFUserException hSSFUserException) {
            // empty catch block
        }
    }

    public short abortableProcessEvents(HSSFRequest req, InputStream in) throws HSSFUserException {
        return this.genericProcessEvents(req, in);
    }

    private short genericProcessEvents(HSSFRequest req, InputStream in) throws HSSFUserException {
        Record r;
        short userCode = 0;
        RecordFactoryInputStream recordStream = new RecordFactoryInputStream(in, false);
        while ((r = recordStream.nextRecord()) != null && (userCode = req.processRecord(r)) == 0) {
        }
        return userCode;
    }
}

