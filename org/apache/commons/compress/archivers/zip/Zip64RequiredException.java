/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

public class Zip64RequiredException
extends ZipException {
    private static final long serialVersionUID = 20110809L;
    static final String ARCHIVE_TOO_BIG_MESSAGE = "Archive's size exceeds the limit of 4GByte.";
    static final String TOO_MANY_ENTRIES_MESSAGE = "Archive contains more than 65535 entries.";

    static String getEntryTooBigMessage(ZipArchiveEntry ze) {
        return ze.getName() + "'s size exceeds the limit of 4GByte.";
    }

    public Zip64RequiredException(String reason) {
        super(reason);
    }
}

