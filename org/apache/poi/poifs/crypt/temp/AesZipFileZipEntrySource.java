/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.temp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.poi.openxml4j.util.ZipEntrySource;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.TempFile;

public final class AesZipFileZipEntrySource
implements ZipEntrySource {
    private static final POILogger LOG = POILogFactory.getLogger(AesZipFileZipEntrySource.class);
    private static final String PADDING = "PKCS5Padding";
    private final File tmpFile;
    private final ZipFile zipFile;
    private final Cipher ci;
    private boolean closed;

    private AesZipFileZipEntrySource(File tmpFile, Cipher ci) throws IOException {
        this.tmpFile = tmpFile;
        this.zipFile = new ZipFile(tmpFile);
        this.ci = ci;
        this.closed = false;
    }

    @Override
    public Enumeration<? extends ZipArchiveEntry> getEntries() {
        return this.zipFile.getEntries();
    }

    @Override
    public ZipArchiveEntry getEntry(String path) {
        return this.zipFile.getEntry(path);
    }

    @Override
    public InputStream getInputStream(ZipArchiveEntry entry) throws IOException {
        InputStream is = this.zipFile.getInputStream(entry);
        return new CipherInputStream(is, this.ci);
    }

    @Override
    public void close() throws IOException {
        if (!this.closed) {
            this.zipFile.close();
            if (!this.tmpFile.delete()) {
                LOG.log(5, this.tmpFile.getAbsolutePath() + " can't be removed (or was already removed.");
            }
        }
        this.closed = true;
    }

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    public static AesZipFileZipEntrySource createZipEntrySource(InputStream is) throws IOException {
        SecureRandom sr = new SecureRandom();
        byte[] ivBytes = new byte[16];
        byte[] keyBytes = new byte[16];
        sr.nextBytes(ivBytes);
        sr.nextBytes(keyBytes);
        File tmpFile = TempFile.createTempFile("protectedXlsx", ".zip");
        AesZipFileZipEntrySource.copyToFile(is, tmpFile, keyBytes, ivBytes);
        IOUtils.closeQuietly(is);
        return AesZipFileZipEntrySource.fileToSource(tmpFile, keyBytes, ivBytes);
    }

    private static void copyToFile(InputStream is, File tmpFile, byte[] keyBytes, byte[] ivBytes) throws IOException {
        SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, CipherAlgorithm.aes128.jceId);
        Cipher ciEnc = CryptoFunctions.getCipher(skeySpec, CipherAlgorithm.aes128, ChainingMode.cbc, ivBytes, 1, PADDING);
        try (ZipArchiveInputStream zis = new ZipArchiveInputStream(is);
             FileOutputStream fos = new FileOutputStream(tmpFile);
             ZipArchiveOutputStream zos = new ZipArchiveOutputStream(fos);){
            ZipArchiveEntry ze;
            while ((ze = zis.getNextZipEntry()) != null) {
                ZipArchiveEntry zeNew = new ZipArchiveEntry(ze.getName());
                zeNew.setComment(ze.getComment());
                zeNew.setExtra(ze.getExtra());
                zeNew.setTime(ze.getTime());
                zos.putArchiveEntry(zeNew);
                FilterOutputStream fos2 = new FilterOutputStream(zos){

                    @Override
                    public void close() {
                    }
                };
                CipherOutputStream cos = new CipherOutputStream(fos2, ciEnc);
                IOUtils.copy((InputStream)zis, cos);
                cos.close();
                fos2.close();
                zos.closeArchiveEntry();
            }
        }
    }

    private static AesZipFileZipEntrySource fileToSource(File tmpFile, byte[] keyBytes, byte[] ivBytes) throws IOException {
        SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, CipherAlgorithm.aes128.jceId);
        Cipher ciDec = CryptoFunctions.getCipher(skeySpec, CipherAlgorithm.aes128, ChainingMode.cbc, ivBytes, 2, PADDING);
        return new AesZipFileZipEntrySource(tmpFile, ciDec);
    }
}

