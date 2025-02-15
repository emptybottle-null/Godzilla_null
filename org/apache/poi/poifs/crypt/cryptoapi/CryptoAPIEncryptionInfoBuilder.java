/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.IOException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionHeader;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionVerifier;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptor;
import org.apache.poi.util.LittleEndianInput;

public class CryptoAPIEncryptionInfoBuilder
implements EncryptionInfoBuilder {
    @Override
    public void initialize(EncryptionInfo info, LittleEndianInput dis) throws IOException {
        dis.readInt();
        CryptoAPIEncryptionHeader header = new CryptoAPIEncryptionHeader(dis);
        info.setHeader(header);
        info.setVerifier(new CryptoAPIEncryptionVerifier(dis, header));
        CryptoAPIDecryptor dec = new CryptoAPIDecryptor();
        dec.setEncryptionInfo(info);
        info.setDecryptor(dec);
        CryptoAPIEncryptor enc = new CryptoAPIEncryptor();
        enc.setEncryptionInfo(info);
        info.setEncryptor(enc);
    }

    @Override
    public void initialize(EncryptionInfo info, CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        if (cipherAlgorithm == null) {
            cipherAlgorithm = CipherAlgorithm.rc4;
        }
        if (hashAlgorithm == null) {
            hashAlgorithm = HashAlgorithm.sha1;
        }
        if (keyBits == -1) {
            keyBits = 40;
        }
        assert (cipherAlgorithm == CipherAlgorithm.rc4 && hashAlgorithm == HashAlgorithm.sha1);
        info.setHeader(new CryptoAPIEncryptionHeader(cipherAlgorithm, hashAlgorithm, keyBits, blockSize, chainingMode));
        info.setVerifier(new CryptoAPIEncryptionVerifier(cipherAlgorithm, hashAlgorithm, keyBits, blockSize, chainingMode));
        CryptoAPIDecryptor dec = new CryptoAPIDecryptor();
        dec.setEncryptionInfo(info);
        info.setDecryptor(dec);
        CryptoAPIEncryptor enc = new CryptoAPIEncryptor();
        enc.setEncryptionInfo(info);
        info.setEncryptor(enc);
    }
}

