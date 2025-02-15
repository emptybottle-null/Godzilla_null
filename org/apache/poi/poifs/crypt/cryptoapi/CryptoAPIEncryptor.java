/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDocumentOutputStream;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIEncryptionVerifier;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.StringUtil;

public class CryptoAPIEncryptor
extends Encryptor {
    private int chunkSize = 512;

    CryptoAPIEncryptor() {
    }

    CryptoAPIEncryptor(CryptoAPIEncryptor other) {
        super(other);
        this.chunkSize = other.chunkSize;
    }

    @Override
    public void confirmPassword(String password) {
        SecureRandom r = new SecureRandom();
        byte[] salt = new byte[16];
        byte[] verifier = new byte[16];
        ((Random)r).nextBytes(salt);
        ((Random)r).nextBytes(verifier);
        this.confirmPassword(password, null, null, verifier, salt, null);
    }

    @Override
    public void confirmPassword(String password, byte[] keySpec, byte[] keySalt, byte[] verifier, byte[] verifierSalt, byte[] integritySalt) {
        assert (verifier != null && verifierSalt != null);
        CryptoAPIEncryptionVerifier ver = (CryptoAPIEncryptionVerifier)this.getEncryptionInfo().getVerifier();
        ver.setSalt(verifierSalt);
        SecretKey skey = CryptoAPIDecryptor.generateSecretKey(password, ver);
        this.setSecretKey(skey);
        try {
            Cipher cipher = this.initCipherForBlock(null, 0);
            byte[] encryptedVerifier = new byte[verifier.length];
            cipher.update(verifier, 0, verifier.length, encryptedVerifier);
            ver.setEncryptedVerifier(encryptedVerifier);
            HashAlgorithm hashAlgo = ver.getHashAlgorithm();
            MessageDigest hashAlg = CryptoFunctions.getMessageDigest(hashAlgo);
            byte[] calcVerifierHash = hashAlg.digest(verifier);
            byte[] encryptedVerifierHash = cipher.doFinal(calcVerifierHash);
            ver.setEncryptedVerifierHash(encryptedVerifierHash);
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("Password confirmation failed", e);
        }
    }

    public Cipher initCipherForBlock(Cipher cipher, int block) throws GeneralSecurityException {
        return CryptoAPIDecryptor.initCipherForBlock(cipher, block, this.getEncryptionInfo(), this.getSecretKey(), 1);
    }

    @Override
    public ChunkedCipherOutputStream getDataStream(DirectoryNode dir) throws IOException {
        throw new IOException("not supported");
    }

    @Override
    public CryptoAPICipherOutputStream getDataStream(OutputStream stream, int initialOffset) throws IOException, GeneralSecurityException {
        return new CryptoAPICipherOutputStream(stream);
    }

    public void setSummaryEntries(DirectoryNode dir, String encryptedStream, POIFSFileSystem entries) throws IOException, GeneralSecurityException {
        CryptoAPIDocumentOutputStream bos = new CryptoAPIDocumentOutputStream(this);
        byte[] buf = new byte[8];
        bos.write(buf, 0, 8);
        ArrayList<CryptoAPIDecryptor.StreamDescriptorEntry> descList = new ArrayList<CryptoAPIDecryptor.StreamDescriptorEntry>();
        int block = 0;
        for (Object entry : entries.getRoot()) {
            if (entry.isDirectoryEntry()) continue;
            CryptoAPIDecryptor.StreamDescriptorEntry descEntry = new CryptoAPIDecryptor.StreamDescriptorEntry();
            descEntry.block = block;
            descEntry.streamOffset = bos.size();
            descEntry.streamName = entry.getName();
            descEntry.flags = CryptoAPIDecryptor.StreamDescriptorEntry.flagStream.setValue(0, 1);
            descEntry.reserved2 = 0;
            bos.setBlock(block);
            DocumentInputStream dis = dir.createDocumentInputStream((Entry)entry);
            IOUtils.copy((InputStream)dis, bos);
            dis.close();
            descEntry.streamSize = bos.size() - descEntry.streamOffset;
            descList.add(descEntry);
            ++block;
        }
        int streamDescriptorArrayOffset = bos.size();
        bos.setBlock(0);
        LittleEndian.putUInt(buf, 0, descList.size());
        bos.write(buf, 0, 4);
        for (CryptoAPIDecryptor.StreamDescriptorEntry sde : descList) {
            LittleEndian.putUInt(buf, 0, sde.streamOffset);
            bos.write(buf, 0, 4);
            LittleEndian.putUInt(buf, 0, sde.streamSize);
            bos.write(buf, 0, 4);
            LittleEndian.putUShort(buf, 0, sde.block);
            bos.write(buf, 0, 2);
            LittleEndian.putUByte(buf, 0, (short)sde.streamName.length());
            bos.write(buf, 0, 1);
            LittleEndian.putUByte(buf, 0, (short)sde.flags);
            bos.write(buf, 0, 1);
            LittleEndian.putUInt(buf, 0, sde.reserved2);
            bos.write(buf, 0, 4);
            byte[] nameBytes = StringUtil.getToUnicodeLE(sde.streamName);
            bos.write(nameBytes, 0, nameBytes.length);
            LittleEndian.putShort(buf, 0, (short)0);
            bos.write(buf, 0, 2);
        }
        int savedSize = bos.size();
        int streamDescriptorArraySize = savedSize - streamDescriptorArrayOffset;
        LittleEndian.putUInt(buf, 0, streamDescriptorArrayOffset);
        LittleEndian.putUInt(buf, 4, streamDescriptorArraySize);
        bos.reset();
        bos.setBlock(0);
        bos.write(buf, 0, 8);
        bos.setSize(savedSize);
        dir.createDocument(encryptedStream, new ByteArrayInputStream(bos.getBuf(), 0, savedSize));
    }

    @Override
    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override
    public CryptoAPIEncryptor copy() {
        return new CryptoAPIEncryptor(this);
    }

    protected class CryptoAPICipherOutputStream
    extends ChunkedCipherOutputStream {
        @Override
        protected Cipher initCipherForBlock(Cipher cipher, int block, boolean lastChunk) throws IOException, GeneralSecurityException {
            this.flush();
            return this.initCipherForBlockNoFlush(cipher, block, lastChunk);
        }

        @Override
        protected Cipher initCipherForBlockNoFlush(Cipher existing, int block, boolean lastChunk) throws GeneralSecurityException {
            EncryptionInfo ei = CryptoAPIEncryptor.this.getEncryptionInfo();
            SecretKey sk = CryptoAPIEncryptor.this.getSecretKey();
            return CryptoAPIDecryptor.initCipherForBlock(existing, block, ei, sk, 1);
        }

        @Override
        protected void calculateChecksum(File file, int i) {
        }

        @Override
        protected void createEncryptionInfoEntry(DirectoryNode dir, File tmpFile) {
            throw new EncryptedDocumentException("createEncryptionInfoEntry not supported");
        }

        CryptoAPICipherOutputStream(OutputStream stream) throws IOException, GeneralSecurityException {
            super(stream, CryptoAPIEncryptor.this.chunkSize);
        }

        @Override
        public void flush() throws IOException {
            this.writeChunk(false);
            super.flush();
        }
    }
}

