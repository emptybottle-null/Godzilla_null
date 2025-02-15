/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;

public class EncryptionInfo
implements GenericRecord {
    public static final BitField flagCryptoAPI = BitFieldFactory.getInstance(4);
    public static final BitField flagDocProps = BitFieldFactory.getInstance(8);
    public static final BitField flagExternal = BitFieldFactory.getInstance(16);
    public static final BitField flagAES = BitFieldFactory.getInstance(32);
    private static final int[] FLAGS_MASKS = new int[]{4, 8, 16, 32};
    private static final String[] FLAGS_NAMES = new String[]{"CRYPTO_API", "DOC_PROPS", "EXTERNAL", "AES"};
    private final EncryptionMode encryptionMode;
    private final int versionMajor;
    private final int versionMinor;
    private final int encryptionFlags;
    private EncryptionHeader header;
    private EncryptionVerifier verifier;
    private Decryptor decryptor;
    private Encryptor encryptor;

    public EncryptionInfo(POIFSFileSystem fs) throws IOException {
        this(fs.getRoot());
    }

    public EncryptionInfo(DirectoryNode dir) throws IOException {
        this(dir.createDocumentInputStream("EncryptionInfo"), null);
    }

    public EncryptionInfo(LittleEndianInput dis, EncryptionMode preferredEncryptionMode) throws IOException {
        EncryptionInfoBuilder eib;
        if (preferredEncryptionMode == EncryptionMode.xor) {
            this.versionMajor = EncryptionMode.xor.versionMajor;
            this.versionMinor = EncryptionMode.xor.versionMinor;
        } else {
            this.versionMajor = dis.readUShort();
            this.versionMinor = dis.readUShort();
        }
        if (this.versionMajor == EncryptionMode.xor.versionMajor && this.versionMinor == EncryptionMode.xor.versionMinor) {
            this.encryptionMode = EncryptionMode.xor;
            this.encryptionFlags = -1;
        } else if (this.versionMajor == EncryptionMode.binaryRC4.versionMajor && this.versionMinor == EncryptionMode.binaryRC4.versionMinor) {
            this.encryptionMode = EncryptionMode.binaryRC4;
            this.encryptionFlags = -1;
        } else if (2 <= this.versionMajor && this.versionMajor <= 4 && this.versionMinor == 2) {
            this.encryptionFlags = dis.readInt();
            this.encryptionMode = preferredEncryptionMode == EncryptionMode.cryptoAPI || !flagAES.isSet(this.encryptionFlags) ? EncryptionMode.cryptoAPI : EncryptionMode.standard;
        } else if (this.versionMajor == EncryptionMode.agile.versionMajor && this.versionMinor == EncryptionMode.agile.versionMinor) {
            this.encryptionMode = EncryptionMode.agile;
            this.encryptionFlags = dis.readInt();
        } else {
            this.encryptionFlags = dis.readInt();
            throw new EncryptedDocumentException("Unknown encryption: version major: " + this.versionMajor + " / version minor: " + this.versionMinor + " / fCrypto: " + flagCryptoAPI.isSet(this.encryptionFlags) + " / fExternal: " + flagExternal.isSet(this.encryptionFlags) + " / fDocProps: " + flagDocProps.isSet(this.encryptionFlags) + " / fAES: " + flagAES.isSet(this.encryptionFlags));
        }
        try {
            eib = EncryptionInfo.getBuilder(this.encryptionMode);
        } catch (Exception e) {
            throw new IOException(e);
        }
        eib.initialize(this, dis);
    }

    public EncryptionInfo(EncryptionMode encryptionMode) {
        this(encryptionMode, null, null, -1, -1, null);
    }

    public EncryptionInfo(EncryptionMode encryptionMode, CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        EncryptionInfoBuilder eib;
        this.encryptionMode = encryptionMode;
        this.versionMajor = encryptionMode.versionMajor;
        this.versionMinor = encryptionMode.versionMinor;
        this.encryptionFlags = encryptionMode.encryptionFlags;
        try {
            eib = EncryptionInfo.getBuilder(encryptionMode);
        } catch (Exception e) {
            throw new EncryptedDocumentException(e);
        }
        eib.initialize(this, cipherAlgorithm, hashAlgorithm, keyBits, blockSize, chainingMode);
    }

    public EncryptionInfo(EncryptionInfo other) {
        this.encryptionMode = other.encryptionMode;
        this.versionMajor = other.versionMajor;
        this.versionMinor = other.versionMinor;
        this.encryptionFlags = other.encryptionFlags;
        this.header = other.header == null ? null : other.header.copy();
        EncryptionVerifier encryptionVerifier = this.verifier = other.verifier == null ? null : other.verifier.copy();
        if (other.decryptor != null) {
            this.decryptor = other.decryptor.copy();
            this.decryptor.setEncryptionInfo(this);
        }
        if (other.encryptor != null) {
            this.encryptor = other.encryptor.copy();
            this.encryptor.setEncryptionInfo(this);
        }
    }

    protected static EncryptionInfoBuilder getBuilder(EncryptionMode encryptionMode) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader cl = EncryptionInfo.class.getClassLoader();
        EncryptionInfoBuilder eib = (EncryptionInfoBuilder)cl.loadClass(encryptionMode.builder).newInstance();
        return eib;
    }

    public int getVersionMajor() {
        return this.versionMajor;
    }

    public int getVersionMinor() {
        return this.versionMinor;
    }

    public int getEncryptionFlags() {
        return this.encryptionFlags;
    }

    public EncryptionHeader getHeader() {
        return this.header;
    }

    public EncryptionVerifier getVerifier() {
        return this.verifier;
    }

    public Decryptor getDecryptor() {
        return this.decryptor;
    }

    public Encryptor getEncryptor() {
        return this.encryptor;
    }

    public void setHeader(EncryptionHeader header) {
        this.header = header;
    }

    public void setVerifier(EncryptionVerifier verifier) {
        this.verifier = verifier;
    }

    public void setDecryptor(Decryptor decryptor) {
        this.decryptor = decryptor;
    }

    public void setEncryptor(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    public EncryptionMode getEncryptionMode() {
        return this.encryptionMode;
    }

    public boolean isDocPropsEncrypted() {
        return !flagDocProps.isSet(this.getEncryptionFlags());
    }

    public EncryptionInfo copy() {
        return new EncryptionInfo(this);
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap<String, Supplier<Object>> m = new LinkedHashMap<String, Supplier<Object>>();
        m.put("encryptionMode", this::getEncryptionMode);
        m.put("versionMajor", this::getVersionMajor);
        m.put("versionMinor", this::getVersionMinor);
        m.put("encryptionFlags", GenericRecordUtil.getBitsAsString(this::getEncryptionFlags, FLAGS_MASKS, FLAGS_NAMES));
        m.put("header", this::getHeader);
        m.put("verifier", this::getVerifier);
        m.put("decryptor", this::getDecryptor);
        m.put("encryptor", this::getEncryptor);
        return Collections.unmodifiableMap(m);
    }
}

