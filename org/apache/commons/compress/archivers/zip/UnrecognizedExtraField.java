/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.Arrays;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.archivers.zip.ZipUtil;

public class UnrecognizedExtraField
implements ZipExtraField {
    private ZipShort headerId;
    private byte[] localData;
    private byte[] centralData;

    public void setHeaderId(ZipShort headerId) {
        this.headerId = headerId;
    }

    @Override
    public ZipShort getHeaderId() {
        return this.headerId;
    }

    public void setLocalFileDataData(byte[] data) {
        this.localData = ZipUtil.copy(data);
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.localData != null ? this.localData.length : 0);
    }

    @Override
    public byte[] getLocalFileDataData() {
        return ZipUtil.copy(this.localData);
    }

    public void setCentralDirectoryData(byte[] data) {
        this.centralData = ZipUtil.copy(data);
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        if (this.centralData != null) {
            return new ZipShort(this.centralData.length);
        }
        return this.getLocalFileDataLength();
    }

    @Override
    public byte[] getCentralDirectoryData() {
        if (this.centralData != null) {
            return ZipUtil.copy(this.centralData);
        }
        return this.getLocalFileDataData();
    }

    @Override
    public void parseFromLocalFileData(byte[] data, int offset, int length) {
        this.setLocalFileDataData(Arrays.copyOfRange(data, offset, offset + length));
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] data, int offset, int length) {
        byte[] tmp = Arrays.copyOfRange(data, offset, offset + length);
        this.setCentralDirectoryData(tmp);
        if (this.localData == null) {
            this.setLocalFileDataData(tmp);
        }
    }
}

