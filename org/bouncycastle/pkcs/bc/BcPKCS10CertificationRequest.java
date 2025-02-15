/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs.bc;

import java.io.IOException;
import org.bouncycastle.asn1.pkcs.CertificationRequest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCSException;

public class BcPKCS10CertificationRequest
extends PKCS10CertificationRequest {
    public BcPKCS10CertificationRequest(CertificationRequest certificationRequest) {
        super(certificationRequest);
    }

    public BcPKCS10CertificationRequest(byte[] byArray) throws IOException {
        super(byArray);
    }

    public BcPKCS10CertificationRequest(PKCS10CertificationRequest pKCS10CertificationRequest) {
        super(pKCS10CertificationRequest.toASN1Structure());
    }

    public AsymmetricKeyParameter getPublicKey() throws PKCSException {
        try {
            return PublicKeyFactory.createKey(this.getSubjectPublicKeyInfo());
        } catch (IOException iOException) {
            throw new PKCSException("error extracting key encoding: " + iOException.getMessage(), iOException);
        }
    }
}

