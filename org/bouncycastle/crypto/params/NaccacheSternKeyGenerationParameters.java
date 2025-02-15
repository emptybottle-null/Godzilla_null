/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;

public class NaccacheSternKeyGenerationParameters
extends KeyGenerationParameters {
    private int certainty;
    private int cntSmallPrimes;
    private boolean debug = false;

    public NaccacheSternKeyGenerationParameters(SecureRandom secureRandom, int n, int n2, int n3) {
        this(secureRandom, n, n2, n3, false);
    }

    public NaccacheSternKeyGenerationParameters(SecureRandom secureRandom, int n, int n2, int n3, boolean bl) {
        super(secureRandom, n);
        this.certainty = n2;
        if (n3 % 2 == 1) {
            throw new IllegalArgumentException("cntSmallPrimes must be a multiple of 2");
        }
        if (n3 < 30) {
            throw new IllegalArgumentException("cntSmallPrimes must be >= 30 for security reasons");
        }
        this.cntSmallPrimes = n3;
        this.debug = bl;
    }

    public int getCertainty() {
        return this.certainty;
    }

    public int getCntSmallPrimes() {
        return this.cntSmallPrimes;
    }

    public boolean isDebug() {
        return this.debug;
    }
}

