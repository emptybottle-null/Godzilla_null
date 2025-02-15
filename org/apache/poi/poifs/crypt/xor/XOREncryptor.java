/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.xor;

import com.zaxxer.sparsebits.SparseBitSet;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.xor.XORDecryptor;
import org.apache.poi.poifs.crypt.xor.XOREncryptionVerifier;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.LittleEndian;

public class XOREncryptor
extends Encryptor {
    protected XOREncryptor() {
    }

    protected XOREncryptor(XOREncryptor other) {
        super(other);
    }

    @Override
    public void confirmPassword(String password) {
        int keyComp = CryptoFunctions.createXorKey1(password);
        int verifierComp = CryptoFunctions.createXorVerifier1(password);
        byte[] xorArray = CryptoFunctions.createXorArray1(password);
        byte[] shortBuf = new byte[2];
        XOREncryptionVerifier ver = (XOREncryptionVerifier)this.getEncryptionInfo().getVerifier();
        LittleEndian.putUShort(shortBuf, 0, keyComp);
        ver.setEncryptedKey(shortBuf);
        LittleEndian.putUShort(shortBuf, 0, verifierComp);
        ver.setEncryptedVerifier(shortBuf);
        this.setSecretKey(new SecretKeySpec(xorArray, "XOR"));
    }

    @Override
    public void confirmPassword(String password, byte[] keySpec, byte[] keySalt, byte[] verifier, byte[] verifierSalt, byte[] integritySalt) {
        this.confirmPassword(password);
    }

    @Override
    public OutputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        return new XORCipherOutputStream(dir);
    }

    @Override
    public XORCipherOutputStream getDataStream(OutputStream stream, int initialOffset) throws IOException, GeneralSecurityException {
        return new XORCipherOutputStream(stream, initialOffset);
    }

    protected int getKeySizeInBytes() {
        return -1;
    }

    @Override
    public void setChunkSize(int chunkSize) {
    }

    @Override
    public XOREncryptor copy() {
        return new XOREncryptor(this);
    }

    private class XORCipherOutputStream
    extends ChunkedCipherOutputStream {
        private int recordStart;
        private int recordEnd;

        public XORCipherOutputStream(OutputStream stream, int initialPos) throws IOException, GeneralSecurityException {
            super(stream, -1);
        }

        public XORCipherOutputStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
            super(dir, -1);
        }

        @Override
        protected Cipher initCipherForBlock(Cipher cipher, int block, boolean lastChunk) throws GeneralSecurityException {
            return XORDecryptor.initCipherForBlock(cipher, block, XOREncryptor.this.getEncryptionInfo(), XOREncryptor.this.getSecretKey(), 1);
        }

        @Override
        protected void calculateChecksum(File file, int i) {
        }

        @Override
        protected void createEncryptionInfoEntry(DirectoryNode dir, File tmpFile) {
            throw new EncryptedDocumentException("createEncryptionInfoEntry not supported");
        }

        @Override
        public void setNextRecordSize(int recordSize, boolean isPlain) {
            if (this.recordEnd > 0 && !isPlain) {
                this.invokeCipher((int)this.getPos(), true);
            }
            this.recordStart = (int)this.getTotalPos() + 4;
            this.recordEnd = this.recordStart + recordSize;
        }

        @Override
        public void flush() throws IOException {
            this.setNextRecordSize(0, true);
            super.flush();
        }

        @Override
        protected int invokeCipher(int posInChunk, boolean doFinal) {
            int i;
            if (posInChunk == 0) {
                return 0;
            }
            int start = Math.max(posInChunk - (this.recordEnd - this.recordStart), 0);
            SparseBitSet plainBytes = this.getPlainByteFlags();
            byte[] xorArray = XOREncryptor.this.getEncryptionInfo().getEncryptor().getSecretKey().getEncoded();
            byte[] chunk = this.getChunk();
            byte[] plain = plainBytes.isEmpty() ? null : (byte[])chunk.clone();
            int xorArrayIndex = this.recordEnd + (start - this.recordStart);
            for (i = start; i < posInChunk; ++i) {
                byte value = chunk[i];
                value = (byte)(value ^ xorArray[xorArrayIndex++ & 0xF]);
                chunk[i] = value = this.rotateLeft(value, 5);
            }
            if (plain != null) {
                i = plainBytes.nextSetBit(start);
                while (i >= 0 && i < posInChunk) {
                    chunk[i] = plain[i];
                    i = plainBytes.nextSetBit(i + 1);
                }
            }
            return posInChunk;
        }

        private byte rotateLeft(byte bits, int shift) {
            return (byte)((bits & 0xFF) << shift | (bits & 0xFF) >>> 8 - shift);
        }
    }
}

