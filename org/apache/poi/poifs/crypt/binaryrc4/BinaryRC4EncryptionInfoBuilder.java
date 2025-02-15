/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.binaryrc4;

import java.io.IOException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionInfoBuilder;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4Decryptor;
import org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4EncryptionHeader;
import org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4EncryptionVerifier;
import org.apache.poi.poifs.crypt.binaryrc4.BinaryRC4Encryptor;
import org.apache.poi.util.LittleEndianInput;

public class BinaryRC4EncryptionInfoBuilder
implements EncryptionInfoBuilder {
    @Override
    public void initialize(EncryptionInfo info, LittleEndianInput dis) throws IOException {
        int vMajor = info.getVersionMajor();
        int vMinor = info.getVersionMinor();
        assert (vMajor == 1 && vMinor == 1);
        info.setHeader(new BinaryRC4EncryptionHeader());
        info.setVerifier(new BinaryRC4EncryptionVerifier(dis));
        BinaryRC4Decryptor dec = new BinaryRC4Decryptor();
        dec.setEncryptionInfo(info);
        info.setDecryptor(dec);
        BinaryRC4Encryptor enc = new BinaryRC4Encryptor();
        enc.setEncryptionInfo(info);
        info.setEncryptor(enc);
    }

    @Override
    public void initialize(EncryptionInfo info, CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        info.setHeader(new BinaryRC4EncryptionHeader());
        info.setVerifier(new BinaryRC4EncryptionVerifier());
        BinaryRC4Decryptor dec = new BinaryRC4Decryptor();
        dec.setEncryptionInfo(info);
        info.setDecryptor(dec);
        BinaryRC4Encryptor enc = new BinaryRC4Encryptor();
        enc.setEncryptionInfo(info);
        info.setEncryptor(enc);
    }
}

