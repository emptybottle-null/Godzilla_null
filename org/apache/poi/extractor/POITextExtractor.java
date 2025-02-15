/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.extractor;

import java.io.Closeable;
import java.io.IOException;

public abstract class POITextExtractor
implements Closeable {
    private Closeable fsToClose;

    public abstract String getText();

    public abstract POITextExtractor getMetadataTextExtractor();

    public void setFilesystem(Closeable fs) {
        this.fsToClose = fs;
    }

    @Override
    public void close() throws IOException {
        if (this.fsToClose != null) {
            this.fsToClose.close();
        }
    }

    public abstract Object getDocument();
}

