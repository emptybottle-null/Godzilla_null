/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.storage;

import java.io.IOException;
import java.io.OutputStream;

public interface BlockWritable {
    public void writeBlocks(OutputStream var1) throws IOException;
}

