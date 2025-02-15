/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DSAParameters;

public class DSAKeyParameters
extends AsymmetricKeyParameter {
    private DSAParameters params;

    public DSAKeyParameters(boolean bl, DSAParameters dSAParameters) {
        super(bl);
        this.params = dSAParameters;
    }

    public DSAParameters getParameters() {
        return this.params;
    }
}

