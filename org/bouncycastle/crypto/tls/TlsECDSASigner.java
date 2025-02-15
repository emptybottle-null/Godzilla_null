/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.crypto.tls.TlsDSASigner;
import org.bouncycastle.crypto.tls.TlsUtils;

public class TlsECDSASigner
extends TlsDSASigner {
    public boolean isValidPublicKey(AsymmetricKeyParameter asymmetricKeyParameter) {
        return asymmetricKeyParameter instanceof ECPublicKeyParameters;
    }

    protected DSA createDSAImpl(short s) {
        return new ECDSASigner(new HMacDSAKCalculator(TlsUtils.createHash(s)));
    }

    protected short getSignatureAlgorithm() {
        return 3;
    }
}

