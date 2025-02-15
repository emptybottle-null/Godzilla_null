/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.Arrays;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.archivers.zip.ZipUtil;

public final class UnparseableExtraFieldData
implements ZipExtraField {
    private static final ZipShort HEADER_ID = new ZipShort(44225);
    private byte[] localFileData;
    private byte[] centralDirectoryData;

    @Override
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.localFileData == null ? 0 : this.localFileData.length);
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        return this.centralDirectoryData == null ? this.getLocalFileDataLength() : new ZipShort(this.centralDirectoryData.length);
    }

    @Override
    public byte[] getLocalFileDataData() {
        return ZipUtil.copy(this.localFileData);
    }

    @Override
    public byte[] getCentralDirectoryData() {
        return this.centralDirectoryData == null ? this.getLocalFileDataData() : ZipUtil.copy(this.centralDirectoryData);
    }

    @Override
    public void parseFromLocalFileData(byte[] buffer, int offset, int length) {
        this.localFileData = Arrays.copyOfRange(buffer, offset, offset + length);
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) {
        this.centralDirectoryData = Arrays.copyOfRange(buffer, offset, offset + length);
        if (this.localFileData == null) {
            this.parseFromLocalFileData(buffer, offset, length);
        }
    }
}

