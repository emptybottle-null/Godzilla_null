/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.extractor;

import org.apache.poi.POIDocument;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hpsf.extractor.HPSFPropertiesExtractor;
import org.apache.poi.poifs.filesystem.DirectoryEntry;

public abstract class POIOLE2TextExtractor
extends POITextExtractor {
    protected POIDocument document;

    public POIOLE2TextExtractor(POIDocument document) {
        this.document = document;
        this.setFilesystem(document);
    }

    protected POIOLE2TextExtractor(POIOLE2TextExtractor otherExtractor) {
        this.document = otherExtractor.document;
    }

    public DocumentSummaryInformation getDocSummaryInformation() {
        return this.document.getDocumentSummaryInformation();
    }

    public SummaryInformation getSummaryInformation() {
        return this.document.getSummaryInformation();
    }

    @Override
    public POITextExtractor getMetadataTextExtractor() {
        return new HPSFPropertiesExtractor(this);
    }

    public DirectoryEntry getRoot() {
        return this.document.getDirectory();
    }

    @Override
    public POIDocument getDocument() {
        return this.document;
    }
}

