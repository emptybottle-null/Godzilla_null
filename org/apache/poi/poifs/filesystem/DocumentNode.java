/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.EntryNode;
import org.apache.poi.poifs.filesystem.POIFSDocument;
import org.apache.poi.poifs.property.DocumentProperty;

public class DocumentNode
extends EntryNode
implements DocumentEntry,
POIFSViewable {
    private POIFSDocument _document;

    DocumentNode(DocumentProperty property, DirectoryNode parent) {
        super(property, parent);
        this._document = property.getDocument();
    }

    POIFSDocument getDocument() {
        return this._document;
    }

    @Override
    public int getSize() {
        return this.getProperty().getSize();
    }

    @Override
    public boolean isDocumentEntry() {
        return true;
    }

    @Override
    protected boolean isDeleteOK() {
        return true;
    }

    @Override
    public Object[] getViewableArray() {
        return new Object[0];
    }

    @Override
    public Iterator<Object> getViewableIterator() {
        ArrayList<POIFSViewable> components = new ArrayList<POIFSViewable>();
        components.add(this.getProperty());
        if (this._document != null) {
            components.add(this._document);
        }
        return components.iterator();
    }

    @Override
    public boolean preferArray() {
        return false;
    }

    @Override
    public String getShortDescription() {
        return this.getName();
    }
}

