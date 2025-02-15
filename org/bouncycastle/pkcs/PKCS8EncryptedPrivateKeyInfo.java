/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.pkcs.PKCSException;
import org.bouncycastle.pkcs.PKCSIOException;
import org.bouncycastle.util.io.Streams;

public class PKCS8EncryptedPrivateKeyInfo {
    private EncryptedPrivateKeyInfo encryptedPrivateKeyInfo;

    private static EncryptedPrivateKeyInfo parseBytes(byte[] byArray) throws IOException {
        try {
            return EncryptedPrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(byArray));
        } catch (ClassCastException classCastException) {
            throw new PKCSIOException("malformed data: " + classCastException.getMessage(), classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new PKCSIOException("malformed data: " + illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }

    public PKCS8EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfo encryptedPrivateKeyInfo) {
        this.encryptedPrivateKeyInfo = encryptedPrivateKeyInfo;
    }

    public PKCS8EncryptedPrivateKeyInfo(byte[] byArray) throws IOException {
        this(PKCS8EncryptedPrivateKeyInfo.parseBytes(byArray));
    }

    public EncryptedPrivateKeyInfo toASN1Structure() {
        return this.encryptedPrivateKeyInfo;
    }

    public byte[] getEncoded() throws IOException {
        return this.encryptedPrivateKeyInfo.getEncoded();
    }

    public PrivateKeyInfo decryptPrivateKeyInfo(InputDecryptorProvider inputDecryptorProvider) throws PKCSException {
        try {
            InputDecryptor inputDecryptor = inputDecryptorProvider.get(this.encryptedPrivateKeyInfo.getEncryptionAlgorithm());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.encryptedPrivateKeyInfo.getEncryptedData());
            return PrivateKeyInfo.getInstance(Streams.readAll(inputDecryptor.getInputStream(byteArrayInputStream)));
        } catch (Exception exception) {
            throw new PKCSException("unable to read encrypted data: " + exception.getMessage(), exception);
        }
    }
}

