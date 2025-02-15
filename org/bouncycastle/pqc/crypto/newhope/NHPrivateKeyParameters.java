/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;

public class NHPrivateKeyParameters
extends AsymmetricKeyParameter {
    final short[] secData;

    public NHPrivateKeyParameters(short[] sArray) {
        super(true);
        this.secData = Arrays.clone(sArray);
    }

    public short[] getSecData() {
        return Arrays.clone(this.secData);
    }
}

