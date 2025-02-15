/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.InputStream;
import java.util.zip.CRC32;
import org.apache.commons.compress.utils.ChecksumVerifyingInputStream;

public class CRC32VerifyingInputStream
extends ChecksumVerifyingInputStream {
    public CRC32VerifyingInputStream(InputStream in, long size, int expectedCrc32) {
        this(in, size, (long)expectedCrc32 & 0xFFFFFFFFL);
    }

    public CRC32VerifyingInputStream(InputStream in, long size, long expectedCrc32) {
        super(new CRC32(), in, size, expectedCrc32);
    }
}

