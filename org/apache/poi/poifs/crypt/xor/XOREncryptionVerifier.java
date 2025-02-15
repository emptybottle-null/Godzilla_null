/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.xor;

import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;

public class XOREncryptionVerifier
extends EncryptionVerifier
implements EncryptionRecord {
    protected XOREncryptionVerifier() {
        this.setEncryptedKey(new byte[2]);
        this.setEncryptedVerifier(new byte[2]);
    }

    protected XOREncryptionVerifier(LittleEndianInput is) {
        byte[] key = new byte[2];
        is.readFully(key);
        this.setEncryptedKey(key);
        byte[] verifier = new byte[2];
        is.readFully(verifier);
        this.setEncryptedVerifier(verifier);
    }

    protected XOREncryptionVerifier(XOREncryptionVerifier other) {
        super(other);
    }

    @Override
    public void write(LittleEndianByteArrayOutputStream bos) {
        bos.write(this.getEncryptedKey());
        bos.write(this.getEncryptedVerifier());
    }

    @Override
    public XOREncryptionVerifier copy() {
        return new XOREncryptionVerifier(this);
    }

    @Override
    protected final void setEncryptedVerifier(byte[] encryptedVerifier) {
        super.setEncryptedVerifier(encryptedVerifier);
    }

    @Override
    protected final void setEncryptedKey(byte[] encryptedKey) {
        super.setEncryptedKey(encryptedKey);
    }
}

