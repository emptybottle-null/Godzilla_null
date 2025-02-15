/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipShort;

public interface ZipExtraField {
    public static final int EXTRAFIELD_HEADER_SIZE = 4;

    public ZipShort getHeaderId();

    public ZipShort getLocalFileDataLength();

    public ZipShort getCentralDirectoryLength();

    public byte[] getLocalFileDataData();

    public byte[] getCentralDirectoryData();

    public void parseFromLocalFileData(byte[] var1, int var2, int var3) throws ZipException;

    public void parseFromCentralDirectoryData(byte[] var1, int var2, int var3) throws ZipException;
}

