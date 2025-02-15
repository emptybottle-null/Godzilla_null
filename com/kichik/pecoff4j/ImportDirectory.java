/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.ImportDirectoryEntry;
import com.kichik.pecoff4j.ImportDirectoryTable;
import com.kichik.pecoff4j.util.DataObject;
import java.util.ArrayList;
import java.util.List;

public class ImportDirectory
extends DataObject {
    private List<ImportDirectoryEntry> entries = new ArrayList<ImportDirectoryEntry>();
    private List<String> names = new ArrayList<String>();
    private List<ImportDirectoryTable> nameTables = new ArrayList<ImportDirectoryTable>();
    private List<ImportDirectoryTable> addressTables = new ArrayList<ImportDirectoryTable>();

    public void add(ImportDirectoryEntry entry) {
        this.entries.add(entry);
    }

    public void add(String name, ImportDirectoryTable names, ImportDirectoryTable addresses) {
        this.names.add(name);
        this.nameTables.add(names);
        this.addressTables.add(addresses);
    }

    public int size() {
        return this.entries.size();
    }

    public String getName(int index) {
        return this.names.get(index);
    }

    public ImportDirectoryTable getNameTable(int index) {
        return this.nameTables.get(index);
    }

    public ImportDirectoryTable getAddressTable(int index) {
        return this.addressTables.get(index);
    }

    public ImportDirectoryEntry getEntry(int index) {
        return this.entries.get(index);
    }
}

