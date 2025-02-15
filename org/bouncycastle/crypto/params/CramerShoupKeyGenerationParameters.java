/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.CramerShoupParameters;

public class CramerShoupKeyGenerationParameters
extends KeyGenerationParameters {
    private CramerShoupParameters params;

    public CramerShoupKeyGenerationParameters(SecureRandom secureRandom, CramerShoupParameters cramerShoupParameters) {
        super(secureRandom, CramerShoupKeyGenerationParameters.getStrength(cramerShoupParameters));
        this.params = cramerShoupParameters;
    }

    public CramerShoupParameters getParameters() {
        return this.params;
    }

    static int getStrength(CramerShoupParameters cramerShoupParameters) {
        return cramerShoupParameters.getP().bitLength();
    }
}

