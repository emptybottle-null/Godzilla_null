/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.agile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionHeader;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader;
import org.apache.poi.poifs.crypt.agile.AgileEncryptionVerifier;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.util.LittleEndian;

public class AgileDecryptor
extends Decryptor {
    static final byte[] kVerifierInputBlock = AgileDecryptor.longToBytes(-96877461722390919L);
    static final byte[] kHashedVerifierBlock = AgileDecryptor.longToBytes(-2906493647876705202L);
    static final byte[] kCryptoKeyBlock = AgileDecryptor.longToBytes(1472127217842311382L);
    static final byte[] kIntegrityKeyBlock = AgileDecryptor.longToBytes(6895764199477731830L);
    static final byte[] kIntegrityValueBlock = AgileDecryptor.longToBytes(-6888397455483960269L);
    private long _length = -1L;

    protected AgileDecryptor() {
    }

    protected AgileDecryptor(AgileDecryptor other) {
        super(other);
        this._length = other._length;
    }

    private static byte[] longToBytes(long l) {
        return ByteBuffer.allocate(8).putLong(l).array();
    }

    @Override
    public boolean verifyPassword(String password) throws GeneralSecurityException {
        AgileEncryptionVerifier ver = (AgileEncryptionVerifier)this.getEncryptionInfo().getVerifier();
        AgileEncryptionHeader header = (AgileEncryptionHeader)this.getEncryptionInfo().getHeader();
        int blockSize = header.getBlockSize();
        byte[] pwHash = CryptoFunctions.hashPassword(password, ver.getHashAlgorithm(), ver.getSalt(), ver.getSpinCount());
        byte[] verfierInputEnc = AgileDecryptor.hashInput(ver, pwHash, kVerifierInputBlock, ver.getEncryptedVerifier(), 2);
        this.setVerifier(verfierInputEnc);
        MessageDigest hashMD = CryptoFunctions.getMessageDigest(ver.getHashAlgorithm());
        byte[] verifierHash = hashMD.digest(verfierInputEnc);
        byte[] verifierHashDec = AgileDecryptor.hashInput(ver, pwHash, kHashedVerifierBlock, ver.getEncryptedVerifierHash(), 2);
        verifierHashDec = CryptoFunctions.getBlock0(verifierHashDec, ver.getHashAlgorithm().hashSize);
        byte[] keyspec = AgileDecryptor.hashInput(ver, pwHash, kCryptoKeyBlock, ver.getEncryptedKey(), 2);
        keyspec = CryptoFunctions.getBlock0(keyspec, header.getKeySize() / 8);
        SecretKeySpec secretKey = new SecretKeySpec(keyspec, header.getCipherAlgorithm().jceId);
        byte[] vec = CryptoFunctions.generateIv(header.getHashAlgorithm(), header.getKeySalt(), kIntegrityKeyBlock, blockSize);
        CipherAlgorithm cipherAlgo = header.getCipherAlgorithm();
        Cipher cipher = CryptoFunctions.getCipher(secretKey, cipherAlgo, header.getChainingMode(), vec, 2);
        byte[] hmacKey = cipher.doFinal(header.getEncryptedHmacKey());
        hmacKey = CryptoFunctions.getBlock0(hmacKey, header.getHashAlgorithm().hashSize);
        vec = CryptoFunctions.generateIv(header.getHashAlgorithm(), header.getKeySalt(), kIntegrityValueBlock, blockSize);
        cipher = CryptoFunctions.getCipher(secretKey, cipherAlgo, ver.getChainingMode(), vec, 2);
        byte[] hmacValue = cipher.doFinal(header.getEncryptedHmacValue());
        hmacValue = CryptoFunctions.getBlock0(hmacValue, header.getHashAlgorithm().hashSize);
        if (Arrays.equals(verifierHashDec, verifierHash)) {
            this.setSecretKey(secretKey);
            this.setIntegrityHmacKey(hmacKey);
            this.setIntegrityHmacValue(hmacValue);
            return true;
        }
        return false;
    }

    public boolean verifyPassword(KeyPair keyPair, X509Certificate x509) throws GeneralSecurityException {
        AgileEncryptionVerifier ver = (AgileEncryptionVerifier)this.getEncryptionInfo().getVerifier();
        AgileEncryptionHeader header = (AgileEncryptionHeader)this.getEncryptionInfo().getHeader();
        HashAlgorithm hashAlgo = header.getHashAlgorithm();
        CipherAlgorithm cipherAlgo = header.getCipherAlgorithm();
        int blockSize = header.getBlockSize();
        AgileEncryptionVerifier.AgileCertificateEntry ace = null;
        for (AgileEncryptionVerifier.AgileCertificateEntry aceEntry : ver.getCertificates()) {
            if (!x509.equals(aceEntry.x509)) continue;
            ace = aceEntry;
            break;
        }
        if (ace == null) {
            return false;
        }
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, keyPair.getPrivate());
        byte[] keyspec = cipher.doFinal(ace.encryptedKey);
        SecretKeySpec secretKey = new SecretKeySpec(keyspec, ver.getCipherAlgorithm().jceId);
        Mac x509Hmac = CryptoFunctions.getMac(hashAlgo);
        x509Hmac.init(secretKey);
        byte[] certVerifier = x509Hmac.doFinal(ace.x509.getEncoded());
        byte[] vec = CryptoFunctions.generateIv(hashAlgo, header.getKeySalt(), kIntegrityKeyBlock, blockSize);
        cipher = CryptoFunctions.getCipher(secretKey, cipherAlgo, header.getChainingMode(), vec, 2);
        byte[] hmacKey = cipher.doFinal(header.getEncryptedHmacKey());
        hmacKey = CryptoFunctions.getBlock0(hmacKey, hashAlgo.hashSize);
        vec = CryptoFunctions.generateIv(hashAlgo, header.getKeySalt(), kIntegrityValueBlock, blockSize);
        cipher = CryptoFunctions.getCipher(secretKey, cipherAlgo, header.getChainingMode(), vec, 2);
        byte[] hmacValue = cipher.doFinal(header.getEncryptedHmacValue());
        hmacValue = CryptoFunctions.getBlock0(hmacValue, hashAlgo.hashSize);
        if (Arrays.equals(ace.certVerifier, certVerifier)) {
            this.setSecretKey(secretKey);
            this.setIntegrityHmacKey(hmacKey);
            this.setIntegrityHmacValue(hmacValue);
            return true;
        }
        return false;
    }

    protected static int getNextBlockSize(int inputLen, int blockSize) {
        int fillSize;
        for (fillSize = blockSize; fillSize < inputLen; fillSize += blockSize) {
        }
        return fillSize;
    }

    static byte[] hashInput(AgileEncryptionVerifier ver, byte[] pwHash, byte[] blockKey, byte[] inputKey, int cipherMode) {
        CipherAlgorithm cipherAlgo = ver.getCipherAlgorithm();
        ChainingMode chainMode = ver.getChainingMode();
        int keySize = ver.getKeySize() / 8;
        int blockSize = ver.getBlockSize();
        HashAlgorithm hashAlgo = ver.getHashAlgorithm();
        byte[] intermedKey = CryptoFunctions.generateKey(pwHash, hashAlgo, blockKey, keySize);
        SecretKeySpec skey = new SecretKeySpec(intermedKey, cipherAlgo.jceId);
        byte[] iv = CryptoFunctions.generateIv(hashAlgo, ver.getSalt(), null, blockSize);
        Cipher cipher = CryptoFunctions.getCipher(skey, cipherAlgo, chainMode, iv, cipherMode);
        try {
            inputKey = CryptoFunctions.getBlock0(inputKey, AgileDecryptor.getNextBlockSize(inputKey.length, blockSize));
            byte[] hashFinal = cipher.doFinal(inputKey);
            return hashFinal;
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    @Override
    public InputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        DocumentInputStream dis = dir.createDocumentInputStream("EncryptedPackage");
        this._length = dis.readLong();
        return new AgileCipherInputStream(dis, this._length);
    }

    @Override
    public long getLength() {
        if (this._length == -1L) {
            throw new IllegalStateException("EcmaDecryptor.getDataStream() was not called");
        }
        return this._length;
    }

    protected static Cipher initCipherForBlock(Cipher existing, int block, boolean lastChunk, EncryptionInfo encryptionInfo, SecretKey skey, int encryptionMode) throws GeneralSecurityException {
        String padding;
        EncryptionHeader header = encryptionInfo.getHeader();
        String string = padding = lastChunk ? "PKCS5Padding" : "NoPadding";
        if (existing == null || !existing.getAlgorithm().endsWith(padding)) {
            existing = CryptoFunctions.getCipher(skey, header.getCipherAlgorithm(), header.getChainingMode(), header.getKeySalt(), encryptionMode, padding);
        }
        byte[] blockKey = new byte[4];
        LittleEndian.putInt(blockKey, 0, block);
        byte[] iv = CryptoFunctions.generateIv(header.getHashAlgorithm(), header.getKeySalt(), blockKey, header.getBlockSize());
        AlgorithmParameterSpec aps = header.getCipherAlgorithm() == CipherAlgorithm.rc2 ? new RC2ParameterSpec(skey.getEncoded().length * 8, iv) : new IvParameterSpec(iv);
        existing.init(encryptionMode, (Key)skey, aps);
        return existing;
    }

    @Override
    public AgileDecryptor copy() {
        return new AgileDecryptor(this);
    }

    private class AgileCipherInputStream
    extends ChunkedCipherInputStream {
        public AgileCipherInputStream(DocumentInputStream stream, long size) throws GeneralSecurityException {
            super(stream, size, 4096);
        }

        @Override
        protected Cipher initCipherForBlock(Cipher cipher, int block) throws GeneralSecurityException {
            return AgileDecryptor.initCipherForBlock(cipher, block, false, AgileDecryptor.this.getEncryptionInfo(), AgileDecryptor.this.getSecretKey(), 2);
        }
    }
}

