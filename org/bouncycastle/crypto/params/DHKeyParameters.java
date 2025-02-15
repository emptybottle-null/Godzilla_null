/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHParameters;

public class DHKeyParameters
extends AsymmetricKeyParameter {
    private DHParameters params;

    protected DHKeyParameters(boolean bl, DHParameters dHParameters) {
        super(bl);
        this.params = dHParameters;
    }

    public DHParameters getParameters() {
        return this.params;
    }

    public boolean equals(Object object) {
        if (!(object instanceof DHKeyParameters)) {
            return false;
        }
        DHKeyParameters dHKeyParameters = (DHKeyParameters)object;
        if (this.params == null) {
            return dHKeyParameters.getParameters() == null;
        }
        return this.params.equals(dHKeyParameters.getParameters());
    }

    public int hashCode() {
        int n;
        int n2 = n = this.isPrivate() ? 0 : 1;
        if (this.params != null) {
            n ^= this.params.hashCode();
        }
        return n;
    }
}

