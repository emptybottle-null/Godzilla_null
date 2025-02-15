/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.changes;

import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;

class Change {
    private final String targetFile;
    private final ArchiveEntry entry;
    private final InputStream input;
    private final boolean replaceMode;
    private final int type;
    static final int TYPE_DELETE = 1;
    static final int TYPE_ADD = 2;
    static final int TYPE_MOVE = 3;
    static final int TYPE_DELETE_DIR = 4;

    Change(String fileName, int type) {
        if (fileName == null) {
            throw new NullPointerException();
        }
        this.targetFile = fileName;
        this.type = type;
        this.input = null;
        this.entry = null;
        this.replaceMode = true;
    }

    Change(ArchiveEntry pEntry, InputStream pInput, boolean replace) {
        if (pEntry == null || pInput == null) {
            throw new NullPointerException();
        }
        this.entry = pEntry;
        this.input = pInput;
        this.type = 2;
        this.targetFile = null;
        this.replaceMode = replace;
    }

    ArchiveEntry getEntry() {
        return this.entry;
    }

    InputStream getInput() {
        return this.input;
    }

    String targetFile() {
        return this.targetFile;
    }

    int type() {
        return this.type;
    }

    boolean isReplaceMode() {
        return this.replaceMode;
    }
}

