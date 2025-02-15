/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.ResourceDirectoryTable;
import com.kichik.pecoff4j.ResourceEntry;
import com.kichik.pecoff4j.util.DataObject;
import java.util.ArrayList;
import java.util.List;

public class ResourceDirectory
extends DataObject {
    private ResourceDirectoryTable table;
    private List<ResourceEntry> entries = new ArrayList<ResourceEntry>();

    public ResourceDirectoryTable getTable() {
        return this.table;
    }

    public void setTable(ResourceDirectoryTable table) {
        this.table = table;
    }

    public void add(ResourceEntry entry) {
        this.entries.add(entry);
    }

    public ResourceEntry get(int index) {
        return this.entries.get(index);
    }

    public int size() {
        return this.entries.size();
    }
}

