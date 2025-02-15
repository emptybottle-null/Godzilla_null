/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import org.apache.poi.poifs.filesystem.POIFSDocumentPath;

public class DocumentDescriptor {
    private POIFSDocumentPath path;
    private String name;
    private int hashcode;

    public DocumentDescriptor(POIFSDocumentPath path, String name) {
        if (path == null) {
            throw new NullPointerException("path must not be null");
        }
        if (name == null) {
            throw new NullPointerException("name must not be null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.path = path;
        this.name = name;
    }

    public boolean equals(Object o) {
        boolean rval = false;
        if (o != null && o.getClass() == this.getClass()) {
            if (this == o) {
                rval = true;
            } else {
                DocumentDescriptor descriptor = (DocumentDescriptor)o;
                rval = this.path.equals(descriptor.path) && this.name.equals(descriptor.name);
            }
        }
        return rval;
    }

    public int hashCode() {
        if (this.hashcode == 0) {
            this.hashcode = this.path.hashCode() ^ this.name.hashCode();
        }
        return this.hashcode;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(40 * (this.path.length() + 1));
        for (int j = 0; j < this.path.length(); ++j) {
            buffer.append(this.path.getComponent(j)).append("/");
        }
        buffer.append(this.name);
        return buffer.toString();
    }
}

