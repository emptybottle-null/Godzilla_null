/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.bc;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.operator.bc.BcAsymmetricKeyUnwrapper;

public class BcRSAAsymmetricKeyUnwrapper
extends BcAsymmetricKeyUnwrapper {
    public BcRSAAsymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, AsymmetricKeyParameter asymmetricKeyParameter) {
        super(algorithmIdentifier, asymmetricKeyParameter);
    }

    protected AsymmetricBlockCipher createAsymmetricUnwrapper(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return new PKCS1Encoding(new RSAEngine());
    }
}

