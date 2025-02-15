/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.xor;

import java.io.IOException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.xor.XORDecryptor;
import org.apache.poi.poifs.crypt.xor.XOREncryptionHeader;
import org.apache.poi.poifs.crypt.xor.XOREncryptionVerifier;
import org.apache.poi.poifs.crypt.xor.XOREncryptor;
import org.apache.poi.util.LittleEndianInput;

public class XOREncryptionInfoBuilder
implements EncryptionInfoBuilder {
    @Override
    public void initialize(EncryptionInfo info, LittleEndianInput dis) throws IOException {
        info.setHeader(new XOREncryptionHeader());
        info.setVerifier(new XOREncryptionVerifier(dis));
        XORDecryptor dec = new XORDecryptor();
        dec.setEncryptionInfo(info);
        info.setDecryptor(dec);
        XOREncryptor enc = new XOREncryptor();
        enc.setEncryptionInfo(info);
        info.setEncryptor(enc);
    }

    @Override
    public void initialize(EncryptionInfo info, CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        info.setHeader(new XOREncryptionHeader());
        info.setVerifier(new XOREncryptionVerifier());
        XORDecryptor dec = new XORDecryptor();
        dec.setEncryptionInfo(info);
        info.setDecryptor(dec);
        XOREncryptor enc = new XOREncryptor();
        enc.setEncryptionInfo(info);
        info.setEncryptor(enc);
    }
}

