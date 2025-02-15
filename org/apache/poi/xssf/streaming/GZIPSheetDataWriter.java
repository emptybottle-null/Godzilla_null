/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.streaming.SheetDataWriter;

public class GZIPSheetDataWriter
extends SheetDataWriter {
    public GZIPSheetDataWriter() throws IOException {
    }

    public GZIPSheetDataWriter(SharedStringsTable sharedStringsTable) throws IOException {
        super(sharedStringsTable);
    }

    @Override
    public File createTempFile() throws IOException {
        return TempFile.createTempFile("poi-sxssf-sheet-xml", ".gz");
    }

    @Override
    protected InputStream decorateInputStream(FileInputStream fis) throws IOException {
        return new GZIPInputStream(fis);
    }

    @Override
    protected OutputStream decorateOutputStream(FileOutputStream fos) throws IOException {
        return new GZIPOutputStream(fos);
    }
}

