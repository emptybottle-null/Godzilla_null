/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.HashAlgorithm;

public abstract class EncryptionVerifier
implements GenericRecord,
Duplicatable {
    private byte[] salt;
    private byte[] encryptedVerifier;
    private byte[] encryptedVerifierHash;
    private byte[] encryptedKey;
    private int spinCount;
    private CipherAlgorithm cipherAlgorithm;
    private ChainingMode chainingMode;
    private HashAlgorithm hashAlgorithm;

    protected EncryptionVerifier() {
    }

    protected EncryptionVerifier(EncryptionVerifier other) {
        this.salt = other.salt == null ? null : (byte[])other.salt.clone();
        this.encryptedVerifier = other.encryptedVerifier == null ? null : (byte[])other.encryptedVerifier.clone();
        this.encryptedVerifierHash = other.encryptedVerifierHash == null ? null : (byte[])other.encryptedVerifierHash.clone();
        this.encryptedKey = other.encryptedKey == null ? null : (byte[])other.encryptedKey.clone();
        this.spinCount = other.spinCount;
        this.cipherAlgorithm = other.cipherAlgorithm;
        this.chainingMode = other.chainingMode;
        this.hashAlgorithm = other.hashAlgorithm;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public byte[] getEncryptedVerifier() {
        return this.encryptedVerifier;
    }

    public byte[] getEncryptedVerifierHash() {
        return this.encryptedVerifierHash;
    }

    public int getSpinCount() {
        return this.spinCount;
    }

    public byte[] getEncryptedKey() {
        return this.encryptedKey;
    }

    public CipherAlgorithm getCipherAlgorithm() {
        return this.cipherAlgorithm;
    }

    public HashAlgorithm getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public ChainingMode getChainingMode() {
        return this.chainingMode;
    }

    protected void setSalt(byte[] salt) {
        this.salt = salt == null ? null : (byte[])salt.clone();
    }

    protected void setEncryptedVerifier(byte[] encryptedVerifier) {
        this.encryptedVerifier = encryptedVerifier == null ? null : (byte[])encryptedVerifier.clone();
    }

    protected void setEncryptedVerifierHash(byte[] encryptedVerifierHash) {
        this.encryptedVerifierHash = encryptedVerifierHash == null ? null : (byte[])encryptedVerifierHash.clone();
    }

    protected void setEncryptedKey(byte[] encryptedKey) {
        this.encryptedKey = encryptedKey == null ? null : (byte[])encryptedKey.clone();
    }

    protected void setSpinCount(int spinCount) {
        this.spinCount = spinCount;
    }

    protected void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm) {
        this.cipherAlgorithm = cipherAlgorithm;
    }

    protected void setChainingMode(ChainingMode chainingMode) {
        this.chainingMode = chainingMode;
    }

    protected void setHashAlgorithm(HashAlgorithm hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    @Override
    public abstract EncryptionVerifier copy();

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap<String, Supplier<Object>> m = new LinkedHashMap<String, Supplier<Object>>();
        m.put("salt", this::getSalt);
        m.put("encryptedVerifier", this::getEncryptedVerifier);
        m.put("encryptedVerifierHash", this::getEncryptedVerifierHash);
        m.put("encryptedKey", this::getEncryptedKey);
        m.put("spinCount", this::getSpinCount);
        m.put("cipherAlgorithm", this::getCipherAlgorithm);
        m.put("chainingMode", this::getChainingMode);
        m.put("hashAlgorithm", this::getHashAlgorithm);
        return Collections.unmodifiableMap(m);
    }
}

