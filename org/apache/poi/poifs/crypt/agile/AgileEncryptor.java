/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.agile;

import com.microsoft.schemas.office.x2006.encryption.CTDataIntegrity;
import com.microsoft.schemas.office.x2006.encryption.CTEncryption;
import com.microsoft.schemas.office.x2006.encryption.CTKeyData;
import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor;
import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptors;
import com.microsoft.schemas.office.x2006.encryption.EncryptionDocument;
import com.microsoft.schemas.office.x2006.encryption.STCipherAlgorithm;
import com.microsoft.schemas.office.x2006.encryption.STCipherChaining;
import com.microsoft.schemas.office.x2006.encryption.STHashAlgorithm;
import com.microsoft.schemas.office.x2006.keyEncryptor.certificate.CTCertificateKeyEncryptor;
import com.microsoft.schemas.office.x2006.keyEncryptor.password.CTPasswordKeyEncryptor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.util.HashMap;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChunkedCipherOutputStream;
import org.apache.poi.poifs.crypt.CryptoFunctions;
import org.apache.poi.poifs.crypt.DataSpaceMapUtils;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.agile.AgileDecryptor;
import org.apache.poi.poifs.crypt.agile.AgileEncryptionHeader;
import org.apache.poi.poifs.crypt.agile.AgileEncryptionVerifier;
import org.apache.poi.poifs.crypt.standard.EncryptionRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.xmlbeans.XmlOptions;

