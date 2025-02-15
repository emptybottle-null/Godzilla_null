/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2Parameters;

public class McElieceCCA2KeyGenerationParameters
extends KeyGenerationParameters {
    private McElieceCCA2Parameters params;

    public McElieceCCA2KeyGenerationParameters(SecureRandom secureRandom, McElieceCCA2Parameters mcElieceCCA2Parameters) {
        super(secureRandom, 128);
        this.params = mcElieceCCA2Parameters;
    }

    public McElieceCCA2Parameters getParameters() {
        return this.params;
    }
}

