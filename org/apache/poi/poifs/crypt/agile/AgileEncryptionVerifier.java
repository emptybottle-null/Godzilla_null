/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.agile;

import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor;
import com.microsoft.schemas.office.x2006.encryption.EncryptionDocument;
import com.microsoft.schemas.office.x2006.keyEncryptor.certificate.CTCertificateKeyEncryptor;
import com.microsoft.schemas.office.x2006.keyEncryptor.password.CTPasswordKeyEncryptor;
import java.io.ByteArrayInputStream;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.poifs.crypt.ChainingMode;
import org.apache.poi.poifs.crypt.CipherAlgorithm;
import org.apache.poi.poifs.crypt.EncryptionVerifier;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.agile.AgileEncryptionInfoBuilder;

public class AgileEncryptionVerifier
extends EncryptionVerifier {
    private final List<AgileCertificateEntry> certList = new ArrayList<AgileCertificateEntry>();
    private int keyBits = -1;
    private int blockSize = -1;

    public AgileEncryptionVerifier(String descriptor) {
        this(AgileEncryptionInfoBuilder.parseDescriptor(descriptor));
    }

    protected AgileEncryptionVerifier(EncryptionDocument ed) {
        CTPasswordKeyEncryptor keyData;
        Iterator<CTKeyEncryptor> encList = ed.getEncryption().getKeyEncryptors().getKeyEncryptorList().iterator();
        try {
            keyData = encList.next().getEncryptedPasswordKey();
            if (keyData == null) {
                throw new NullPointerException("encryptedKey not set");
            }
        } catch (Exception e) {
            throw new EncryptedDocumentException("Unable to parse keyData", e);
        }
        int kb = (int)keyData.getKeyBits();
        CipherAlgorithm ca = CipherAlgorithm.fromXmlId(keyData.getCipherAlgorithm().toString(), kb);
        this.setCipherAlgorithm(ca);
        this.setKeySize(kb);
        int blockSize = keyData.getBlockSize();
        this.setBlockSize(blockSize);
        int hashSize = keyData.getHashSize();
        HashAlgorithm ha = HashAlgorithm.fromEcmaId(keyData.getHashAlgorithm().toString());
        this.setHashAlgorithm(ha);
        if (this.getHashAlgorithm().hashSize != hashSize) {
            throw new EncryptedDocumentException("Unsupported hash algorithm: " + keyData.getHashAlgorithm() + " @ " + hashSize + " bytes");
        }
        this.setSpinCount(keyData.getSpinCount());
        this.setEncryptedVerifier(keyData.getEncryptedVerifierHashInput());
        this.setSalt(keyData.getSaltValue());
        this.setEncryptedKey(keyData.getEncryptedKeyValue());
        this.setEncryptedVerifierHash(keyData.getEncryptedVerifierHashValue());
        int saltSize = keyData.getSaltSize();
        if (saltSize != this.getSalt().length) {
            throw new EncryptedDocumentException("Invalid salt size");
        }
        switch (keyData.getCipherChaining().intValue()) {
            case 1: {
                this.setChainingMode(ChainingMode.cbc);
                break;
            }
            case 2: {
                this.setChainingMode(ChainingMode.cfb);
                break;
            }
            default: {
                throw new EncryptedDocumentException("Unsupported chaining mode - " + keyData.getCipherChaining());
            }
        }
        if (!encList.hasNext()) {
            return;
        }
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            while (encList.hasNext()) {
                CTCertificateKeyEncryptor certKey = encList.next().getEncryptedCertificateKey();
                AgileCertificateEntry ace = new AgileCertificateEntry();
                ace.certVerifier = certKey.getCertVerifier();
                ace.encryptedKey = certKey.getEncryptedKeyValue();
                ace.x509 = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream(certKey.getX509Certificate()));
                this.certList.add(ace);
            }
        } catch (GeneralSecurityException e) {
            throw new EncryptedDocumentException("can't parse X509 certificate", e);
        }
    }

    public AgileEncryptionVerifier(CipherAlgorithm cipherAlgorithm, HashAlgorithm hashAlgorithm, int keyBits, int blockSize, ChainingMode chainingMode) {
        this.setCipherAlgorithm(cipherAlgorithm);
        this.setHashAlgorithm(hashAlgorithm);
        this.setChainingMode(chainingMode);
        this.setKeySize(keyBits);
        this.setBlockSize(blockSize);
        this.setSpinCount(100000);
    }

    public AgileEncryptionVerifier(AgileEncryptionVerifier other) {
        super(other);
        this.keyBits = other.keyBits;
        this.blockSize = other.blockSize;
        other.certList.stream().map(AgileCertificateEntry::new).forEach(this.certList::add);
    }

    @Override
    protected void setSalt(byte[] salt) {
        if (salt == null || salt.length != this.getCipherAlgorithm().blockSize) {
            throw new EncryptedDocumentException("invalid verifier salt");
        }
        super.setSalt(salt);
    }

    @Override
    protected void setEncryptedVerifier(byte[] encryptedVerifier) {
        super.setEncryptedVerifier(encryptedVerifier);
    }

    @Override
    protected void setEncryptedVerifierHash(byte[] encryptedVerifierHash) {
        super.setEncryptedVerifierHash(encryptedVerifierHash);
    }

    @Override
    protected void setEncryptedKey(byte[] encryptedKey) {
        super.setEncryptedKey(encryptedKey);
    }

    public void addCertificate(X509Certificate x509) {
        AgileCertificateEntry ace = new AgileCertificateEntry();
        ace.x509 = x509;
        this.certList.add(ace);
    }

    public List<AgileCertificateEntry> getCertificates() {
        return this.certList;
    }

    @Override
    public AgileEncryptionVerifier copy() {
        return new AgileEncryptionVerifier(this);
    }

    public int getKeySize() {
        return this.keyBits;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    protected void setKeySize(int keyBits) {
        this.keyBits = keyBits;
        for (int allowedBits : this.getCipherAlgorithm().allowedKeySize) {
            if (allowedBits != keyBits) continue;
            return;
        }
        throw new EncryptedDocumentException("KeySize " + keyBits + " not allowed for cipher " + (Object)((Object)this.getCipherAlgorithm()));
    }

    protected void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    @Override
    protected final void setCipherAlgorithm(CipherAlgorithm cipherAlgorithm) {
        super.setCipherAlgorithm(cipherAlgorithm);
        if (cipherAlgorithm.allowedKeySize.length == 1) {
            this.setKeySize(cipherAlgorithm.defaultKeySize);
        }
    }

    public static class AgileCertificateEntry {
        X509Certificate x509;
        byte[] encryptedKey;
        byte[] certVerifier;

        public AgileCertificateEntry() {
        }

        public AgileCertificateEntry(AgileCertificateEntry other) {
            this.x509 = other.x509;
            this.encryptedKey = other.encryptedKey == null ? null : (byte[])other.encryptedKey.clone();
            this.certVerifier = other.certVerifier == null ? null : (byte[])other.certVerifier.clone();
        }
    }
}

