/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.CramerShoupParameters;

public class CramerShoupKeyParameters
extends AsymmetricKeyParameter {
    private CramerShoupParameters params;

    protected CramerShoupKeyParameters(boolean bl, CramerShoupParameters cramerShoupParameters) {
        super(bl);
        this.params = cramerShoupParameters;
    }

    public CramerShoupParameters getParameters() {
        return this.params;
    }

    public boolean equals(Object object) {
        if (!(object instanceof CramerShoupKeyParameters)) {
            return false;
        }
        CramerShoupKeyParameters cramerShoupKeyParameters = (CramerShoupKeyParameters)object;
        if (this.params == null) {
            return cramerShoupKeyParameters.getParameters() == null;
        }
        return this.params.equals(cramerShoupKeyParameters.getParameters());
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

