/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.util.BitSet;
import org.apache.commons.compress.archivers.sevenz.Folder;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.StreamMap;
import org.apache.commons.compress.archivers.sevenz.SubStreamsInfo;

class Archive {
    long packPos;
    long[] packSizes;
    BitSet packCrcsDefined;
    long[] packCrcs;
    Folder[] folders;
    SubStreamsInfo subStreamsInfo;
    SevenZArchiveEntry[] files;
    StreamMap streamMap;

    Archive() {
    }

    public String toString() {
        return "Archive with packed streams starting at offset " + this.packPos + ", " + Archive.lengthOf(this.packSizes) + " pack sizes, " + Archive.lengthOf(this.packCrcs) + " CRCs, " + Archive.lengthOf(this.folders) + " folders, " + Archive.lengthOf(this.files) + " files and " + this.streamMap;
    }

    private static String lengthOf(long[] a) {
        return a == null ? "(null)" : String.valueOf(a.length);
    }

    private static String lengthOf(Object[] a) {
        return a == null ? "(null)" : String.valueOf(a.length);
    }
}

