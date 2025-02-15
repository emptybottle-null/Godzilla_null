/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.ImportEntry;
import java.util.ArrayList;

public class ImportDirectoryTable {
    private ArrayList imports = new ArrayList();

    public void add(ImportEntry entry) {
        this.imports.add(entry);
    }

    public int size() {
        return this.imports.size();
    }

    public ImportEntry getEntry(int index) {
        return (ImportEntry)this.imports.get(index);
    }
}

