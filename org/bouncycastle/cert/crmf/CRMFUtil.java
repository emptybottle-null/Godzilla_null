/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.crmf.CRMFRuntimeException;

class CRMFUtil {
    CRMFUtil() {
    }

    static void derEncodeToStream(ASN1Encodable aSN1Encodable, OutputStream outputStream) {
        DEROutputStream dEROutputStream = new DEROutputStream(outputStream);
        try {
            dEROutputStream.writeObject(aSN1Encodable);
            dEROutputStream.close();
        } catch (IOException iOException) {
            throw new CRMFRuntimeException("unable to DER encode object: " + iOException.getMessage(), iOException);
        }
    }

    static void addExtension(ExtensionsGenerator extensionsGenerator, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, ASN1Encodable aSN1Encodable) throws CertIOException {
        try {
            extensionsGenerator.addExtension(aSN1ObjectIdentifier, bl, aSN1Encodable);
        } catch (IOException iOException) {
            throw new CertIOException("cannot encode extension: " + iOException.getMessage(), iOException);
        }
    }
}

