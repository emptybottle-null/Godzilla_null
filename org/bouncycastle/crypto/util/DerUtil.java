/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.util;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.util.Arrays;

class DerUtil {
    DerUtil() {
    }

    static ASN1OctetString getOctetString(byte[] byArray) {
        if (byArray == null) {
            return new DEROctetString(new byte[0]);
        }
        return new DEROctetString(Arrays.clone(byArray));
    }

    static byte[] toByteArray(ASN1Primitive aSN1Primitive) {
        try {
            return aSN1Primitive.getEncoded();
        } catch (IOException iOException) {
            throw new IllegalStateException("Cannot get encoding: " + iOException.getMessage()){

                public Throwable getCause() {
                    return iOException;
                }
            };
        }
    }
}

