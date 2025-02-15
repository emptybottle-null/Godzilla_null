/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.standard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.DataSpaceMapUtils;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.poifs.crypt.standard.StandardDecryptor;
import org.apache.poi.poifs.crypt.standard.StandardEncryptionHeader;
import org.apache.poi.poifs.crypt.standard.StandardEncryptionVerifier;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.POIFSWriterEvent;
import org.apache.poi.poifs.filesystem.POIFSWriterListener;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianOutputStream;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.TempFile;

public class StandardEncryptor
extends Encryptor {
    private static final POILogger logger = POILogFactory.getLogger(StandardEncryptor.class);

    protected StandardEncryptor() {
    }

    protected StandardEncryptor(StandardEncryptor other) {
        super(other);
    }

    @Override
    public void confirmPassword(String password) {
        SecureRandom r = new SecureRandom();
        byte[] salt = new byte[16];
        byte[] verifier = new byte[16];
        ((Random)r).nextBytes(salt);
        ((Random)r).nextBytes(verifier);
        this.confirmPassword(password, null, null, salt, verifier, null);
    }

    @Override
    public void confirmPassword(String password, byte[] keySpec, byte[] keySalt, byte[] verifier, byte[] verifierSalt, byte[] integritySalt) {
        StandardEncryptionVerifier ver = (StandardEncryptionVerifier)this.getEncryptionInfo().getVerifier();
        ver.setSalt(verifierSalt);
        SecretKey secretKey = StandardDecryptor.generateSecretKey(password, ver, this.getKeySizeInBytes());
        this.setSecretKey(secretKey);
        Cipher cipher = this.getCipher(secretKey, null);
        try {
            byte[] encryptedVerifier = cipher.doFinal(verifier);
            MessageDigest hashAlgo = CryptoFunctions.getMessageDigest(ver.getHashAlgorithm());
            byte[] calcVerifierHash = hashAlgo.digest(verifier);
            int encVerHashSize = ver.getCipherAlgorithm().encryptedVerifierHashLength;
            byte[] encryptedVerifierHash = cipher.doFinal(Arrays.copyOf(calcVerifierHash, encVerHashSize));
            ver.setEncryptedVerifier(encryptedVerifier);
            ver.setEncryptedVerifierHash(encryptedVerifierHash);
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("Password confirmation failed", e);
        }
    }

    private Cipher getCipher(SecretKey key, String padding) {
        EncryptionVerifier ver = this.getEncryptionInfo().getVerifier();
        return CryptoFunctions.getCipher(key, ver.getCipherAlgorithm(), ver.getChainingMode(), null, 1, padding);
    }

    @Override
    public OutputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        this.createEncryptionInfoEntry(dir);
        DataSpaceMapUtils.addDefaultDataSpace(dir);
        return new StandardCipherOutputStream(dir);
    }

    protected int getKeySizeInBytes() {
        return this.getEncryptionInfo().getHeader().getKeySize() / 8;
    }

    protected void createEncryptionInfoEntry(DirectoryNode dir) throws IOException {
        final EncryptionInfo info = this.getEncryptionInfo();
        final StandardEncryptionHeader header = (StandardEncryptionHeader)info.getHeader();
        final StandardEncryptionVerifier verifier = (StandardEncryptionVerifier)info.getVerifier();
        EncryptionRecord er = new EncryptionRecord(){

            @Override
            public void write(LittleEndianByteArrayOutputStream bos) {
                bos.writeShort(info.getVersionMajor());
                bos.writeShort(info.getVersionMinor());
                bos.writeInt(info.getEncryptionFlags());
                header.write(bos);
                verifier.write(bos);
            }
        };
        DataSpaceMapUtils.createEncryptionEntry(dir, "EncryptionInfo", er);
    }

    @Override
    public StandardEncryptor copy() {
        return new StandardEncryptor(this);
    }

    protected class StandardCipherOutputStream
    extends FilterOutputStream
    implements POIFSWriterListener {
        protected long countBytes;
        protected final File fileOut;
        protected final DirectoryNode dir;

        private StandardCipherOutputStream(DirectoryNode dir, File fileOut) throws IOException {
            super(new CipherOutputStream(new FileOutputStream(fileOut), StandardEncryptor.this.getCipher(StandardEncryptor.this.getSecretKey(), "PKCS5Padding")));
            this.fileOut = fileOut;
            this.dir = dir;
        }

        protected StandardCipherOutputStream(DirectoryNode dir) throws IOException {
            this(dir, TempFile.createTempFile("encrypted_package", "crypt"));
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            this.out.write(b, off, len);
            this.countBytes += (long)len;
        }

        @Override
        public void write(int b) throws IOException {
            this.out.write(b);
            ++this.countBytes;
        }

        @Override
        public void close() throws IOException {
            super.close();
            this.writeToPOIFS();
        }

        void writeToPOIFS() throws IOException {
            int oleStreamSize = (int)(this.fileOut.length() + 8L);
            this.dir.createDocument("EncryptedPackage", oleStreamSize, this);
        }

        @Override
        public void processPOIFSWriterEvent(POIFSWriterEvent event) {
            try {
                LittleEndianOutputStream leos = new LittleEndianOutputStream(event.getStream());
                leos.writeLong(this.countBytes);
                try (FileInputStream fis = new FileInputStream(this.fileOut);){
                    IOUtils.copy((InputStream)fis, leos);
                }
                if (!this.fileOut.delete()) {
                    logger.log(7, "Can't delete temporary encryption file: " + this.fileOut);
                }
                leos.close();
            } catch (IOException e) {
                throw new EncryptedDocumentException(e);
            }
        }
    }
}

