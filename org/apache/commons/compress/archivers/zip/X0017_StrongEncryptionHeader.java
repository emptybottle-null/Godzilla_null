/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.Arrays;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.PKWareExtraHeader;
import org.apache.commons.compress.archivers.zip.ZipLong;
import org.apache.commons.compress.archivers.zip.ZipShort;

public class X0017_StrongEncryptionHeader
extends PKWareExtraHeader {
    private int format;
    private PKWareExtraHeader.EncryptionAlgorithm algId;
    private int bitlen;
    private int flags;
    private long rcount;
    private PKWareExtraHeader.HashAlgorithm hashAlg;
    private int hashSize;
    private byte[] ivData;
    private byte[] erdData;
    private byte[] recipientKeyHash;
    private byte[] keyBlob;
    private byte[] vData;
    private byte[] vCRC32;

    public X0017_StrongEncryptionHeader() {
        super(new ZipShort(23));
    }

    public long getRecordCount() {
        return this.rcount;
    }

    public PKWareExtraHeader.HashAlgorithm getHashAlgorithm() {
        return this.hashAlg;
    }

    public PKWareExtraHeader.EncryptionAlgorithm getEncryptionAlgorithm() {
        return this.algId;
    }

    public void parseCentralDirectoryFormat(byte[] data, int offset, int length) throws ZipException {
        this.assertMinimalLength(12, length);
        this.format = ZipShort.getValue(data, offset);
        this.algId = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(data, offset + 2));
        this.bitlen = ZipShort.getValue(data, offset + 4);
        this.flags = ZipShort.getValue(data, offset + 6);
        this.rcount = ZipLong.getValue(data, offset + 8);
        if (this.rcount > 0L) {
            this.assertMinimalLength(16, length);
            this.hashAlg = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(data, offset + 12));
            this.hashSize = ZipShort.getValue(data, offset + 14);
            for (long i = 0L; i < this.rcount; ++i) {
                for (int j = 0; j < this.hashSize; ++j) {
                }
            }
        }
    }

    public void parseFileFormat(byte[] data, int offset, int length) throws ZipException {
        this.assertMinimalLength(4, length);
        int ivSize = ZipShort.getValue(data, offset);
        this.assertDynamicLengthFits("ivSize", ivSize, 4, length);
        this.ivData = Arrays.copyOfRange(data, offset + 4, ivSize);
        this.assertMinimalLength(16 + ivSize, length);
        this.format = ZipShort.getValue(data, offset + ivSize + 6);
        this.algId = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(data, offset + ivSize + 8));
        this.bitlen = ZipShort.getValue(data, offset + ivSize + 10);
        this.flags = ZipShort.getValue(data, offset + ivSize + 12);
        int erdSize = ZipShort.getValue(data, offset + ivSize + 14);
        this.assertDynamicLengthFits("erdSize", erdSize, ivSize + 16, length);
        this.erdData = Arrays.copyOfRange(data, offset + ivSize + 16, erdSize);
        this.assertMinimalLength(20 + ivSize + erdSize, length);
        this.rcount = ZipLong.getValue(data, offset + ivSize + 16 + erdSize);
        if (this.rcount == 0L) {
            this.assertMinimalLength(ivSize + 20 + erdSize + 2, length);
            int vSize = ZipShort.getValue(data, offset + ivSize + 20 + erdSize);
            this.assertDynamicLengthFits("vSize", vSize, ivSize + 22 + erdSize, length);
            if (vSize < 4) {
                throw new ZipException("Invalid X0017_StrongEncryptionHeader: vSize " + vSize + " is too small to hold CRC");
            }
            this.vData = Arrays.copyOfRange(data, offset + ivSize + 22 + erdSize, vSize - 4);
            this.vCRC32 = Arrays.copyOfRange(data, offset + ivSize + 22 + erdSize + vSize - 4, 4);
        } else {
            this.assertMinimalLength(ivSize + 20 + erdSize + 6, length);
            this.hashAlg = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(data, offset + ivSize + 20 + erdSize));
            this.hashSize = ZipShort.getValue(data, offset + ivSize + 22 + erdSize);
            int resize = ZipShort.getValue(data, offset + ivSize + 24 + erdSize);
            this.recipientKeyHash = new byte[this.hashSize];
            if (resize < this.hashSize) {
                throw new ZipException("Invalid X0017_StrongEncryptionHeader: resize " + resize + " is too small to hold hashSize" + this.hashSize);
            }
            this.keyBlob = new byte[resize - this.hashSize];
            this.assertDynamicLengthFits("resize", resize, ivSize + 24 + erdSize, length);
            System.arraycopy(data, offset + ivSize + 24 + erdSize, this.recipientKeyHash, 0, this.hashSize);
            System.arraycopy(data, offset + ivSize + 24 + erdSize + this.hashSize, this.keyBlob, 0, resize - this.hashSize);
            this.assertMinimalLength(ivSize + 26 + erdSize + resize + 2, length);
            int vSize = ZipShort.getValue(data, offset + ivSize + 26 + erdSize + resize);
            if (vSize < 4) {
                throw new ZipException("Invalid X0017_StrongEncryptionHeader: vSize " + vSize + " is too small to hold CRC");
            }
            this.assertDynamicLengthFits("vSize", vSize, ivSize + 22 + erdSize + resize, length);
            this.vData = new byte[vSize - 4];
            this.vCRC32 = new byte[4];
            System.arraycopy(data, offset + ivSize + 22 + erdSize + resize, this.vData, 0, vSize - 4);
            System.arraycopy(data, offset + ivSize + 22 + erdSize + resize + vSize - 4, this.vCRC32, 0, 4);
        }
    }

    @Override
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        super.parseFromLocalFileData(data, offset, length);
        this.parseFileFormat(data, offset, length);
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] data, int offset, int length) throws ZipException {
        super.parseFromCentralDirectoryData(data, offset, length);
        this.parseCentralDirectoryFormat(data, offset, length);
    }

    private void assertDynamicLengthFits(String what, int dynamicLength, int prefixLength, int length) throws ZipException {
        if (prefixLength + dynamicLength > length) {
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: " + what + " " + dynamicLength + " doesn't fit into " + length + " bytes of data at position " + prefixLength);
        }
    }
}

