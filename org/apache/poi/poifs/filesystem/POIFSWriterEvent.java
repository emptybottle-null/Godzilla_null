/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import org.apache.poi.poifs.filesystem.DocumentOutputStream;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;

public class POIFSWriterEvent {
    private DocumentOutputStream stream;
    private POIFSDocumentPath path;
    private String documentName;
    private int limit;

    POIFSWriterEvent(DocumentOutputStream stream, POIFSDocumentPath path, String documentName, int limit) {
        this.stream = stream;
        this.path = path;
        this.documentName = documentName;
        this.limit = limit;
    }

    public DocumentOutputStream getStream() {
        return this.stream;
    }

    public POIFSDocumentPath getPath() {
        return this.path;
    }

    public String getName() {
        return this.documentName;
    }

    public int getLimit() {
        return this.limit;
    }
}

