/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.resources.IconDirectoryEntry;
import java.util.ArrayList;

public class IconDirectory {
    private int reserved;
    private int type;
    private ArrayList entries = new ArrayList();

    public void add(IconDirectoryEntry entry) {
        this.entries.add(entry);
    }

    public int getCount() {
        return this.entries.size();
    }

    public IconDirectoryEntry getEntry(int index) {
        return (IconDirectoryEntry)this.entries.get(index);
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getReserved() {
        return this.reserved;
    }

    public int getType() {
        return this.type;
    }

    public int sizeOf() {
        return 6 + this.entries.size() * IconDirectoryEntry.sizeOf();
    }
}

