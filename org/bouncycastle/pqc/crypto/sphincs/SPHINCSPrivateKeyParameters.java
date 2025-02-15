/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;

public class SPHINCSPrivateKeyParameters
extends AsymmetricKeyParameter {
    private final byte[] keyData;

    public SPHINCSPrivateKeyParameters(byte[] byArray) {
        super(true);
        this.keyData = Arrays.clone(byArray);
    }

    public byte[] getKeyData() {
        return Arrays.clone(this.keyData);
    }
}

