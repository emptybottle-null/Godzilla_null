/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.binaryrc4;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;

public class BinaryRC4EncryptionVerifier
extends EncryptionVerifier
implements EncryptionRecord {
    protected BinaryRC4EncryptionVerifier() {
        this.setSpinCount(-1);
        this.setCipherAlgorithm(CipherAlgorithm.rc4);
        this.setChainingMode(null);
        this.setEncryptedKey(null);
        this.setHashAlgorithm(HashAlgorithm.md5);
    }

    protected BinaryRC4EncryptionVerifier(LittleEndianInput is) {
        byte[] salt = new byte[16];
        is.readFully(salt);
        this.setSalt(salt);
        byte[] encryptedVerifier = new byte[16];
        is.readFully(encryptedVerifier);
        this.setEncryptedVerifier(encryptedVerifier);
        byte[] encryptedVerifierHash = new byte[16];
        is.readFully(encryptedVerifierHash);
        this.setEncryptedVerifierHash(encryptedVerifierHash);
        this.setSpinCount(-1);
        this.setCipherAlgorithm(CipherAlgorithm.rc4);
        this.setChainingMode(null);
        this.setEncryptedKey(null);
        this.setHashAlgorithm(HashAlgorithm.md5);
    }

    protected BinaryRC4EncryptionVerifier(BinaryRC4EncryptionVerifier other) {
        super(other);
    }

    @Override
    protected void setSalt(byte[] salt) {
        if (salt == null || salt.length != 16) {
            throw new EncryptedDocumentException("invalid verifier salt");
        }
        super.setSalt(salt);
    }

    @Override
    protected void setEncryptedVerifier(byte[] encryptedVerifier) {
        super.setEncryptedVerifier(encryptedVerifier);
    }

    @Override
    protected void setEncryptedVerifierHash(byte[] encryptedVerifierHash) {
        super.setEncryptedVerifierHash(encryptedVerifierHash);
    }

    @Override
    public void write(LittleEndianByteArrayOutputStream bos) {
        byte[] salt = this.getSalt();
        assert (salt.length == 16);
        bos.write(salt);
        byte[] encryptedVerifier = this.getEncryptedVerifier();
        assert (encryptedVerifier.length == 16);
        bos.write(encryptedVerifier);
        byte[] encryptedVerifierHash = this.getEncryptedVerifierHash();
        assert (encryptedVerifierHash.length == 16);
        bos.write(encryptedVerifierHash);
    }

    @Override
    public BinaryRC4EncryptionVerifier copy() {
        return new BinaryRC4EncryptionVerifier(this);
    }
}

