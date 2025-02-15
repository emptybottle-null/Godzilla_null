/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.standard;

import org.apache.poi.util.LittleEndianByteArrayOutputStream;

public interface EncryptionRecord {
    public void write(LittleEndianByteArrayOutputStream var1);
}

