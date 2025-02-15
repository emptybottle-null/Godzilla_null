/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.UnparseableExtraFieldBehavior;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;

public interface ExtraFieldParsingBehavior
extends UnparseableExtraFieldBehavior {
    public ZipExtraField createExtraField(ZipShort var1) throws ZipException, InstantiationException, IllegalAccessException;

    public ZipExtraField fill(ZipExtraField var1, byte[] var2, int var3, int var4, boolean var5) throws ZipException;
}

