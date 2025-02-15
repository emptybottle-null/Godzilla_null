/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.mceliece.McElieceParameters;

public class McElieceKeyParameters
extends AsymmetricKeyParameter {
    private McElieceParameters params;

    public McElieceKeyParameters(boolean bl, McElieceParameters mcElieceParameters) {
        super(bl);
        this.params = mcElieceParameters;
    }

    public McElieceParameters getParameters() {
        return this.params;
    }
}

