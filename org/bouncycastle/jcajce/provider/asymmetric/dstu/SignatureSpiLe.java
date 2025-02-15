/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dstu;

import java.io.IOException;
import java.security.SignatureException;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.jcajce.provider.asymmetric.dstu.SignatureSpi;

public class SignatureSpiLe
extends SignatureSpi {
    void reverseBytes(byte[] byArray) {
        for (int i = 0; i < byArray.length / 2; ++i) {
            byte by = byArray[i];
            byArray[i] = byArray[byArray.length - 1 - i];
            byArray[byArray.length - 1 - i] = by;
        }
    }

    protected byte[] engineSign() throws SignatureException {
        byte[] byArray = ASN1OctetString.getInstance(super.engineSign()).getOctets();
        this.reverseBytes(byArray);
        try {
            return new DEROctetString(byArray).getEncoded();
        } catch (Exception exception) {
            throw new SignatureException(exception.toString());
        }
    }

    protected boolean engineVerify(byte[] byArray) throws SignatureException {
        byte[] byArray2 = null;
        try {
            byArray2 = ((ASN1OctetString)ASN1OctetString.fromByteArray(byArray)).getOctets();
        } catch (IOException iOException) {
            throw new SignatureException("error decoding signature bytes.");
        }
        this.reverseBytes(byArray2);
        try {
            return super.engineVerify(new DEROctetString(byArray2).getEncoded());
        } catch (SignatureException signatureException) {
            throw signatureException;
        } catch (Exception exception) {
            throw new SignatureException(exception.toString());
        }
    }
}

