/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import java.io.File;
import java.io.IOException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.nio.FileBackedDataSource;
import org.apache.poi.util.TempFile;

public class TempFilePOIFSFileSystem
extends POIFSFileSystem {
    File tempFile;

    @Override
    protected void createNewDataSource() {
        try {
            this.tempFile = TempFile.createTempFile("poifs", ".tmp");
            this._data = new FileBackedDataSource(this.tempFile, false);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create data source", e);
        }
    }

    @Override
    public void close() throws IOException {
        if (this.tempFile != null && this.tempFile.exists()) {
            this.tempFile.delete();
        }
        super.close();
    }
}

