/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.ExchangePair;
import org.bouncycastle.pqc.crypto.ExchangePairGenerator;
import org.bouncycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

public class NHExchangePairGenerator
implements ExchangePairGenerator {
    private final SecureRandom random;

    public NHExchangePairGenerator(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public ExchangePair GenerateExchange(AsymmetricKeyParameter asymmetricKeyParameter) {
        return this.generateExchange(asymmetricKeyParameter);
    }

    public ExchangePair generateExchange(AsymmetricKeyParameter asymmetricKeyParameter) {
        NHPublicKeyParameters nHPublicKeyParameters = (NHPublicKeyParameters)asymmetricKeyParameter;
        byte[] byArray = new byte[32];
        byte[] byArray2 = new byte[2048];
        NewHope.sharedB(this.random, byArray, byArray2, nHPublicKeyParameters.pubData);
        return new ExchangePair(new NHPublicKeyParameters(byArray2), byArray);
    }
}

