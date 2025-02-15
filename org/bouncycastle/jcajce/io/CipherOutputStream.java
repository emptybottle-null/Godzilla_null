/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.bouncycastle.crypto.io.InvalidCipherTextIOException;

public class CipherOutputStream
extends FilterOutputStream {
    private final Cipher cipher;
    private final byte[] oneByte = new byte[1];

    public CipherOutputStream(OutputStream outputStream, Cipher cipher) {
        super(outputStream);
        this.cipher = cipher;
    }

    public void write(int n) throws IOException {
        this.oneByte[0] = (byte)n;
        this.write(this.oneByte, 0, 1);
    }

    public void write(byte[] byArray, int n, int n2) throws IOException {
        byte[] byArray2 = this.cipher.update(byArray, n, n2);
        if (byArray2 != null) {
            this.out.write(byArray2);
        }
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void close() throws IOException {
        IOException iOException;
        block7: {
            iOException = null;
            try {
                byte[] byArray = this.cipher.doFinal();
                if (byArray != null) {
                    this.out.write(byArray);
                }
            } catch (GeneralSecurityException generalSecurityException) {
                iOException = new InvalidCipherTextIOException("Error during cipher finalisation", generalSecurityException);
            } catch (Exception exception) {
                iOException = new IOException("Error closing stream: " + exception);
            }
            try {
                this.flush();
                this.out.close();
            } catch (IOException iOException2) {
                if (iOException != null) break block7;
                iOException = iOException2;
            }
        }
        if (iOException != null) {
            throw iOException;
        }
    }
}

