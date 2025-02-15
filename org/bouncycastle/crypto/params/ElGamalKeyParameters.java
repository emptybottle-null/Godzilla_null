/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ElGamalParameters;

public class ElGamalKeyParameters
extends AsymmetricKeyParameter {
    private ElGamalParameters params;

    protected ElGamalKeyParameters(boolean bl, ElGamalParameters elGamalParameters) {
        super(bl);
        this.params = elGamalParameters;
    }

    public ElGamalParameters getParameters() {
        return this.params;
    }

    public int hashCode() {
        return this.params != null ? this.params.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ElGamalKeyParameters)) {
            return false;
        }
        ElGamalKeyParameters elGamalKeyParameters = (ElGamalKeyParameters)object;
        if (this.params == null) {
            return elGamalKeyParameters.getParameters() == null;
        }
        return this.params.equals(elGamalKeyParameters.getParameters());
    }
}

