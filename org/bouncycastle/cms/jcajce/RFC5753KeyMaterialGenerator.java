/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import org.bouncycastle.asn1.cms.ecc.ECCCMSSharedInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.jcajce.KeyMaterialGenerator;
import org.bouncycastle.util.Pack;

class RFC5753KeyMaterialGenerator
implements KeyMaterialGenerator {
    RFC5753KeyMaterialGenerator() {
    }

    public byte[] generateKDFMaterial(AlgorithmIdentifier algorithmIdentifier, int n, byte[] byArray) {
        ECCCMSSharedInfo eCCCMSSharedInfo = new ECCCMSSharedInfo(algorithmIdentifier, byArray, Pack.intToBigEndian(n));
        try {
            return eCCCMSSharedInfo.getEncoded("DER");
        } catch (IOException iOException) {
            throw new IllegalStateException("Unable to create KDF material: " + iOException);
        }
    }
}

