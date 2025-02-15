/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;

public class PKCS8EncryptedPrivateKeyInfoBuilder {
    private PrivateKeyInfo privateKeyInfo;

    public PKCS8EncryptedPrivateKeyInfoBuilder(byte[] byArray) {
        this(PrivateKeyInfo.getInstance(byArray));
    }

    public PKCS8EncryptedPrivateKeyInfoBuilder(PrivateKeyInfo privateKeyInfo) {
        this.privateKeyInfo = privateKeyInfo;
    }

    public PKCS8EncryptedPrivateKeyInfo build(OutputEncryptor outputEncryptor) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStream outputStream = outputEncryptor.getOutputStream(byteArrayOutputStream);
            outputStream.write(this.privateKeyInfo.getEncoded());
            outputStream.close();
            return new PKCS8EncryptedPrivateKeyInfo(new EncryptedPrivateKeyInfo(outputEncryptor.getAlgorithmIdentifier(), byteArrayOutputStream.toByteArray()));
        } catch (IOException iOException) {
            throw new IllegalStateException("cannot encode privateKeyInfo");
        }
    }
}

