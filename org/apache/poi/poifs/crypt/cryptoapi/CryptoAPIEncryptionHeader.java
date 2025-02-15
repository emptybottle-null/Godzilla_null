/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CipherProvider;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.standard.StandardEncryptionHeader;
import org.apache.poi.util.LittleEndianInput;

public class CryptoAPIEncryptionHeader
extends StandardEncryptionHeader {
    public CryptoAPIEncryptionHeader(LittleEndianInput is) throws IOException {
        super(is);
    }

    protected CryptoAPIEncryptionHeader(CryptoAPIEncryptionHeader other) {
        super(other);
    }

    protected CryptoAPIEncryptionHeader(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        super(cipherAlgorithm, hashAlgorithm, keyBits, blockSize, chainingMode);
    }

    @Override
    public void setKeySize(int keyBits) {
        boolean found = false;
        for (int size : this.getCipherAlgorithm().allowedKeySize) {
            if (size != keyBits) continue;
            found = true;
            break;
        }
        if (!found) {
            throw new EncryptedDocumentException("invalid keysize " + keyBits + " for cipher algorithm " + (Object)((Object)this.getCipherAlgorithm()));
        }
        super.setKeySize(keyBits);
        if (keyBits > 40) {
            this.setCspName("Microsoft Enhanced Cryptographic Provider v1.0");
        } else {
            this.setCspName(CipherProvider.rc4.cipherProviderName);
        }
    }

    @Override
    public CryptoAPIEncryptionHeader copy() {
        return new CryptoAPIEncryptionHeader(this);
    }
}

