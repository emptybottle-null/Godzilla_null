/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.xor;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherInputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.xor.XOREncryptionVerifier;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.LittleEndian;

public class XORDecryptor
extends Decryptor {
    private long length = -1L;
    private int chunkSize = 512;

    protected XORDecryptor() {
    }

    protected XORDecryptor(XORDecryptor other) {
        super(other);
        this.length = other.length;
        this.chunkSize = other.chunkSize;
    }

    @Override
    public boolean verifyPassword(String password) {
        XOREncryptionVerifier ver = (XOREncryptionVerifier)this.getEncryptionInfo().getVerifier();
        int keyVer = LittleEndian.getUShort(ver.getEncryptedKey());
        int verifierVer = LittleEndian.getUShort(ver.getEncryptedVerifier());
        int keyComp = CryptoFunctions.createXorKey1(password);
        int verifierComp = CryptoFunctions.createXorVerifier1(password);
        if (keyVer == keyComp && verifierVer == verifierComp) {
            byte[] xorArray = CryptoFunctions.createXorArray1(password);
            this.setSecretKey(new SecretKeySpec(xorArray, "XOR"));
            return true;
        }
        return false;
    }

    @Override
    public Cipher initCipherForBlock(Cipher cipher, int block) throws GeneralSecurityException {
        return null;
    }

    protected static Cipher initCipherForBlock(Cipher cipher, int block, EncryptionInfo encryptionInfo, SecretKey skey, int encryptMode) throws GeneralSecurityException {
        return null;
    }

    @Override
    public ChunkedCipherInputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        throw new EncryptedDocumentException("not supported");
    }

    @Override
    public InputStream getDataStream(InputStream stream, int size, int initialPos) throws IOException, GeneralSecurityException {
        return new XORCipherInputStream(stream, initialPos);
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
    public XORDecryptor copy() {
        return new XORDecryptor(this);
    }

    private class XORCipherInputStream
    extends ChunkedCipherInputStream {
        private final int initialOffset;
        private int recordStart;
        private int recordEnd;

        public XORCipherInputStream(InputStream stream, int initialPos) throws GeneralSecurityException {
            super(stream, Integer.MAX_VALUE, XORDecryptor.this.chunkSize);
            this.initialOffset = initialPos;
        }

        @Override
        protected Cipher initCipherForBlock(Cipher existing, int block) throws GeneralSecurityException {
            return XORDecryptor.this.initCipherForBlock(existing, block);
        }

        @Override
        protected int invokeCipher(int totalBytes, boolean doFinal) {
            int pos = (int)this.getPos();
            byte[] xorArray = XORDecryptor.this.getEncryptionInfo().getDecryptor().getSecretKey().getEncoded();
            byte[] chunk = this.getChunk();
            byte[] plain = this.getPlain();
            int posInChunk = pos & this.getChunkMask();
            int xorArrayIndex = this.initialOffset + this.recordEnd + (pos - this.recordStart);
            for (int i = 0; pos + i < this.recordEnd && i < totalBytes; ++i) {
                byte value = plain[posInChunk + i];
                value = this.rotateLeft(value, 3);
                chunk[posInChunk + i] = value = (byte)(value ^ xorArray[xorArrayIndex + i & 0xF]);
            }
            return totalBytes;
        }

        private byte rotateLeft(byte bits, int shift) {
            return (byte)((bits & 0xFF) << shift | (bits & 0xFF) >>> 8 - shift);
        }

        @Override
        public void setNextRecordSize(int recordSize) {
            int pos = (int)this.getPos();
            byte[] chunk = this.getChunk();
            int chunkMask = this.getChunkMask();
            this.recordStart = pos;
            this.recordEnd = this.recordStart + recordSize;
            int nextBytes = Math.min(recordSize, chunk.length - (pos & chunkMask));
            this.invokeCipher(nextBytes, true);
        }
    }
}

