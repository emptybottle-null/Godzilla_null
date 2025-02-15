/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.crmf.EncryptedValue;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.EncryptedValuePadder;
import org.bouncycastle.operator.KeyWrapper;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.util.Strings;

public class EncryptedValueBuilder {
    private KeyWrapper wrapper;
    private OutputEncryptor encryptor;
    private EncryptedValuePadder padder;

    public EncryptedValueBuilder(KeyWrapper keyWrapper, OutputEncryptor outputEncryptor) {
        this(keyWrapper, outputEncryptor, null);
    }

    public EncryptedValueBuilder(KeyWrapper keyWrapper, OutputEncryptor outputEncryptor, EncryptedValuePadder encryptedValuePadder) {
        this.wrapper = keyWrapper;
        this.encryptor = outputEncryptor;
        this.padder = encryptedValuePadder;
    }

    public EncryptedValue build(char[] cArray) throws CRMFException {
        return this.encryptData(this.padData(Strings.toUTF8ByteArray(cArray)));
    }

    public EncryptedValue build(X509CertificateHolder x509CertificateHolder) throws CRMFException {
        try {
            return this.encryptData(this.padData(x509CertificateHolder.getEncoded()));
        } catch (IOException iOException) {
            throw new CRMFException("cannot encode certificate: " + iOException.getMessage(), iOException);
        }
    }

    private EncryptedValue encryptData(byte[] byArray) throws CRMFException {
        DERBitString dERBitString;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream outputStream = this.encryptor.getOutputStream(byteArrayOutputStream);
        try {
            outputStream.write(byArray);
            outputStream.close();
        } catch (IOException iOException) {
            throw new CRMFException("cannot process data: " + iOException.getMessage(), iOException);
        }
        AlgorithmIdentifier algorithmIdentifier = null;
        AlgorithmIdentifier algorithmIdentifier2 = this.encryptor.getAlgorithmIdentifier();
        try {
            this.wrapper.generateWrappedKey(this.encryptor.getKey());
            dERBitString = new DERBitString(this.wrapper.generateWrappedKey(this.encryptor.getKey()));
        } catch (OperatorException operatorException) {
            throw new CRMFException("cannot wrap key: " + operatorException.getMessage(), operatorException);
        }
        AlgorithmIdentifier algorithmIdentifier3 = this.wrapper.getAlgorithmIdentifier();
        ASN1OctetString aSN1OctetString = null;
        DERBitString dERBitString2 = new DERBitString(byteArrayOutputStream.toByteArray());
        return new EncryptedValue(algorithmIdentifier, algorithmIdentifier2, dERBitString, algorithmIdentifier3, aSN1OctetString, dERBitString2);
    }

    private byte[] padData(byte[] byArray) {
        if (this.padder != null) {
            return this.padder.getPaddedData(byArray);
        }
        return byArray;
    }
}

