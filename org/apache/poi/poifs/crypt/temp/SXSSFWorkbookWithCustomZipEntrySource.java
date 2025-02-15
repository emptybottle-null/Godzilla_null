/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.temp;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.poi.poifs.crypt.temp.AesZipFileZipEntrySource;
import org.apache.poi.poifs.crypt.temp.EncryptedTempData;
import org.apache.poi.poifs.crypt.temp.SheetDataWriterWithDecorator;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.streaming.SheetDataWriter;

public class SXSSFWorkbookWithCustomZipEntrySource
extends SXSSFWorkbook {
    private static final POILogger LOG = POILogFactory.getLogger(SXSSFWorkbookWithCustomZipEntrySource.class);

    public SXSSFWorkbookWithCustomZipEntrySource() {
        super(20);
        this.setCompressTempFiles(true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void write(OutputStream stream) throws IOException {
        this.flushSheets();
        EncryptedTempData tempData = new EncryptedTempData();
        AesZipFileZipEntrySource source = null;
        try {
            try (OutputStream os = tempData.getOutputStream();){
                this.getXSSFWorkbook().write(os);
            }
            source = AesZipFileZipEntrySource.createZipEntrySource(tempData.getInputStream());
            this.injectData(source, stream);
            tempData.dispose();
        } catch (Throwable throwable) {
            tempData.dispose();
            IOUtils.closeQuietly(source);
            throw throwable;
        }
        IOUtils.closeQuietly(source);
    }

    @Override
    protected SheetDataWriter createSheetDataWriter() throws IOException {
        LOG.log(3, "isCompressTempFiles: " + this.isCompressTempFiles());
        LOG.log(3, "SharedStringSource: " + this.getSharedStringSource());
        return new SheetDataWriterWithDecorator();
    }
}

