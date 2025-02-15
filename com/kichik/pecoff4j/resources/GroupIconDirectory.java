/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.resources;

import com.kichik.pecoff4j.io.DataReader;
import com.kichik.pecoff4j.io.IDataReader;
import com.kichik.pecoff4j.resources.GroupIconDirectoryEntry;
import com.kichik.pecoff4j.util.Reflection;
import java.io.IOException;

public class GroupIconDirectory {
    private int reserved;
    private int type;
    private int count;
    private GroupIconDirectoryEntry[] entries;

    public int getReserved() {
        return this.reserved;
    }

    public int getType() {
        return this.type;
    }

    public int getCount() {
        return this.count;
    }

    public GroupIconDirectoryEntry getEntry(int index) {
        return this.entries[index];
    }

    public String toString() {
        return Reflection.toString(this);
    }

    public static GroupIconDirectory read(byte[] data) throws IOException {
        return GroupIconDirectory.read(new DataReader(data));
    }

    public static GroupIconDirectory read(IDataReader dr) throws IOException {
        GroupIconDirectory gi = new GroupIconDirectory();
        gi.reserved = dr.readWord();
        gi.type = dr.readWord();
        gi.count = dr.readWord();
        gi.entries = new GroupIconDirectoryEntry[gi.count];
        for (int i = 0; i < gi.count; ++i) {
            gi.entries[i] = GroupIconDirectoryEntry.read(dr);
        }
        return gi;
    }
}

