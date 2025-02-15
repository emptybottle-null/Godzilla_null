/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.PasswordRequiredException;
import org.apache.commons.compress.archivers.sevenz.Coder;
import org.apache.commons.compress.archivers.sevenz.CoderBase;

class AES256SHA256Decoder
extends CoderBase {
    AES256SHA256Decoder() {
        super(new Class[0]);
    }

    @Override
    InputStream decode(final String archiveName, final InputStream in, long uncompressedLength, final Coder coder, final byte[] passwordBytes, int maxMemoryLimitInKb) throws IOException {
        return new InputStream(){
            private boolean isInitialized = false;
            private CipherInputStream cipherInputStream = null;

            private CipherInputStream init() throws IOException {
                byte[] aesKeyBytes;
                if (this.isInitialized) {
                    return this.cipherInputStream;
                }
                if (coder.properties == null) {
                    throw new IOException("Missing AES256 properties in " + archiveName);
                }
                if (coder.properties.length < 2) {
                    throw new IOException("AES256 properties too short in " + archiveName);
                }
                int byte0 = 0xFF & coder.properties[0];
                int numCyclesPower = byte0 & 0x3F;
                int byte1 = 0xFF & coder.properties[1];
                int saltSize = (byte0 >> 7 & 1) + (byte1 >> 4);
                int ivSize = (byte0 >> 6 & 1) + (byte1 & 0xF);
                if (2 + saltSize + ivSize > coder.properties.length) {
                    throw new IOException("Salt size + IV size too long in " + archiveName);
                }
                byte[] salt = new byte[saltSize];
                System.arraycopy(coder.properties, 2, salt, 0, saltSize);
                byte[] iv = new byte[16];
                System.arraycopy(coder.properties, 2 + saltSize, iv, 0, ivSize);
                if (passwordBytes == null) {
                    throw new PasswordRequiredException(archiveName);
                }
                if (numCyclesPower == 63) {
                    aesKeyBytes = new byte[32];
                    System.arraycopy(salt, 0, aesKeyBytes, 0, saltSize);
                    System.arraycopy(passwordBytes, 0, aesKeyBytes, saltSize, Math.min(passwordBytes.length, aesKeyBytes.length - saltSize));
                } else {
                    MessageDigest digest;
                    try {
                        digest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                        throw new IOException("SHA-256 is unsupported by your Java implementation", noSuchAlgorithmException);
                    }
                    byte[] extra = new byte[8];
                    block4: for (long j = 0L; j < 1L << numCyclesPower; ++j) {
                        digest.update(salt);
                        digest.update(passwordBytes);
                        digest.update(extra);
                        for (int k = 0; k < extra.length; ++k) {
                            int n = k;
                            extra[n] = (byte)(extra[n] + 1);
                            if (extra[k] != 0) continue block4;
                        }
                    }
                    aesKeyBytes = digest.digest();
                }
                SecretKeySpec aesKey = new SecretKeySpec(aesKeyBytes, "AES");
                try {
                    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                    cipher.init(2, (Key)aesKey, new IvParameterSpec(iv));
                    this.cipherInputStream = new CipherInputStream(in, cipher);
                    this.isInitialized = true;
                    return this.cipherInputStream;
                } catch (GeneralSecurityException generalSecurityException) {
                    throw new IOException("Decryption error (do you have the JCE Unlimited Strength Jurisdiction Policy Files installed?)", generalSecurityException);
                }
            }

            @Override
            public int read() throws IOException {
                return this.init().read();
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                return this.init().read(b, off, len);
            }

            @Override
            public void close() throws IOException {
                if (this.cipherInputStream != null) {
                    this.cipherInputStream.close();
                }
            }
        };
    }
}

