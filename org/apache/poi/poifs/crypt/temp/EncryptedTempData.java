/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.temp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.TempFile;

public class EncryptedTempData {
    private static POILogger LOG = POILogFactory.getLogger(EncryptedTempData.class);
    private static final CipherAlgorithm cipherAlgorithm = CipherAlgorithm.aes128;
    private static final String PADDING = "PKCS5Padding";
    private final SecretKeySpec skeySpec;
    private final byte[] ivBytes;
    private final File tempFile;

    public EncryptedTempData() throws IOException {
        SecureRandom sr = new SecureRandom();
        this.ivBytes = new byte[16];
        byte[] keyBytes = new byte[16];
        sr.nextBytes(this.ivBytes);
        sr.nextBytes(keyBytes);
        this.skeySpec = new SecretKeySpec(keyBytes, EncryptedTempData.cipherAlgorithm.jceId);
        this.tempFile = TempFile.createTempFile("poi-temp-data", ".tmp");
    }

    public OutputStream getOutputStream() throws IOException {
        Cipher ciEnc = CryptoFunctions.getCipher(this.skeySpec, cipherAlgorithm, ChainingMode.cbc, this.ivBytes, 1, PADDING);
        return new CipherOutputStream(new FileOutputStream(this.tempFile), ciEnc);
    }

    public InputStream getInputStream() throws IOException {
        Cipher ciDec = CryptoFunctions.getCipher(this.skeySpec, cipherAlgorithm, ChainingMode.cbc, this.ivBytes, 2, PADDING);
        return new CipherInputStream(new FileInputStream(this.tempFile), ciDec);
    }

    public void dispose() {
        if (!this.tempFile.delete()) {
            LOG.log(5, this.tempFile.getAbsolutePath() + " can't be removed (or was already removed.");
        }
    }
}

