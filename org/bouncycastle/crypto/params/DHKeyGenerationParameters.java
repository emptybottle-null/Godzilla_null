/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.DHParameters;

public class DHKeyGenerationParameters
extends KeyGenerationParameters {
    private DHParameters params;

    public DHKeyGenerationParameters(SecureRandom secureRandom, DHParameters dHParameters) {
        super(secureRandom, DHKeyGenerationParameters.getStrength(dHParameters));
        this.params = dHParameters;
    }

    public DHParameters getParameters() {
        return this.params;
    }

    static int getStrength(DHParameters dHParameters) {
        return dHParameters.getL() != 0 ? dHParameters.getL() : dHParameters.getP().bitLength();
    }
}

