/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.util;

import java.io.IOException;
import java.security.AlgorithmParameters;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;

public class AlgorithmParametersUtils {
    private AlgorithmParametersUtils() {
    }

    public static ASN1Encodable extractParameters(AlgorithmParameters algorithmParameters) throws IOException {
        ASN1Primitive aSN1Primitive;
        try {
            aSN1Primitive = ASN1Primitive.fromByteArray(algorithmParameters.getEncoded("ASN.1"));
        } catch (Exception exception) {
            aSN1Primitive = ASN1Primitive.fromByteArray(algorithmParameters.getEncoded());
        }
        return aSN1Primitive;
    }

    public static void loadParameters(AlgorithmParameters algorithmParameters, ASN1Encodable aSN1Encodable) throws IOException {
        try {
            algorithmParameters.init(aSN1Encodable.toASN1Primitive().getEncoded(), "ASN.1");
        } catch (Exception exception) {
            algorithmParameters.init(aSN1Encodable.toASN1Primitive().getEncoded());
        }
    }
}

