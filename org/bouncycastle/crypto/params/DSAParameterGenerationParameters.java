/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import java.security.SecureRandom;

public class DSAParameterGenerationParameters {
    public static final int DIGITAL_SIGNATURE_USAGE = 1;
    public static final int KEY_ESTABLISHMENT_USAGE = 2;
    private final int l;
    private final int n;
    private final int usageIndex;
    private final int certainty;
    private final SecureRandom random;

    public DSAParameterGenerationParameters(int n, int n2, int n3, SecureRandom secureRandom) {
        this(n, n2, n3, secureRandom, -1);
    }

    public DSAParameterGenerationParameters(int n, int n2, int n3, SecureRandom secureRandom, int n4) {
        this.l = n;
        this.n = n2;
        this.certainty = n3;
        this.usageIndex = n4;
        this.random = secureRandom;
    }

    public int getL() {
        return this.l;
    }

    public int getN() {
        return this.n;
    }

    public int getCertainty() {
        return this.certainty;
    }

    public SecureRandom getRandom() {
        return this.random;
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }
}