public class AgileEncryptor
extends Encryptor {
    private static final int MAX_RECORD_LENGTH = 1000000;
    private byte[] integritySalt;
    private byte[] pwHash;
    private final CTKeyEncryptor.Uri.Enum passwordUri = CTKeyEncryptor.Uri.HTTP_SCHEMAS_MICROSOFT_COM_OFFICE_2006_KEY_ENCRYPTOR_PASSWORD;
    private final CTKeyEncryptor.Uri.Enum certificateUri = CTKeyEncryptor.Uri.HTTP_SCHEMAS_MICROSOFT_COM_OFFICE_2006_KEY_ENCRYPTOR_CERTIFICATE;

    protected AgileEncryptor() {
    }

    protected AgileEncryptor(AgileEncryptor other) {
        super(other);
        this.integritySalt = other.integritySalt == null ? null : (byte[])other.integritySalt.clone();
        this.pwHash = other.pwHash == null ? null : (byte[])other.pwHash.clone();
    }

    @Override
    public void confirmPassword(String password) {
        SecureRandom r = new SecureRandom();
        AgileEncryptionHeader header = (AgileEncryptionHeader)this.getEncryptionInfo().getHeader();
        int blockSize = header.getBlockSize();
        int keySize = header.getKeySize() / 8;
        int hashSize = header.getHashAlgorithm().hashSize;
        byte[] newVerifierSalt = IOUtils.safelyAllocate(blockSize, 1000000);
        byte[] newVerifier = IOUtils.safelyAllocate(blockSize, 1000000);
        byte[] newKeySalt = IOUtils.safelyAllocate(blockSize, 1000000);
        byte[] newKeySpec = IOUtils.safelyAllocate(keySize, 1000000);
        byte[] newIntegritySalt = IOUtils.safelyAllocate(hashSize, 1000000);
        ((Random)r).nextBytes(newVerifierSalt);
        ((Random)r).nextBytes(newVerifier);
        ((Random)r).nextBytes(newKeySalt);
        ((Random)r).nextBytes(newKeySpec);
        ((Random)r).nextBytes(newIntegritySalt);
        this.confirmPassword(password, newKeySpec, newKeySalt, newVerifierSalt, newVerifier, newIntegritySalt);
    }

    @Override
    public void confirmPassword(String password, byte[] keySpec, byte[] keySalt, byte[] verifier, byte[] verifierSalt, byte[] integritySalt) {
        AgileEncryptionVerifier ver = (AgileEncryptionVerifier)this.getEncryptionInfo().getVerifier();
        AgileEncryptionHeader header = (AgileEncryptionHeader)this.getEncryptionInfo().getHeader();
        ver.setSalt(verifierSalt);
        header.setKeySalt(keySalt);
        int blockSize = header.getBlockSize();
        this.pwHash = CryptoFunctions.hashPassword(password, ver.getHashAlgorithm(), verifierSalt, ver.getSpinCount());
        byte[] encryptedVerifier = AgileDecryptor.hashInput(ver, this.pwHash, AgileDecryptor.kVerifierInputBlock, verifier, 1);
        ver.setEncryptedVerifier(encryptedVerifier);
        MessageDigest hashMD = CryptoFunctions.getMessageDigest(ver.getHashAlgorithm());
        byte[] hashedVerifier = hashMD.digest(verifier);
        byte[] encryptedVerifierHash = AgileDecryptor.hashInput(ver, this.pwHash, AgileDecryptor.kHashedVerifierBlock, hashedVerifier, 1);
        ver.setEncryptedVerifierHash(encryptedVerifierHash);
        byte[] encryptedKey = AgileDecryptor.hashInput(ver, this.pwHash, AgileDecryptor.kCryptoKeyBlock, keySpec, 1);
        ver.setEncryptedKey(encryptedKey);
        SecretKeySpec secretKey = new SecretKeySpec(keySpec, header.getCipherAlgorithm().jceId);
        this.setSecretKey(secretKey);
        this.integritySalt = (byte[])integritySalt.clone();
        try {
            byte[] vec = CryptoFunctions.generateIv(header.getHashAlgorithm(), header.getKeySalt(), AgileDecryptor.kIntegrityKeyBlock, header.getBlockSize());
            Cipher cipher = CryptoFunctions.getCipher(secretKey, header.getCipherAlgorithm(), header.getChainingMode(), vec, 1);
            byte[] hmacKey = CryptoFunctions.getBlock0(this.integritySalt, AgileDecryptor.getNextBlockSize(this.integritySalt.length, blockSize));
            byte[] encryptedHmacKey = cipher.doFinal(hmacKey);
            header.setEncryptedHmacKey(encryptedHmacKey);
            cipher = Cipher.getInstance("RSA");
            for (AgileEncryptionVerifier.AgileCertificateEntry ace : ver.getCertificates()) {
                cipher.init(1, ace.x509.getPublicKey());
                ace.encryptedKey = cipher.doFinal(this.getSecretKey().getEncoded());
                Mac x509Hmac = CryptoFunctions.getMac(header.getHashAlgorithm());
                x509Hmac.init(this.getSecretKey());
                ace.certVerifier = x509Hmac.doFinal(ace.x509.getEncoded());
            }
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException(e);
        }
    }

    @Override
    public OutputStream getDataStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
        return new AgileCipherOutputStream(dir);
    }

    protected void updateIntegrityHMAC(File tmpFile, int oleStreamSize) throws GeneralSecurityException, IOException {
        AgileEncryptionHeader header = (AgileEncryptionHeader)this.getEncryptionInfo().getHeader();
        int blockSize = header.getBlockSize();
        HashAlgorithm hashAlgo = header.getHashAlgorithm();
        Mac integrityMD = CryptoFunctions.getMac(hashAlgo);
        byte[] hmacKey = CryptoFunctions.getBlock0(this.integritySalt, AgileDecryptor.getNextBlockSize(this.integritySalt.length, blockSize));
        integrityMD.init(new SecretKeySpec(hmacKey, hashAlgo.jceHmacId));
        byte[] buf = new byte[1024];
        LittleEndian.putLong(buf, 0, oleStreamSize);
        integrityMD.update(buf, 0, 8);
        try (FileInputStream fis = new FileInputStream(tmpFile);){
            int readBytes;
            while ((readBytes = ((InputStream)fis).read(buf)) != -1) {
                integrityMD.update(buf, 0, readBytes);
            }
        }
        byte[] hmacValue = integrityMD.doFinal();
        byte[] hmacValueFilled = CryptoFunctions.getBlock0(hmacValue, AgileDecryptor.getNextBlockSize(hmacValue.length, blockSize));
        byte[] iv = CryptoFunctions.generateIv(header.getHashAlgorithm(), header.getKeySalt(), AgileDecryptor.kIntegrityValueBlock, blockSize);
        Cipher cipher = CryptoFunctions.getCipher(this.getSecretKey(), header.getCipherAlgorithm(), header.getChainingMode(), iv, 1);
        byte[] encryptedHmacValue = cipher.doFinal(hmacValueFilled);
        header.setEncryptedHmacValue(encryptedHmacValue);
    }

    protected EncryptionDocument createEncryptionDocument() {
        AgileEncryptionVerifier ver = (AgileEncryptionVerifier)this.getEncryptionInfo().getVerifier();
        AgileEncryptionHeader header = (AgileEncryptionHeader)this.getEncryptionInfo().getHeader();
        EncryptionDocument ed = EncryptionDocument.Factory.newInstance();
        CTEncryption edRoot = ed.addNewEncryption();
        CTKeyData keyData = edRoot.addNewKeyData();
        CTKeyEncryptors keyEncList = edRoot.addNewKeyEncryptors();
        CTKeyEncryptor keyEnc = keyEncList.addNewKeyEncryptor();
        keyEnc.setUri(this.passwordUri);
        CTPasswordKeyEncryptor keyPass = keyEnc.addNewEncryptedPasswordKey();
        keyPass.setSpinCount(ver.getSpinCount());
        keyData.setSaltSize(header.getBlockSize());
        keyPass.setSaltSize(ver.getBlockSize());
        keyData.setBlockSize(header.getBlockSize());
        keyPass.setBlockSize(ver.getBlockSize());
        keyData.setKeyBits(header.getKeySize());
        keyPass.setKeyBits(ver.getKeySize());
        keyData.setHashSize(header.getHashAlgorithm().hashSize);
        keyPass.setHashSize(ver.getHashAlgorithm().hashSize);
        if (!header.getCipherAlgorithm().xmlId.equals(ver.getCipherAlgorithm().xmlId)) {
            throw new EncryptedDocumentException("Cipher algorithm of header and verifier have to match");
        }
        STCipherAlgorithm.Enum xmlCipherAlgo = STCipherAlgorithm.Enum.forString(header.getCipherAlgorithm().xmlId);
        if (xmlCipherAlgo == null) {
            throw new EncryptedDocumentException("CipherAlgorithm " + (Object)((Object)header.getCipherAlgorithm()) + " not supported.");
        }
        keyData.setCipherAlgorithm(xmlCipherAlgo);
        keyPass.setCipherAlgorithm(xmlCipherAlgo);
        switch (header.getChainingMode()) {
            case cbc: {
                keyData.setCipherChaining(STCipherChaining.CHAINING_MODE_CBC);
                keyPass.setCipherChaining(STCipherChaining.CHAINING_MODE_CBC);
                break;
            }
            case cfb: {
                keyData.setCipherChaining(STCipherChaining.CHAINING_MODE_CFB);
                keyPass.setCipherChaining(STCipherChaining.CHAINING_MODE_CFB);
                break;
            }
            default: {
                throw new EncryptedDocumentException("ChainingMode " + (Object)((Object)header.getChainingMode()) + " not supported.");
            }
        }
        keyData.setHashAlgorithm(AgileEncryptor.mapHashAlgorithm(header.getHashAlgorithm()));
        keyPass.setHashAlgorithm(AgileEncryptor.mapHashAlgorithm(ver.getHashAlgorithm()));
        keyData.setSaltValue(header.getKeySalt());
        keyPass.setSaltValue(ver.getSalt());
        keyPass.setEncryptedVerifierHashInput(ver.getEncryptedVerifier());
        keyPass.setEncryptedVerifierHashValue(ver.getEncryptedVerifierHash());
        keyPass.setEncryptedKeyValue(ver.getEncryptedKey());
        CTDataIntegrity hmacData = edRoot.addNewDataIntegrity();
        hmacData.setEncryptedHmacKey(header.getEncryptedHmacKey());
        hmacData.setEncryptedHmacValue(header.getEncryptedHmacValue());
        for (AgileEncryptionVerifier.AgileCertificateEntry ace : ver.getCertificates()) {
            keyEnc = keyEncList.addNewKeyEncryptor();
            keyEnc.setUri(this.certificateUri);
            CTCertificateKeyEncryptor certData = keyEnc.addNewEncryptedCertificateKey();
            try {
                certData.setX509Certificate(ace.x509.getEncoded());
            } catch (CertificateEncodingException e) {
                throw new EncryptedDocumentException(e);
            }
            certData.setEncryptedKeyValue(ace.encryptedKey);
            certData.setCertVerifier(ace.certVerifier);
        }
        return ed;
    }

    private static STHashAlgorithm.Enum mapHashAlgorithm(HashAlgorithm hashAlgo) {
        STHashAlgorithm.Enum xmlHashAlgo = STHashAlgorithm.Enum.forString(hashAlgo.ecmaString);
        if (xmlHashAlgo == null) {
            throw new EncryptedDocumentException("HashAlgorithm " + (Object)((Object)hashAlgo) + " not supported.");
        }
        return xmlHashAlgo;
    }

    protected void marshallEncryptionDocument(EncryptionDocument ed, LittleEndianByteArrayOutputStream os) {
        XmlOptions xo = new XmlOptions();
        xo.setCharacterEncoding("UTF-8");
        HashMap<String, String> nsMap = new HashMap<String, String>();
        nsMap.put(this.passwordUri.toString(), "p");
        nsMap.put(this.certificateUri.toString(), "c");
        xo.setUseDefaultNamespace();
        xo.setSaveSuggestedPrefixes(nsMap);
        xo.setSaveNamespacesFirst();
        xo.setSaveAggressiveNamespaces();
        xo.setSaveNoXmlDecl();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            bos.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n".getBytes(StandardCharsets.UTF_8));
            ed.save(bos, xo);
            bos.writeTo(os);
        } catch (IOException e) {
            throw new EncryptedDocumentException("error marshalling encryption info document", e);
        }
    }

    protected void createEncryptionInfoEntry(DirectoryNode dir, File tmpFile) throws IOException, GeneralSecurityException {
        DataSpaceMapUtils.addDefaultDataSpace(dir);
        final EncryptionInfo info = this.getEncryptionInfo();
        EncryptionRecord er = new EncryptionRecord(){

            @Override
            public void write(LittleEndianByteArrayOutputStream bos) {
                bos.writeShort(info.getVersionMajor());
                bos.writeShort(info.getVersionMinor());
                bos.writeInt(info.getEncryptionFlags());
                EncryptionDocument ed = AgileEncryptor.this.createEncryptionDocument();
                AgileEncryptor.this.marshallEncryptionDocument(ed, bos);
            }
        };
        DataSpaceMapUtils.createEncryptionEntry(dir, "EncryptionInfo", er);
    }

    @Override
    public AgileEncryptor copy() {
        return new AgileEncryptor(this);
    }

    private class AgileCipherOutputStream
    extends ChunkedCipherOutputStream {
        public AgileCipherOutputStream(DirectoryNode dir) throws IOException, GeneralSecurityException {
            super(dir, 4096);
        }

        @Override
        protected Cipher initCipherForBlock(Cipher existing, int block, boolean lastChunk) throws GeneralSecurityException {
            return AgileDecryptor.initCipherForBlock(existing, block, lastChunk, AgileEncryptor.this.getEncryptionInfo(), AgileEncryptor.this.getSecretKey(), 1);
        }

        @Override
        protected void calculateChecksum(File fileOut, int oleStreamSize) throws GeneralSecurityException, IOException {
            AgileEncryptor.this.updateIntegrityHMAC(fileOut, oleStreamSize);
        }

        @Override
        protected void createEncryptionInfoEntry(DirectoryNode dir, File tmpFile) throws IOException, GeneralSecurityException {
            AgileEncryptor.this.createEncryptionInfoEntry(dir, tmpFile);
        }
    }
}

