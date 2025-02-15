/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.cryptoapi;

import java.io.ByteArrayInputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.cryptoapi.CryptoAPIDecryptor;
import org.apache.poi.util.Internal;

@Internal
class CryptoAPIDocumentInputStream
extends ByteArrayInputStream {
    private Cipher cipher;
    private final CryptoAPIDecryptor decryptor;
    private byte[] oneByte = new byte[]{0};

    public void seek(int newpos) {
        if (newpos > this.count) {
            throw new ArrayIndexOutOfBoundsException(newpos);
        }
        this.pos = newpos;
        this.mark = newpos;
    }

    public void setBlock(int block) throws GeneralSecurityException {
        this.cipher = this.decryptor.initCipherForBlock(this.cipher, block);
    }

    @Override
    public synchronized int read() {
        int ch = super.read();
        if (ch == -1) {
            return -1;
        }
        this.oneByte[0] = (byte)ch;
        try {
            this.cipher.update(this.oneByte, 0, 1, this.oneByte);
        } catch (ShortBufferException e) {
            throw new EncryptedDocumentException(e);
        }
        return this.oneByte[0] & 0xFF;
    }

    @Override
    public synchronized int read(byte[] b, int off, int len) {
        int readLen = super.read(b, off, len);
        if (readLen == -1) {
            return -1;
        }
        try {
            this.cipher.update(b, off, readLen, b, off);
        } catch (ShortBufferException e) {
            throw new EncryptedDocumentException(e);
        }
        return readLen;
    }

    public CryptoAPIDocumentInputStream(CryptoAPIDecryptor decryptor, byte[] buf) throws GeneralSecurityException {
        super(buf);
        this.decryptor = decryptor;
        this.cipher = decryptor.initCipherForBlock(null, 0);
    }
}

