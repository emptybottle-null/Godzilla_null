/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.property;

import org.apache.poi.poifs.filesystem.POIFSDocument;
import org.apache.poi.poifs.property.Property;

public class DocumentProperty
extends Property {
    private POIFSDocument _document = null;

    public DocumentProperty(String name, int size) {
        this.setName(name);
        this.setSize(size);
        this.setNodeColor((byte)1);
        this.setPropertyType((byte)2);
    }

    protected DocumentProperty(int index, byte[] array, int offset) {
        super(index, array, offset);
    }

    public void setDocument(POIFSDocument doc) {
        this._document = doc;
    }

    public POIFSDocument getDocument() {
        return this._document;
    }

    @Override
    public boolean shouldUseSmallBlocks() {
        return super.shouldUseSmallBlocks();
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    protected void preWrite() {
    }

    public void updateSize(int size) {
        this.setSize(size);
    }
}

