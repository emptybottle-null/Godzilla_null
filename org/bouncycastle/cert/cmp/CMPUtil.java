/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.cmp;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.cert.cmp.CMPRuntimeException;

class CMPUtil {
    CMPUtil() {
    }

    static void derEncodeToStream(ASN1Encodable aSN1Encodable, OutputStream outputStream) {
        DEROutputStream dEROutputStream = new DEROutputStream(outputStream);
        try {
            dEROutputStream.writeObject(aSN1Encodable);
            dEROutputStream.close();
        } catch (IOException iOException) {
            throw new CMPRuntimeException("unable to DER encode object: " + iOException.getMessage(), iOException);
        }
    }
}

