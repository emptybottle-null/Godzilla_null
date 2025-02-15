/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;

public class NHPublicKeyParameters
extends AsymmetricKeyParameter {
    final byte[] pubData;

    public NHPublicKeyParameters(byte[] byArray) {
        super(false);
        this.pubData = Arrays.clone(byArray);
    }

    public byte[] getPubData() {
        return Arrays.clone(this.pubData);
    }
}

