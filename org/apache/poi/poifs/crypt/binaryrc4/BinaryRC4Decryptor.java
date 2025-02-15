/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.binaryrc4;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.StringUtil;

public class BinaryRC4Decryptor
extends Decryptor {
    private long length = -1L;
    private int chunkSize = 512;

    protected BinaryRC4Decryptor() {
    }

    protected BinaryRC4Decryptor(BinaryRC4Decryptor other) {
        super(other);
        this.length = other.length;
        this.chunkSize = other.chunkSize;
    }

    @Override
    public boolean verifyPassword(String password) {
        EncryptionVerifier ver = this.getEncryptionInfo().getVerifier();
        SecretKey skey = BinaryRC4Decryptor.generateSecretKey(password, ver);
        try {
            Cipher cipher = BinaryRC4Decryptor.initCipherForBlock(null, 0, this.getEncryptionInfo(), skey, 2);
            byte[] encryptedVerifier = ver.getEncryptedVerifier();
            byte[] verifier = new byte[encryptedVerifier.length];
            cipher.update(encryptedVerifier, 0, encryptedVerifier.length, verifier);
            this.setVerifier(verifier);
            byte[] encryptedVerifierHash = ver.getEncryptedVerifierHash();
            byte[] verifierHash = cipher.doFinal(encryptedVerifierHash);
            HashAlgorithm hashAlgo = ver.getHashAlgorithm();
            MessageDigest hashAlg = CryptoFunctions.getMessageDigest(hashAlgo);
            byte[] calcVerifierHash = hashAlg.digest(verifier);
            if (Arrays.equals(calcVerifierHash, verifierHash)) {
                this.setSecretKey(skey);
                return true;
            }
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException(e);
        }
        return false;
    }

    @Override
    public Cipher initCipherForBlock(Cipher cipher, int block) throws GeneralSecurityException {
        return BinaryRC4Decryptor.initCipherForBlock(cipher, block, this.getEncryptionInfo(), this.getSecretKey(), 2);
    }

    protected static Cipher initCipherForBlock(Cipher cipher, int block, EncryptionInfo encryptionInfo, SecretKey skey, int encryptMode) throws GeneralSecurityException {
        EncryptionVerifier ver = encryptionInfo.getVerifier();
        HashAlgorithm hashAlgo = ver.getHashAlgorithm();
        byte[] blockKey = new byte[4];
        LittleEndian.putUInt(blockKey, 0, block);
        byte[] encKey = CryptoFunctions.generateKey(skey.getEncoded(), hashAlgo, blockKey, 16);
        SecretKeySpec key = new SecretKeySpec(encKey, skey.getAlgorithm());
        if (cipher == null) {
            EncryptionHeader em = encryptionInfo.getHeader();
            cipher = CryptoFunctions.getCipher(key, em.getCipherAlgorithm(), null, null, encryptMode);
        } else {
            cipher.init(encryptMode, key);
        }
        return cipher;
    }

    protected static SecretKey generateSecretKey(String password, EncryptionVerifier ver) {
        if (password.length() > 255) {
            password = password.substring(0, 255);
        }
        HashAlgorithm hashAlgo = ver.getHashAlgorithm();
        MessageDigest hashAlg = CryptoFunctions.getMessageDigest(hashAlgo);
        byte[] hash = hashAlg.digest(StringUtil.getToUnicodeLE(password));
        byte[] salt = ver.getSalt();
        hashAlg.reset();
        for (int i = 0; i < 16; ++i) {
            hashAlg.update(hash, 0, 5);
            hashAlg.update(salt);
        }
        hash = new byte[5];
        System.arraycopy(hashAlg.digest(), 0, hash, 0, 5);
        return new SecretKeySpec(hash, ver.getCipherAlgorithm().jceId);
    }

    @Override
    public ChunkedCipherInputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        DocumentInputStream dis = dir.createDocumentInputStream("EncryptedPackage");
        this.length = dis.readLong();
        return new BinaryRC4CipherInputStream(dis, this.length);
    }

    @Override
    public InputStream getDataStream(InputStream stream, int size, int initialPos) throws IOException, GeneralSecurityException {
        return new BinaryRC4CipherInputStream(stream, size, initialPos);
    }

    @Override
    public long getLength() {
        if (this.length == -1L) {
            throw new IllegalStateException("Decryptor.getDataStream() was not called");
        }
        return this.length;
    }

    @Override
    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override
    public BinaryRC4Decryptor copy() {
        return new BinaryRC4Decryptor(this);
    }

    private class BinaryRC4CipherInputStream
    extends ChunkedCipherInputStream {
        @Override
        protected Cipher initCipherForBlock(Cipher existing, int block) throws GeneralSecurityException {
            return BinaryRC4Decryptor.this.initCipherForBlock(existing, block);
        }

        public BinaryRC4CipherInputStream(DocumentInputStream stream, long size) throws GeneralSecurityException {
            super(stream, size, BinaryRC4Decryptor.this.chunkSize);
        }

        public BinaryRC4CipherInputStream(InputStream stream, int size, int initialPos) throws GeneralSecurityException {
            super(stream, size, BinaryRC4Decryptor.this.chunkSize, initialPos);
        }
    }
}

