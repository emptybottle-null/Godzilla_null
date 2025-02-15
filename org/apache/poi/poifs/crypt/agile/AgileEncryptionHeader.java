/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.agile;

import com.microsoft.schemas.office.x2006.encryption.CTDataIntegrity;
import com.microsoft.schemas.office.x2006.encryption.CTKeyData;
import com.microsoft.schemas.office.x2006.encryption.EncryptionDocument;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.agile.AgileEncryptionInfoBuilder;
import org.apache.poi.util.GenericRecordUtil;

public class AgileEncryptionHeader
extends EncryptionHeader {
    private byte[] encryptedHmacKey;
    private byte[] encryptedHmacValue;

    public AgileEncryptionHeader(String descriptor) {
        this(AgileEncryptionInfoBuilder.parseDescriptor(descriptor));
    }

    public AgileEncryptionHeader(AgileEncryptionHeader other) {
        super(other);
        this.encryptedHmacKey = other.encryptedHmacKey == null ? null : (byte[])other.encryptedHmacKey.clone();
        this.encryptedHmacValue = other.encryptedHmacValue == null ? null : (byte[])other.encryptedHmacValue.clone();
    }

    protected AgileEncryptionHeader(EncryptionDocument ed) {
        CTKeyData keyData;
        try {
            keyData = ed.getEncryption().getKeyData();
            if (keyData == null) {
                throw new NullPointerException("keyData not set");
            }
        } catch (Exception e) {
            throw new EncryptedDocumentException("Unable to parse keyData");
        }
        int keyBits = (int)keyData.getKeyBits();
        CipherAlgorithm ca = CipherAlgorithm.fromXmlId(keyData.getCipherAlgorithm().toString(), keyBits);
        this.setCipherAlgorithm(ca);
        this.setCipherProvider(ca.provider);
        this.setKeySize(keyBits);
        this.setFlags(0);
        this.setSizeExtra(0);
        this.setCspName(null);
        this.setBlockSize(keyData.getBlockSize());
        switch (keyData.getCipherChaining().intValue()) {
            case 1: {
                this.setChainingMode(ChainingMode.cbc);
                break;
            }
            case 2: {
                this.setChainingMode(ChainingMode.cfb);
                break;
            }
            default: {
                throw new EncryptedDocumentException("Unsupported chaining mode - " + keyData.getCipherChaining());
            }
        }
        int hashSize = keyData.getHashSize();
        HashAlgorithm ha = HashAlgorithm.fromEcmaId(keyData.getHashAlgorithm().toString());
        this.setHashAlgorithm(ha);
        if (this.getHashAlgorithm().hashSize != hashSize) {
            throw new EncryptedDocumentException("Unsupported hash algorithm: " + keyData.getHashAlgorithm() + " @ " + hashSize + " bytes");
        }
        int saltLength = keyData.getSaltSize();
        this.setKeySalt(keyData.getSaltValue());
        if (this.getKeySalt().length != saltLength) {
            throw new EncryptedDocumentException("Invalid salt length");
        }
        CTDataIntegrity di = ed.getEncryption().getDataIntegrity();
        this.setEncryptedHmacKey(di.getEncryptedHmacKey());
        this.setEncryptedHmacValue(di.getEncryptedHmacValue());
    }

    public AgileEncryptionHeader(CipherAlgorithm algorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        this.setCipherAlgorithm(algorithm);
        this.setHashAlgorithm(hashAlgorithm);
        this.setKeySize(keyBits);
        this.setBlockSize(blockSize);
        this.setChainingMode(chainingMode);
    }

    @Override
    protected void setKeySalt(byte[] salt) {
        if (salt == null || salt.length != this.getBlockSize()) {
            throw new EncryptedDocumentException("invalid verifier salt");
        }
        super.setKeySalt(salt);
    }

    public byte[] getEncryptedHmacKey() {
        return this.encryptedHmacKey;
    }

    protected void setEncryptedHmacKey(byte[] encryptedHmacKey) {
        this.encryptedHmacKey = encryptedHmacKey == null ? null : (byte[])encryptedHmacKey.clone();
    }

    public byte[] getEncryptedHmacValue() {
        return this.encryptedHmacValue;
    }

    protected void setEncryptedHmacValue(byte[] encryptedHmacValue) {
        this.encryptedHmacValue = encryptedHmacValue == null ? null : (byte[])encryptedHmacValue.clone();
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "encryptedHmacKey", this::getEncryptedHmacKey, "encryptedHmacValue", this::getEncryptedHmacValue);
    }

    @Override
    public AgileEncryptionHeader copy() {
        return new AgileEncryptionHeader(this);
    }
}

