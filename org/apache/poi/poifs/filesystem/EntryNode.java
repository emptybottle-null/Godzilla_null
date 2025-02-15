/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.property.Property;

public abstract class EntryNode
implements Entry {
    private Property _property;
    private DirectoryNode _parent;

    protected EntryNode(Property property, DirectoryNode parent) {
        this._property = property;
        this._parent = parent;
    }

    protected Property getProperty() {
        return this._property;
    }

    protected boolean isRoot() {
        return this._parent == null;
    }

    protected abstract boolean isDeleteOK();

    @Override
    public String getName() {
        return this._property.getName();
    }

    @Override
    public boolean isDirectoryEntry() {
        return false;
    }

    @Override
    public boolean isDocumentEntry() {
        return false;
    }

    @Override
    public DirectoryEntry getParent() {
        return this._parent;
    }

    @Override
    public boolean delete() {
        boolean rval = false;
        if (!this.isRoot() && this.isDeleteOK()) {
            rval = this._parent.deleteEntry(this);
        }
        return rval;
    }

    @Override
    public boolean renameTo(String newName) {
        boolean rval = false;
        if (!this.isRoot()) {
            rval = this._parent.changeName(this.getName(), newName);
        }
        return rval;
    }
}

