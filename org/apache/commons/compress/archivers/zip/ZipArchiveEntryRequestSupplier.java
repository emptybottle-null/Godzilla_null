/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntryRequest;

public interface ZipArchiveEntryRequestSupplier {
    public ZipArchiveEntryRequest get();
}

