/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

public class NHKeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private SecureRandom random;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        byte[] byArray = new byte[1824];
        short[] sArray = new short[1024];
        NewHope.keygen(this.random, byArray, sArray);
        return new AsymmetricCipherKeyPair(new NHPublicKeyParameters(byArray), new NHPrivateKeyParameters(sArray));
    }
}

