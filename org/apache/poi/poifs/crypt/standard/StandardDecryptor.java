/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.standard;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.BoundedInputStream;
import org.apache.poi.util.LittleEndian;

public class StandardDecryptor
extends Decryptor {
    private long _length = -1L;

    protected StandardDecryptor() {
    }

    protected StandardDecryptor(StandardDecryptor other) {
        super(other);
        this._length = other._length;
    }

    @Override
    public boolean verifyPassword(String password) {
        EncryptionVerifier ver = this.getEncryptionInfo().getVerifier();
        SecretKey skey = StandardDecryptor.generateSecretKey(password, ver, this.getKeySizeInBytes());
        Cipher cipher = this.getCipher(skey);
        try {
            byte[] encryptedVerifier = ver.getEncryptedVerifier();
            byte[] verifier = cipher.doFinal(encryptedVerifier);
            this.setVerifier(verifier);
            MessageDigest sha1 = CryptoFunctions.getMessageDigest(ver.getHashAlgorithm());
            byte[] calcVerifierHash = sha1.digest(verifier);
            byte[] encryptedVerifierHash = ver.getEncryptedVerifierHash();
            byte[] decryptedVerifierHash = cipher.doFinal(encryptedVerifierHash);
            byte[] verifierHash = Arrays.copyOf(decryptedVerifierHash, calcVerifierHash.length);
            if (Arrays.equals(calcVerifierHash, verifierHash)) {
                this.setSecretKey(skey);
                return true;
            }
            return false;
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    protected static SecretKey generateSecretKey(String password, EncryptionVerifier ver, int keySize) {
        HashAlgorithm hashAlgo = ver.getHashAlgorithm();
        byte[] pwHash = CryptoFunctions.hashPassword(password, hashAlgo, ver.getSalt(), ver.getSpinCount());
        byte[] blockKey = new byte[4];
        LittleEndian.putInt(blockKey, 0, 0);
        byte[] finalHash = CryptoFunctions.generateKey(pwHash, hashAlgo, blockKey, hashAlgo.hashSize);
        byte[] x1 = StandardDecryptor.fillAndXor(finalHash, (byte)54);
        byte[] x2 = StandardDecryptor.fillAndXor(finalHash, (byte)92);
        byte[] x3 = new byte[x1.length + x2.length];
        System.arraycopy(x1, 0, x3, 0, x1.length);
        System.arraycopy(x2, 0, x3, x1.length, x2.length);
        byte[] key = Arrays.copyOf(x3, keySize);
        return new SecretKeySpec(key, ver.getCipherAlgorithm().jceId);
    }

    protected static byte[] fillAndXor(byte[] hash, byte fillByte) {
        byte[] buff = new byte[64];
        Arrays.fill(buff, fillByte);
        for (int i = 0; i < hash.length; ++i) {
            buff[i] = (byte)(buff[i] ^ hash[i]);
        }
        MessageDigest sha1 = CryptoFunctions.getMessageDigest(HashAlgorithm.sha1);
        return sha1.digest(buff);
    }

    private Cipher getCipher(SecretKey key) {
        EncryptionHeader em = this.getEncryptionInfo().getHeader();
        ChainingMode cm = em.getChainingMode();
        assert (cm == ChainingMode.ecb);
        return CryptoFunctions.getCipher(key, em.getCipherAlgorithm(), cm, null, 2);
    }

    @Override
    public InputStream getDataStream(DirectoryNode dir) throws IOException {
        DocumentInputStream dis = dir.createDocumentInputStream("EncryptedPackage");
        this._length = dis.readLong();
        if (this.getSecretKey() == null) {
            this.verifyPassword(null);
        }
        int blockSize = this.getEncryptionInfo().getHeader().getCipherAlgorithm().blockSize;
        long cipherLen = (this._length / (long)blockSize + 1L) * (long)blockSize;
        Cipher cipher = this.getCipher(this.getSecretKey());
        BoundedInputStream boundedDis = new BoundedInputStream(dis, cipherLen);
        return new BoundedInputStream(new CipherInputStream(boundedDis, cipher), this._length);
    }

    @Override
    public long getLength() {
        if (this._length == -1L) {
            throw new IllegalStateException("Decryptor.getDataStream() was not called");
        }
        return this._length;
    }

    @Override
    public StandardDecryptor copy() {
        return new StandardDecryptor(this);
    }
}

