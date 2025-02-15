/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.crmf.EncryptedValue;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.EncryptedValuePadder;
import org.bouncycastle.cert.crmf.ValueDecryptorGenerator;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.io.Streams;

public class EncryptedValueParser {
    private EncryptedValue value;
    private EncryptedValuePadder padder;

    public EncryptedValueParser(EncryptedValue encryptedValue) {
        this.value = encryptedValue;
    }

    public EncryptedValueParser(EncryptedValue encryptedValue, EncryptedValuePadder encryptedValuePadder) {
        this.value = encryptedValue;
        this.padder = encryptedValuePadder;
    }

    private byte[] decryptValue(ValueDecryptorGenerator valueDecryptorGenerator) throws CRMFException {
        if (this.value.getIntendedAlg() != null) {
            throw new UnsupportedOperationException();
        }
        if (this.value.getValueHint() != null) {
            throw new UnsupportedOperationException();
        }
        InputDecryptor inputDecryptor = valueDecryptorGenerator.getValueDecryptor(this.value.getKeyAlg(), this.value.getSymmAlg(), this.value.getEncSymmKey().getBytes());
        InputStream inputStream = inputDecryptor.getInputStream(new ByteArrayInputStream(this.value.getEncValue().getBytes()));
        try {
            byte[] byArray = Streams.readAll(inputStream);
            if (this.padder != null) {
                return this.padder.getUnpaddedData(byArray);
            }
            return byArray;
        } catch (IOException iOException) {
            throw new CRMFException("Cannot parse decrypted data: " + iOException.getMessage(), iOException);
        }
    }

    public X509CertificateHolder readCertificateHolder(ValueDecryptorGenerator valueDecryptorGenerator) throws CRMFException {
        return new X509CertificateHolder(Certificate.getInstance(this.decryptValue(valueDecryptorGenerator)));
    }

    public char[] readPassphrase(ValueDecryptorGenerator valueDecryptorGenerator) throws CRMFException {
        return Strings.fromUTF8ByteArray(this.decryptValue(valueDecryptorGenerator)).toCharArray();
    }
}

