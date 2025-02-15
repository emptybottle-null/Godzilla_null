/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.eventfilesystem;

import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;

public class POIFSReaderEvent {
    private final DocumentInputStream stream;
    private final POIFSDocumentPath path;
    private final String documentName;

    POIFSReaderEvent(DocumentInputStream stream, POIFSDocumentPath path, String documentName) {
        this.stream = stream;
        this.path = path;
        this.documentName = documentName;
    }

    public DocumentInputStream getStream() {
        return this.stream;
    }

    public POIFSDocumentPath getPath() {
        return this.path;
    }

    public String getName() {
        return this.documentName;
    }
}

