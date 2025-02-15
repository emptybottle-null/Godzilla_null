/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.archivers.zip.ZipUtil;

public abstract class PKWareExtraHeader
implements ZipExtraField {
    private final ZipShort headerId;
    private byte[] localData;
    private byte[] centralData;

    protected PKWareExtraHeader(ZipShort headerId) {
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
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        this.setLocalFileDataData(Arrays.copyOfRange(data, offset, offset + length));
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] data, int offset, int length) throws ZipException {
        byte[] tmp = Arrays.copyOfRange(data, offset, offset + length);
        this.setCentralDirectoryData(tmp);
        if (this.localData == null) {
            this.setLocalFileDataData(tmp);
        }
    }

    protected final void assertMinimalLength(int minimum, int length) throws ZipException {
        if (length < minimum) {
            throw new ZipException(this.getClass().getName() + " is too short, only " + length + " bytes, expected at least " + minimum);
        }
    }

    public static enum HashAlgorithm {
        NONE(0),
        CRC32(1),
        MD5(32771),
        SHA1(32772),
        RIPEND160(32775),
        SHA256(32780),
        SHA384(32781),
        SHA512(32782);

        private final int code;
        private static final Map<Integer, HashAlgorithm> codeToEnum;

        private HashAlgorithm(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

        public static HashAlgorithm getAlgorithmByCode(int code) {
            return codeToEnum.get(code);
        }

        static {
            HashMap<Integer, HashAlgorithm> cte = new HashMap<Integer, HashAlgorithm>();
            for (HashAlgorithm method : HashAlgorithm.values()) {
                cte.put(method.getCode(), method);
            }
            codeToEnum = Collections.unmodifiableMap(cte);
        }
    }

    public static enum EncryptionAlgorithm {
        DES(26113),
        RC2pre52(26114),
        TripleDES168(26115),
        TripleDES192(26121),
        AES128(26126),
        AES192(26127),
        AES256(26128),
        RC2(26370),
        RC4(26625),
        UNKNOWN(65535);

        private final int code;
        private static final Map<Integer, EncryptionAlgorithm> codeToEnum;

        private EncryptionAlgorithm(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }

        public static EncryptionAlgorithm getAlgorithmByCode(int code) {
            return codeToEnum.get(code);
        }

        static {
            HashMap<Integer, EncryptionAlgorithm> cte = new HashMap<Integer, EncryptionAlgorithm>();
            for (EncryptionAlgorithm method : EncryptionAlgorithm.values()) {
                cte.put(method.getCode(), method);
            }
            codeToEnum = Collections.unmodifiableMap(cte);
        }
    }
}

